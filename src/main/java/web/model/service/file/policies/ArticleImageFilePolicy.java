package web.model.service.file.policies;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import web.model.jpa.entities.Article;

public class ArticleImageFilePolicy implements FilePolicy{

	private String articleImageFilePathString;
	private Path articleImageFilePath;
	
	public ArticleImageFilePolicy (Article article, String imageFileId, String extension) {
		ArticleImageDirectoryPolicy aidp = new ArticleImageDirectoryPolicy(article);
		articleImageFilePathString = aidp.getPathString() + File.separator + imageFileId + "." + extension;
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
