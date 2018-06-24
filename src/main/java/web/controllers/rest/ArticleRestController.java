package web.controllers.rest;

import java.io.IOException;
import java.util.ArrayList;
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
import web.model.jpa.entities.Tag;
import web.model.jpa.entities.TagArticle;
import web.model.service.article.ArticleService;
import web.utils.UUIDUtil;


@RestController
@RequestMapping(value="/api", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ArticleRestController {
	
	@Autowired
	ArticleService articleSerivce;
	
	@RequestMapping(value="/article/{articleId}", method=RequestMethod.GET)
	public Article getArticle(@PathVariable String articleId) throws IOException {
		Article a = articleSerivce.getArticle(articleId);
		System.out.println(a.getTagsArticles());
		return a;
	}
	
	@RequestMapping(value="/article/save", method=RequestMethod.POST)
	public Article saveArticle(HttpServletRequest req, @RequestBody Article article) throws IOException {
		System.out.println(article);
		System.out.println(article.getCategory());
		System.out.println(article.getAuthor());
		System.out.println(article);
		
		return articleSerivce.write(article);
	}
	
	
	/*private void normalizeCompositeArticle(Article compositeArticle) {
		//because jackson doesn't perfectly interchange with jpa
		//Json.stringify can't stringify recursive object.
		
		List<TagArticle> tas = compositeArticle.getTagArticles();
		List<Tag> tags = new ArrayList<Tag>();
		System.out.println(tas);
		for(TagArticle ta : tas) {
			ta.setArticle(compositeArticle);
			ta.setId(UUIDUtil.getUUID());
			System.out.println(ta);
			
			tags.add(ta.getTag());
		}
	}*/
	
	@RequestMapping(value="/category/{categoryId}/articles", method=RequestMethod.GET)
	public Set<Article> getArticlesOfCategory(HttpServletRequest req, @PathVariable String categoryId) {
		return articleSerivce.getArticlesOfCategory(categoryId);
	}
	
	@RequestMapping(value="/{username}/recent-articles", method=RequestMethod.GET)
	public List<Article> getRecentArticles(HttpServletRequest req, @PathVariable String username) {
		return articleSerivce.getRecentArticles(username);
	}

}
