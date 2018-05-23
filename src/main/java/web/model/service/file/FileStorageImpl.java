package web.model.service.file;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.model.jpa.entities.Article;
import web.model.service.file.policies.ArticleFilePolicy;

@Service("fileStorage")
public class FileStorageImpl implements FileStorage{
	
	@Autowired
	FilePathMapService filePathMapService;

	@Override
	public File getFile(String id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public File writeContentFile(Article article) throws IOException {
		ArticleFilePolicy policy = new ArticleFilePolicy(article);
		String contentPath = policy.getArticlePath() + article.getId();
		Files.write(Paths.get(contentPath), article.getContent().getBytes(StandardCharsets.UTF_8));
		return new File(contentPath);
	}

}
