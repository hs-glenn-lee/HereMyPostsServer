package web.model.service.file.policies;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import web.model.jpa.entities.Account;

public class AccountFilePolicy implements FilePolicy{

	private String accountPathString;
	private Path accountPath;
	
	public AccountFilePolicy (Account account) {
		RootFilePolicy rfp = new RootFilePolicy();
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
