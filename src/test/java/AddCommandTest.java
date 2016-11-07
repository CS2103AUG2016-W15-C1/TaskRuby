//@@author A0130164W
import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import core.AddCommand;
import core.TaskRuby;
import core.CommandException;
import core.DatabaseStorage;

public class AddCommandTest {
    private AddCommand addCommand;
    private DatabaseStorage storage;
    @Before
    public void setUp() throws Exception {
        storage = new DatabaseStorage("testAddCommand.db");
        storage.initializeStorage();
        TaskRuby main = new TaskRuby();
        addCommand = new AddCommand(this.storage, main);
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
    
    //@@author A0108515L
    @Test
    public void emptyArgument() throws SQLException {
        String[] stubArgs = {};
        try {
            addCommand.execute(stubArgs);
        } catch (CommandException e) {
            assertNotEquals("exception string is not empty", "",
                            e.getMessage());
        }
    }
}
