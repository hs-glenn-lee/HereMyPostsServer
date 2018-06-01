package web.model.service.article;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.model.jpa.entities.Article;
import web.model.jpa.entities.Category;
import web.model.jpa.repos.ArticleRepo;
import web.model.service.file.FileStorage;
import web.utils.UUIDUtil;

@Service("articleSerivce")
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	ArticleRepo articleRepo;
	
	@Autowired
	EntityManager em;
	
	@Autowired
	FileStorage fileStorage;
	
	@Transactional
	@Override
	public Article write(Article compositeArticle) throws IOException {
		//set id of new article
		compositeArticle.setId(UUIDUtil.getUUID());
		
		//write article content file and set its file path
		File contentFile = fileStorage.writeContentFile(compositeArticle);
		compositeArticle.setContentFilePath(contentFile.toString());
		em.persist(compositeArticle);
		
		em.flush();
		em.close();
		return compositeArticle;
	}


	@Override
	public Article read(String articleId) {
		Query q = em.createQuery("SELECT article FROM Article article "
								  +"inner join fetch article.category "
								  +"inner join fetch article.author "
								  +"WHERE article.id = :id"
								  );
		q.setParameter("id", articleId);
		Article article = (Article) q.getSingleResult();
		return article;
	}

	@Override
	public Article getArticle(String articleId) throws IOException {
		Article article = em.find(Article.class, articleId);
		article.getCategory();
		article.getAuthor();
		
		File content = fileStorage.getFile(articleId);
		article.setContent(fileToString(content));
		em.close();
		return article;
	}

	@Override
	public List<Article> getArticlesAuthoredBy(String authorId) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Set<Article> getArticlesOfCategory(String categoryId) {
		Category category = em.find(Category.class, categoryId);
		Set<Article> articles = category.getArticles();
		
		em.close();
		
		for(Article article : articles) {//this statement prevent loop when serialize object by jackson 
			article.setCategory(null);
		}
		
		return articles;
	}


	@Override
	public String getArticleContent(String articleId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private String fileToString(File file) throws IOException {
		byte[] encoded = Files.readAllBytes(file.toPath());
		return new String(encoded, StandardCharsets.UTF_8);
	}
}
