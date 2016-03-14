package ca.gc.hc.model.lookups;

import ca.gc.hc.util.LocaleDependantObject;

public class WeightUnit extends LocaleDependantObject{

	private Long weightUnitID;
	private String weightUnitCode;	
	private String weightUnitNameE;
	private String weightUnitNameF;

	/**
	 * no parameter default constructor.
	 */	
	public WeightUnit(){
	}
	
	public String getName() throws Exception {
		if (isLanguageFrench()) {
			return getWeightUnitNameF();
		}
		return getWeightUnitNameE();		}

	public String getWeightUnitCode() {
		return weightUnitCode;
	}

	public void setWeightUnitCode(String weightUnitCode) {
		this.weightUnitCode = weightUnitCode;
	}

	public Long getWeightUnitID() {
		return weightUnitID;
	}

	public void setWeightUnitID(Long weightUnitID) {
		this.weightUnitID = weightUnitID;
	}

	public String getWeightUnitNameE() {
		return weightUnitNameE;
	}

	public void setWeightUnitNameE(String weightUnitNameE) {
		this.weightUnitNameE = weightUnitNameE;
	}

	public String getWeightUnitNameF() {
		return weightUnitNameF;
	}

	public void setWeightUnitNameF(String weightUnitNameF) {
		this.weightUnitNameF = weightUnitNameF;
	}


}
