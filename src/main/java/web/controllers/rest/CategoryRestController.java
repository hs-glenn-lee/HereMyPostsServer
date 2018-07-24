package web.controllers.rest;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
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
import web.utils.UUIDUtil;

@RestController
@RequestMapping(value="/api", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CategoryRestController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	SignService signService;
	
	@RequestMapping(value="/category/create", method=RequestMethod.PUT)
	public Category create(@RequestBody Category category, HttpServletRequest req) throws NotSignedInException {
		Sign sign = signService.getSign(req.getSession());
		Account curAcc = sign.getAccount();
		category.setId(UUIDUtil.getUUID());
		category.setOwner(curAcc);
		Category newwest = categoryService.create(category);
		category.setOwner(curAcc);
		return newwest;
	}
	
	@RequestMapping(value="/category/update", method=RequestMethod.PUT)
	public Category update(@RequestBody Category category, HttpServletRequest req) throws NotSignedInException {
		Sign sign = signService.getSign(req.getSession());
		Account curAcc = sign.getAccount();
		category.setOwner(curAcc);
		Category newwest = categoryService.update(category);
		category.setOwner(curAcc);
		return newwest;
	}
	
	@RequestMapping(value="/category/remove", method=RequestMethod.PUT)
	public HashMap<?,?> remove(@RequestBody Category category, HttpServletRequest req) throws NotSignedInException {
		Sign sign = signService.getSign(req.getSession());
		Account curAcc = sign.getAccount();
		
		int removedArticleCount = categoryService.remove(category.getId(), curAcc.getId());
		
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
	
	@RequestMapping(value="/{username}/category/public", method=RequestMethod.POST)
	public List<Category>  publicCategories(Account account) {
		//List<Category> catList = categoryService.getCategoriesOwnedBy(account);
		return null;
	}
	
	
/*	@RequestMapping("/{username}/category/update")
	public GenericResponse<Category> update(@PathVariable String username) {
		
		return null;
	}
	
	@RequestMapping("/{username}/category/{categoryId}")
	public Category one(@PathVariable String username,
						@PathVariable String categoryId) {
		
		return null;
	}
	*/
}
