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

import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class StringEncrypterTest.
 * 
 * @author Guillaume Thoreton
 */
public class StringEncrypterTest extends TestCase {

	/**
	 * Test encryption key is defined.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void testEncryptionKeyIsDefined() {
		assertEquals(StringEncrypter.DEFAULT_ENCRYPTION_KEY, "This is a fairly long phrase used to encrypt");
	}

	/**
	 * Test throws error on invalid key spec.
	 * 
	 * @throws Exception the exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public void testThrowsErrorOnInvalidKeySpec() throws Exception {
		String encryptionScheme = "asdf";
		String encryptionKey = "123456789012345678901234567890";

		try {
			new StringEncrypter(encryptionScheme, encryptionKey);
		} catch (IllegalArgumentException e) {
			assertEquals("Encryption scheme not supported: asdf", e.getMessage());
		}
	}

	/**
	 * Test encrypts using des ede.
	 * 
	 * @throws Exception the exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public void testEncryptsUsingDesEde() throws Exception {
		String stringToEncrypt = "test";
		String encryptionKey = "123456789012345678901234567890";
		String encryptionScheme = StringEncrypter.DESEDE_ENCRYPTION_SCHEME;

		StringEncrypter encrypter = new StringEncrypter(encryptionScheme, encryptionKey);
		String encryptedString = encrypter.encrypt(stringToEncrypt);

		assertEquals("Ni2Bih3nCUU=", encryptedString);
	}

	/**
	 * Test encrypts using des.
	 * 
	 * @throws Exception the exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public void testEncryptsUsingDes() throws Exception {
		String stringToEncrypt = "test";
		String encryptionKey = "123456789012345678901234567890";
		String encryptionScheme = StringEncrypter.DES_ENCRYPTION_SCHEME;

		StringEncrypter encrypter = new StringEncrypter(encryptionScheme, encryptionKey);
		String encryptedString = encrypter.encrypt(stringToEncrypt);

		assertEquals("oEtoaxGK9ns=", encryptedString);
	}

	/**
	 * Test encryption key can contain letters.
	 * 
	 * @throws Exception the exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public void testEncryptionKeyCanContainLetters() throws Exception {
		String string = "test";
		String encryptionKey = "ASDF asdf 1234 8983 jklasdf J2Jaf8";
		String encryptionScheme = StringEncrypter.DESEDE_ENCRYPTION_SCHEME;

		StringEncrypter encrypter = new StringEncrypter(encryptionScheme, encryptionKey);
		String encryptedString = encrypter.encrypt(string);

		assertEquals("Q+UyPrxdge0=", encryptedString);
	}

	/**
	 * Test decrypts using des ede.
	 * 
	 * @throws Exception the exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public void testDecryptsUsingDesEde() throws Exception {
		String string = "Ni2Bih3nCUU=";
		String encryptionKey = "123456789012345678901234567890";
		String encryptionScheme = StringEncrypter.DESEDE_ENCRYPTION_SCHEME;

		StringEncrypter encrypter = new StringEncrypter(encryptionScheme, encryptionKey);
		String decryptedString = encrypter.decrypt(string);

		assertEquals("test", decryptedString);
	}

	/**
	 * Test decrypts using des.
	 * 
	 * @throws Exception the exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public void testDecryptsUsingDes() throws Exception {
		String string = "oEtoaxGK9ns=";
		String encryptionKey = "123456789012345678901234567890";
		String encryptionScheme = StringEncrypter.DES_ENCRYPTION_SCHEME;

		StringEncrypter encrypter = new StringEncrypter(encryptionScheme, encryptionKey);
		String decryptedString = encrypter.decrypt(string);

		assertEquals("test", decryptedString);
	}

	/**
	 * Test cant instantiate with null encryption key.
	 * 
	 * @throws Exception the exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public void testCantInstantiateWithNullEncryptionKey() throws Exception {
		try {
			String encryptionScheme = StringEncrypter.DESEDE_ENCRYPTION_SCHEME;
			String encryptionKey = null;

			new StringEncrypter(encryptionScheme, encryptionKey);
		} catch (IllegalArgumentException e) {
			assertEquals("encryption key was null", e.getMessage());
		}

	}

	/**
	 * Test cant instantiate with empty encryption key.
	 * 
	 * @throws Exception the exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public void testCantInstantiateWithEmptyEncryptionKey() throws Exception {
		try {
			String encryptionScheme = StringEncrypter.DESEDE_ENCRYPTION_SCHEME;
			String encryptionKey = "";

			new StringEncrypter(encryptionScheme, encryptionKey);
		} catch (IllegalArgumentException e) {
			assertEquals("encryption key was less than 24 characters", e.getMessage());
		}

	}

	/**
	 * Test cant decrypt with null string.
	 * 
	 * @throws Exception the exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public void testCantDecryptWithNullString() throws Exception {
		try {
			String encryptionScheme = StringEncrypter.DESEDE_ENCRYPTION_SCHEME;
			String encryptionKey = "123456789012345678901234";

			StringEncrypter encrypter = new StringEncrypter(encryptionScheme, encryptionKey);
			encrypter.decrypt(null);
		} catch (IllegalArgumentException e) {
			assertEquals("encrypted string was null or empty", e.getMessage());
		}

	}

	/**
	 * Test cant decrypt with empty string.
	 * 
	 * @throws Exception the exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public void testCantDecryptWithEmptyString() throws Exception {
		try {
			String encryptionScheme = StringEncrypter.DESEDE_ENCRYPTION_SCHEME;
			String encryptionKey = "123456789012345678901234";

			StringEncrypter encrypter = new StringEncrypter(encryptionScheme, encryptionKey);
			encrypter.decrypt("");
		} catch (IllegalArgumentException e) {
			assertEquals("encrypted string was null or empty", e.getMessage());
		}

	}

	/**
	 * Test cant encrypt with null string.
	 * 
	 * @throws Exception the exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public void testCantEncryptWithNullString() throws Exception {
		try {
			String encryptionScheme = StringEncrypter.DESEDE_ENCRYPTION_SCHEME;
			String encryptionKey = "123456789012345678901234";

			StringEncrypter encrypter = new StringEncrypter(encryptionScheme, encryptionKey);
			encrypter.encrypt(null);
		} catch (IllegalArgumentException e) {
			assertEquals("unencrypted string was null or empty", e.getMessage());
		}

	}

	/**
	 * Test cant encrypt with empty string.
	 * 
	 * @throws Exception the exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public void testCantEncryptWithEmptyString() throws Exception {
		try {
			String encryptionScheme = StringEncrypter.DESEDE_ENCRYPTION_SCHEME;
			String encryptionKey = "123456789012345678901234";

			StringEncrypter encrypter = new StringEncrypter(encryptionScheme, encryptionKey);
			encrypter.encrypt("");
		} catch (IllegalArgumentException e) {
			assertEquals("unencrypted string was null or empty", e.getMessage());
		}

	}
}