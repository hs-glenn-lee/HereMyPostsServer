package web.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import web.model.jpa.entities.Account;
import web.model.jpa.entities.Article;
import web.model.jpa.entities.Category;
import web.model.service.AccountService;
import web.model.service.CategoryService;
import web.model.service.article.ArticleService;
import web.utils.UUIDUtil;

@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ArticleService articleService;
	
	/*@RequestMapping(value="write",method=RequestMethod.POST)
	public String write(HttpServletRequest req,
						String content,
						String title,
						String categoryId
						) throws IOException {
		
		
		
		Article article = new Article();
		String articleId = UUIDUtil.getUUID();
		article.setId(articleId);
		article.setContetnFilePath("/user_resources/article_bundle/"+articleId);//이부분 인터페이스-구현 필요
		article.setTitle("title111");
		article.setSummary("summary -----");
		

		
		//원래는 로그인한 사용자만 작성 가능.. 테스트 데이터
		long authorId = 2l;
		Account account = accountService.getAccount(authorId);

		categoryId = "test1";
		Category category = categoryService.get(categoryId);
		//테스트 데이터 end
		
		
		article.setAuthor(account);
		article.setCategory(category);
		
		
		System.out.println(article);
		
		articleService.write(article);
		
		return "redirect:/article/read/"+articleId;
	}*/
	
	@RequestMapping(value="write",method=RequestMethod.GET)
	public String write() {
		return "article/write-form";
	}
	
	
	/*@RequestMapping(value="read/{articleId}", method=RequestMethod.GET)
	public String read(
			@PathVariable("articleId") String articleId
			) {
		Article article = articleService.read(articleId);
		System.out.println(article);
		Account author = article.getAuthor();
		System.out.println(author);
		Category cat = article.getCategory();
		System.out.println(cat.getName());
		
		return "article/read";
	}*/
	
	@RequestMapping(value="modify",method=RequestMethod.GET)
	public String modify() {
		return null;
	}
	
}
