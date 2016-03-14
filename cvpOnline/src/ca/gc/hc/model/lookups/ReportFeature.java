/* - deprecated May 9, 2012
 * package ca.gc.hc.model.lookups;

import ca.gc.hc.util.LocaleDependantObject;

public class ReportFeature extends LocaleDependantObject{

	private Long reportFeatureID;
	private String reportFeatureCode;	
	private String reportFeatureNameE;
	private String reportFeatureNameF;
	private String reportFeatureCodeAndName;
	
	*//**
	 * no parameter default constructor.
	 *//*	
	public ReportFeature(){
	}
	
	public String getName() throws Exception {
		if (isLanguageFrench()) {
			return getReportFeatureNameF();
		}
		return getReportFeatureNameE();	
	}
	

	public String getReportFeatureCode() {
		return reportFeatureCode;
	}
	
	public void setReportFeatureCode(String reportFeatureCode) {
		this.reportFeatureCode = reportFeatureCode;
	}
	
	public Long getReportFeatureID() {
		return reportFeatureID;
	}
	public void setReportFeatureID(Long reportFeatureID) {
		this.reportFeatureID = reportFeatureID;
	}
	
	public String getReportFeatureNameE(){
		return this.reportFeatureNameE;
	}
	public void setReportFeatureNameE(String reportFeatureNameE){
		this.reportFeatureNameE = reportFeatureNameE;
	}
	
	public String getReportFeatureNameF(){
		return this.reportFeatureNameF;
	}
	
	public void setReportFeatureNameF(String reportFeatureNameF){
		this.reportFeatureNameF = reportFeatureNameF;
	}

	public String getReportFeatureCodeAndName() {
		try {
			this.reportFeatureCodeAndName = this.getReportFeatureCode() + ", " + this.getReportFeatureNameE() + ", " + this.getReportFeatureNameF();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reportFeatureCodeAndName;
	}

	public void setReportFeatureCodeAndName(String reportFeatureCodeAndName) {
		this.reportFeatureCodeAndName = reportFeatureCodeAndName;
	}


}
*/