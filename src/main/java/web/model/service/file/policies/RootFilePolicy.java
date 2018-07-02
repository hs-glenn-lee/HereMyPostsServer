package web.model.service.file.policies;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RootFilePolicy implements FilePolicy{
	private static final String USER_FILES_ROOT_PATH_STRING = "/usr/heremyposts/user-files";
	private static final Path USER_FILES_ROOT_PATH = Paths.get(USER_FILES_ROOT_PATH_STRING);
	
	static {//this block will called when this class loaded
		if( !checkRootDirectoryExist() ) {
			makeDirs(Paths.get(USER_FILES_ROOT_PATH_STRING));
		}
	}
	
	public static boolean checkRootDirectoryExist () {
		File f = new File(USER_FILES_ROOT_PATH_STRING);
		if(f.exists()) {
			return true;
		}else {
			return false;
		}		
	}
	
	private static void makeDirs(Path path) {
		File asFile = path.toFile();
		if(!asFile.exists()) {
			asFile.mkdirs();
		}
	}
	
	public RootFilePolicy() {}
	
	
	@Override
	public Path getPath() {
		return USER_FILES_ROOT_PATH;
	}

	@Override
	public String getPathString() {
		return USER_FILES_ROOT_PATH_STRING;
	}

	@Override
	public boolean isDir() {
		return true;
	}
}
