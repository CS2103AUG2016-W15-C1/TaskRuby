package controllers;
import core.CommandException;
import core.ParseException;
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
    
    private void parseInput(String input) {
        try {
            main.getParser().parse(input);
        } catch (ParseException | CommandException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
