//@@author A0118894N
package core;

/*
 * ParserException is an exception for errors raised during
 * the parsing of user input.
 * 
 * Instead of throwing generic Java errors we can allow users
 * to only deal with our specific exceptions (ex: via an interface)
 */
public class ParseException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 3682302192990535477L;
    
    public ParseException(String message) {
        super(message); 
    }
}
