package web.model.service.sign;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.exceptions.NotSignedInException;
import web.model.jpa.entities.Account;
import web.model.jpa.entities.Category;
import web.model.service.AccountService;
import web.model.service.CategoryService;
import web.model.service.file.FileService;
import web.model.service.file.policies.AccountFilePolicy;

@Service("signService")

public class SignServiceImpl implements SignService{
	
	private final static String SIGN_KEY = "SIGN";
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	FileService fileService;
	
	@Autowired
	CategoryService categoryService;
	
	@Override
	@Transactional
	public boolean signup(Account newAccount) throws IOException {
		newAccount = accountService.createNewAccount(newAccount);
		createAccountDirectory(newAccount);
		createRootCategory(newAccount);
		return true;
	}

	private void createAccountDirectory(Account account) throws IOException {
		AccountFilePolicy afp = new AccountFilePolicy(account);
		fileService.createDirs(afp);
	}
	
	private void createRootCategory(Account account) {
		Category root = new Category();
		root.setAccount(account);
		root.setName("default");
		root.setId("default_" + account.getId());
		root.setSeq(0);
		categoryService.create(root);
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
	/**
	 * return sign object
	 * there's no stored sign object in current session throw NotSignedInException
	 * */
	@Override
	public Sign getSign(HttpSession session) throws NotSignedInException {
		Sign sign = (Sign) session.getAttribute(SIGN_KEY);
		if(sign == null)
			throw new NotSignedInException();
		return sign;
	}
	
	

}
