package web.model.service.sign;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import web.model.jpa.entities.Account;

@Service("signService")
public class SignServiceImpl implements SignService{
	
	private final static String SIGN_KEY = "SIGN";
	
	@Override
	public boolean signup(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean signin(Account account, HttpSession httpSession) {
		Sign sign = new Sign();
		sign.setAccount(account);
		httpSession.setAttribute(SIGN_KEY, sign);
		
		return true;
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
