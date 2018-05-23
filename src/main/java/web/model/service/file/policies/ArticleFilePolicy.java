package web.model.service.file.policies;

import java.io.File;

import web.model.jpa.entities.Article;

/*
 * Article 과 관련된 파일을 쓸 떄 이 클래스에 명시된 path로 파일을 쓴다.
 * 
 * */
public class ArticleFilePolicy extends FilePolicy{

	private String articleId = null;
	
	private Long userId = null;
	
	public ArticleFilePolicy(Article article) {
		this.articleId = article.getId();
		this.userId = article.getAuthor().getId();
	}
	
	public String getArticlePath() {
		return super.getRootPath() + File.separator + this.userId + File.separator + this.articleId;
	}
	
	public String getArticleImagePath() {
		return super.getRootPath() + File.separator
				+ this.userId + File.separator
				+ this.articleId + File.separator
				+ "images";
	}
	
}
