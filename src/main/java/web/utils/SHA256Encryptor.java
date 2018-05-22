package web.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Encryptor {
	
	private static MessageDigest SHA256_MESSAGE_DIGEST;
	
	static {
		try {
			SHA256_MESSAGE_DIGEST = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String encrypt(String plainString) {

		SHA256_MESSAGE_DIGEST.update(plainString.getBytes()); 
		byte byteData[] = SHA256_MESSAGE_DIGEST.digest();
		StringBuffer sb = new StringBuffer(); 

		for(int i = 0 ; i < byteData.length ; i++){
			sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
		}
		String encryptedString = sb.toString();

		return encryptedString;
	}
	
}
