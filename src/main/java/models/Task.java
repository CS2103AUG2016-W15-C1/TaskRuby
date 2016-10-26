package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task {
    private IntegerProperty taskIdentifier;
    private final StringProperty taskShortName;
    private ObjectProperty<LocalDateTime> taskStartTime;
    private ObjectProperty<LocalDateTime> taskDeadline;
    private StringProperty taskPriority; // this should be an enum
    private StringProperty taskStatus;
    
    public Task(String taskName) {
        this.taskShortName = new SimpleStringProperty(taskName);
        this.taskIdentifier = new SimpleIntegerProperty(0);
    }
    
    public Task(int taskId, String taskName, String startTime) {
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime t = LocalDateTime.parse(startTime, formatter);
        this.taskStartTime = new SimpleObjectProperty<LocalDateTime>(t);
        this.taskPriority = new SimpleStringProperty("NORMAL");
        this.taskStatus = new SimpleStringProperty("not done");
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
    
    public StringProperty taskStatus() {
        return this.taskStatus;
    }
    
    public void setTaskPriority(String p) {
        this.taskPriority = new SimpleStringProperty(p);
    }
    
    public void setTaskDeadline(LocalDateTime time) {
        this.taskDeadline.set(time);
    }
    
    public String getTaskDeadline() {
        return this.taskDeadline.get().toString();
    }
    
    public void setTaskStartTime(LocalDateTime time) {
        this.taskStartTime.set(time);
    }
    
    public String getTaskStartTime() {
        System.out.println(this.taskStartTime.get().toString());
        return this.taskStartTime.get().toString();
    }
}
