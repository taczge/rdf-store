package query.primitive;

import query.Constant;
import query.PrimitiveQuery;
import query.QueryTarget;
import query.Resolution;
import query.Substitution;

public class CCCPrimitive implements PrimitiveQuery {
	
	private final Constant s;
	private final Constant p;
	private final Constant o;
	
	public CCCPrimitive(Constant s, Constant p, Constant o) {
		super();
		this.s = s;
		this.p = p;
		this.o = o;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((o == null) ? 0 : o.hashCode());
		result = prime * result + ((p == null) ? 0 : p.hashCode());
		result = prime * result + ((s == null) ? 0 : s.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CCCPrimitive other = (CCCPrimitive) obj;
		if (o == null) {
			if (other.o != null) {
				return false;
			}
		} else if (!o.equals(other.o)) {
			return false;
		}
		if (p == null) {
			if (other.p != null) {
				return false;
			}
		} else if (!p.equals(other.p)) {
			return false;
		}
		if (s == null) {
			if (other.s != null) {
				return false;
			}
		} else if (!s.equals(other.s)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s,%s,%s.", s, p, o);
	}

	@Override
	public Resolution solve(QueryTarget target) {
		return target.contains(s, p, o) ? Resolution.SUCCESS : Resolution.FAILURE;
	}

	@Override
	public PrimitiveQuery apply(Substitution substitusion) {
		return this;
	}

}
