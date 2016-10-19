package core;

public class FindCommand extends BaseCommand {
    private TaskRuby main;
    
    public FindCommand(StorageBackend storage, TaskRuby main) {
        super(storage);
        this.main = main;
    }

    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length == 0) {
            throw new CommandException("not enough args for find");
        }
        try {
            System.out.println(args[0]);
            //main.getTasks().clear();
            main.setTasks(
                storage.findTaskByName(args[0])
            );
        } catch (StorageException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new CommandException(e.getMessage());
        }
    }

    @Override
    public String getHelpString() {
        // TODO Auto-generated method stub
        return null;
    }

}
