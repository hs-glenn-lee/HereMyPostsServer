package web.model.service.sign;

import javax.servlet.http.HttpSession;

import web.model.jpa.entities.Account;

public interface SignService {
	
	public boolean signup(Account account);
	
	/**
	 * if not valid username and password, this will return null
	 * */
	public Account signin(Account account, HttpSession httpSession);
	
	public boolean signout(HttpSession session);
	
	public boolean isSignedin(HttpSession httpSession);
	
	public Sign getSign(HttpSession session);
	
}
