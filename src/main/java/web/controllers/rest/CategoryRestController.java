package web.controllers.rest;

import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import web.controllers.rest.responses.GenericResponse;
import web.exceptions.NotSignedInException;
import web.model.jpa.entities.Account;
import web.model.jpa.entities.Category;
import web.model.service.CategoryService;
import web.model.service.sign.Sign;
import web.model.service.sign.SignService;

@RestController
public class CategoryRestController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	SignService signService;
	
	@RequestMapping(value="/category/create", method=RequestMethod.POST)
	public Category all(Category category, HttpServletRequest req) throws NotSignedInException {
		Sign sign = signService.getSign(req.getSession());
		Account curAcc = sign.getAccount();
		Category newwest = categoryService.create(category);
		category.setAccount(curAcc);
		return newwest;
	}
	
	
	@RequestMapping(value="/category/all", method=RequestMethod.POST)
	public List<Category> all(Account account) {
		List<Category> catList = categoryService.getCategoriesOwnedBy(account);
		return catList;
	}
	
	@RequestMapping(value="/{username}/category/public", method=RequestMethod.POST)
	public List<Category>  publicCategories(Account account) {
		//List<Category> catList = categoryService.getCategoriesOwnedBy(account);
		return null;
	}
	
	
	@RequestMapping("/{username}/category/update")
	public GenericResponse<Category> update(@PathVariable String username) {
		
		return null;
	}
	
	@RequestMapping("/{username}/category/{categoryId}")
	public Category one(@PathVariable String username,
						@PathVariable String categoryId) {
		
		return null;
	}
	
}
