package core;
//@@author A0108515L
import java.util.logging.Logger;
/*
 * This command quits the task manager from the CLI
 * The user does not have to use the mouse - conveinience function
 */
public class ExitCommand extends BaseCommand {

    private static final Logger logger = Logger.getLogger(ExitCommand.class.getName());

    private static final String helpString = "exit";

    public ExitCommand(StorageBackend storage) {
        super(storage);
    }

    @Override
    public String getHelpString() {
        return helpString;
    }

    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length > 0) {
            throw new CommandException("exit do not take in args");
        }
       
        logger.severe("Shutting down on request");
        /*
         * @TODO I think this can be handled in a better way
         */
        System.exit(0);

    }

}
