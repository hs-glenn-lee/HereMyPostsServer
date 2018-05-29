package web.model.service.article;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import web.model.jpa.entities.Article;
import web.model.jpa.entities.Category;

public interface ArticleService {
	
	public Article write(Article compositeArticle) throws IOException;
	public Article read(String articleId);
	
	public Article getArticle(String articleId);
	public List<Article> getArticlesAuthoredBy(String authorId);
	public Set<Article> getArticlesOfCategory(String categoryId);
	
	
}
