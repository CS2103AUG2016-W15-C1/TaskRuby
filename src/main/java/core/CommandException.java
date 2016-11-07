//@@author A0118894N
package core;

/*
 * Any exception that is thrown while executing a command
 * must be morphed into this exception and bubbled up.
 */
public class CommandException extends Exception {

    private static final long serialVersionUID = -3400354534214776091L;
    
    public CommandException(String message) {
        super(message);
    }

}
