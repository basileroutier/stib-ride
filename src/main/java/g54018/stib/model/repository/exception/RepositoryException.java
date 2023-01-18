/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package g54018.stib.model.repository.exception;

/**
 *
 * @author basile
 */
public class RepositoryException extends Exception{

    /**
     * Creates a new instance of <code>RepositoryException</code> without detail
     * message.
     */
    public RepositoryException() {
        super();
    }

    /**
     * Constructs an instance of <code>RepositoryException</code> with the specified
     * detail message.
     *
     * @param msg message of the exception.
     */
    public RepositoryException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>RepositoryException</code> and wrapped the
     * source exception.
     *
     * @param exception wrapped exception.
     */
    public RepositoryException(Exception exception) {
        super(exception);
    }

}
