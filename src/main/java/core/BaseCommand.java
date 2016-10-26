package core;

import java.sql.SQLException;

public abstract class BaseCommand {
    protected StorageBackend storage;
    
    public BaseCommand(StorageBackend storage) {
        this.storage = storage;
    }
    
    public abstract void execute(String[] args) throws CommandException, SQLException;
    public abstract String getHelpString();
}
