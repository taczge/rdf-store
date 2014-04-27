package query;

import java.util.Set;

public class QueryAnswer {

	private final Set<Resolution> answer;
	
	public QueryAnswer(Set<Resolution> answer) {
		super();
		this.answer = answer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
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
		QueryAnswer other = (QueryAnswer) obj;
		if (answer == null) {
			if (other.answer != null) {
				return false;
			}
		} else if (!answer.equals(other.answer)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		final String LS = System.lineSeparator();
		
		for ( final Resolution r : answer ) {
			sb.append(r).append(LS);
		}
		
		return sb.toString();
	}
	
}
