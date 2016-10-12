package controllers;

import core.InputParser;
import core.TaskRuby;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import models.Task;

public class TaskController {
    @FXML
    private ListView<Task> taskListView;
    @FXML
    private TextField commandField;
    
    private TaskRuby main;
    private InputParser commandParser;
    
    private void updateList() {
        commandParser.stubParser(main.getTasks(), commandField.getText());
        commandField.setText("");
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
                                    if (main.isVisible)
                                    setText(t.taskShortName().get());
                                } else {
                                    setText("");
                                }
                            }
                        };
                        return cell;
                    }
                }
            );
        
        commandField.setOnAction(event -> updateList());
    }
    
    public TaskController() {}
    
    public void setMain(TaskRuby main) {
        this.main = main;
        commandParser = new InputParser(this.main);
        taskListView.setItems(main.getTasks());
    }
}
