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
    private StringProperty taskPriority; 
    private StringProperty taskStatus;
    private boolean isFloating;
    private boolean isDeadline;
    
    public Task(String taskName) {
        this.taskShortName = new SimpleStringProperty(taskName);
        this.taskIdentifier = new SimpleIntegerProperty(0);
    }
    
    public Task(int taskId, String taskName, String startTime, String dueDate,
    		String priority, String status, String floating, String deadline) {
        this.taskIdentifier = new SimpleIntegerProperty(taskId);
        this.taskShortName = new SimpleStringProperty(taskName);
        this.isFloating = Integer.parseInt(floating) == 1 ? true : false;
        this.isDeadline = Integer.parseInt(deadline) == 1 ? true : false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d'T'HH:mm");
        if (!isFloating) {
        	LocalDateTime t = LocalDateTime.parse(startTime, formatter);
        	this.taskStartTime = new SimpleObjectProperty<LocalDateTime>(t);
        } else {
        	this.taskStartTime = new SimpleObjectProperty<LocalDateTime>(LocalDateTime.now());
        }
        
        if (!isDeadline) {
        	this.taskDeadline = new SimpleObjectProperty<LocalDateTime>(
        			LocalDateTime.parse(dueDate, formatter));
        } else {
        	this.taskDeadline = new SimpleObjectProperty<LocalDateTime>(LocalDateTime.now());
        }
        
        this.taskPriority = new SimpleStringProperty(priority);
        this.taskStatus = new SimpleStringProperty(status);
    }
    
    public Task(String taskName, LocalDateTime startDate, 
    		LocalDateTime taskDue, String information, String priority) {
    	if (startDate == null) {
    		System.out.println("cannot parse");
    		isFloating = true;
    		startDate = LocalDateTime.now();
    	}
    	
    	if (taskDue == null) {
    		isDeadline = true;
    		taskDue = LocalDateTime.now();
    	}
    	
    	this.taskShortName = new SimpleStringProperty(taskName);
    	this.taskStartTime = new SimpleObjectProperty<LocalDateTime>(startDate);
    	this.taskDeadline = new SimpleObjectProperty<LocalDateTime>(taskDue);
    	this.taskPriority = new SimpleStringProperty(priority);
    	this.taskStatus = new SimpleStringProperty("Not Done");	
    }
    
    public IntegerProperty taskIdentifier() {
        return taskIdentifier;
    }
    
    public boolean isFloating() {
    	return isFloating;
    }
    
    public boolean isDeadline() {
    	return isDeadline;
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
    
    public String getTaskStatus() {
    	return this.taskStatus.get().toString();
    }
    
    public void setTaskPriority(String p) {
        this.taskPriority = new SimpleStringProperty(p);
    }
    
    public String getTaskPriorityString() {
    	return taskPriority.get().toString();
    }
    
    public String getTaskPriority() {
        
        
       String priorityString = null;
        
        if (taskPriority.get().equals("HIGH")){
            priorityString = "1";
        }
        
        else if (taskPriority.get().equals("MED")){
            priorityString = "2";
        }
        
        else {
            priorityString = "3";
        }
        
    	return priorityString;
    }
    
    public void setTaskDeadline(LocalDateTime time) {
        this.taskDeadline.set(time);
    }
    
    public String getTaskDeadline() {
        return this.isDeadline ? "" : this.taskDeadline.get().toString();
    }
    
    public LocalDateTime getTaskDeadlineInDateTime() {
    	return this.taskDeadline.get();
    }
    
    public void setTaskStartTime(LocalDateTime time) {
        this.taskStartTime.set(time);
    }
    
    public LocalDateTime getTaskStartTimeInDateTime() {
    	return this.taskStartTime.get();
    }
    
    public String getTaskStartTime() {
        System.out.println(this.taskStartTime.get().toString());
        return this.isFloating ? "" : this.taskStartTime.get().toString();
    }
}
