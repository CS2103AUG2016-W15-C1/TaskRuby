package core;

import java.io.IOException;

import controllers.TaskController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import models.Task;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

@SuppressWarnings("unused") //TODO remove
public class TaskRuby extends Application {
    
    public TaskRuby() {
        /*
         * TODO
         * test item list
         */
        isVisible = false;
        testTasks = FXCollections.observableArrayList(
                    new Task("test task 1"),
                    new Task("test task 2")
                );
    }
    
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Task> testTasks;
    public boolean isVisible;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        launch(args);
    }
    
    public ObservableList<Task> getTasks() {
        return testTasks;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("TaskRuby");
        initRootLayout();
        initTaskOverviewLayout();
    }
    
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TaskRuby.class.getResource("/views/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void initTaskOverviewLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TaskRuby.class.getResource("/views/TaskOverview.fxml"));
            AnchorPane taskOverview = (AnchorPane) loader.load();
            rootLayout.setCenter(taskOverview);
            
            TaskController controller = loader.getController();
            controller.setMain(this);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
