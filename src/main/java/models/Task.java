package models;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task {
    private IntegerProperty taskIdentifier;
    private final StringProperty taskShortName;
    private ObjectProperty<ZonedDateTime> taskStartTime;
    private ObjectProperty<ZonedDateTime> taskDeadline;
    private StringProperty taskPriority; // this should be an enum
    
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
    
    public void setTaskIdentifier(int id) {
        this.taskIdentifier = new SimpleIntegerProperty(id);
    }
    
    public StringProperty taskPriority() {
        return taskPriority;
    }
    
    public void setTaskPriority(String p) {
        this.taskPriority = new SimpleStringProperty(p);
    }
    
    public void setTaskDeadline(ZonedDateTime time) {
        this.taskDeadline.set(time);
    }
    
    public String getTaskDeadline() {
        return this.taskDeadline.get().toString();
    }
    
    public void setTaskStartTime(ZonedDateTime time) {
        this.taskStartTime.set(time);
    }
    
    public String getTaskStartTime() {
        return this.taskStartTime.get().toString();
    }
}
