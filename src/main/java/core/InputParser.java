package core;

import java.util.Arrays;
import javafx.collections.ObservableList;
import models.Task;

public class InputParser {
    private TaskRuby main;
    public InputParser (TaskRuby main) {
        this.main = main;
    }
    
    public void stubParser(ObservableList<Task> taskList, String input) {
        String[] tok = input.split("\\s+");
        switch (tok[0]) {
        case "add":
            taskList.add(
                    new Task(String.join(" ", Arrays.copyOfRange(tok, 1, tok.length))));
            break;
        case "del":
            taskList.remove(Integer.parseInt(tok[1]) - 1); 
            break;
        case "update":
            taskList.remove(Integer.parseInt(tok[1]) - 1);
            taskList.add(
                    new Task(String.join(" ", Arrays.copyOfRange(tok, 2, tok.length))));
            break;
        case "list":
            main.isVisible = true;
            taskList.add(new Task(""));
            break;
        default:
            break;
        }
    }
}
