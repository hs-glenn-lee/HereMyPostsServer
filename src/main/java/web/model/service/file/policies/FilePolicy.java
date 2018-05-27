package web.model.service.file.policies;

import java.io.File;

/*
 * 파일을 쓸 떄 이 클래스에 명시된 path로 파일을 쓴다.
 * 
 * */
public class FilePolicy {
	private static String ROOT_PATH = "/usr/heremyposts/user-files";
	
	static {
		if( !checkRootDirectoryExist() ) {
			makeRootDirectory();
		}
	}
	
	public static boolean checkRootDirectoryExist () {
		File f = new File(ROOT_PATH);
		if(f.exists()) {
			return true;
		}else {
			return false;
		}		
	}
	
	public static void makeRootDirectory () {
		
		
	}
	
	
	public String getRootPath() {
		return this.ROOT_PATH;
	}
}
