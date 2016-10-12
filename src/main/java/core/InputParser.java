package core;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.StringTokenizer;

import javafx.collections.ObservableList;
import models.Task;

public class InputParser {
    public void stubParser(ObservableList<Task> taskList, Persistence p, String input) {
        String[] tok = input.split("\\s+");
        switch (tok[0]) {
        case "add":
            try {
                taskList.add(p.saveTask(new Task(String.join(" ", Arrays.copyOfRange(tok, 1, tok.length)))));
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            break;
        case "del":
            taskList.remove(Integer.parseInt(tok[1]) - 1); 
            break;
        case "update":
            taskList.remove(Integer.parseInt(tok[1]) - 1);
            //taskList.add(
            //        new Task(String.join(" ", Arrays.copyOfRange(tok, 2, tok.length)),
            //                 LocalDate.of(2016, 2, 14)));
            break;
        default:
            break;
        }
    }
}
