package com.finance.pms.events.scoring.functions;

public class Line<XT extends Comparable<XT>, YT extends Comparable<YT>> implements Comparable<Line<XT, YT>>{

	XT xStart;
	XT xEnd;
	YT intersect;
	Double slope;

	public Line() {
		super();
	}

	public XT getxEnd() {
		return xEnd;
	}
	public void setxEnd(XT xEnd) {
		this.xEnd = xEnd;
	}

	public YT getIntersect() {
		return intersect;
	}

	public XT getxStart() {
		return xStart;
	}

	public void setIntersect(XT xStart, YT intersect) {
		this.xStart = xStart;
		this.intersect = intersect;
	}

	public Double getSlope() {
		return slope;
	}

	public void setSlope(Double slope) {
		this.slope = slope;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((intersect == null) ? 0 : intersect.hashCode());
		result = prime * result + ((slope == null) ? 0 : slope.hashCode());
		result = prime * result + ((xEnd == null) ? 0 : xEnd.hashCode());
		result = prime * result + ((xStart == null) ? 0 : xStart.hashCode());
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
		@SuppressWarnings("unchecked")
		Line<XT, YT> other = (Line<XT, YT>) obj;
		if (intersect == null) {
			if (other.intersect != null)
				return false;
		} else if (!intersect.equals(other.intersect))
			return false;
		if (slope == null) {
			if (other.slope != null)
				return false;
		} else if (!slope.equals(other.slope))
			return false;
		if (xEnd == null) {
			if (other.xEnd != null)
				return false;
		} else if (!xEnd.equals(other.xEnd))
			return false;
		if (xStart == null) {
			if (other.xStart != null)
				return false;
		} else if (!xStart.equals(other.xStart))
			return false;
		return true;
	}

	@Override
	public int compareTo(Line<XT, YT> obj) {
		if (this == obj) return 0;
		Line<XT, YT> other = (Line<XT, YT>) obj;
		if (intersect == null) {
			if (other.intersect != null)
				return -1;
		}
		int cmp = intersect.compareTo(other.intersect);
		if (cmp != 0) return cmp;
		if (slope == null) {
			if (other.slope != null)
				return -1;
		}
		cmp = slope.compareTo(other.slope);
		if (cmp != 0) return cmp;
		if (cmp != 0) return cmp;
		if (xEnd == null) {
			if (other.xEnd != null)
				return -1;
		}
		cmp = xEnd.compareTo(other.xEnd);
		if (cmp != 0)
			return cmp;
		if (xStart == null) {
			if (other.xStart != null)
				return -1;
		}
		cmp = xStart.compareTo(other.xStart);
		return cmp;
	}

	public boolean isSet() {
		return xStart != null && xEnd != null && slope != null && intersect != null;
	}

	public void set(Line<XT, YT> otherTangent) {
		this.setIntersect(otherTangent.getxStart(), otherTangent.getIntersect());
		this.setxEnd(otherTangent.getxEnd());
		this.setSlope(otherTangent.getSlope());
	}
}
