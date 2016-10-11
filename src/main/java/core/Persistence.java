package core;

import java.sql.*;
import java.util.ArrayList;

import models.Task;

public class Persistence {
    private Connection connection = null;
    
    private static final int COL_PRIMARY_KEY = 1;
    private static final int COL_TASK_NAME = 2;
    
    private void initializeDB() throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "CREATE TABLE IF NOT EXISTS tasks(id INTEGER PRIMARY KEY AUTOINCREMENT, task_name VARCHAR(255))";
        stmt.execute(query);
        //query = "insert into tasks(task_name) values(\"buy milk\")";
        //stmt.execute(query);
        stmt.close();
    }
    
    public Persistence(String dbName) {
        try {
            Class.forName("org.sqlite.JDBC");
            setConnection(DriverManager.getConnection("jdbc:sqlite:" + dbName));
            initializeDB();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            System.err.println("initialized db");
        }
    }
    
    public ArrayList<Task> getTasks() throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM tasks";
        ResultSet r = stmt.executeQuery(query);
        ArrayList<Task> tasks = new ArrayList<Task>();
        
        while (r.next()) {
            //System.err.println(r.getInt(COL_PRIMARY_KEY));
            //System.err.println(r.getString(COL_TASK_NAME));
            tasks.add(new Task(r.getInt(COL_PRIMARY_KEY),
                               r.getString(COL_TASK_NAME)));
        }
        
        return tasks;
    }
    
    boolean save() {
        //TODO
        return false;
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
