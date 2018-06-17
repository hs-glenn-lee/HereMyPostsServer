package web.model.service.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;

import web.model.service.file.policies.NewFilePolicy;

public interface FileService {
	public String saveFile(MultipartFile mFile, NewFilePolicy filePolicy) throws IOException;
	public String saveFile(byte[] data, NewFilePolicy filePolicy) throws IOException;
	
	public void createDirs(NewFilePolicy filePolicy) throws IOException;
	
	public File getFile(String fileId);
	public Path getPath(String fileId);
	public void deleteFile(String fileId) throws IOException;
	public void updateFile(MultipartFile mFile, String oldFileId) throws IOException;
}
