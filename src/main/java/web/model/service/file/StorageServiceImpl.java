package web.model.service.file;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.model.jpa.entities.Account;
import web.model.jpa.entities.Article;
import web.model.jpa.entities.FilePathMap;
import web.model.service.file.policies.ArticleFilePolicy;
import web.model.service.file.policies.AccountFilePolicy;

/*
 * 얘는 대체 하는게뭐냐 ㅋㅋㅋㅋ
 * 끔찍한 혼종
 * */
@Service("fileStorage")
public class StorageServiceImpl implements StorageService{
	
	@Autowired
	FilePathMapService filePathMapService;

	@Override
	public File getFile(String id) {
		FilePathMap filePathMap = filePathMapService.getFilePathMap(id);
		String path = filePathMap.getPath();
		return new File(path);
	}

	@Override
	public File writeFile(File file, Path path) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public File writeProfilePictureFile(File file, Account account) {
		AccountFilePolicy ufp = new AccountFilePolicy(account);
		Path up = ufp.getUserPath();
		Path picFilePath = ufp.getProfilePicturePath();
		
		makeDirsIfNotExists(up);
		/*file.get
		Files.write(picFilePath, file., options)*/
		
		return null;
	}

	@Override
	public File writeContentFile(Article article) throws IOException {
		ArticleFilePolicy policy = new ArticleFilePolicy(article);
		
		Path articlePath = policy.getArticlePath();
		makeDirsIfNotExists(articlePath);
		
		Path contentPath = policy.getArticleContentFilePath();
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
