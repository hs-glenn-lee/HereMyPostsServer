package web.controllers.rest;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import web.controllers.rest.responses.GenericResponse;
import web.exceptions.NotSignedInException;
import web.model.jpa.entities.Account;
import web.model.service.AccountService;
import web.model.service.sign.Sign;
import web.model.service.sign.SignService;

@RestController
public class SignRestController {
	
	@Autowired
	SignService signService;
	
	@Autowired
	AccountService accountSerivce;
	
	@RequestMapping(value="/sign-in", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public GenericResponse<?> signin(@RequestBody Account trying,
													HttpServletRequest req) {
		Account account = signService.signin(trying, req.getSession());

		if(account == null) {
			return GenericResponse.getFail("일치하는 사용자 정보가 없습니다.");
		}else {
			GenericResponse<Account> gr = new GenericResponse<Account>();
			gr.setStatus(GenericResponse.STATUS_SUCCESS);
			gr.setData(account);
			return gr;
		}
		
	}
	
	@RequestMapping(value="/sign-up", method=RequestMethod.PUT)
	public @ResponseBody GenericResponse<?> signup(@RequestBody Account account) {
		signService.signup(account);
		return new GenericResponse<Object>();
	}
	
	@RequestMapping(value="/isUniqueNewUsername", method=RequestMethod.POST)
	public HashMap<String, Boolean> isUniqueUsername(@RequestBody HashMap<String, Object> jsonMap) {
		boolean isUniqueAndNew = accountSerivce.isUniqueNewUsername((String)jsonMap.get("username"));
		HashMap<String, Boolean> ret = new HashMap<>();
		ret.put("isUniqueNewUserName", isUniqueAndNew);
		return ret;
	}
	
	@RequestMapping(value="/getMyAccount", method=RequestMethod.GET)
	public Account getMyAccount(HttpServletRequest req) throws NotSignedInException {
		Sign sign = signService.getSign(req.getSession());
		Account mine = sign.getAccount();
		mine.setPassword("");
		return mine;
	}

/*	@RequestMapping("/sign-out")
	public String signout(HttpServletRequest req) {
		req.getSession().invalidate();
		return "/";
	}*/

}
