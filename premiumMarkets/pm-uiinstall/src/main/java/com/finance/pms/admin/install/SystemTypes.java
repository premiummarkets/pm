/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
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
package com.finance.pms.admin.install;

// TODO: Auto-generated Javadoc
/**
 * The Enum SystemTypes.
 * 
 * @author Guillaume Thoreton
 */
public enum SystemTypes {
	
	LINUX ("Linux",".sh",".png"),
	MAC	("Mac","_mac.sh",".png"),
	WINDOWS ("Windows",".bat",".ico");

	private String sys = "Windows";
	private String shext = ".bat";
	private String icoext = ".ico";

	
	/**
	 * Instantiates a new system types.
	 * 
	 * @param sys the sys
	 * @param shext the shext
	 * @param icoext the icoext
	 * @param swtjar the swtjar
	 * 
	 * @author Guillaume Thoreton
	 */
	private SystemTypes(String sys, String shext, String icoext) {
		this.sys = sys;
		this.shext = shext;
		this.icoext = icoext;
		
	}


	/**
	 * Gets the type.
	 * 
	 * @param name the name
	 * 
	 * @return the type
	 */
	public static SystemTypes getType(String name) {
		
		if (name != null && name.toLowerCase().contains(SystemTypes.WINDOWS.getLowerSys())) 
			return SystemTypes.WINDOWS;
		
		if (name != null && name.toLowerCase().contains(SystemTypes.LINUX.getLowerSys())) 
			return SystemTypes.LINUX;
		
		if (name != null && name.toLowerCase().contains(SystemTypes.MAC.getLowerSys())) 
			return SystemTypes.MAC;
		
		return SystemTypes.LINUX;
	}


	/**
	 * Gets the sys.
	 * 
	 * @return the sys
	 */
	public String getSys() {
		return sys;
	}
	
	public String getLowerSys() {
		return sys.toLowerCase();
	}

	/**
	 * Gets the shext.
	 * 
	 * @return the shext
	 */
	public String getShext() {
		return shext;
	}


	/**
	 * Gets the icoext.
	 * 
	 * @return the icoext
	 */
	public String getIcoext() {
		return icoext;
	}


	@Override
	public String toString() {
		return this.sys;
	}	
}
