package nuris.epam.connection;

import nuris.epam.dao.exception.ResourcesException;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Кастомный класс-список , для хранений потоковых обектов.
 *
 * @author Kalenov Nurislam
 */
public class ResourcesQueue<T> {

    private Semaphore semaphore;

    private Queue<T> resource = new ConcurrentLinkedQueue<T>();

    private int timeOut;

    public ResourcesQueue(int size , int timeOut) {
        semaphore = new Semaphore(size, true);
        this.timeOut = timeOut;
    }

    public T takeResource() throws ResourcesException {
        try {
           if(semaphore.tryAcquire(timeOut , TimeUnit.SECONDS)) {
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


