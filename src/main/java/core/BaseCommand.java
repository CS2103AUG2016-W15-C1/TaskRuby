//@@author A0144017R
package core;

import java.sql.SQLException;

/*
 * This class implements a generic command that others
 * can inherit off.
 * It defines some functions that every command must have
 */
public abstract class BaseCommand {
    protected StorageBackend storage;
    
    public BaseCommand(StorageBackend storage) {
        this.storage = storage;
    }
    
    public abstract void execute(String[] args) throws CommandException, SQLException;
    public abstract String getHelpString();
}
