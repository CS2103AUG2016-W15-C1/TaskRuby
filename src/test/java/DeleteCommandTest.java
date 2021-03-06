//@@author A0130164W
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import core.TaskRuby;
import core.CommandException;
import core.DatabaseStorage;
import core.DeleteCommand;
import core.StorageBackend;
import core.StorageException;
import models.Task;

public class DeleteCommandTest {
    
    private StorageBackend storage;
    private DeleteCommand deleteCommand;
    private static TaskRuby main;

    @Before
    public void setUp() throws Exception {
        storage = new DatabaseStorage("DeleteCommandTest.db");
        storage.initializeStorage();
        main = new TaskRuby();
        deleteCommand = new DeleteCommand(storage, main);

    }

    @After
    public void tearDown() throws Exception {
        storage.deleteStorage();
    }

    @Test
    public void deleteByIdTest() throws SQLException {
        String[] cmd = {"2"};
        String[] cmd2 = {"1"};
        
        try {    
            storage.addTask(new Task("test1"));
            storage.addTask(new Task("test2"));
            
            assertEquals("that the size is 2 to begin with", 
                    storage.getTasks().size(), 2);
            deleteCommand.execute(cmd);
            assertEquals("that the size is 1 after deleting 1", 
                    storage.getTasks().size(), 1);
            deleteCommand.execute(cmd2);
            assertEquals("that the size is 0 after deleting 1 more", 
                    storage.getTasks().size(), 0);
        } catch (CommandException | StorageException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail(e.getMessage());
        }    
    }
 
}
