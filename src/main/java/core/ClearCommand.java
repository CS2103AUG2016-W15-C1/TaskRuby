package core;

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

    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length > 0) {
            throw new CommandException("clear do not take in args");
        }
        try {
            int index = storage.getNextAvailableIdentifier();
            logger.info("clearing all tasks in list");
            // iterate and delete
            for (int i = 1; i < index; i++) {
               
                storage.deleteTask(i);
            }

        } catch (StorageException e) {
            e.printStackTrace();
        }

    }

}
