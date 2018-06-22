package web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import web.exceptions.NotSignedInException;
import web.model.jpa.entities.Category;
import web.model.service.CategoryService;
import web.model.service.sign.Sign;
import web.model.service.sign.SignService;



@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	SignService signService;
	
	@Autowired
	CategoryService categoryService;
	
	
	/*@RequestMapping(value="create", method=RequestMethod.GET)
	public String createCategoryForm(Model model, HttpServletRequest req) {
		
		return "/category/create-category-form";
	}*/
	
/*	@RequestMapping(value="create", method=RequestMethod.POST)
	public ModelAndView createCategory(ModelMap model, HttpServletRequest req,
										@RequestParam String id,
										@RequestParam String name,
										@RequestParam String parentId) throws NotSignedInException {
		
		Category cat = new Category();
		cat.setId(id);
		cat.setName(name);
		cat.setParentId(parentId);
		cat.setSeq(0);
		Sign sign = signService.getSign(req.getSession());
		cat.setAccount(sign.getAccount());
		
		categoryService.create(cat);
		
		return new ModelAndView("redirect:/category/myCats");
	}*/
	
	
	@RequestMapping(value="myCats",method=RequestMethod.GET)
	public String myCategories(HttpServletRequest req) throws NotSignedInException {
		Sign sign = signService.getSign(req.getSession());
		List<Category> cats = categoryService.getCategoriesOwnedBy(sign.getAccount());
		return "home";
	}
	
	@RequestMapping(value="myCats",method=RequestMethod.POST)
	public String errorTest(HttpServletRequest req) throws NotSignedInException {
		Sign sign = signService.getSign(req.getSession());
		List<Category> cats = categoryService.getCategoriesOwnedBy(sign.getAccount());
		return "home";
	}
}
