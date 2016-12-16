package ch.interlis.iox_j.validator;

import java.util.Iterator;
import java.util.List;

import ch.interlis.ili2c.metamodel.EnumerationType;
import ch.interlis.ili2c.metamodel.NumericType;
import ch.interlis.ili2c.metamodel.TextType;
import ch.interlis.ili2c.metamodel.Type;
import ch.interlis.ili2c.metamodel.TypeAlias;
import ch.interlis.iom.IomObject;

public class Value {
	private boolean error=false;
	private boolean booleanValue;
	private String value=null;
	private IomObject complexValue=null;
	private Type type=null;
	private String attrName=null;
	
	public Value(boolean booleanValue) {
		this.booleanValue = booleanValue;
	}
	
	public Value(Type type,String valueStr){
		this.value = valueStr;
		this.type=type;
	}
	
	public Value(IomObject value){
		this.complexValue = value;
	}
	
	private Value() {
		error=true;
	}
	
	public boolean isTrue(){
		if(isError()){
			throw new IllegalArgumentException();
		}
		return booleanValue;
	}
	
	public String getValue(){
		if(isError()){
			throw new IllegalArgumentException();
		}
		return value;
	}
	
	public IomObject getComplexValue(){
		if(isError()){
			throw new IllegalArgumentException();
		}
		return complexValue;
	}
	
	public static Value createError(){
		return new Value();
	}
	
	public boolean isError(){
		return error;
	}
	
	// compare in appropriate type.
	public int compareTo(Value other){
		// if value is error, return exception.
		if(isError() || other.isError()){
			throw new IllegalArgumentException();
		}
		// intercept complex value
		if (this.complexValue != null && other.complexValue != null){
			// intercept coords and arcs
			if((this.complexValue.getobjecttag().equals("COORD") && other.complexValue.getobjecttag().equals("COORD"))
				|| (this.complexValue.getobjecttag().equals("ARC") && other.complexValue.getobjecttag().equals("ARC"))){
				return compareCoordsTo(this.complexValue, other);
			}
			// intercept polylines
			if(this.complexValue.getobjecttag().equals("POLYLINE") && other.complexValue.getobjecttag().equals("POLYLINE")){
				return comparePolylineTo(this.complexValue, other);
			}
			// intercept surfaces and areas
			if(this.complexValue.getobjecttag().equals("MULTISURFACE") && other.complexValue.getobjecttag().equals("MULTISURFACE")){
				return compareSurfaceOrAreaTo(this.complexValue, other);
			}
		}
		// intercept value
		if(this.value!=null && other.value!=null){
			// intercept text
			if(type instanceof TextType){
				return value.compareTo(other.value);
			// intercept numeric
			} else if(type instanceof NumericType){
				return Integer.compare(Integer.parseInt(value), Integer.parseInt(other.value));
			} else if(type instanceof TypeAlias){
				TypeAlias typeAlias = (TypeAlias) type;
				Type type = typeAlias.getAliasing().getType();
				if(type instanceof EnumerationType){
					EnumerationType enumeration = (EnumerationType) type;
					// if ordered = true (>,<,>=,<=)
					if(enumeration.isOrdered() && !enumeration.isCircular()){
						int thisIndex = enumeration.getValues().indexOf(this.value);
						int otherIndex = enumeration.getValues().indexOf(other.value);
						return Integer.compare(thisIndex, otherIndex);
					} else {
						// if ordered = false (==, !=, <>)
						return this.value.compareTo(other.value);
					}
				}
			}
		// intercept boolean
		} else if(this.value==null && other.value==null){
			return Boolean.compare(this.booleanValue, other.booleanValue);
		}
		// incompatible type
		throw new IllegalArgumentException("incompatible values");
	}
	
	private int compareSurfaceOrAreaTo(IomObject complexValue2, Value other) {
		for(int l=0;l<complexValue2.getattrvaluecount("surface");l++){
			IomObject thisSurfaceValue=complexValue2.getattrobj("surface",l);
			IomObject otherSurfaceValue=other.complexValue.getattrobj("surface",l);
			for(int m=0;m<thisSurfaceValue.getattrvaluecount("boundary");m++){
				IomObject thisBoundaryValue = thisSurfaceValue.getattrobj("boundary",m);
				IomObject otherBoundaryValue = otherSurfaceValue.getattrobj("boundary", m);
				for(int n=0;n<thisBoundaryValue.getattrvaluecount("polyline");n++){
					IomObject thisPolylineValue = thisBoundaryValue.getattrobj("polyline",n);
					Value otherPolylineValue = new Value(otherBoundaryValue.getattrobj("polyline", n));
					if (comparePolylineTo(thisPolylineValue, otherPolylineValue) !=0){
						return comparePolylineTo(thisPolylineValue, otherPolylineValue);
					}
				}
			}
		}
		return 0;
	}
	
	private int comparePolylineTo(IomObject complexValue2, Value other) {
		for(int l=0;l<complexValue2.getattrvaluecount("sequence");l++){
			IomObject thisSegmentsValue=complexValue2.getattrobj("sequence",l);
			IomObject otherSegmentsValue=other.complexValue.getattrobj("sequence",l);
			for(int m=0;m<thisSegmentsValue.getattrvaluecount("segment");m++){
				IomObject thisCoord=thisSegmentsValue.getattrobj("segment",m);
				Value otherCoord = new Value(otherSegmentsValue.getattrobj("segment", m));
				if (compareCoordsTo(thisCoord, otherCoord) !=0){
					return compareCoordsTo(thisCoord, otherCoord);
				}
			}
		}
		return 0;
	}
	
	private int compareCoordsTo(IomObject complexValue, Value other){
		// if complexValue, return boolean of complex value comparison.
		if(complexValue.getattrvalue("C1") != null && other.complexValue.getattrvalue("C1") != null){
			if (complexValue.getattrvalue("C1").compareTo(other.complexValue.getattrvalue("C1"))!=0){
				return complexValue.getattrvalue("C1").compareTo(other.complexValue.getattrvalue("C1"));
			}
		}
		if(complexValue.getattrvalue("C2") != null && other.complexValue.getattrvalue("C2") != null){
			if (complexValue.getattrvalue("C2").compareTo(other.complexValue.getattrvalue("C2"))!=0){
				return complexValue.getattrvalue("C2").compareTo(other.complexValue.getattrvalue("C2"));
			}
		}
		if(complexValue.getattrvalue("C3") != null && other.complexValue.getattrvalue("C3") != null){
			if (complexValue.getattrvalue("C3").compareTo(other.complexValue.getattrvalue("C3"))!=0){
				return complexValue.getattrvalue("C3").compareTo(other.complexValue.getattrvalue("C3"));
			}
		}
		if(complexValue.getattrvalue("A1") != null && other.complexValue.getattrvalue("A1") != null){
			if (complexValue.getattrvalue("A1").compareTo(other.complexValue.getattrvalue("A1"))!=0){
				return complexValue.getattrvalue("A1").compareTo(other.complexValue.getattrvalue("A1"));
			}
		}
		if(complexValue.getattrvalue("A2") != null && other.complexValue.getattrvalue("A2") != null){
			if (complexValue.getattrvalue("A2").compareTo(other.complexValue.getattrvalue("A2"))!=0){
				return complexValue.getattrvalue("A2").compareTo(other.complexValue.getattrvalue("A2"));
			}
		}
		return 0;
	}
}