import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import core.DatabaseStorage;
import core.StorageException;

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

}
