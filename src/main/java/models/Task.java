package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task {
    private final IntegerProperty taskIdentifier;
    private final StringProperty taskShortName;
    
    public Task(String taskName) {
        this.taskShortName = new SimpleStringProperty(taskName);
        this.taskIdentifier = new SimpleIntegerProperty(0);
    }
    
    public Task(int taskId, String taskName) {
        /*
         * TODO
         * taskIdentifier is the primary identifier for each task and as such
         * it is the primary key in the storage medium that we should use
         * right now I initialize it randomly because the storage controller
         * has not been created yet
         * 
         */
        this.taskIdentifier = new SimpleIntegerProperty(taskId);
        this.taskShortName = new SimpleStringProperty(taskName);
    }
    
    public IntegerProperty taskIdentifier() {
        return taskIdentifier;
    }
    
    public StringProperty taskShortName() {
        return taskShortName;
    }
    
    public String getTaskShortName() {
        return taskShortName.get();
    }
}
