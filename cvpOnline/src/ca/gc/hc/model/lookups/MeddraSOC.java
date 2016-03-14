package ca.gc.hc.model.lookups;

import ca.gc.hc.util.LocaleDependantObject;


public class MeddraSOC extends LocaleDependantObject {

	private Long socCode;
	private String socName;
	private String socNameE;
	private String socNameF;
	private String meddraVersion;

	// default constructor
	public MeddraSOC(){		
	}
	
	public String getSocNameF() {
		return socNameF;
	}
	public void setSocNameF(String socNameF) {
		this.socNameF = socNameF;
	}
	public Long getSocCode() {
		return socCode;
	}
	public void setSocCode(Long socCode) {
		this.socCode = socCode;
	}
	public String getSocNameE() {
		return socNameE;
	}
	public void setSocNameE(String socNameE) {
		this.socNameE = socNameE;
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
	
	public String getSocName() {
		if (isLanguageFrench()) {
			return getSocNameF();
		}
		return getSocNameE();
	}

	public void setSocName(String socName) {
		this.socName = socName;
	}
}
