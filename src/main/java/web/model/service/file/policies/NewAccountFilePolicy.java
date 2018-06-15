package web.model.service.file.policies;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import web.model.jpa.entities.Account;

public class NewAccountFilePolicy implements NewFilePolicy{

	private String accountPathString;
	private Path accountPath;
	
	public NewAccountFilePolicy (Account account) {
		NewRootFilePolicy rfp = new NewRootFilePolicy();
		accountPathString = rfp.getPathString() + File.separator + account.getId();
		accountPath = Paths.get(accountPathString);
	}
	
	@Override
	public Path getPath() {
		return accountPath;
	}

	@Override
	public String getPathString() {
		return accountPathString;
	}

	@Override
	public boolean isDir() {
		return true;
	}
	
}
