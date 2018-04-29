package web.model.service;

import web.model.jpa.entities.Account;

public interface AccountService {
	public Account getAccount(String username);
	public Account getAccount(Long id);
	public Account createAccount(Account newAccount);
	public Account authenticate(String username, String password);
	public boolean isUniqueNewUsername(String username);
}
