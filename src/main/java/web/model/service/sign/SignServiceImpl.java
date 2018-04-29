package web.model.service.sign;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.model.jpa.entities.Account;
import web.model.service.AccountService;

@Service("signService")
public class SignServiceImpl implements SignService{
	
	private final static String SIGN_KEY = "SIGN";
	
	@Autowired
	AccountService accountService;
	
	@Override
	public boolean signup(Account newAccount) {
		accountService.createAccount(newAccount);
		return true;
	}

	@Override
	public Account signin(Account account, HttpSession httpSession) {

		Account authenicatedAccount = accountService.authenticate(account.getUsername(), account.getPassword());
		
		if(authenicatedAccount == null)
			return null;
		
		Sign sign = new Sign();
		sign.setAccount(authenicatedAccount);
		httpSession.setAttribute(SIGN_KEY, sign);
		
		return authenicatedAccount;
	}

	@Override
	public boolean signout(HttpSession session) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSignedin(HttpSession httpSession) {
		if(httpSession.getAttribute(SIGN_KEY) == null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public Sign getSign(HttpSession session) {
		Sign sign = (Sign) session.getAttribute(SIGN_KEY);
		return sign;
	}

}
