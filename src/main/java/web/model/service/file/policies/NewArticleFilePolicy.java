package web.model.service.file.policies;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import web.model.jpa.entities.Article;

public class NewArticleFilePolicy implements NewFilePolicy{

	private String articlePathString;
	private Path articlePath;
	
	public NewArticleFilePolicy (Article article) {
		NewAccountFilePolicy afp = new NewAccountFilePolicy(article.getAuthor());
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
	
}
