package ch.interlis.iox_j.validator;

import ch.interlis.iom.IomObject;
import java.util.Arrays;
import java.util.List;

/**
 * Composite key used for UNIQUE constraint checking.
 * Normalizes IomObject reference wrappers (REF) to stable scalars
 * so duplicates are reliably detected (incl. EXTERNAL roles across baskets).
 *
 * API is compatible with older Validator code:
 *  - ctor accepts List (e.g., ArrayList<Object>)
 *  - valuesAsString() for log output
 *  - Java 1.6 compatible (no java.util.Objects)
 */
final class AttributeArray {
    private final Object[] values;
    private final int hash; // cached for maps/sets

    /** Backward-compatible ctor: accept List<?> (e.g., ArrayList<Object>). */
    AttributeArray(List<?> rawValues) {
        if (rawValues == null) {
            this.values = new Object[0];
        } else {
            this.values = new Object[rawValues.size()];
            for (int i = 0; i < rawValues.size(); i++) {
                this.values[i] = normalizeKeyPart(rawValues.get(i));
            }
        }
        this.hash = Arrays.hashCode(this.values);
    }

    /** Also keep array-based ctor (used in a few call sites). */
    AttributeArray(Object[] rawValues) {
        if (rawValues == null) {
            this.values = new Object[0];
        } else {
            this.values = new Object[rawValues.length];
            for (int i = 0; i < rawValues.length; i++) {
                this.values[i] = normalizeKeyPart(rawValues[i]);
            }
        }
        this.hash = Arrays.hashCode(this.values);
    }

    /** Turn any reference-like IomObject into a stable scalar (bid:oid / oid). */
    private static Object normalizeKeyPart(Object v) {
        if (v == null) return null;

        if (v instanceof IomObject) {
            IomObject io = (IomObject) v;

            // 1) REF wrapper? -> use bid:oid (or oid)
            // Available directly on the wrapper without dereferencing.
            try {
                String oid = safe(io.getobjectrefoid());
                String bid = safe(io.getobjectrefbid());
                if (oid != null) {
                    return (bid != null && bid.length() > 0) ? (bid + ":" + oid) : oid;
                }
            } catch (Throwable ignored) {
                // not a REF wrapper in this build; fall through
            }

            // 2) Object with its own OID? -> use that OID
            try {
                String oid2 = safe(io.getobjectoid());
                if (oid2 != null) return oid2;
            } catch (Throwable ignored) {
                // fall through
            }

            // 3) Deterministic textual fallback: tag + simple attributes (order-independent)
            StringBuilder sb = new StringBuilder();
            try {
                String tag = io.getobjecttag();
                if (tag != null) sb.append(tag);
                int attrc = io.getattrcount();
                String[] parts = new String[attrc];
                for (int i = 0; i < attrc; i++) {
                    String an = io.getattrname(i);
                    String av = io.getattrvalue(an);
                    parts[i] = (an == null ? "" : an) + "=" + (av == null ? "" : av);
                }
                Arrays.sort(parts);
                for (int i = 0; i < parts.length; i++) {
                    sb.append('|').append(parts[i]);
                }
            } catch (Throwable ignored) {
                // As a last resort, use toString (still better than identity semantics).
                return String.valueOf(v);
            }
            return sb.toString();
        }

        // Strings, numbers, booleans are already stable.
        return v;
    }

    private static String safe(String s) {
        return (s == null || s.length() == 0) ? null : s;
    }

    /** Used by Validator for human-readable logging. */
    String valuesAsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if (i > 0) sb.append(", ");
            Object v = values[i];
            sb.append(v == null ? "null" : String.valueOf(v));
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AttributeArray)) return false;
        AttributeArray that = (AttributeArray) o;
        return Arrays.equals(this.values, that.values);
    }

    @Override
    public int hashCode() {
        return hash;
    }

    @Override
    public String toString() {
        return Arrays.toString(values);
    }
}



// package ch.interlis.iox_j.validator;
// import java.util.ArrayList;
// import java.util.Arrays;

// import ch.interlis.iom.IomObject;

// public class AttributeArray {
// 	private Object[] values;
	
// 	@Override
// 	public int hashCode() {
// 		final int prime = 31;
// 		int result = 1;
// 		for(Object obj:values){
// 			if(obj==null){
// 				result = prime*result + 0;
// 			}else if(obj instanceof IomObject){
// 				result = prime*result + hashCodeIomObj((IomObject)obj);
// 			}else{
// 				result = prime*result + obj.hashCode();
// 			}

