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
		return "home";
	}
}
