package web.controllers.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/api", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ArticleRestController {
	
	@RequestMapping("/{username}/article/{articleId}")
	public String article(@PathVariable String username,
						@PathVariable String articleId) {
		return "";
	}
	
	

}
