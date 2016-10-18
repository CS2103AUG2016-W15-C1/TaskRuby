package controllers;
import java.util.logging.Logger;

import core.CommandException;
import core.ParseException;
import core.StorageException;
import core.TaskRuby;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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
    
    private void parseInput(String input) {
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
        taskListView.setCellFactory(
                /*
                 * TODO change to lambda expression
                 */
                new Callback<ListView<Task>, ListCell<Task>>() {

                    @Override
                    public ListCell<Task> call(ListView<Task> param) {
                        ListCell<Task> cell = new ListCell<Task>() {
                            @Override
                            protected void updateItem(Task t, boolean b) {
                                super.updateItem(t, b);
                                if (t != null) {
                                    setText(t.taskIdentifier().get() +
                                            ": " + t.taskShortName().get());
                                }
                            }
                        };
                        return cell;
                    }
                }
            );
        
        commandField.setOnAction(event ->
            parseInput(commandField.getText()));
    }
    

    public TaskController() {}
    
    public void setMain(TaskRuby main) {
        this.main = main;
        taskListView.setItems(main.getTasks());
    }
}
