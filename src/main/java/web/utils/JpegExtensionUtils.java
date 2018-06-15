package web.utils;

import java.util.HashSet;
import java.util.Set;

public class JpegExtensionUtils {
	
	private static final Set<String> JPEG_FILE_EXTENSIONS;
	static {
		JPEG_FILE_EXTENSIONS = new HashSet<String> ();
		JPEG_FILE_EXTENSIONS.add("jpg");
		JPEG_FILE_EXTENSIONS.add("jpeg");
		JPEG_FILE_EXTENSIONS.add("jpe");
		JPEG_FILE_EXTENSIONS.add("jfif");
		JPEG_FILE_EXTENSIONS.add("jif");
	}
	
	/**
	 * if input string ext is possible extension of JPEG file type
	 * return "jpeg"
	 * else return input string as it is.
	 * */
	public static String normalize(String ext) {
		if( JPEG_FILE_EXTENSIONS.contains(ext) ) {
			return "jpeg";
		}else {
			return ext;
		}
	}
}
