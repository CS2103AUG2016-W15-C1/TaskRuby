package core;

import java.util.logging.Logger;
import javax.swing.JOptionPane;

//@@author A0144017R

public class HelpCommand extends BaseCommand {

    private static final Logger logger = Logger.getLogger(HelpCommand.class.getName());

    private static final String helpString = "help";

    public HelpCommand(StorageBackend storage) {
        super(storage);
    }

    @Override
    public String getHelpString() {
        return helpString;
    }

    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length > 0) {
            throw new CommandException("help does not take in args");
        }
       
        logger.info("Displaying TaskRuby Cheat Sheet");
        JOptionPane.showMessageDialog(null, "Add a task: add <task name> \\d <start time> \\D <end time> \\p <priority> \n" +
                                            "Delete a task: delete <task id>\n" +
                                            "Update status of a task: update <task id> <done / not done> \n" +
                                            "Edit task information: edit <task id> \\t <new task name> \\d <new start time> \\D <new end time> \\p <new priority> \n" +
        		                            "List all tasks: list \n" +
                                            "Clear all tasks: clear \n" +
        		                            "Hide all tasks: hide \n" +
        		                            "Undo previous add / delete command: undo \n" +
                                            "Find a task: find <keyword> \n" +
                                            "Show help panel: help \n" +
                                            "Exit TaskRuby: exit", "TaskRuby Cheat Sheet", JOptionPane.INFORMATION_MESSAGE);

    }

}
