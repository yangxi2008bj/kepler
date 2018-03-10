package com.lachesis.windranger.common.codec;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import java.util.Map;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-2-26
 * <p>
 * Version: 1.0
 */
@SuppressWarnings("restriction")
public class HmacSHA256Utils {
	public static final String KEY_SHA = "SHA";
	public static final String KEY_MD5 = "MD5";
	public static final String KEY_MAC = "HMACMD5";

	public static String digest(String key, String content) {
		try {
			Mac mac = Mac.getInstance("HmacSHA256");
			byte[] secretByte = key.getBytes("utf-8");
			byte[] dataBytes = content.getBytes("utf-8");

			SecretKey secret = new SecretKeySpec(secretByte, "HMACSHA256");
			mac.init(secret);

			byte[] doFinal = mac.doFinal(dataBytes);
			byte[] hexB = new Hex().encode(doFinal);
			return new String(hexB, "utf-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static String digest(String key, Map<String, ?> map) {
		StringBuilder s = new StringBuilder();
		for (Object values : map.values()) {
			if (values instanceof String[]) {
				for (String value : (String[]) values) {
					s.append(value);
				}
			} else if (values instanceof List) {
				for (String value : (List<String>) values) {
					s.append(value);
				}
			} else {
				s.append(values);
			}
		}
		return digest(key, s.toString());
	}

	/**
	 * BASE64解密
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}

	// BASE64加密
	public static String encryptBASE64(byte[] key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key);
	}

	// MD5加密
	public static byte[] encryptMD5(byte[] data) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
		md5.update(data);

		return md5.digest();
	}

	// SHA加密
	public static byte[] encryptSHA(byte[] data) throws Exception {
		MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
		sha.update(data);

		return sha.digest();
	}

	// 初始化HMAC密钥
	public static String initMacKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);

		SecretKey secretKey = keyGenerator.generateKey();

		return encryptBASE64(secretKey.getEncoded());
	}

	// HMAC加密
	public static byte[] encryptHMAC(byte[] data, String key) throws Exception {
		SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);

		return mac.doFinal();
	}

	
	public static String encryptMD5App(String input) {
		byte[] inputData = input.getBytes();
		BigInteger md5;
		try {
			md5 = new BigInteger(encryptMD5(inputData));
			return md5.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) throws Exception {
		String inputStr = "admin";
		
		System.out.println("MD5:\n" + encryptMD5App(inputStr));
	}
}
