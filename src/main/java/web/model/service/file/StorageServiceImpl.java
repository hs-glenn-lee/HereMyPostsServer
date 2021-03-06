package web.model.service.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;

/*
 * 얘는 대체 하는게뭐냐 ㅋㅋㅋㅋ
 * 끔찍한 혼종
 * */
@Service("storageService")
public class StorageServiceImpl implements StorageService{

	@Override
	public File makeDirsIfNotExists(Path path) throws SecurityException {//todo the parameter path must be directory path. if it is not operation will work as expected.
		File asFile = path.toFile();
		if(!asFile.exists())
			path.toFile().mkdirs();
		
		return asFile;
	}

	@Override
	public File writeFile(InputStream in, Path path) throws IOException {
		byte[] buf = new byte[1024];
		File file = path.toFile();
		FileOutputStream fos = new FileOutputStream(file);
		try {
			while(in.read(buf) > 0) {
				fos.write(buf);
			}
			fos.flush();
		}catch(IOException ioe) {
			throw ioe;
		}finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return file;
	}
	
	
	@Override
	public Path writeFile(byte[] data, Path path) throws IOException {
		return Files.write(path, data);
	}

	@Override
	public Path copyFile(Path sourcePath, Path targetPath) throws IOException {
		Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
		return targetPath;
	}

}
