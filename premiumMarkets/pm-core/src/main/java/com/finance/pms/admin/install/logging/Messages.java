/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With around 80% of forecasted trades above buy and hold, while back testing over DJI, 
 * FTSE, DAX and SBF, Back testing, 
 * Buy sell email notifications with automated markets and user defined portfolios scanning.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview and a free workable demo.
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

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.finance.pms.admin.install.logging.StringEncrypter.EncryptionException;


// TODO: Auto-generated Javadoc
/**
 * The Class Messages.
 * 
 * @author Guillaume Thoreton
 */
public class Messages {

	/** The Constant BUNDLE_NAME. */
	//	private static final String BUNDLE_NAME = "com.finance.pms.admin.install.logging.messages"; 
	private static final String BUNDLE_NAME = "installloggingmessages"; 

	/** The Constant RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);


	/**
	 * Instantiates a new messages.
	 * 
	 * @author Guillaume Thoreton
	 */
	private Messages() {
	}

	/**
	 * Gets the encripted string.
	 * 
	 * @param key the key
	 * 
	 * @return the encripted string
	 */
	public static String getEncriptedString(String key) {

		try {
			String cryptedKey = RESOURCE_BUNDLE.getString(key);
			StringEncrypter encrypter = new StringEncrypter();
			String decryptedString = encrypter.decrypt(cryptedKey);
			return decryptedString;
		} catch (EncryptionException e) {
			System.out.println("Encription failure : "+e);
			return '!' + key + '!';
		} catch (MissingResourceException e) {
			System.out.println("Encription failure : "+e);
			return '!' + key + '!';
		}
	}

}
