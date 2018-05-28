package web.model.service.file;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.model.jpa.entities.Article;
import web.model.jpa.entities.FilePathMap;
import web.model.service.file.policies.ArticleFilePolicy;

@Service("fileStorage")
public class FileStorageImpl implements FileStorage{
	
	@Autowired
	FilePathMapService filePathMapService;

	@Override
	public File getFile(String id) {
		FilePathMap filePathMap = filePathMapService.getFilePathMap(id);
		String path = filePathMap.getPath();
		return new File(path);
	}


	@Override
	public File writeContentFile(Article article) throws IOException {
		ArticleFilePolicy policy = new ArticleFilePolicy(article);
		
		Path articlePath = policy.getArticlePath();
		makeDirsIfNotExists(articlePath);
		
		Path contentPath = policy.getArticleContentFilePath();
		
		System.out.println(article.getContent());
		Files.write(contentPath, article.getContent().getBytes(StandardCharsets.UTF_8));
		
		article.setContentFilePath(contentPath.toString());
		
		return contentPath.toFile();
	}


	@Override
	public File makeDirsIfNotExists(Path path) throws SecurityException {//todo the parameter path must be directory path. if it is not operation will work as expected.
		File asFile = path.toFile();
		if(!asFile.exists())
			path.toFile().mkdirs();
		
		return asFile;
	}

}
