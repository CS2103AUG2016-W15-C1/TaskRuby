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
    private InputParser parser;
    
    @FXML
    private void initialize() {
        parser = new InputParser();
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
            parser.stubParser(main.getTasks(),
                              main.getStorageLayer(),
                              commandField.getText())
                );
    }
    
    public TaskController() {}
    
    public void setMain(TaskRuby main) {
        this.main = main;
        taskListView.setItems(main.getTasks());
    }
}
