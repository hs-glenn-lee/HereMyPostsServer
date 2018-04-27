package web.model.service.article;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.model.jpa.entities.Account;
import web.model.jpa.entities.Article;
import web.model.jpa.entities.Category;
import web.model.jpa.repos.ArticleRepo;

@Service("articleSerivce")
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	ArticleRepo articleRepo;
	
	@Autowired
	EntityManager em;
	
	
	@Override
	public Article write(Article compositeArticle) {
		return articleRepo.saveAndFlush(compositeArticle);
	}

	@Override
	public Article write(Article article, Category category, Account author) {
		
		return null;
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
	public Article getArticle(String articleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> getArticlesAuthoredBy(String authorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> getArticlesIn(String categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

}
