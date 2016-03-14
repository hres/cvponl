package ca.gc.hc.model.lookups;

import java.util.Date;

import ca.gc.hc.util.LocaleDependantObject;

public class Outcome extends LocaleDependantObject{

	private Long outcomeID;
	private String outcomeCode;	
	private String outcomeNameE;
	private String outcomeNameF;
	private Date lastUpdateDate;
	private String outcomeCodeAndName;
	
	/**
	 * no parameter default constructor.
	 */	
	public Outcome(){
	}
	
	public String getName() throws Exception {
		if (isLanguageFrench()) {
			return getOutcomeNameF();
		}
		return getOutcomeNameE();
	}
	
	
	public String getOutcomeCode() {
		return outcomeCode;
	}
	
	public void setOutcomeCode(String outcomeCode) {
		this.outcomeCode = outcomeCode;
	}
	
	public Long getOutcomeID() {
		return outcomeID;
	}
	public void setOutcomeID(Long outcomeID) {
		this.outcomeID = outcomeID;
	}
	
	public String getOutcomeNameE(){
		return outcomeNameE;
		//return name.getEnglish();
	}
	public void setOutcomeNameE(String outcomeNameE){
		this.outcomeNameE = outcomeNameE;
		//this.name.setEnglish(outcomeNameE);
	}
	
	public String getOutcomeNameF(){
		return outcomeNameF;
		//return name.getFrench();
	}
	
	public void setOutcomeNameF(String outcomeNameF){
		this.outcomeNameF = outcomeNameF;
		//this.name.setFrench(outcomeNameF);
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getOutcomeCodeAndName() {
		try {
			this.outcomeCodeAndName = this.getOutcomeCode() + ", " + this.getOutcomeNameE() + ", " + this.getOutcomeNameF();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outcomeCodeAndName;
	}

	public void setOutcomeCodeAndName(String outcomeCodeAndName) {
		this.outcomeCodeAndName = outcomeCodeAndName;
	}


}
