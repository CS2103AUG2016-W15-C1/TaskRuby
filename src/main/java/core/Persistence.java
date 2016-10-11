package core;

import java.sql.*;

public class Persistence {
    private Connection connection = null;
    public Persistence() {
        try {
            Class.forName("org.sqlite.JDBC");
            setConnection(DriverManager.getConnection("jdbc:sqlite:test.db"));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
