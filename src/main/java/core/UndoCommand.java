package core;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Logger;

import models.Task;

public class UndoCommand extends BaseCommand {
    
    private static final Logger logger = Logger.getLogger(UndoCommand.class.getName());
    private TaskRuby main;
    private static final String helpString = "undo";

    public UndoCommand(StorageBackend storage, TaskRuby main) {
        super(storage);
        this.main = main;
        // TODO Auto-generated constructor stub
    }

    @Override
    public String getHelpString() {
        return helpString;
    }

    @Override
    public void execute(String[] args) throws CommandException, SQLException {
        if (args.length > 0) {
            throw new CommandException("undo do not take in args");
        }
        String lastCommand = main.getLastCommand();
        if (lastCommand.equals(""))
        	logger.info("nothing to undo");
        else {
		    logger.info("trying to undo command: " + lastCommand);
		    String[] tokens = lastCommand.split("\\s+");
		    if (tokens[0].equals("add"))
				try {
					storage.deleteLastTask();
				} catch (StorageException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    else if (tokens[0].equals("delete"))
		    	try {
		            Task t = new Task(String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length)));
		            logger.info("trying to add task: " + String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length)));
		            storage.addTask(t);
		        } catch (StorageException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		            throw new CommandException(e.getMessage());
		        }
		    main.setLastCommand("");
        }
    }

}
