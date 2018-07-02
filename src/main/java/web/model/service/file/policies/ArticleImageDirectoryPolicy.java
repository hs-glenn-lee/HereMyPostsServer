package web.model.service.file.policies;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import web.model.jpa.entities.Article;

public class ArticleImageDirectoryPolicy implements FilePolicy{

	private String articleImageDirectoryPathString;
	private Path articleImageDirectoryPath;
	
	public ArticleImageDirectoryPolicy (Article article) {
		ArticleDirectoryPolicy afp = new ArticleDirectoryPolicy(article);
		articleImageDirectoryPathString = afp.getPathString() + File.separator + "image";
		articleImageDirectoryPath = Paths.get(articleImageDirectoryPathString);
	}
	
	@Override
	public Path getPath() {
		return articleImageDirectoryPath;
	}

	@Override
	public String getPathString() {
		return articleImageDirectoryPathString;
	}

	@Override
	public boolean isDir() {
		return true;
	}
	
}
