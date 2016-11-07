//@@author A0118894N
package core;

import java.sql.SQLException;
import java.util.ArrayList;

import models.Task;

/*
 * This interface describes what TaskRuby expects as a storage layer
 * Regardless of storage medium (text file, json, db), if the storage layer
 * implements this interface it is plug an play to the task manager.
 * There is also a companion `StorageException`
 */
public interface StorageBackend {
    
    public void setFileName(String fileName);
    public void initializeStorage() throws StorageException;
    public void deleteStorage() throws SQLException;
    
    public void addTask(Task task) throws StorageException, SQLException;
    public void updateTaskStatus(int id, String status) throws StorageException, SQLException;
    public void editTask(int id, Task task) throws StorageException;
    public ArrayList<Task> getTasks() throws StorageException;
    public Task getTaskById(int id) throws StorageException;
    public int getNextAvailableIdentifier() throws StorageException;
    public void deleteTask(int id) throws StorageException;
    public void deleteAllTasks() throws StorageException;
    public void deleteLastTask() throws StorageException;
    public ArrayList<Task> getTasksByName(String name) throws StorageException;
}
