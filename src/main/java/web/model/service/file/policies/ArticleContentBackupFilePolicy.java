package web.model.service.file.policies;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import web.model.jpa.entities.Article;

public class ArticleContentBackupFilePolicy implements FilePolicy{

	private String articleContentFileBackupPathString;
	private Path articleCotentFilePath;
	
	public ArticleContentBackupFilePolicy (Article article) {
		ArticleDirectoryPolicy afp = new ArticleDirectoryPolicy(article);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date currentDate = new Date();
		
		articleContentFileBackupPathString = afp.getPathString() + File.separator
												+ article.getId() + "_" + dateFormat.format(currentDate)
												+ ".html";
		articleCotentFilePath = Paths.get(articleContentFileBackupPathString);
	}
	
	@Override
	public Path getPath() {
		return articleCotentFilePath;
	}

	@Override
	public String getPathString() {
		return articleContentFileBackupPathString;
	}

	@Override
	public boolean isDir() {
		return false;
	}
	
}
