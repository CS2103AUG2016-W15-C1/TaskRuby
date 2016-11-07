package controllers;
import java.sql.SQLException;
import java.util.logging.Logger;

import core.CommandException;
import core.ParseException;
import core.StorageException;
import core.TaskRuby;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.Task;

public class TaskController {
    
    private static final Logger logger = Logger.getLogger(TaskController.class.getName());
    
    @FXML
    private ListView<Task> taskListView;
    @FXML
    private TextField commandField;
    
    private TaskRuby main;
    
    @FXML
    private TableView<Task> taskTableView;
    @FXML
    private TableColumn<Task, String> taskNameColumn;
    @FXML
    private TableColumn<Task, Integer> taskIdColumn;
    @FXML
    private TableColumn<Task, String> taskStartTimeColumn;
    @FXML
    private TableColumn<Task, String> taskPriority;
    @FXML
    private TableColumn<Task, String> taskStatus;
    @FXML
    private TableColumn<Task, String> taskEndDateColumn;
    
    
    /*
     * Takes input from the user and then passes it down to the
     * command parsers
     * @param input the string from the UI
     * 
     */
    private void parseInput(String input) {
        commandField.setText("");
        logger.info("trying to parse input to textField: " + input);
        try {
        	if (input.startsWith("find")) {
        		System.out.println("calling find");
        		main.getAvailableCommands().get("find").execute(input.split("\\s+"));
        		return;
        	}
            main.getParser().parse(input);
        } catch (ParseException | CommandException e) {
            e.printStackTrace();
            return;
        } catch (SQLException e) {
			e.printStackTrace();
			return;
		}
        
        try {
            this.main.getTasks().clear();
            if (this.main.getTaskListVisibility()) 
            	this.main.getTasks().addAll(this.main.getStorage().getTasks());
        } catch (StorageException e) {
            e.printStackTrace();
            logger.severe(e.getMessage());
        }
    }
    
    /*
     * FXML function that initializes the task list
     * when the app starts
     */
    @FXML
    private void initialize() {
        taskNameColumn.setCellValueFactory(cellData ->
            cellData.getValue().taskShortName());
        taskIdColumn.setCellValueFactory(c -> c.getValue().taskIdentifier().asObject());
        taskStartTimeColumn.setCellValueFactory(c ->
            new ReadOnlyStringWrapper(c.getValue().getTaskStartTime()));
        taskStatus.setCellValueFactory(c -> c.getValue().taskStatus());
        taskPriority.setCellValueFactory(c -> c.getValue().taskPriority());
        taskEndDateColumn.setCellValueFactory(c -> 
        	new ReadOnlyStringWrapper(c.getValue().getTaskDeadline()));
        commandField.setOnAction(event ->
				parseInput(commandField.getText())
			);
    }
    

    /*
     * Constructor
     */
    public TaskController() {}
    
    /*
     * Function that sets the reference to the main class
     * inside the controller
     * 
     * @params main the reference to the main class
     */
    public void setMain(TaskRuby main) {
        this.main = main;
        taskTableView.setItems(main.getTasks());
    	try {
            this.main.getTasks().clear();
            if (this.main.getTaskListVisibility()) 
            	this.main.getTasks().addAll(this.main.getStorage().getTasks());
        } catch (StorageException e) {
            e.printStackTrace();
            logger.severe(e.getMessage());
        }
    }
}
