package web.controllers.rest;

import java.util.HashMap;
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
import web.model.jpa.entities.Category;
import web.model.service.CategoryService;
import web.model.service.sign.Sign;
import web.model.service.sign.SignService;

@RestController
@RequestMapping(value="/api", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CategoryRestController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	SignService signService;
	
	@RequestMapping(value="/{username}/category/create", method=RequestMethod.PUT)
	public Category create(@RequestBody Category category, @PathVariable("username") String username, HttpServletRequest req) throws NotSignedInException {
		Sign sign = signService.getSign(req.getSession());
		Account me = sign.getAccount();
		if(!me.getUsername().equals(username)) { throw new IllegalStateException("Bad Request"); }
		
		Category newwest = categoryService.create(category, me);
		return newwest;
	}
	
	@RequestMapping(value="/{username}/category/update", method=RequestMethod.PUT)
	public Category update(@RequestBody Category category, HttpServletRequest req) throws NotSignedInException {
		Sign sign = signService.getSign(req.getSession());
		Account me = sign.getAccount();
		Category updated = categoryService.update(category, me);
		return updated;
	}
	
	@RequestMapping(value="/{username}/category/remove", method=RequestMethod.PUT)
	public HashMap<?,?> remove(@RequestBody Category category, HttpServletRequest req) throws NotSignedInException {
		Sign sign = signService.getSign(req.getSession());
		Account me = sign.getAccount();
		
		int removedArticleCount = categoryService.remove(category.getId(), me);
		
		HashMap<String, Integer> res = new HashMap<String, Integer>();
		res.put("removedArticleCount", removedArticleCount);
		return res;
	}
	
	@RequestMapping(value="/category/all", method=RequestMethod.GET)
	public List<Category> getAllMyCategory(HttpServletRequest req) throws NotSignedInException {
		Sign sign = signService.getSign(req.getSession());
		List<Category> catList = categoryService.getCategoriesOwnedBy(sign.getAccount());
		return catList;
	}
	
	
	@RequestMapping(value="/{username}/category/public", method=RequestMethod.GET)
	public List<Category> getPublicCategories(HttpServletRequest req, @PathVariable("username") String username) throws NotSignedInException {
		List<Category> catList = categoryService.getPublicCategoriesOwnedBy(username);
		return catList;
	}
	

}
