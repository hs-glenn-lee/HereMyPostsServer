package web.model.service.file.policies;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * 파일을 쓸 떄 이 클래스에 명시된 path로 파일을 쓴다.
 * 
 * */
public class FilePolicy {
	private static String USER_FILES_ROOT_PATH = "/usr/heremyposts/user-files";
	
	static {//this block will called when this class loaded
		if( !checkRootDirectoryExist() ) {
			makeDirs(Paths.get(USER_FILES_ROOT_PATH));
		}
	}
	
	public static boolean checkRootDirectoryExist () {
		File f = new File(USER_FILES_ROOT_PATH);
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

	
	
	public String getRootPathString() {
		return this.USER_FILES_ROOT_PATH;
	}
	
	public Path getRootPath() {
		return Paths.get(getRootPathString());
	}
	


}
