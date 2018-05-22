package web.utils;

public class UUIDUtil {
	
	public static String getUUID() {
		String uuid = java.util.UUID.randomUUID().toString().replace("-", "");
		return uuid;
	}
}
