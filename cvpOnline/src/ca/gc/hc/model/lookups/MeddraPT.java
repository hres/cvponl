package ca.gc.hc.model.lookups;

import ca.gc.hc.util.LocaleDependantObject;


public class MeddraPT extends LocaleDependantObject {

	private Long ptCode;
	private String ptName;
	private String ptNameE;
	private String ptNameF;
	private String meddraVersion;

	// default constructor
	public MeddraPT(){		
	}
	
	public String getPtNameF() {
		return ptNameF;
	}
	public void setPtNameF(String ptNameF) {
		this.ptNameF = ptNameF;
	}
	public Long getPtCode() {
		return ptCode;
	}
	public void setPtCode(Long ptCode) {
		this.ptCode = ptCode;
	}
	public String getPtNameE() {
		return ptNameE;
	}
	public void setPtNameE(String ptNameE) {
		this.ptNameE = ptNameE;
	}

	public String getMeddraVersion() {
		return meddraVersion;
	}

	public void setMeddraVersion(String meddraVersion) {
		this.meddraVersion = meddraVersion;
	}

	/***************************************************************************
	 * Bilingual fields adjustment based on the current Locale.
	 */
	
	public String getPtName() {
		if (isLanguageFrench()) {
			return getPtNameF();
		}
		return getPtNameE();
	}

	public void setPtName(String ptName) {
		this.ptName = ptName;
	}
}
