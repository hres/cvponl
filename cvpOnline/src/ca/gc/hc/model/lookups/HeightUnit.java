package ca.gc.hc.model.lookups;

import ca.gc.hc.util.LocaleDependantObject;

public class HeightUnit extends LocaleDependantObject{

	private Long heightUnitID;
	private String heightUnitCode;	
	private String heightUnitNameE;
	private String heightUnitNameF;

	/**
	 * no parameter default constructor.
	 */	
	public HeightUnit(){
	}
	
	public String getName() throws Exception {
		if (isLanguageFrench()) {
			return getHeightUnitNameF();
		}
		return getHeightUnitNameE();		}

	public String getHeightUnitCode() {
		return heightUnitCode;
	}

	public void setHeightUnitCode(String heightUnitCode) {
		this.heightUnitCode = heightUnitCode;
	}

	public Long getHeightUnitID() {
		return heightUnitID;
	}

	public void setHeightUnitID(Long heightUnitID) {
		this.heightUnitID = heightUnitID;
	}

	public String getHeightUnitNameE() {
		return heightUnitNameE;
	}

	public void setHeightUnitNameE(String heightUnitNameE) {
		this.heightUnitNameE = heightUnitNameE;
	}

	public String getHeightUnitNameF() {
		return heightUnitNameF;
	}

	public void setHeightUnitNameF(String heightUnitNameF) {
		this.heightUnitNameF = heightUnitNameF;
	}
	


}
