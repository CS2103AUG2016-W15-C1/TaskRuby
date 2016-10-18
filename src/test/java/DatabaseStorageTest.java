import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import core.DatabaseStorage;
import core.StorageException;
import models.Task;

/**
 * 
 */

public class DatabaseStorageTest {
    
    DatabaseStorage storage;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        storage = new DatabaseStorage("testDB.db");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        storage.deleteStorage();
    }
    
    @Test
    public void getNextIdentifierTest() {
        try {
            assertEquals("that the next id returned is always 1 for a new db",
                         storage.getNextAvailableIdentifier(), 1);
        } catch (StorageException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    
    @Test
    public void addTaskTest() {
        try {
            int nextIdentifier = storage.getNextAvailableIdentifier();
            assertEquals("next identifier is available", 1, nextIdentifier);
            Task t = new Task("add an undo and redo command");
            storage.addTask(t);
            assertEquals("next identifier incremented", ++nextIdentifier,
                         storage.getNextAvailableIdentifier());
            storage.addTask(new Task("add one more task"));
            assertEquals("next identifier incremented", ++nextIdentifier,
                    storage.getNextAvailableIdentifier());
            
        } catch (StorageException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

}
