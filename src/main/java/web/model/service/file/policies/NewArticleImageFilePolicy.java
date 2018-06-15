package web.model.service.file.policies;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import web.model.jpa.entities.Article;

public class NewArticleImageFilePolicy implements NewFilePolicy{

	private String articleImageFilePathString;
	private Path articleImageFilePath;
	
	public NewArticleImageFilePolicy (Article article, String imageFileId, String extension) {
		NewArticleFilePolicy afp = new NewArticleFilePolicy(article);
		articleImageFilePathString = afp.getPathString() + File.separator + "images" + File.separator + imageFileId + "." + extension;
		articleImageFilePath = Paths.get(articleImageFilePathString);
	}
	
	@Override
	public Path getPath() {
		return articleImageFilePath;
	}

	@Override
	public String getPathString() {
		return articleImageFilePathString;
	}

	@Override
	public boolean isDir() {
		return false;
	}
	
}