// 		}
// 		return result;
// 	}

// 	private int hashCodeIomObj(IomObject thisObj) {
// 		final int prime = 31;
// 		int result = 1;
// 		result = prime*result + thisObj.getobjecttag().hashCode();
// 		String refoid=thisObj.getobjectrefoid();
// 		if(refoid!=null){
// 			result = prime*result + refoid.hashCode();
// 		}
// 		// compare attrs
// 		int attrc=thisObj.getattrcount();
// 		for(int attri=0;attri<attrc;attri++){
// 			String attrName=thisObj.getattrname(attri);
// 			int valuec=thisObj.getattrvaluecount(attrName);
// 			for(int valuei=0;valuei<valuec;valuei++){
// 				IomObject valueObj=thisObj.getattrobj(attrName, valuei);
// 				if(valueObj!=null){
// 					result = prime*result + hashCodeIomObj(valueObj);
// 				}else {
// 					String valueStr=thisObj.getattrprim(attrName, valuei);
// 					if(valueStr!=null){
// 						result = prime*result + valueStr.hashCode();
// 					}
// 				}
// 			}
// 		}
// 		return result;
// 	}

// 	@Override
// 	public boolean equals(Object obj) {
// 		if (this == obj)
// 			return true;
// 		if (obj == null)
// 			return false;
// 		if (getClass() != obj.getClass())
// 			return false;
// 		AttributeArray other = (AttributeArray) obj;
// 		if(values.length==other.values.length){
// 			for(int i=0;i<values.length;i++){
// 				Object object=values[i];
// 				Object otherObject=other.values[i];
// 				// iomObject
// 				if(object instanceof IomObject && otherObject instanceof IomObject){
// 					IomObject thisIomObj = (IomObject) object;
// 					IomObject otherIomObj = (IomObject) otherObject;
// 					if(!equalsIomObj(thisIomObj,otherIomObj)){
// 						return false;
// 					}
// 				} else {
// 					// String
// 					if(!object.equals(otherObject)){
// 						return false;
// 					}
// 				}
// 			}
// 			return true;
// 		}
// 		return false;
// 	}

// 	private boolean equalsIomObj(IomObject thisObj, IomObject otherObj) {
// 		// compare class/assoc/coord/...
// 		if(!thisObj.getobjecttag().equals(otherObj.getobjecttag())){
// 			return false;
// 		}

// 		// referenced objects can have oid
// 		String oid = thisObj.getobjectoid();
// 		String otherOid = otherObj.getobjectoid();
// 		if (oid == null ? otherOid != null : !oid.equals(otherOid)) {
// 			return false;
// 		}

// 		// compare ref
// 		String refoid=thisObj.getobjectrefoid();
// 		String otherRefoid=otherObj.getobjectrefoid();
// 		if(refoid==null && otherRefoid==null){
// 			// equal
// 		}else if(refoid!=null && otherRefoid!=null && !refoid.equals(otherRefoid)){
// 			return false;
// 		}else{
// 			return false;
// 		}
		
// 		// compare attrs
// 		int attrc=thisObj.getattrcount();
// 		if(attrc!=otherObj.getattrcount()){
// 			return false;
// 		}
// 		for(int attri=0;attri<attrc;attri++){
// 			String attrName=thisObj.getattrname(attri);
// 			int valuec=thisObj.getattrvaluecount(attrName);
// 			if(valuec!=otherObj.getattrvaluecount(attrName)){
// 				return false;
// 			}
// 			for(int valuei=0;valuei<valuec;valuei++){
// 				IomObject valueObj=thisObj.getattrobj(attrName, valuei);
// 				if(valueObj!=null && !equalsIomObj(valueObj,otherObj.getattrobj(attrName, valuei))){
// 					return false;
// 				}
// 				String valueStr=thisObj.getattrprim(attrName, valuei);
// 				if(valueStr!=null && !valueStr.equals(otherObj.getattrprim(attrName, valuei))){
// 					return false;
// 				}
// 			}
// 		}
// 		return true;
// 	}

// 	public AttributeArray(ArrayList<Object> values){
// 		this.values = values.toArray(new Object[values.size()]);
// 	}
	
// 	private Object[] getValues() {
// 		return values;
// 	}
	
// 	public String valuesAsString() {
// 		StringBuilder ret=new StringBuilder();
// 		String sep="";
// 		for(Object v: values){
// 			ret.append(sep);
// 			ret.append(v);
// 			sep=", ";
// 		}
// 		return ret.toString();
// 	}
// }