package core;

import java.util.logging.Logger;

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
       
        logger.info("Shutting down on request");
        System.exit(0);

    }

}
