package web.model.service.article;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import web.model.jpa.entities.Article;

public interface ArticleService {
	
	public Article save(Article compositeArticle) throws IOException;

	public Article getArticle(String articleId) throws IOException;

	public List<Article> getRecentArticles(String username);
	public List<Article> getArticlesAuthoredBy(String authorId);
	public Set<Article> getArticlesOfCategory(String categoryId);
	
	
}
