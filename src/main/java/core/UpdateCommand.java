// @@author A0144017R
package core;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Logger;

/*
 * This command helps us update a task status
 */
public class UpdateCommand extends BaseCommand {
    
    private static final Logger logger = Logger.getLogger(UpdateCommand.class.getName());
    private static final String helpString = "update task_id status";
    private static final String[] possibleDoneStatus = {"done", "complete", "completed", "finish", "finished", "over"};
    private static final String[] possibleNotDoneStatus = {"not done", "incomplete", "not completed", "progress", "working"};

    public UpdateCommand(StorageBackend storage, TaskRuby main) {
        super(storage);
    }

    @Override
    public String getHelpString() {
        return helpString;
    }

    @Override
    public void execute(String[] args) throws CommandException, SQLException {
        if (args.length < 2) {
            throw new CommandException("Update takes 2 arguments");
        }
        
        logger.info("Trying to update status of task.");
        int taskId = Integer.parseInt(args[0]);
        String status = String.join(" ", Arrays.copyOfRange(args, 1, args.length)).toLowerCase();
        boolean isUpdated = false;
        for (String str: possibleDoneStatus) {
        	if (str.contains(status)) {
        		try {
					storage.updateTaskStatus(taskId, "Done");
					isUpdated = true;
					break;
				} catch (StorageException e) {
					e.printStackTrace();
					logger.severe(e.getMessage());
		    		throw new CommandException(e.getMessage());
				}
        	}
        }
        if (!isUpdated) {
        	for (String str: possibleNotDoneStatus) {
        		if (str.contains(status)) {
        			try {
        				storage.updateTaskStatus(taskId, "Not Done");
        				break;
        			} catch (StorageException e) {
        				e.printStackTrace();
        				logger.severe(e.getMessage());
        				throw new CommandException(e.getMessage());
        			}
        		}
        	}
        }
        
        if (!isUpdated) {
        	logger.info("Invalid command arguments.");
        }
        
    }

}
