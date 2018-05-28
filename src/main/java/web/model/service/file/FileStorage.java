package web.model.service.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import web.model.jpa.entities.Article;

public interface FileStorage {
	public File getFile(String id);
	public File writeContentFile(Article article) throws IOException;
	public File makeDirsIfNotExists(Path path) throws IOException;
}
