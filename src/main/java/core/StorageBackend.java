package core;

import models.Task;

public interface StorageBackend {
    
    public void setFileName(String fileName);
    public void initializeStorage() throws StorageException;
    public void deleteStorage();
    
    public boolean save(Task task) throws StorageException;
    public int getNextAvailableIdentifier() throws StorageException;
}
