package core;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import models.Task;

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
            int updateResult = persistence.saveTask(new Task("buy robert"));
            assertEquals(1, updateResult);
        } catch(SQLException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    @Test
    public void getTasks() {
        try {
            ArrayList<Task> tasks = persistence.getTasks();
            assertEquals("that there are no tasks at first", 0, tasks.size());
            persistence.saveTask(new Task("test task"));
            assertEquals("that 1 task was added", 1, persistence.getTasks().size());
            String addedTaskName = persistence.getTasks().get(0).taskShortName().get();
            assertEquals("that the task added is the same", "test task", addedTaskName);
        } catch(SQLException e) {
            fail(e.getMessage());
        }
    }

}
