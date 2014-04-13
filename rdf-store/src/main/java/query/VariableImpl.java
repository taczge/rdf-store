package query;

public class VariableImpl implements Variable {
	
	public static Variable of(String idendifier) {
		return new VariableImpl(idendifier);
	}
	
	private final String idendifier;
	
	public VariableImpl(String idendifier) {
		super();
		this.idendifier = idendifier;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idendifier == null) ? 0 : idendifier.hashCode());
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
		VariableImpl other = (VariableImpl) obj;
		if (idendifier == null) {
			if (other.idendifier != null) {
				return false;
			}
		} else if (!idendifier.equals(other.idendifier)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return idendifier;
	}

	@Override
	public Token apply(Substitution s) {
		return s.contains(this) ? s.getAssignedValue(this) : this;
	}

}
