package web.model.service.article;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import web.model.jpa.entities.Account;
import web.model.jpa.entities.Article;
import web.model.jpa.entities.Category;
import web.model.jpa.repos.AccountRepo;
import web.model.jpa.repos.ArticleRepo;
import web.model.service.TagService;
import web.model.service.file.FileService;
import web.model.service.file.policies.ArticleContentBackupFilePolicy;
import web.model.service.file.policies.ArticleContentFilePolicy;
import web.model.service.file.policies.ArticleDirectoryPolicy;
import web.model.service.file.policies.ArticleImageDirectoryPolicy;
import web.model.service.file.policies.ArticleImageFilePolicy;
import web.model.service.sign.SignService;
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
	AccountRepo accountRepo;
	
	@Autowired
	TagService tagService;
	
	@Autowired
	SignService signService;
	
	@Transactional
	@Override
	public Article save(Article compositeArticle) throws IOException {
		//find old article
		Article oldArticle = articleRepo.findOne(compositeArticle.getId());
		if(oldArticle != null) {
			createBackupContentFile(oldArticle);
		}
		
		//sync author
		Account author = accountRepo.findOne(compositeArticle.getAuthor().getId());
		author.getAccountSetting();
		compositeArticle.setAuthor(author);

		//write article content file and set its file path
		ArticleDirectoryPolicy afp = new ArticleDirectoryPolicy(compositeArticle);
		fileService.createDirs(afp);
		String fileId = fileService.saveFile(compositeArticle.getContent().getBytes(StandardCharsets.UTF_8),
						new ArticleContentFilePolicy(compositeArticle));

		compositeArticle.setContentFileId(fileId);
		
		Article saved = articleRepo.save(compositeArticle);

		return saved;
	}
	
	private String createBackupContentFile(Article savedArticle) throws IOException{
		String fileId = savedArticle.getContentFileId();
		ArticleContentBackupFilePolicy acbfp = new ArticleContentBackupFilePolicy(savedArticle);
		return fileService.copyFileAs(fileId, acbfp);
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
		article.getTagsArticles();
		
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
			article.setUpdateDateStringAsUpdateTimestamp();
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
	public Page<Article> findRecentArticlesPageByUsername(String username, Pageable pageable) {
		return articleRepo.findRecentArticlesPageByUsername(username, pageable);
	}
	
	private String fileToString(File file) throws IOException {
		byte[] encoded = Files.readAllBytes(file.toPath());
		return new String(encoded, StandardCharsets.UTF_8);
	}

	@Transactional
	@Override
	public String saveArticleImage(MultipartFile uploadedImage, String articleId, Account me) throws IOException {
		Article virtualArticle = new Article();
		virtualArticle.setId(articleId);
		virtualArticle.setAuthor(me);
		
		String imageFileId = UUIDUtil.getUUID();
		String ext = FilenameUtils.getExtension(uploadedImage.getOriginalFilename()).toLowerCase();
		
		ArticleImageDirectoryPolicy aidp = new ArticleImageDirectoryPolicy(virtualArticle);
		fileService.createDirs(aidp);
		
		ArticleImageFilePolicy aifp = new ArticleImageFilePolicy(virtualArticle, imageFileId, ext);
		return fileService.saveFile(uploadedImage, aifp);
	}

	@Override
	public Long countOfArticlesByUsername(String username) {
		return articleRepo.countByUsername(username);
	}

	@Override
	public Article delete(String articleId) {
		Article deletingTarget = articleRepo.findOne(articleId);
		deletingTarget.setIsDel(true);
		articleRepo.save(deletingTarget);
		return deletingTarget;
	}

	


	
}
