import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import core.AddCommand;
import core.CommandException;
import core.DatabaseStorage;

public class AddCommandTest {
    private AddCommand addCommand;
    private DatabaseStorage storage;
    @Before
    public void setUp() throws Exception {
        storage = new DatabaseStorage("testAddCommand.db");
        storage.initializeStorage();
        addCommand = new AddCommand(this.storage);
    }

    @After
    public void tearDown() throws Exception {
        storage.deleteStorage();
    }

    @Test
    public void testHelpString() {
        assertNotEquals("that the help string is not null", "",
                        addCommand.getHelpString());
    }
    
    @Test
    public void emptyArgument() {
        String[] stubArgs = {};
        try {
            addCommand.execute(stubArgs);
        } catch (CommandException e) {
            assertNotEquals("exception string is not empty", "",
                            e.getMessage());
        }
    }
}
