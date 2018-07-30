package web.controllers.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import web.exceptions.NotSignedInException;
import web.model.jpa.entities.Account;
import web.model.jpa.entities.Tag;
import web.model.jpa.entities.TagArticle;
import web.model.jpa.repos.AccountRepo;
import web.model.jpa.repos.TagArticleRepo;
import web.model.service.TagService;
import web.model.service.sign.SignService;

@RestController
@RequestMapping(value="/api", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TagRestController {
	
	@Autowired
	TagService tagService;
	
	@Autowired
	SignService signService;
	
	/////
	@Autowired
	TagArticleRepo tagArticleRepo;
	
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
	
	@RequestMapping(value="/article/update-tag/{articleId}", method=RequestMethod.PUT)
	public List<TagArticle> updateTagsArticlesOfArticle(HttpServletRequest req,
								@PathVariable("articleId") String articleId,
								@RequestBody List<TagArticle> tsas) {
		return tagService.updateTagsArticlesOfArticle(articleId, tsas);
	}
	
	@RequestMapping(value="/test/test", method=RequestMethod.GET)
	public List<TagArticle> testest() {
		List<TagArticle> oldTagArticles = tagArticleRepo.findByArticleId("6cfc5109db604313b65f8360854806d7");
		System.out.println(oldTagArticles);
		
		return tagService.findTagArticlesByArticleId("6cfc5109db604313b65f8360854806d7");
	}
	
	@RequestMapping(value="/test/test/test", method=RequestMethod.GET)
	public List<Tag> testetst() {
		return tagService.findTagsByArticle("6cfc5109db604313b65f8360854806d7");
	}
	
	@Autowired
	AccountRepo accountRepo;
	
	@RequestMapping(value="/test/test/test/test", method=RequestMethod.GET)
	public String test(Model model) {
		System.out.println("eeeeee");
		Account a = accountRepo.test(12l);
		return "index";
	}
	

	
}
