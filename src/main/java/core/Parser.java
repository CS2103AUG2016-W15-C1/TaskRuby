package core;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.joestelmach.natty.DateGroup;

public class Parser {
    private TaskRuby main;
    public Parser(TaskRuby main) {
        this.main = main;
    }
    
    private static LocalDateTime getDateTime(List<DateGroup> parsedDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d H:m");
        Date d = parsedDate.get(0).getDates().get(0);
        String t = (d.getYear() + 1900) + "-" + (d.getMonth() + 1) + "-" + (d.getDate() + 1) + " " + (d.getHours() + 1)
                + ":" + (d.getMinutes() + 1);
        return LocalDateTime.parse(t, formatter);
    }  
  
    public static CommandOptions genericParse(String s) throws ParseException {
    	com.joestelmach.natty.Parser nattyParser = new com.joestelmach.natty.Parser();
    	if (s.length() == 0) throw new ParseException("empty length string");
    	String args = "\\t" + s;
    	String[] tok = args.split("\\\\");
    	CommandOptions opts = new CommandOptions();
        for (String t : tok) {
            if (t.startsWith("t"))
                opts.setTaskName(t.substring(1));
            if (t.startsWith("d")) {
                List<DateGroup> groups = nattyParser.parse(t.substring(1));
                opts.setStartDate(getDateTime(groups));
            }
            if (t.startsWith("D"))
                opts.setEndDate(getDateTime(nattyParser.parse(t.substring(1))));
            if (t.startsWith("p")) {
                String prio = t.substring(1).trim();
                int prioInt = Integer.parseInt(prio);
                switch (prioInt) {
               
                case 1: opts.setPriority("HIGH");
                break;
                
                case 2: opts.setPriority("MED");
                break;
                
                case 3: opts.setPriority("LOW");
                break;
                }

            }
        }
        return opts;
    }
    
    
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
