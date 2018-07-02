package web.model.service.file.policies;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import web.model.jpa.entities.Article;

public class ArticleContentFilePolicy implements FilePolicy{

	private String articleContentFilePathString;
	private Path articleCotentFilePath;
	
	public ArticleContentFilePolicy (Article article) {
		ArticleDirectoryPolicy afp = new ArticleDirectoryPolicy(article);
		articleContentFilePathString = afp.getPathString() + File.separator + article.getId() + ".html";
		articleCotentFilePath = Paths.get(articleContentFilePathString);
	}
	
	@Override
	public Path getPath() {
		return articleCotentFilePath;
	}

	@Override
	public String getPathString() {
		return articleContentFilePathString;
	}

	@Override
	public boolean isDir() {
		return false;
	}
	
}
