package web.model.service.article;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.model.jpa.entities.Article;
import web.model.jpa.entities.Category;
import web.model.jpa.entities.Tag;
import web.model.jpa.entities.TagArticle;
import web.model.jpa.repos.ArticleRepo;
import web.model.service.TagService;
import web.model.service.file.FilePathMapService;
import web.model.service.file.FileService;
import web.model.service.file.StorageService;
import web.model.service.file.policies.ArticleFilePolicy;
import web.model.service.file.policies.NewArticleContentFilePolicy;
import web.model.service.file.policies.NewArticleFilePolicy;
import web.utils.UUIDUtil;

@Service("articleSerivce")
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	ArticleRepo articleRepo;
	
	@Autowired
	EntityManager em;

	@Autowired
	FileService fileService;
	
	@Autowired
	TagService tagService;
	
	@Transactional
	@Override
	public Article write(Article compositeArticle) throws IOException {
		//set id of new article
		compositeArticle.setId(UUIDUtil.getUUID());
		
		//write article content file and set its file path
		NewArticleFilePolicy afp = new NewArticleFilePolicy(compositeArticle);
		fileService.createDirs(afp);
		String fileId = fileService.saveFile(compositeArticle.getContent().getBytes(StandardCharsets.UTF_8),
						new NewArticleContentFilePolicy(compositeArticle));

		compositeArticle.setContentFileId(fileId);
		
		articleRepo.save(compositeArticle);
		
		
		//save tags
		
				
		/*tagService.saveTags(tags);
		tagService.addTagsToArticle(tas);*/

		return compositeArticle;
	}


/*	@Override
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
*/
	@Override
	public Article getArticle(String articleId) throws IOException {
		Article article = em.find(Article.class, articleId);
		article.getCategory();
		article.getAuthor();
		
		File content = fileService.getFile(article.getContentFileId());
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
	public List<Article> getRecentArticles(String username) {
		Query query = em.createQuery("select article from Article article"
				+ " where article.author.username = :username "
				+ " order by article.createTimestamp desc");
		query.setParameter("username", username);
		int pageNumber = 1;
		int pageSize = 10;
		query.setFirstResult((pageNumber-1) * pageSize);
		query.setMaxResults(pageSize);
		
		List<Article> recentArticles = query.getResultList();
		em.close();
		return recentArticles;
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
