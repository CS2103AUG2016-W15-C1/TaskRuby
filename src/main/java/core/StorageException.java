//@@author A0118894N
package core;

/*
 * Errors raised during interacting with the storage layer must be
 * morphed and bubbled up in this exception
 * 
 */
public class StorageException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 5972739342506216554L;

    public StorageException(String message) {
        super(message);
    }
}
