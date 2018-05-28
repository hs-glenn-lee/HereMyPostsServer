package web.model.service.file.policies;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import web.model.jpa.entities.Article;

/*
 * Article 과 관련된 파일을 쓸 떄 이 클래스에 명시된 path로 파일을 쓴다.
 * 
 * */
public class ArticleFilePolicy extends FilePolicy{

	private String articleId = null;
	
	private UserFilePolicy userFile = null;
	
	public ArticleFilePolicy(Article article) {
		this.articleId = article.getId();
		this.userFile = new UserFilePolicy(article.getAuthor());
	}

	public String getArticlePathString() {
		return userFile.getUserPathString() + File.separator + this.articleId;
	}
	
	public String getArticleContentFilePathString () {
		return userFile.getUserPathString() + File.separator + this.articleId + File.separator + this.articleId + ".html";
	}
	
	public String getArticleImagePathString() {
		return getArticlePathString() + File.separator + "images";
	}
	
	public Path getArticlePath() {
		return Paths.get(getArticlePathString());
	}
	
	public Path getArticleContentFilePath () {
		return Paths.get(getArticleContentFilePathString());
	}
	
	public Path getArticleImagePath() {
		return Paths.get(getArticleImagePathString());
	}
}
