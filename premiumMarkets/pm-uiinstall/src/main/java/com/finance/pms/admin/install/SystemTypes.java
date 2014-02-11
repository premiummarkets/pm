/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
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


public enum SystemTypes {
	
	LINUX ("Linux",".sh",".png", "script file"),
	MAC	("Mac",".command",".png", "command file"),
	WINDOWS ("Windows",".bat",".ico", "Windows batch file");

	private String sys = "Windows";
	private String shext = ".bat";
	private String sdescr = "Windows batch file";
	private String icoext = ".ico";

	
	private SystemTypes(String sys, String shext, String icoext, String sdescr) {
		this.sys = sys;
		this.shext = shext;
		this.icoext = icoext;
		this.sdescr = sdescr;
		
	}

	public static SystemTypes getType(String name) {
		
		if (name != null && (name.toLowerCase().contains(SystemTypes.WINDOWS.getLowerSys()) || name.toLowerCase().contains("win32") || name.toLowerCase().contains("wce")) ) 
			return SystemTypes.WINDOWS;
		
		if (name != null && (name.toLowerCase().contains(SystemTypes.LINUX.getLowerSys()) || name.toLowerCase().contains("sun") || name.toLowerCase().contains("solaris")) ) 
			return SystemTypes.LINUX;
		
		if (name != null && name.toLowerCase().contains(SystemTypes.MAC.getLowerSys())) 
			return SystemTypes.MAC;
		
		return null; //SystemTypes.WINDOWS;
	}


	public String getSys() {
		return sys;
	}
	
	public String getLowerSys() {
		return sys.toLowerCase();
	}

	public String getShext() {
		return shext;
	}

	public String getIcoext() {
		return icoext;
	}

	@Override
	public String toString() {
		return this.sys;
	}


	public String getSdescr() {
		return sdescr;
	}	
}
