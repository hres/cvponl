package ca.gc.hc.model.lookups;

import ca.gc.hc.util.LocaleDependantObject;

public class ReportType extends LocaleDependantObject{

	private Long reportTypeID;
	private String reportTypeCode;	
	private String reportTypeNameE;
	private String reportTypeNameF;
	private String reportTypeCodeAndName;
	
	/**
	 * no parameter default constructor.
	 */	
	public ReportType(){
	}
	
	
	public String getName() throws Exception {
		if (isLanguageFrench()) {
			return getReportTypeNameF();
		}
		return getReportTypeNameE();	
	}
	
	public String getReportTypeCode() {
		return reportTypeCode;
	}
	
	public void setReportTypeCode(String reportTypeCode) {
		this.reportTypeCode = reportTypeCode;
	}
	
	public Long getReportTypeID() {
		return reportTypeID;
	}
	public void setReportTypeID(Long reportTypeID) {
		this.reportTypeID = reportTypeID;
	}
	
	public String getReportTypeNameE(){
		return this.reportTypeNameE;
	}
	public void setReportTypeNameE(String reportTypeNameE){
		this.reportTypeNameE = reportTypeNameE;
	}
	
	public String getReportTypeNameF(){
		return this.reportTypeNameF;
	}
	
	public void setReportTypeNameF(String reportTypeNameF){
		this.reportTypeNameF = reportTypeNameF;
	}


	public String getReportTypeCodeAndName() {
		try {
			this.reportTypeCodeAndName = this.getReportTypeCode() + ", " + this.getReportTypeNameE() + ", " + this.getReportTypeNameF();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reportTypeCodeAndName;			
	}


	public void setReportTypeCodeAndName(String reportTypeCodeAndName) {
		this.reportTypeCodeAndName = reportTypeCodeAndName;
	}

}
