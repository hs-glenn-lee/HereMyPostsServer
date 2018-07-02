package web.model.service.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;

import web.model.service.file.policies.FilePolicy;

public interface FileService {
	public String saveFile(MultipartFile mFile, FilePolicy filePolicy) throws IOException;
	public String saveFile(byte[] data, FilePolicy filePolicy) throws IOException;
	public String copyFileAs(String id, FilePolicy filePolicy) throws IOException;
	
	public void createDirs(FilePolicy filePolicy) throws IOException;
	
	public File getFile(String fileId);
	public Path getPath(String fileId);
	public void deleteFile(String fileId) throws IOException;
	public void updateFile(MultipartFile mFile, String oldFileId) throws IOException;
}
