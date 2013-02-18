package com.finance.pms.datasources;

import com.finance.pms.portfolio.InfoObject;

public class ShareListInfo implements InfoObject {
	
	String shareListName;

	public ShareListInfo(String shareListName) {
		super();
		this.shareListName = shareListName;
	}

	@Override
	public String info() {
		return shareListName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((shareListName == null) ? 0 : shareListName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShareListInfo other = (ShareListInfo) obj;
		if (shareListName == null) {
			if (other.shareListName != null)
				return false;
		} else if (!shareListName.equals(other.shareListName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ShareListInfo [shareListName=" + shareListName + "]";
	}
	
	
	
}