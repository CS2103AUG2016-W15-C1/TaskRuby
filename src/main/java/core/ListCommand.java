package core;

public class ListCommand extends BaseCommand {
    
    private TaskRuby main;

    public ListCommand(StorageBackend storage, TaskRuby main) {
        super(storage);
        this.main = main;
        // TODO Auto-generated constructor stub
    }

    @Override
    public void execute(String[] args) throws CommandException {
        this.main.toggleVisible();
    }

    @Override
    public String getHelpString() {
        // TODO Auto-generated method stub
        return null;
    }

}
