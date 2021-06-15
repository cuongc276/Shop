package com.shop.utils;

import org.mindrot.jbcrypt.BCrypt;

public class HashUtil {
	public static String hash(String plain) {
		String salt = BCrypt.gensalt();
		return BCrypt.hashpw(plain, salt);
	}
	
	public static Boolean verify(String password, String hashPassword) {
		return BCrypt.checkpw(password, hashPassword);
	}
}

