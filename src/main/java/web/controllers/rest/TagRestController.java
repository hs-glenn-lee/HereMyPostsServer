package web.controllers.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import web.exceptions.NotSignedInException;
import web.model.jpa.entities.Account;
import web.model.jpa.entities.TagArticle;
import web.model.service.TagService;
import web.model.service.sign.SignService;

@RestController
@RequestMapping(value="/api", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TagRestController {
	
	@Autowired
	TagService tagService;
	
	@Autowired
	SignService signService;
	
	@RequestMapping(value="/tag/myTags", method=RequestMethod.GET)
	public List<String> getMyTags(HttpServletRequest req) throws NotSignedInException {
		Account me = signService.getSign(req.getSession()).getAccount();
		return tagService.getMyTags(me);
	}
	
	@RequestMapping(value="/article/{articleId}/tags", method=RequestMethod.GET)
	public List<TagArticle> getTagArticlesOfArticle(HttpServletRequest req,
										@PathVariable("articleId") String articleId) {
		List<TagArticle> tas = tagService.findTagArticlesByArticleId(articleId);
		
		System.out.println("getTagArticlesOfArticle");
		System.out.println(tas);
		
		return tas;
	}
	
	@RequestMapping(value="/tag/save", method=RequestMethod.PUT)
	public List<TagArticle> saveTagsArticles(HttpServletRequest req,
								@RequestBody List<TagArticle> tsas) {
		return tagService.saveTagsArticles(tsas);
	}
	
	

	
}
