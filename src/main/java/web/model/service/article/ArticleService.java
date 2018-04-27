package web.model.service.article;

import java.util.List;

import web.model.jpa.entities.Account;
import web.model.jpa.entities.Article;
import web.model.jpa.entities.Category;

public interface ArticleService {
	
	public Article write(Article compositeArticle);
	public Article write(Article article, Category category, Account author);
	public Article read(String articleId);
	
	public Article getArticle(String articleId);
	public List<Article> getArticlesAuthoredBy(String authorId);
	public List<Article> getArticlesIn(String categoryId);
	
	
}
