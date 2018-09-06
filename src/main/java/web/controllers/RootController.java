package web.controllers;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Controller
@RequestMapping("/")
public class RootController {

/*	@RequestMapping(method=RequestMethod.GET)
	public String root(Model model) {
		//String v = testService.getValue(0);
		return "index";
	}
	*/
	
	@RequestMapping(value={""
			, "/sign/sign-in"
			, "/sign/sign-up"
			, "/me/settings"
			, "/{username}"
			, "/{username}/manager/{articleId}"
			, "/{username}/manager"
			, "/{username}/article"
			, "/{username}/article/{articleId}"
			
			, "/error/not-signed-in"
			
			, "/agreements/privacy-policy"
			, "/agreements/terms-of-service"
			})
	public String root(ModelAndView mnv) throws Exception {
		return "index";
	}
}
