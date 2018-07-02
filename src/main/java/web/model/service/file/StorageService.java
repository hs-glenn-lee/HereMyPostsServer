package web.model.service.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public interface StorageService {

	public File makeDirsIfNotExists(Path path) throws IOException;
	
	//--
	public File writeFile(InputStream in, Path path) throws IOException;
	public Path writeFile(byte[] data, Path path) throws IOException;
	public Path copyFile(Path sourcePath,Path targetPath) throws IOException;
	
}
