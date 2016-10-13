package core;

import models.Task;

public class DatabaseStorage implements StorageBackend {

    @Override
    public void setFileName(String fileName) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void initializeStorage() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteStorage() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean save(Task task) throws StorageException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getNextAvailableIdentifier() {
        // TODO Auto-generated method stub
        return 0;
    }

}
