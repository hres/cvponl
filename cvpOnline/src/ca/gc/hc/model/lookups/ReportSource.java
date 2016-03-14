package ca.gc.hc.model.lookups;

import ca.gc.hc.util.LocaleDependantObject;

public class ReportSource extends LocaleDependantObject{

	private Long reportSourceID;
	private String reportSourceCode;	
	private String reportSourceNameE;
	private String reportSourceNameF;
	private String reportSourceCodeAndName;
	
	/**
	 * no parameter default constructor.
	 */	
	public ReportSource(){
	}
	
	public String getName() throws Exception {
		if (isLanguageFrench()) {
			return getReportSourceNameF();
		}
		return getReportSourceNameE();	
	}
	
	public String getReportSourceCode() {
		return reportSourceCode;
	}
	
	public void setReportSourceCode(String reportSourceCode) {
		this.reportSourceCode = reportSourceCode;
	}
	
	public Long getReportSourceID() {
		return reportSourceID;
	}
	public void setReportSourceID(Long reportSourceID) {
		this.reportSourceID = reportSourceID;
	}
	
	public String getReportSourceNameE(){
		return this.reportSourceNameE;
	}
	public void setReportSourceNameE(String reportSourceNameE){
		this.reportSourceNameE = reportSourceNameE;
	}
	
	public String getReportSourceNameF(){
		return this.reportSourceNameF;
	}
	
	public void setReportSourceNameF(String reportSourceNameF){
		this.reportSourceNameF = reportSourceNameF;
	}

	public String getReportSourceCodeAndName() {
		try {
			this.reportSourceCodeAndName = this.getReportSourceCode() + ", " + this.getReportSourceNameE() + ", " + this.getReportSourceNameF();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reportSourceCodeAndName;		
	}

	public void setReportSourceCodeAndName(String reportSourceCodeAndName) {
		this.reportSourceCodeAndName = reportSourceCodeAndName;
	}


}
