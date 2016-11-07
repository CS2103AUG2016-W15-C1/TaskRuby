package core;
//@@author A0108515L
import java.util.logging.Logger;

public class ClearCommand extends BaseCommand {

    private static final Logger logger = Logger.getLogger(ClearCommand.class.getName());

    private static final String helpString = "clear";

    public ClearCommand(StorageBackend storage) {
        super(storage);
    }

    @Override
    public String getHelpString() {
        return helpString;
    }

    /*
     * Clears the taskList on `clear`
     * 
     */
    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length > 0) {
            throw new CommandException("clear do not take in args");
        }
        try {
            logger.info("clearing all tasks in list");
            storage.deleteAllTasks();
        } catch (StorageException e) {
            e.printStackTrace();
        }

    }

}
