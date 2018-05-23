package web.controllers.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import web.model.jpa.entities.Article;
import web.model.service.article.ArticleService;


@RestController
@RequestMapping(value="/api", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ArticleRestController {
	
	@Autowired
	ArticleService articleSerivce;
	
	@RequestMapping("/{username}/article/{articleId}")
	public String article(@PathVariable String username,
						@PathVariable String articleId) {
		return "";
	}
	
	@RequestMapping(value="/article/save", method=RequestMethod.POST)
	public String saveArticle(HttpServletRequest req, @RequestBody Article article) {
		System.out.println(article);
		
		
		//articleSerivce.write(compositeArticle);
		
		return "";
	}
	
	

}
