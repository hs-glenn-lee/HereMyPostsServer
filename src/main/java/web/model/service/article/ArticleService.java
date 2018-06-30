package web.model.service.article;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import web.model.jpa.entities.Account;
import web.model.jpa.entities.Article;

public interface ArticleService {
	
	public Article save(Article compositeArticle) throws IOException;

	public Article getArticle(String articleId) throws IOException;
	
	public String saveArticleImage(MultipartFile uploadedImage, String articleId, Account me) throws IOException;

	public List<Article> getRecentArticles(String username);
	public List<Article> getArticlesAuthoredBy(String authorId);
	public Set<Article> getArticlesOfCategory(String categoryId);
	
	
}
