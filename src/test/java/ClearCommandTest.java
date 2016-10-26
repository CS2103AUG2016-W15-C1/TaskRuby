import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import core.ClearCommand;
import core.CommandException;
import core.DatabaseStorage;
import core.StorageBackend;
import core.StorageException;
import models.Task;

public class ClearCommandTest {

    private StorageBackend storage;
    private ClearCommand clearCommand;

    @Before
    public void setUp() throws Exception {
        storage = new DatabaseStorage("ClearCommandTest.db");
        storage.initializeStorage();
        clearCommand = new ClearCommand(storage);

    }

    @After
    public void tearDown() throws Exception {
        storage.deleteStorage();
    }
    
    @Test
    public void clearHelpString() {
        assertNotEquals("that the help string is not null", "",
                clearCommand.getHelpString());
    }
    
    @Test
    public void clearExceptionHandled() {
        String[] stubArgs = new String[]{"test", "test2"};
        try {
            clearCommand.execute(stubArgs);
        } catch (CommandException e) {
            assertNotEquals("exception string is not empty", "",
                            e.getMessage());
        }
    }
    
    

    @Test
    public void clearCommandTest() throws SQLException {
        String[] stubArgs = {};
        try {
            for (int i = 0; i < 10; i++) {
                storage.addTask(new Task(UUID.randomUUID().toString()));
            }

            assertEquals("that 10 tasks were added", 10, storage.getTasks().size());
            clearCommand.execute(stubArgs);
            assertEquals("that clear command was executed", 0, storage.getTasks().size());
            assertEquals("that the nextId was reset", 1, storage.getNextAvailableIdentifier());
        } catch (StorageException | CommandException e) {
            fail(e.getMessage());
        }
    }

}
