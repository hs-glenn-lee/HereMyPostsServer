package web.controllers.rest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import web.controllers.rest.parameters.PageParameter;
import web.controllers.rest.responses.GenericResponse;
import web.exceptions.NotSignedInException;
import web.model.jpa.entities.Account;
import web.model.jpa.entities.Article;
import web.model.service.article.ArticleService;
import web.model.service.file.FileService;
import web.model.service.sign.SignService;
import web.utils.UUIDUtil;


@RestController
@RequestMapping(value="/api", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ArticleRestController {
	
	@Autowired
	ArticleService articleSerivce;
	
	
	@Autowired
	FileService fileService;
	
	@Autowired
	SignService signService;
	
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
		
		return articleSerivce.save(article);
	}
	
	@RequestMapping(value="/category/{categoryId}/articles", method=RequestMethod.GET)
	public Set<Article> getArticlesOfCategory(HttpServletRequest req, @PathVariable String categoryId) {
		return articleSerivce.getArticlesOfCategory(categoryId);
	}
	
	@RequestMapping(value="/{username}/recent-articles", method=RequestMethod.GET)
	public List<Article> getRecentArticles(HttpServletRequest req, @PathVariable String username) {
		return articleSerivce.getRecentArticles(username);
	}
	
	@RequestMapping(value="/{username}/recent-articles-", method=RequestMethod.POST)
	public List<Article> findRecentArticlesByUsername(HttpServletRequest req,
			@PathVariable String username,
			@RequestBody PageParameter pageParameter) {
		PageRequest pReq = pageParameter.toPageRequest();
		Page<Article> ret_ = articleSerivce.findRecentArticlesPageByUsername(username, pReq);
		
		//TODO page isFirst ... learn these api
		List<Article> ret = ret_.getContent();
		return ret;
	}
	
	@RequestMapping(value="/{username}/article/count", method=RequestMethod.GET)
	public HashMap<String, Long> countOfArticlesByUsername (	@PathVariable String username ) {
		Long ret = articleSerivce.countOfArticlesByUsername(username);
		HashMap<String, Long> res = new HashMap<String, Long> ();
		res.put("count", ret);
		return res;
	}
	
	
	@RequestMapping(value="/article/{articleId}/image", method=RequestMethod.POST)
	public HashMap<String, String> uploadArticleImage(
			@RequestParam("file") MultipartFile uploadedImage,
			@PathVariable("articleId") String articleId,
			HttpServletRequest req)
			throws NotSignedInException, IOException {
		Account me = signService.getSign(req.getSession()).getAccount();
		String fileImageId = articleSerivce.saveArticleImage(uploadedImage, articleId, me);

		String location = String.format("/article/%s/image/%s", articleId, fileImageId);
		HashMap<String, String> res = new HashMap<String, String>();
		res.put("location", location);
		return res;
	}
	
	
	
	
	@RequestMapping(value="/article/utils/getNewArticleId", method=RequestMethod.GET)
	public HashMap<String, String> getNewArticleId() {
		HashMap<String, String> res = new HashMap<String,String>();
		res.put("newArticleId", UUIDUtil.getUUID());
		return res;
	}
	
	
}
