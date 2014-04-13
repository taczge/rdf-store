package query.primitive;

import query.Constant;
import query.PrimitiveQuery;
import query.Token;
import query.Variable;

public class PrimitiveQueryFactory {

	private PrimitiveQueryFactory() {}
	
	public static PrimitiveQuery create(Token s, Token p, Token o) {
		final boolean isSVariable = s instanceof Variable; 
		final boolean isPVariable = p instanceof Variable; 
		final boolean isOVariable = o instanceof Variable; 
		
		if ( isSVariable && isPVariable && isOVariable ) {
			return create( (Variable)s, (Variable)p, (Variable)o );
		}
		
		if ( isSVariable && isPVariable ) {
			return create( (Variable)s, (Variable)p, (Constant)o );
		}
		
		if ( isPVariable && isOVariable ) {
			return create( (Constant)s, (Variable)p, (Variable)o );
		}
		
		if ( isSVariable && isOVariable ) {
			return create( (Variable)s, (Constant)p, (Variable)o );
		}
		
		if ( isSVariable ) {
			return create( (Variable)s, (Constant)p, (Constant)o );
		}
		
		if ( isPVariable ) {
			return create( (Constant)s, (Variable)p, (Constant)o );
		}
		
		if ( isOVariable ) {
			return create( (Constant)s, (Constant)p, (Variable)o );
		}
		
		return create( (Constant)s, (Constant)p, (Constant)o );
	}
	
	public static PrimitiveQuery create(Constant s, Constant p, Constant o) {
		return new SPO(s, p, o);
	}

	public static PrimitiveQuery create(Variable s, Constant p, Constant o) {
		return new XPO(s, p, o);
	}

	public static PrimitiveQuery create(Constant s, Variable p, Constant o) {
		return new SXO(s, p, o);
	}

	public static PrimitiveQuery create(Constant s, Constant p, Variable o) {
		return new SPX(s, p, o);
	}

	public static PrimitiveQuery create(Variable s, Variable p, Constant o) {
		return s.equals(p) ? new XXO(s, p, o) : new XYO(s, p, o);
	}
	
	public static PrimitiveQuery create(Constant s, Variable p, Variable o) {
		return p.equals(o) ? new SXX(s, p, o) : new SXY(s, p, o); 
	}

	public static PrimitiveQuery create(Variable s, Constant p, Variable o) {
		return s.equals(o) ? new XPX(s, p, o) : new XPY(s, p, o); 
	}

	public static PrimitiveQuery create(Variable s, Variable p, Variable o) {
		if ( s.equals(p) && p.equals(o) ) return new XXX(s, p, o); 
		
		if ( s.equals(p) ) return new XXY(s, p, o); 
		if ( p.equals(o) ) return new XYY(s, p, o); 
		if ( s.equals(o) ) return new XYX(s, p, o); 
		
		return new XYZ(s, p, o);
	}

}
