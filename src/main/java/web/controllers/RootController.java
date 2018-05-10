package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import web.model.service.TestService;

@Controller
@RequestMapping("/")
public class RootController {
	@Autowired
	TestService testService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String root(Model model) {
		String v = testService.getValue(0);
		return "index";
	}
	
	@RequestMapping(value="sign-in", method=RequestMethod.GET)
	public String signIn(Model model) {
		String v = testService.getValue(0); 
		return "index";
	}
	
	@RequestMapping(value="sign-up", method=RequestMethod.GET)
	public String signUp(Model model) {
		String v = testService.getValue(0);
		return "index";
	}
}
