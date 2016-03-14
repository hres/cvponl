package ca.gc.hc.model.lookups;

import ca.gc.hc.util.LocaleDependantObject;

public class Seriousness extends LocaleDependantObject{
	
	private Long seriousnessID;
	private String seriousnessCode;	
	private String seriousnessName;
	private String seriousnessNameE;
	private String seriousnessNameF;
	private String seriousCodeAndName;
	
	/**
	 * no parameter default constructor.
	 */	
	public Seriousness(){
	}
	
	public String getName() throws Exception {
		if (isLanguageFrench()) {
			return getSeriousnessNameF();
		}
		return getSeriousnessNameE();		
	}
	
	public String getSeriousnessCode() {
		return seriousnessCode;
	}
	
	public void setSeriousnessCode(String seriousnessCode) {
		this.seriousnessCode = seriousnessCode;
	}
	
	public Long getSeriousnessID() {
		return seriousnessID;
	}
	public void setSeriousnessID(Long seriousnessID) {
		this.seriousnessID = seriousnessID;
	}
	
	public String getSeriousnessNameE(){
		return seriousnessNameE;
		//return name.getEnglish();
	}
	public void setSeriousnessNameE(String seriousnessNameE){
		this.seriousnessNameE = seriousnessNameE;
		//this.name.setEnglish(seriousnessNameE);
	}
	
	public String getSeriousnessNameF(){
		return seriousnessNameF;
		//return name.getFrench();
	}
	
	public void setSeriousnessNameF(String seriousnessNameF){
		this.seriousnessNameF = seriousnessNameF;
		//this.name.setFrench(seriousnessNameF);
	}

	/***************************************************************************
	 * Bilingual fields adjustment based on the current Locale.
	 */
	
	public String getSeriousnessName() {
		if (isLanguageFrench()) {
			return getSeriousnessNameF();
		}
		return getSeriousnessNameE();
	}

	public void setSeriousnessName(String seriousnessName) {
		this.seriousnessName = seriousnessName;
	}

	public String getSeriousCodeAndName() {
		try {
			this.seriousCodeAndName = this.getSeriousnessCode() + ", " + this.getSeriousnessNameE() + ", " + this.getSeriousnessNameF();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return seriousCodeAndName;
	}

	public void setSeriousCodeAndName(String seriousCodeAndName) {
		this.seriousCodeAndName = seriousCodeAndName;
	}

}
