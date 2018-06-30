package web.model.service.file.policies;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import web.model.jpa.entities.Article;

public class NewArticleImageDirectoryPolicy implements NewFilePolicy{

	private String articleImageDirectoryPathString;
	private Path articleImageDirectoryPath;
	
	public NewArticleImageDirectoryPolicy (Article article) {
		NewArticleDirectoryPolicy afp = new NewArticleDirectoryPolicy(article);
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
