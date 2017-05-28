package com.robvangastel.kwetter.configuration;

import org.mindrot.jbcrypt.BCrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

	private static String hex(byte[] array) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; ++i) {
			sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString();
	}

	private static String md5Hex(String message) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return hex(md.digest(message.getBytes("CP1252")));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            return null;
		}
	}
	
	public static String setAvatarURL(String email, String defaultImage, int size) {
		return String.format(
					"http://www.gravatar.com/avatar/%s?d=%s&s=%d",
					md5Hex(email),
					defaultImage,
					size
				);
	}

	public static String hashPassword(String pwd) {
		return BCrypt.hashpw(pwd, BCrypt.gensalt());
	}

	public static boolean verifyPassword(String pwd, String hash) {
		return BCrypt.checkpw(pwd, hash);
	}
}
