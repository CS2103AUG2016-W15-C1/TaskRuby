//@@author A0130164W
package core;

import java.sql.SQLException;

/*
 * This command finds tasks following input from the user.
 */
public class FindCommand extends BaseCommand {
	private TaskRuby main;
	public FindCommand(StorageBackend storage, TaskRuby main) {
		super(storage);
		this.main = main;
	}

	@Override
	public void execute(String[] args) throws CommandException, SQLException {
		if (args.length == 0) return; //empty find is as good as *
		try {
			main.getTasks().clear();
			main.getTasks().addAll(storage.getTasksByName(args[1]));
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}
	@Override
	public String getHelpString() {
		return null;
	}
}
