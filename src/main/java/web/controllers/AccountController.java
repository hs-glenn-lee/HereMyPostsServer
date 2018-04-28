package web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import web.model.jpa.entities.Account;
import web.model.service.AccountService;
import web.model.service.sign.Sign;
import web.model.service.sign.SignService;


@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	SignService signService;
	
	@RequestMapping(value="sign-up",method=RequestMethod.GET)
	public String signupForm() {
		return "account/sign-up-form";
	}
	
	@RequestMapping(value="sign-up",method=RequestMethod.POST)
	public String signup(	@RequestParam String email,
							@RequestParam String password,
							@RequestParam String username	) {
		
		Account newAccount = new Account();
		newAccount.setUsername(username);
		newAccount.setPassword(password);
		newAccount.setEmail(email);
		
		System.out.println(newAccount.toString());
		
		accountService.createAccount(newAccount);
		
		return "account/after-sign-up";
	}
	
	@RequestMapping(value="sign-in",method=RequestMethod.GET)
	public String signinForm() {
		
		return "account/sign-in-form";
	}
	
	@RequestMapping(value="sign-in",method=RequestMethod.POST)
	public String signin(	@RequestParam String username,
							@RequestParam String password,
							HttpServletRequest req) {
		
/*		boolean isAuthenticated = accountService.authenticate(username, password);
		if(isAuthenticated) {
			Account account = accountService.getAccount(username);
			signService.signin(account, req.getSession());
		}else {
			
		}
		Sign s = (Sign) req.getSession().getAttribute("SIGN");
		System.out.println(s.getAccount().getUsername());*/
		return "account/sign-in";
	}
}
