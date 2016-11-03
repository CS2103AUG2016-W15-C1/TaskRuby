package core;

import java.sql.SQLException;

public class FindCommand extends BaseCommand {
	private TaskRuby main;
	public FindCommand(StorageBackend storage, TaskRuby main) {
		super(storage);
		this.main = main;
	}

	@Override
	public void execute(String[] args) throws CommandException, SQLException {
		if (args.length == 0) return; //empty find is as good as *
	}

	@Override
	public String getHelpString() {
		// TODO Auto-generated method stub
		return null;
	}

}
