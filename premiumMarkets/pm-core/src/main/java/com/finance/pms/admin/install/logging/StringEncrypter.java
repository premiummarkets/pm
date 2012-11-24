/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * Around 80% of predicted trades more profitable than buy and hold, leading to 4 times 
 * more profit, while back testing over NYSE, NASDAQ, EURONEXT and LSE, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2012 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.admin.install.logging;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

// TODO: Auto-generated Javadoc
/**
 * The Class StringEncrypter.
 * 
 * @author Guillaume Thoreton
 */
public class StringEncrypter {

	/** The Constant DESEDE_ENCRYPTION_SCHEME. */
	public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
	
	/** The Constant DES_ENCRYPTION_SCHEME. */
	public static final String DES_ENCRYPTION_SCHEME = "DES";
	
	/** The Constant DEFAULT_ENCRYPTION_KEY. */
	public static final String DEFAULT_ENCRYPTION_KEY = "Chez les Papous il y a des Papous papas, des Papous pas papas, des Papous � poux et des Papous pas � poux.";

	/** The key spec. */
	private KeySpec keySpec;
	
	/** The key factory. */
	private SecretKeyFactory keyFactory;
	
	/** The cipher. */
	private Cipher cipher;

	/** The Constant UNICODE_FORMAT. */
	private static final String UNICODE_FORMAT = "UTF8";
	
	

	/**
	 * Instantiates a new string encrypter.
	 * 
	 * @throws EncryptionException the encryption exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public StringEncrypter() throws EncryptionException  {
		this(DES_ENCRYPTION_SCHEME, DEFAULT_ENCRYPTION_KEY);
	}

	/**
	 * Instantiates a new string encrypter.
	 * 
	 * @param encryptionScheme the encryption scheme
	 * @param encryptionKey the encryption key
	 * 
	 * @throws EncryptionException the encryption exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public StringEncrypter(String encryptionScheme, String encryptionKey) throws EncryptionException {

		if (encryptionKey == null)
			throw new IllegalArgumentException("Encryption key was null");
		if (encryptionKey.trim().length() < 24)
			throw new IllegalArgumentException("Encryption key was less than 24 characters");

		try {
			byte[] keyAsBytes = encryptionKey.getBytes(UNICODE_FORMAT);

			if (encryptionScheme.equals(DESEDE_ENCRYPTION_SCHEME)) {
				keySpec = new DESedeKeySpec(keyAsBytes);
			} else if (encryptionScheme.equals(DES_ENCRYPTION_SCHEME)) {
				keySpec = new DESKeySpec(keyAsBytes);
			} else {
				throw new IllegalArgumentException("Encryption scheme not supported: " + encryptionScheme);
			}

			keyFactory = SecretKeyFactory.getInstance(encryptionScheme);
			cipher = Cipher.getInstance(encryptionScheme);

		} catch (InvalidKeyException e) {
			throw new EncryptionException(e);
		} catch (UnsupportedEncodingException e) {
			throw new EncryptionException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new EncryptionException(e);
		} catch (NoSuchPaddingException e) {
			throw new EncryptionException(e);
		}

	}

	/**
	 * Encrypt.
	 * 
	 * @param unencryptedString the unencrypted string
	 * 
	 * @return the string
	 * 
	 * @throws EncryptionException the encryption exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public String encrypt(String unencryptedString) throws EncryptionException {
		if (unencryptedString == null || unencryptedString.trim().length() == 0)
			throw new IllegalArgumentException("unencrypted string was null or empty");

		try {
			SecretKey key = keyFactory.generateSecret(keySpec);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] cleartext = unencryptedString.getBytes(UNICODE_FORMAT);
			byte[] ciphertext = cipher.doFinal(cleartext);

			BASE64Encoder base64encoder = new BASE64Encoder();
			return base64encoder.encode(ciphertext);
		} catch (Exception e) {
			throw new EncryptionException(e);
		}
	}

	/**
	 * Decrypt.
	 * 
	 * @param encryptedString the encrypted string
	 * 
	 * @return the string
	 * 
	 * @throws EncryptionException the encryption exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public String decrypt(String encryptedString) throws EncryptionException {
		if (encryptedString == null || encryptedString.trim().length() <= 0)
			throw new IllegalArgumentException("encrypted string was null or empty");

		try {
			SecretKey key = keyFactory.generateSecret(keySpec);
			cipher.init(Cipher.DECRYPT_MODE, key);
			BASE64Decoder base64decoder = new BASE64Decoder();
			byte[] cleartext = base64decoder.decodeBuffer(encryptedString);
			byte[] ciphertext = cipher.doFinal(cleartext);

			return bytes2String(ciphertext);
		} catch (Exception e) {
			throw new EncryptionException(e);
		}
	}

	/**
	 * Bytes2 string.
	 * 
	 * @param bytes the bytes
	 * 
	 * @return the string
	 * 
	 * @author Guillaume Thoreton
	 */
	private static String bytes2String(byte[] bytes) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			stringBuffer.append((char) bytes[i]);
		}
		return stringBuffer.toString();
	}

	/**
	 * The Class EncryptionException.
	 * 
	 * @author Guillaume Thoreton
	 */
	public static class EncryptionException extends Exception {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 5326641184977052246L;

		/**
		 * Instantiates a new encryption exception.
		 * 
		 * @param t the t
		 * 
		 * @author Guillaume Thoreton
		 */
		public EncryptionException(Throwable t) {
			super(t);
		}
	}
	
	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 * 
	 * @author Guillaume Thoreton
	 */
	public static void main(String... args) {
		
		try {
			String bundle_name = args[0];//"com.finance.pms.admin.install.logging.messages"; 
			ResourceBundle resource_bundle = ResourceBundle.getBundle(bundle_name);
			Enumeration<String> keys = resource_bundle.getKeys();
			StringEncrypter encrypter = new StringEncrypter();
			
			while (keys.hasMoreElements()) {
				String key = keys.nextElement();
				String msg = resource_bundle.getString(key);
				System.out.println(key+"="+encrypter.encrypt(msg));
			}
		} catch (EncryptionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}