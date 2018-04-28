package web.controllers.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignRestController {
	
	@RequestMapping("/sign-in")
	public String signin() {
		return "";
	}
	
	@RequestMapping("/sign-up")
	public String signup() {
		return "";
	}
	
	@RequestMapping("/sign-out")
	public String signout() {
		return "";
	}
	
	
}
