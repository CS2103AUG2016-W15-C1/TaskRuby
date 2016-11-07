package core;

import java.util.logging.Logger;

import models.Task;

/*
 * This command lets an user delete a task from the taskmanager
 * as well as from the storage layer
 */
public class DeleteCommand extends BaseCommand {

    private static final Logger logger = Logger.getLogger(DeleteCommand.class.getName());
    private static final String helpString = "delete id";
    private TaskRuby main;
    
    public DeleteCommand(StorageBackend storage, TaskRuby main) {
        super(storage);
        this.main = main;
    }

    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length == 0) {
        	logger.warning("Not enough arguments to DeleteCommand");
            throw new CommandException("not enough arguments");
        }
        try {
        	Task taskToBeDeleted = storage.getTaskById(Integer.parseInt(args[0]));
            storage.deleteTask(Integer.parseInt(args[0]));
            
        	main.setLastCommand("delete " + taskToBeDeleted.getTaskShortName() + 
        			            " -d " + taskToBeDeleted.getTaskStartTime() + 
        			            " -D " + taskToBeDeleted.getTaskDeadline() +
        			            " -p " + taskToBeDeleted.getTaskPriority());
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
