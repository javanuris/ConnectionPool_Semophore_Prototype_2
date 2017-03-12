package nuris.epam.connection;

import nuris.epam.dao.exception.ResourcesException;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by User on 08.03.2017.
 */
public class ResourcesQueue<T> {

    private Semaphore semaphore;

    private Queue<T> resource = new ConcurrentLinkedQueue<T>();

    public ResourcesQueue(Queue<T> resource, int size) {
        semaphore = new Semaphore(size, true);
        resource.addAll(resource);
    }

    public ResourcesQueue(int size) {
        semaphore = new Semaphore(size, true);
    }

    public T takeResource() throws ResourcesException {
        try {
           if(semaphore.tryAcquire(5000 , TimeUnit.MILLISECONDS)) {
               T res = resource.poll();
               return res;
           }
        } catch (InterruptedException e) {
            throw new ResourcesException("You didn't wait for connect bro." ,e);
        }
        throw new ResourcesException("You didn't wait for connect bro." );
    }

    public void returnResource(T res) {
        resource.add(res);
        semaphore.release();
    }

    public void addResource(T t) {
        resource.add(t);
    }

    public int size() {
        return resource.size();
    }
}


