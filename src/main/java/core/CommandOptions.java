package core;

import java.time.LocalDateTime;

public class CommandOptions {
	private String command;
	private String taskName;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String priority;
	private String status;
	
	public CommandOptions() {}
	
	public CommandOptions(String command, String taskName, LocalDateTime startDate,
			LocalDateTime endDate, String priority, String status) {
		this.setCommand(command);
		this.setTaskName(taskName);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setPriority(priority);
		this.setStatus(status);
	}


	public String getCommand() {
		return command;
	}


	public void setCommand(String command) {
		this.command = command;
	}


	public String getTaskName() {
		return taskName;
	}


	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}


	public LocalDateTime getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}


	public LocalDateTime getEndDate() {
		return endDate;
	}


	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}


	public String getPriority() {
		return priority;
	}


	public void setPriority(String priority) {
		this.priority = priority;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
}
