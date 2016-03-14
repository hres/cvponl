package ca.gc.hc.model.lookups;

import ca.gc.hc.util.LocaleDependantObject;

public class Gender extends LocaleDependantObject{

	private Long genderID;
	private String genderCode;	
	private String genderNameE;
	private String genderNameF;
	private String genderCodeAndName;

	/**
	 * no parameter default constructor.
	 */	
	public Gender(){
	}
	
	public String getName() throws Exception {
		if (isLanguageFrench()) {
			return getGenderNameF();
		}
		return getGenderNameE();		
	}
	
	public String getGenderCode() {
		return genderCode;
	}
	
	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}
	
	public Long getGenderID() {
		return genderID;
	}
	public void setGenderID(Long genderID) {
		this.genderID = genderID;
	}
	
	public String getGenderNameE(){
		return this.genderNameE;
	}
	public void setGenderNameE(String genderNameE){
		this.genderNameE = genderNameE;
	}
	
	public String getGenderNameF(){
		return this.genderNameF;
	}
	
	public void setGenderNameF(String genderNameF){
		this.genderNameF = genderNameF;
	}

	public String getGenderCodeAndName() {
		try {
			this.genderCodeAndName = this.getGenderCode() + ", " + this.getGenderNameE() + ", " + this.getGenderNameF();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return genderCodeAndName;			
	}

	public void setGenderCodeAndName(String genderCodeAndName) {
		this.genderCodeAndName = genderCodeAndName;
	}

}
