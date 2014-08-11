package dependencygraph.exception;

/**
 * Exception to be thrown when invalid dependency graph representation is given to the app
 *
 * @author Jijo Wilson (jiwilson@expedia.com)
 */
public class InvalidDependencyGraphRepresentationException extends RuntimeException {

    public InvalidDependencyGraphRepresentationException(String message) {
        super(message);
    }

    public InvalidDependencyGraphRepresentationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
