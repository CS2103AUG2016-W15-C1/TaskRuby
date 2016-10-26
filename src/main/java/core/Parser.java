package core;

import java.sql.SQLException;
import java.util.Arrays;

public class Parser {
    private TaskRuby main;
    public Parser(TaskRuby main) {
        this.main = main;
    }
    
    public void parse(String input) throws ParseException, CommandException, SQLException {
        String[] tokens = input.split("\\s+");
        if (tokens.length == 0) 
            throw new ParseException("not enough args");
        BaseCommand command = main.getAvailableCommands().get(tokens[0]);
        if (command == null)
            throw new CommandException("command not found");
        command.execute(Arrays.copyOfRange(tokens, 1, tokens.length));
    }
}
