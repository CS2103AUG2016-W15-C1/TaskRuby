package core;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.joestelmach.natty.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import models.Task;
//@@author A0130164W
public class AddCommand extends BaseCommand {

    private static final Logger logger = Logger.getLogger(AddCommand.class.getName());

    private static final String helpString = "add <task>";
    private TaskRuby main;
    /*
     * we call it by its fully qualified name to avoid
     * ambiguity with our own Parser class
     */
    com.joestelmach.natty.Parser nattyParser = new com.joestelmach.natty.Parser();

    //@@author A0108515L
    /*
     * Constructor for the addCommand class
     */
    public AddCommand(StorageBackend storage, TaskRuby main) {
        super(storage);
        this.main = main;
    }
    
    //@@author

    /*
     * (non-Javadoc)
     * @see core.BaseCommand#getHelpString()
     */
    @Override
    public String getHelpString() {
        return helpString;
    }
    

    //@@author A0118894N
    /*
     * Parses the date and time from a natural string
     * to a LocalDateTime instance using natty
     * @TODO: fix bug with indexing
     * @param parsedDate a list of obtained dategroups from natty
     */
    private LocalDateTime getDateTime(List<DateGroup> parsedDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d H:m");
        Date d = parsedDate.get(0).getDates().get(0);
        @SuppressWarnings("deprecation")
		String t = (d.getYear() + 1900) + "-" + (d.getMonth() + 1) + "-" 
                   + (d.getDate()) + " " + (d.getHours())
                + ":" + (d.getMinutes());
        return LocalDateTime.parse(t, formatter);
    }

    //@@author A0130164W
    /*
     * Obtain the list of arguments passed in from the UI
     * and then split them according to our command keyword
     * Parse tokens corresponding to different fields and
     * then finally add the full task to storage
     * 
     */
    @Override
    public void execute(String[] args) throws CommandException, SQLException {
        if (args.length == 0) {
            throw new CommandException("empty arguments");
        }
        // System.out.println(args.length);
        try {
            String desc = "\\t" + String.join(" ", Arrays.copyOfRange(args, 0, args.length));
            logger.info("trying to add: " + desc);
            String[] tokens = desc.split("\\\\");
            String taskDesc = "";
            LocalDateTime taskDue = null;
            LocalDateTime taskStart = null;
            String inf = null;
            String prio = null;
            String priority = "MED";
            for (String t : tokens) {
                if (t.startsWith("t") && t.substring(1).length() > 1)
                    taskDesc = t.substring(1);
                if (t.startsWith("d") && t.substring(1).length() > 1) {
                    List<DateGroup> groups = nattyParser.parse(t.substring(1));
                    taskStart = getDateTime(groups);
                }
                if (t.startsWith("D") && t.substring(1).length() > 1)
                    taskDue = getDateTime(nattyParser.parse(t.substring(1)));
                if (t.startsWith("i") && t.substring(1).length() > 1)
                    inf = t.substring(1);
                if (t.startsWith("p") && t.substring(1).length() > 1) {
                    prio = t.substring(1).trim();
                    int prioInt = Integer.parseInt(prio);
                    switch (prioInt) {
                   
                    case 1: priority = "HIGH";
                    break;
                    
                    case 2: priority = "MED";
                    break;
                    
                    case 3: priority = "LOW";
                    break;
                    }

                }
            }
            logger.info("trying to add task: " + desc);
            //logger.info(taskStart.toString());
            Task t = new Task(taskDesc, taskStart, taskDue, inf, priority);
            storage.addTask(t);
            main.setLastCommand("add " + desc);
        } catch (StorageException e) {
            e.printStackTrace();
            logger.severe(e.getMessage());
            throw new CommandException(e.getMessage());
        }
    }

}
