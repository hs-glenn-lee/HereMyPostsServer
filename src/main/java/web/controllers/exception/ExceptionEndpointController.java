package web.controllers.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionEndpointController {
	
	/*
	 * redirect endpoint, when exception occurs
	 * */
	@RequestMapping("/exception/not-signed-in")
	public String redirectedExceptionPage() {
		return "exception/notSignedInException";
	}
	
}
