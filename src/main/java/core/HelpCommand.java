package core;

import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
        JOptionPane.showMessageDialog(null, "To add a task: add <task name>\n" +
                                            "To delete a task: delete <task id>\n" +
        		                            "To list all tasks: list \n" +
                                            "To clear all tasks: clear \n" +
        		                            "To undo previous command: undo \n", "TaskRuby Cheat Sheet", JOptionPane.INFORMATION_MESSAGE);

    }

}
