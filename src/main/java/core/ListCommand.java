package core;
/*
 * This command lists all available tasks in the task manager
 * it is the inverse of the hide command
 */
//@@author A0108515L
public class ListCommand extends BaseCommand {

    private TaskRuby main;

    public ListCommand(StorageBackend storage, TaskRuby main) {
        super(storage);
        this.main = main;
        // TODO Auto-generated constructor stub
    }

    @Override
    public void execute(String[] args) throws CommandException {
        if (!this.main.getTaskListVisibility()) {
            this.main.toggleVisible();
        }
    }

    @Override
    public String getHelpString() {
        // TODO Auto-generated method stub
        return null;
    }

}
