package core;

public class AddCommand extends BaseCommand {
    
    private static final String helpString = "add <task>";

    public AddCommand(StorageBackend storage) {
        super(storage);
        // TODO Auto-generated constructor stub
    }

    @Override
    public String getHelpString() {
        return helpString;
    }

    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length == 0) {
            throw new CommandException("empty arguments");
        }
        //System.out.println(args.length);
        try {
            System.out.println(this.storage.getNextAvailableIdentifier());
        } catch (StorageException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new CommandException(e.getMessage());
        }
    }

}
