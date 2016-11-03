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
//@@author A0130164W
public class AddCommand extends BaseCommand {

    private static final Logger logger = Logger.getLogger(AddCommand.class.getName());

    private static final String helpString = "add <task>";
    private TaskRuby main;

    public AddCommand(StorageBackend storage, TaskRuby main) {
        super(storage);
        this.main = main;
    }

    @Override
    public String getHelpString() {
        return helpString;
    }

    @Override
    public void execute(String[] args) throws CommandException, SQLException {
        if (args.length == 0) {
            throw new CommandException("empty arguments");
        }
        try {
			CommandOptions o = Parser.genericParse(
					String.join(" ", Arrays.copyOfRange(args, 0, args.length))
					);
			Task t = new Task(o.getTaskName(),
							  o.getStartDate(),
							  o.getEndDate(),
							  "",
							  o.getPriority());
			storage.addTask(t);
		} catch (ParseException | StorageException e) {
			throw new CommandException(e.getMessage());
		}
    }

}
