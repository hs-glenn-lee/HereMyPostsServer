package web.model.service.article;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import web.model.jpa.entities.Article;

public interface ArticleService {
	
	public Article write(Article compositeArticle) throws IOException;
	public Article read(String articleId);
	
	public Article getArticle(String articleId) throws IOException;
	public String getArticleContent(String articleId);
	
	public List<Article> getArticlesAuthoredBy(String authorId);
	public Set<Article> getArticlesOfCategory(String categoryId);
	
	
}
