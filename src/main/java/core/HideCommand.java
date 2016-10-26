package core;
//@author A0108515L
public class HideCommand extends BaseCommand {

    private TaskRuby main;

    public HideCommand(StorageBackend storage, TaskRuby main) {
        super(storage);
        this.main = main;
        // TODO Auto-generated constructor stub
    }

    @Override
    public void execute(String[] args) throws CommandException {
        if (this.main.getTaskListVisibility()) {
            this.main.toggleVisible();
        }
    }

    @Override
    public String getHelpString() {
        // TODO Auto-generated method stub
        return null;
    }

}
