// @@author A0144017R
package core;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Logger;

import core.AddCommand;

/*
 * This command undos a previously done command
 */
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
		    logger.info("Undo command");
		    String[] tokens = lastCommand.split("\\s+");
		    if (tokens[0].equals("add"))
				try {
					logger.info("trying to undo previous addition");
					storage.deleteLastTask();
				} catch (StorageException e) {
					e.printStackTrace();
				}
		    else if (tokens[0].equals("delete")) {
				logger.info("trying to undo previous deletion");
				new AddCommand(storage, main).execute(Arrays.copyOfRange(tokens, 1, tokens.length));
			}
		    main.setLastCommand("");
        }
    }

}
