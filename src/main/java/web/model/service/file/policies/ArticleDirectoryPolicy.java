package web.model.service.file.policies;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import web.model.jpa.entities.Article;

public class ArticleDirectoryPolicy implements FilePolicy{

	private String articlePathString;
	private Path articlePath;
	
	public ArticleDirectoryPolicy (Article article) {
		AccountFilePolicy afp = new AccountFilePolicy(article.getAuthor());
		articlePathString = afp.getPathString() + File.separator + article.getId();
		articlePath = Paths.get(articlePathString);
	}
	
	@Override
	public Path getPath() {
		return articlePath;
	}

	@Override
	public String getPathString() {
		return articlePathString;
	}

	@Override
	public boolean isDir() {
		return true;
	}
	
}
