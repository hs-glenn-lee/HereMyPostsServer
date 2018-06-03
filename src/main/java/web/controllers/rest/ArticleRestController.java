package web.controllers.rest;

import java.io.IOException;
import java.util.List;
import java.util.Set;

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
	
	@RequestMapping(value="/article/{articleId}", method=RequestMethod.GET)
	public Article getArticle(@PathVariable String articleId) throws IOException {
		return articleSerivce.getArticle(articleId);
	}
	
	@RequestMapping(value="/article/save", method=RequestMethod.POST)
	public Article saveArticle(HttpServletRequest req, @RequestBody Article article) throws IOException {
		System.out.println(article);
		System.out.println(article.getCategory());
		System.out.println(article.getAuthor());

		return articleSerivce.write(article);
	}
	
	@RequestMapping(value="/category/{categoryId}/articles", method=RequestMethod.GET)
	public Set<Article> getArticlesOfCategory(HttpServletRequest req, @PathVariable String categoryId) {
		return articleSerivce.getArticlesOfCategory(categoryId);
	}
	
	@RequestMapping(value="/{username}/recent-articles", method=RequestMethod.GET)
	public List<Article> getRecentArticles(HttpServletRequest req, @PathVariable String username) {
		return articleSerivce.getRecentArticles(username);
	}

}
