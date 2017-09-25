package festival;

import java.awt.RenderingHints.Key;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class PasswordHasher {
	/* TODO: Lag ein skikkeleg funksjon. Den skal under *ingen* omstende brukast i produksjon! */
	public static String hash(String password) throws NoSuchAlgorithmException {
		byte[] phrase = (password + generateSalt().toString()).getBytes(StandardCharsets.UTF_8);
		byte[] hash = MessageDigest.getInstance("SHA-256").digest(phrase);
		return new String(Base64.getEncoder().encode(hash));
	}
	
	private static byte[] generateSalt() {
		SecureRandom rand = new SecureRandom();
		byte bytes[] = new byte[20];
		rand.nextBytes(bytes);
		return bytes;
	}
	
	/*
	private char[] veryFancyMethod(char[] password) {
		try {
			SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			KeySpec ks = new PBEKeySpec(password, generateSalt(), 1024, 128);
			SecretKey s = f.generateSecret(ks);
			SecretKeySpec k = new SecretKeySpec(s.getEncoded(), "AES");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new char[];
	}*/
	
	public static void main(String[] args) {
		try {
			for (int i = 0; i<10;i++) {
				System.out.println(hash("hunter2"));
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}