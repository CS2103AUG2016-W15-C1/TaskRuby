package core;

import java.io.File;
import java.net.ConnectException;
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
 * This class allows tasks to be stored to a sqlite3 db
 */
public class DatabaseStorage implements StorageBackend {
    
    private static final Logger logger = Logger.getLogger(DatabaseStorage.class.getName());
    
    private String fileName = "test.db";
    private Connection conn = null;
    
    private static final int COL_PRIMARY_KEY = 1;
    private static final int COL_TASK_NAME = 2;
    private static final int COL_CREATED_DATE = 3;
    private static final int COL_DUE_DATE = 4;
    
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
    
    private ResultSet runQuery(String query) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(query);
    }
    

    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void initializeStorage() throws StorageException {
        String query = "CREATE TABLE IF NOT EXISTS tasks(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                       "task_name VARCHAR(255), created_at DATETIME DEFAULT (datetime('now','localtime')))";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            throw new StorageException(e.getMessage());
        }
    }

    @Override
    public void deleteStorage() {
        File file = new File(fileName);
        file.delete();
    }
    
    public void addTask(Task task) throws StorageException {
        logger.info("trying to add task to database");
        String query = "INSERT INTO tasks(task_name) VALUES(?)";
        PreparedStatement stmt;
        
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, task.getTaskShortName());
            if (stmt.executeUpdate() != 1) {
                throw new StorageException("unable to add task to database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.severe(e.getMessage());
            throw new StorageException(e.getMessage());
        }
    }
    
    public ArrayList<Task> getTasks() throws StorageException {
        String query = "SELECT * FROM tasks";
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            ResultSet r = runQuery(query);
            while (r.next()) {
                taskList.add(new Task(r.getInt(COL_PRIMARY_KEY),
                                      r.getString(COL_TASK_NAME)));
            }
        } catch (SQLException e) {
            throw new StorageException(e.getMessage());
        }
        return taskList;
    }

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

}
