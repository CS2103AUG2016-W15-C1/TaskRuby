import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import core.AddCommand;
import core.TaskRuby;
import core.UndoCommand;
import core.CommandException;
import core.DatabaseStorage;
import core.DeleteCommand;
import core.StorageException;

public class UndoCommandTest {
    private AddCommand addCommand;
    private DeleteCommand deleteCommand;
    private UndoCommand undoCommand;
    private DatabaseStorage storage;
    private static TaskRuby main;
    
    @Before
    public void setUp() throws Exception {
        storage = new DatabaseStorage("testUndoCommand.db");
        storage.initializeStorage();
        main = new TaskRuby();
        addCommand = new AddCommand(this.storage, main);
        deleteCommand = new DeleteCommand(this.storage, main);
        undoCommand = new UndoCommand(this.storage, main);
    }

    @After
    public void tearDown() throws Exception {
        storage.deleteStorage();
    }

    @Test
    public void testHelpString() {
        assertNotEquals("that the help string is not null", "",
                        undoCommand.getHelpString());
    }
    
    @Test
    public void undoAfterAdd() throws SQLException, StorageException {
        String[] stubArgs = {"task1"};
        try {
            addCommand.execute(stubArgs);
            assertEquals("that the size is 1 to begin with", storage.getTasks().size(), 1);
            undoCommand.execute(new String[] {});
            assertEquals("that the size is 0 after undo command", storage.getTasks().size(), 0);
            
        } catch (CommandException e) {
            assertNotEquals("exception string is not empty", "",
                            e.getMessage());
        }
    }
    
    @Test
    public void undoAfterDelete() throws SQLException, StorageException {
        String[] cmd1 = {"task1"};
        String[] cmd2 = {"1"};
        try {
            addCommand.execute(cmd1);
            assertEquals("that the size is 1 to begin with", storage.getTasks().size(), 1);
            deleteCommand.execute(cmd2);
            assertEquals("that the size is 0 after delete command", storage.getTasks().size(), 0);
            undoCommand.execute(new String[] {});
            assertEquals("that the task should have the same name after undo command", storage.getTasks().get(0).getTaskShortName(), "task1");
        } catch (CommandException e) {
            assertNotEquals("exception string is not empty", "",
                            e.getMessage());
        }
    }
    
    @Test
    public void undoAfterUndo() throws SQLException, StorageException {
    	 String[] cmd1 = {"task1"};
         String[] cmd2 = {};
         try {
             addCommand.execute(cmd1);
             assertEquals("that the size is 1 to begin with", storage.getTasks().size(), 1);
             undoCommand.execute(cmd2);
             assertEquals("that the size is 0 after undo command", storage.getTasks().size(), 0);
             undoCommand.execute(cmd2);
             assertEquals("that the size is 0 after undo command since there is nothing to undo", storage.getTasks().size(), 0);
         } catch (CommandException e) {
             assertNotEquals("exception string is not empty", "",
                             e.getMessage());
         }
    }
}
