package web.controllers.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleRestController {
	
	@RequestMapping("/{username}/article/{articleId}")
	public String article(@PathVariable String username,
						@PathVariable String articleId) {
		return "";
	}

}
