package top.arexstorm.sharing.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;



public class PasswordUtils {

	public static String getEncodedPassword(String password) {
		//加盐
		//password = "wdsfscz" + password + "asdfaser";
		
		//md5加密
		String algorithm = "MD5";
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			byte[] md5Password = digest.digest(password.getBytes());
			
			//十六进制
			Hex hex = new Hex();
			char[] hexPassword = hex.encodeHex(md5Password);
			String encodedPassword = new String(hexPassword);
			
			return encodedPassword;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		String encodedPassword = PasswordUtils.getEncodedPassword("123456");
		System.out.println(encodedPassword);
	}
}
