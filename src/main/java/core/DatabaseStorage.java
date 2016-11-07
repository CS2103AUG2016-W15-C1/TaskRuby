//@@author A0118894N
package core;
//@@author A0118894N
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

import models.Task;
/*
 * This class allows tasks to be stored to a sqlite3 db file
 * In addition to maintaining data integrity this allows
 * the user to backup their storage layer to the cloud
 * or send it over email (just an example).
 */
public class DatabaseStorage implements StorageBackend {
    
    private static final Logger logger = Logger.getLogger(DatabaseStorage.class.getName());
    
    /*
     * @todo change
     */
    private String fileName = "test.db";
    private Connection conn = null;
    
    private static final int COL_PRIMARY_KEY = 1;
    private static final int COL_TASK_NAME = 2;
    private static final int COL_CREATED_DATE = 3;
    private static final int COL_DUE_DATE = 4;
    private static final int COL_PRIORITY = 5;
    private static final int COL_STATUS = 6;
    private static final int COL_FLOATING = 7;
    private static final int COL_DEADLINE = 8;

    /*
     * Constructor that takes in an arbitrary name for the filename
     * It opens the connection as well
     */
    public DatabaseStorage(String file) {
        if (file == "") file = this.fileName;
        setFileName(file);
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + fileName);
            initializeStorage();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /*
     * Helper function that runs a query and returns a resultSet
     * 
     */
    private ResultSet runQuery(String query) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(query);
    }
    

    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /*
     * Initialize the storage layer with our predefined
     * database schema
     * It is important that the schema is static across
     * the same version
     */
    @Override
    public void initializeStorage() throws StorageException {
        String query = "CREATE TABLE IF NOT EXISTS tasks(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                       "task_name VARCHAR(255), created_at DATETIME DEFAULT (datetime('now','localtime')), " +
                       "due_date DATETIME DEFAULT (datetime('now', 'localtime')), priority VARCHAR(10), " +
                       "status VARCHAR(10) DEFAULT('Not Done'), floating INTEGER, deadline INTEGER);";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            throw new StorageException(e.getMessage());
        }
    }

    /*
     * Deletes the database file.
     */
    @Override
    public void deleteStorage() throws SQLException {
        File file = new File(fileName);
        file.setWritable(true);
        conn.close();
        file.delete();
    }
    
    //@@author A0108515L
    /*
     * Adds a task to the storage layer
     * from an instance of the Task model
     */
    public void addTask(Task task) throws StorageException {
        logger.info("trying to add task to database");
        String query = "INSERT INTO tasks(task_name, created_at, due_date, priority, floating, deadline) VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt;
        
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, task.getTaskShortName());
            stmt.setString(2, task.getTaskStartTime());
            stmt.setString(3, task.getTaskDeadline());
            stmt.setString(4, task.taskPriority().get());
            stmt.setInt(5, task.isFloating() ? 1 : 0);
            stmt.setInt(6, task.isDeadline() ? 1 : 0);
            if (stmt.executeUpdate() != 1) {
                throw new StorageException("unable to add task to database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.severe(e.getMessage());
            throw new StorageException(e.getMessage());
        }
        
    }
    /*
     * Updates the task status in the db
     */
    //@@author A0144017R
    public void updateTaskStatus(int id, String status) throws StorageException {
    	logger.info("trying to update task status");
    	String query = "UPDATE tasks SET status = ? WHERE id = ?";
    	PreparedStatement stmt;
    	
    	try {
    		stmt = conn.prepareStatement(query);
    		stmt.setString(1, status);
    		stmt.setInt(2,  id);
    		if (stmt.executeUpdate() != 1) {
    			throw new StorageException("unable to update status of the task");
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    		logger.severe(e.getMessage());
    		throw new StorageException(e.getMessage());
    	}
    }
    
    /*
     * Edits a task in the db using an UPDATE statement
     */
    //@@author A0144017R
    public void editTask(int id, Task task) throws StorageException {
    	logger.info("trying to edit task");
    	String query = "UPDATE tasks SET task_name = ?, created_at= ?, due_date = ?, priority = ?, status = ? where id = ?";
    	PreparedStatement stmt;
    	System.out.println(task.isFloating());
    	try {
    		stmt=conn.prepareStatement(query);
    		stmt.setString(1, task.getTaskShortName());
    		stmt.setString(2, task.getTaskStartTime());
            stmt.setString(3, task.getTaskDeadline());
            stmt.setString(4, task.taskPriority().get());
            stmt.setString(5,  task.getTaskStatus());
    		stmt.setInt(6, id);
    		if (stmt.executeUpdate() != 1) {
    			throw new StorageException("unable to edit the task");    	
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    		logger.severe(e.getMessage());
    		throw new StorageException(e.getMessage());
    	}
    }
    
    //@@author A0118894N
    /*
     * Get all tasks from the db as an ArrayList<Task>
     */
    public ArrayList<Task> getTasks() throws StorageException {
        String query = "SELECT * FROM tasks";
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            ResultSet r = runQuery(query);
            while (r.next()) {
                taskList.add(new Task(r.getInt(COL_PRIMARY_KEY),
                                      r.getString(COL_TASK_NAME),
                                      r.getString(COL_CREATED_DATE),
                                      r.getString(COL_DUE_DATE),
                                      r.getString(COL_PRIORITY),
                                      r.getString(COL_STATUS),
                                      r.getString(COL_FLOATING),
                                      r.getString(COL_DEADLINE)));
            }
        } catch (SQLException e) {
            throw new StorageException(e.getMessage());
        }
        return taskList;
    }
    
    //@@author A0118894N
    /*
     * Get tasks by id from the db as an instance of Task model
     */
    @Override
    public Task getTaskById(int id) throws StorageException {
        String query = "SELECT * from tasks where id = ?";
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet r = stmt.executeQuery();
            
            if (!r.next()) throw new StorageException("unable to fetch task with that id");
            else {
                Task t = new Task(r.getInt(COL_PRIMARY_KEY),
                                  r.getString(COL_TASK_NAME),
                                  r.getString(COL_CREATED_DATE),
                                  r.getString(COL_DUE_DATE),
                                  r.getString(COL_PRIORITY),
                                  r.getString(COL_STATUS),
                                  r.getString(COL_FLOATING),
                                  r.getString(COL_DEADLINE));
                return t;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new StorageException(e.getMessage());
        }
    }
    
    /*
     * Get all tasks that match a particular name from the database
     * this function uses an SQL SELECT LIKE statement on the task_name
     */
    @Override
    public ArrayList<Task> getTasksByName(String name) throws StorageException {
        String query = "SELECT * FROM tasks where task_name like ?";
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            System.out.println(name);
            pstmt.setString(1, "%" + name + "%");
            ResultSet r = pstmt.executeQuery();
            while (r.next()) {
                taskList.add(new Task(r.getInt(COL_PRIMARY_KEY),
                                      r.getString(COL_TASK_NAME),
                                      r.getString(COL_CREATED_DATE),
                                      r.getString(COL_DUE_DATE),
                                      r.getString(COL_PRIORITY),
                                      r.getString(COL_STATUS),
                                      r.getString(COL_FLOATING),
                                      r.getString(COL_DEADLINE)));
            }
        } catch (SQLException e) {
            throw new StorageException(e.getMessage());
        }
        return taskList;
    }

    /*
     * Since we have unique identifiers for our tasks, 
     * we have to get the primary key from the database to set it.
     * This function returns the next available primary key from the SQL
     * generated list.
     */
    @Override
    public int getNextAvailableIdentifier() throws StorageException {
        String query = "SELECT seq FROM sqlite_sequence WHERE name='tasks'";
        try {
            ResultSet res = runQuery(query);
            if (!res.next()) return 1;
            else {
                //res.first();
                return res.getInt(COL_PRIMARY_KEY) + 1;
            }
        } catch (SQLException e) {
            throw new StorageException(e.getMessage());
        }
    }
    
    /*
     * Deletes tasks by id from the db
     */
    @Override
    public void deleteTask(int id) throws StorageException {
        String query = "DELETE FROM tasks WHERE id = ?";
        try {
            if (id == 0) throw new StorageException("illegal task id");
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StorageException(e.getMessage());
        }
    }
    
    /*
     * Deletes all tasks from the db
     */
    @Override
    public void deleteAllTasks() throws StorageException {
        String query = "DELETE FROM tasks";
        String query2 = "delete from sqlite_sequence where name='tasks'";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
            stmt = conn.prepareStatement(query2);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StorageException(e.getMessage());
        }
    }
    
    
    //@@author A0130164W
    /*
     * Deletes the last added task from the db
     */
    @Override
    public void deleteLastTask() throws StorageException {
        String query = "SELECT * FROM tasks ORDER BY id DESC";
        try {
            ResultSet r = runQuery(query);
            deleteTask(r.getInt(COL_PRIMARY_KEY));
            
        } catch (SQLException e) {
            throw new StorageException(e.getMessage());
        }
    }
}
