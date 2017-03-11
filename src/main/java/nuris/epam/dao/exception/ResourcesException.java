package nuris.epam.dao.exception;

/**
 * Created by User on 08.03.2017.
 */
public class ResourcesException extends Exception {

    public ResourcesException() {}

    public ResourcesException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourcesException(String message) {
        super(message);
    }

    public ResourcesException(Throwable cause){
        super(cause);
    }
}
