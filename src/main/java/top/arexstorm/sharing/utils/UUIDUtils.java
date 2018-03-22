package top.arexstorm.sharing.utils;

import java.util.UUID;

public class UUIDUtils {
	
	public static String generateUUIDString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static void main(String[] args) {
		String str1 = UUIDUtils.generateUUIDString();
		System.out.println(str1);
		String str2 = UUIDUtils.generateUUIDString();
		System.out.println(str2);
		System.out.println(str2.length());
	}
}
