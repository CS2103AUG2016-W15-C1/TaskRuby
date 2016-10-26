package core;

import java.util.logging.Logger;
//@@author A0130164W
public class DeleteCommand extends BaseCommand {

    private static final Logger logger = Logger.getLogger(DeleteCommand.class.getName());
    private static final String helpString = "delete id";
    
    public DeleteCommand(StorageBackend storage) {
        super(storage);
    }

    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length == 0) {
            throw new CommandException("not enough arguments");
        }
        try {
            storage.deleteTask(Integer.parseInt(args[0]));
        } catch (NumberFormatException | StorageException e) {
            e.printStackTrace();
            throw new CommandException(e.getMessage());
        }
    }

    @Override
    public String getHelpString() {
        return helpString;
    }    
    
    
}
