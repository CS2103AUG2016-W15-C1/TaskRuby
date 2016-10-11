package core;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PersistenceTest {
    
    private Persistence persistence;
    private static final String TEST_DB = "testCases.db";

    @Before
    public void setUp() throws Exception {
        persistence = new Persistence(TEST_DB);
    }

    @After
    public void tearDown() throws Exception {
        File f = new File(TEST_DB);
        f.delete();
    }

    @Test
    public void baseTest() {
        try {
            assertTrue("storage should have no rows", persistence.getTasks().size() == 0);
        } catch (SQLException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    @Test
    public void addTask() {
        try {
            persistence.getTasks();//TODO STUB ONLY
        } catch(SQLException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

}
