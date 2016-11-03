package controllers;
import java.sql.SQLException;
import java.util.logging.Logger;

import core.CommandException;
import core.ParseException;
import core.StorageException;
import core.TaskRuby;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
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
    
    private void parseInput(String input) {
        commandField.setText("");
        logger.info("trying to parse input to textField: " + input);
        try {
            main.getParser().parse(input);
        } catch (ParseException | CommandException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        try {
            this.main.getTasks().clear();
            this.main.getTasks().addAll(this.main.getStorage().getTasks());
        } catch (StorageException e) {
            e.printStackTrace();
            logger.severe(e.getMessage());
        }
    }
    
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
    

    public TaskController() {}
    
    public void setMain(TaskRuby main) {
        this.main = main;
        taskTableView.setItems(main.getTasks());
    }
}
