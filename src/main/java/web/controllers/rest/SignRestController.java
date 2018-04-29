package web.controllers.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import web.controllers.rest.responses.GeneralResponse;
import web.model.jpa.entities.Account;
import web.model.service.AccountService;
import web.model.service.sign.SignService;

@RestController
public class SignRestController {
	
	@Autowired
	SignService signService;
	
	@Autowired
	AccountService accountSerivce;
	
	
	/*
	@RequestMapping(value="", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> getAll() {
	    List<Entity> entityList = entityManager.findAll();

	    List<JSONObject> entities = new ArrayList<JSONObject>();
	    for (Entity n : entityList) {
	        JSONObject Entity = new JSONObject();
	        entity.put("id", n.getId());
	        entity.put("address", n.getAddress());
	        entities.add(entity);
	    }
	    return new ResponseEntity<Object>(entities, HttpStatus.OK);
	}*/
	
	@RequestMapping(value="/sign-in", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody GeneralResponse<?> signin(@RequestBody Account trying,
													HttpServletRequest req) {
		Account account = signService.signin(trying, req.getSession());

		if(account == null) {//no matched user found
			GeneralResponse<Object> gr = new GeneralResponse<Object>();
			gr.setResult(GeneralResponse.RESULT_FAIL);
			gr.setMessage("입력하신 사용자정보가 맞지 않습니다.");
			return gr;
		}else {
			GeneralResponse<Account> gr = new GeneralResponse<Account>();
			gr.setResult(GeneralResponse.RESULT_SUCCESS);
			gr.setResponseData(account);
			return gr;
		}
		
	}
	
	@RequestMapping(value="/sign-up", method=RequestMethod.PUT)
	public @ResponseBody GeneralResponse<?> signup(@RequestBody Account account) {
		boolean isSuccess = false; 
		isSuccess = signService.signup(account);
		
		if(isSuccess) {
			GeneralResponse<Object> gr = new GeneralResponse<Object>();
			gr.setResult(GeneralResponse.RESULT_SUCCESS);
			return gr;
		}else {
			GeneralResponse<Object> gr = new GeneralResponse<Object>();
			gr.setResult(GeneralResponse.RESULT_FAIL);
			gr.setMessage("실패");
			return gr;
		}
		
	}
	
	@RequestMapping(value="/isUniqueNewUsername", method=RequestMethod.GET)
	public GeneralResponse<?> isUniqueUsername(@RequestParam String username) {
		boolean isUniqueAndNew = accountSerivce.isUniqueNewUsername(username);
		if(isUniqueAndNew) {
			return GeneralResponse.createSUCCESSResponse();
		}else {
			return GeneralResponse.createFAILResponse();
		}
	}
	
	@RequestMapping("/sign-out")
	public String signout(HttpServletRequest req) {
		req.getSession().invalidate();
		return "/";
	}
	
	
}
