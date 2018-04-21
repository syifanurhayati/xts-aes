package com.jgc.rca.main;

/**
 * 
 * @author Faridah,Putri, Syifa
 *
 */

import javax.xml.bind.DatatypeConverter;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @author user
 *
 */
public class AES {
	
	private String key;
	
	public AES(byte[] key) {
		this.key = ConverterBytesHex.bytesToHex(key);
	}
	
	/**
	 * 
	 * @param plaintext
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public byte[] encrypt(byte[] plaintext) throws Exception {
		SecretKey secretKey = new SecretKeySpec(DatatypeConverter.parseHexBinary(key), "AES");
		
		Cipher eCipher = Cipher.getInstance("AES/ECB/NoPadding");
		eCipher.init(Cipher.ENCRYPT_MODE, secretKey);
		
		byte[] result = eCipher.doFinal(DatatypeConverter.parseHexBinary(ConverterBytesHex.bytesToHex(plaintext)));
		
		return result;
	}
	
	/**
	 * 
	 * @param ciphertext
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public byte[] decrypt(byte[] ciphertext) throws Exception {
		SecretKey secretKey = new SecretKeySpec(DatatypeConverter.parseHexBinary(key), "AES");
		
		Cipher dCipher = Cipher.getInstance("AES/ECB/NoPadding");
		dCipher.init(Cipher.DECRYPT_MODE, secretKey);
		
		byte[] result = dCipher.doFinal(DatatypeConverter.parseHexBinary(ConverterBytesHex.bytesToHex(ciphertext)));
		return result;
	}
}