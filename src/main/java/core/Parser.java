//@@author A0130164W
package core;

import java.sql.SQLException;
import java.util.Arrays;
/*
 * Parser that checks for the existence of the command
 * as well as if there are enough arguments to even parse.
 */
public class Parser {
    private TaskRuby main;
    public Parser(TaskRuby main) {
        this.main = main;
    }
    
    /*
     * parse throws two exceptions because errors can be raised during parsing
     * as well as during the execution of the command
     */
    public void parse(String input) throws ParseException, CommandException {
        String[] tokens = input.split("\\s+");
        if (tokens.length == 0) 
            throw new ParseException("not enough args");
        BaseCommand command = main.getAvailableCommands().get(tokens[0]);
        if (command == null)
            throw new CommandException("command not found");
        try {
            command.execute(Arrays.copyOfRange(tokens, 1, tokens.length));
        } catch (SQLException e) {
            throw new CommandException(e.getMessage());
        }
    }
}
