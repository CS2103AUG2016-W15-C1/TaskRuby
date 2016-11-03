package core;

import java.sql.SQLException;
import java.util.ArrayList;

import models.Task;

public interface StorageBackend {
    
    public void setFileName(String fileName);
    public void initializeStorage() throws StorageException;
    public void deleteStorage() throws SQLException;
    
    public void addTask(Task task) throws StorageException, SQLException;
    public void updateTaskStatus(int id, String status) throws StorageException, SQLException;
    public ArrayList<Task> getTasks() throws StorageException;
    public Task getTaskById(int id) throws StorageException;
    public int getNextAvailableIdentifier() throws StorageException;
    public void deleteTask(int id) throws StorageException;
    public void deleteAllTasks() throws StorageException;
    public void deleteLastTask() throws StorageException;
}
