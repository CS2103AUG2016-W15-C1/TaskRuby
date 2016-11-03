package core;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.joestelmach.natty.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.text.DateFormatter;

import models.Task;
//@@author A0144017R
public class EditCommand extends BaseCommand {

    private static final Logger logger = Logger.getLogger(EditCommand.class.getName());

    private static final String helpString = "edit <task>";
    private TaskRuby main;
    com.joestelmach.natty.Parser nattyParser = new com.joestelmach.natty.Parser();

    public EditCommand(StorageBackend storage, TaskRuby main) {
        super(storage);
        this.main = main;
        // TODO Auto-generated constructor stub
    }

    @Override
    public String getHelpString() {
        return helpString;
    }

    private LocalDateTime getDateTime(List<DateGroup> parsedDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d H:m");
        Date d = parsedDate.get(0).getDates().get(0);
        String t = (d.getYear() + 1900) + "-" + (d.getMonth() + 1) + "-" + (d.getDate() + 1) + " " + (d.getHours() + 1)
                + ":" + (d.getMinutes() + 1);
        return LocalDateTime.parse(t, formatter);
    }

    @Override
    public void execute(String[] args) throws CommandException, SQLException {
        if (args.length == 0) {
            throw new CommandException("empty arguments");
        }
        // System.out.println(args.length);
        try {
        	int taskId = Integer.parseInt(args[0]);
        	Task taskToBeEdited = storage.getTaskById(taskId);
            String desc = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
            String[] tokens = desc.split("-");
            String taskDesc = taskToBeEdited.getTaskShortName();
            LocalDateTime taskDue = taskToBeEdited.getTaskDeadlineInDateTime();
            LocalDateTime taskStart = taskToBeEdited.getTaskStartTimeInDateTime();
            String inf = null;
            String priority = taskToBeEdited.getTaskPriorityString();
            String prio = null;
            for (String t : tokens) {
                if (t.startsWith("t"))
                    taskDesc = t.substring(1);
                if (t.startsWith("d")) {
                    List<DateGroup> groups = nattyParser.parse(t.substring(1));
                    taskStart = getDateTime(groups);
                }
                if (t.startsWith("D"))
                    taskDue = getDateTime(nattyParser.parse(t.substring(1)));
                if (t.startsWith("p")) {
                    prio = t.substring(1).trim();
                    int prioInt = Integer.parseInt(prio);
                    switch (prioInt) {
                   
                    case 1: priority = "HIGH";
                    break;
                    
                    case 2: priority = "MED";
                    break;
                    
                    case 3: priority = "LOW";
                    break;
                    }

                }
            }
            logger.info("trying to edit task");
            Task t = new Task(taskDesc, taskStart, taskDue, inf, priority);
            storage.editTask(taskId, t);
            // throw StorageException("stub");
        } catch (StorageException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new CommandException(e.getMessage());
        }
    }

}
