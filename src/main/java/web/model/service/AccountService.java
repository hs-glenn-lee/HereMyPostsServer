package web.model.service;

import web.model.jpa.entities.Account;
import web.model.jpa.entities.AccountSetting;

public interface AccountService {
	
	/**
	 * this method would be called in context of signing up. create new account and initialize AccountSetting, 
	 * */
	public Account createNewAccount(AccountSetting newAccountSetting) throws IllegalStateException;
	
	/**
	 * match account with username and password. if authenticate successfully return Account, else return null.
	 * */
	public Account authenticate(String username, String password);
	
	/**
	 * is parameter username unique as new Account
	 * */
	public boolean isUniqueNewUsername(String username);
	
	/**
	 * is parameter email unique as new Account
	 * */
	public boolean isUniqueNewEmail(String email);
	
	/**
	 * get Account that has public information of.
	 * */
	public AccountSetting getPublicAccountSetting(String username);

}
