package ca.gc.hc.model.lookups;

import ca.gc.hc.util.LocaleDependantObject;

public class ReporterType extends LocaleDependantObject{

	private Long reporterTypeID;
	private String reporterTypeCode;	
	private String reporterTypeNameE;
	private String reporterTypeNameF;

	/**
	 * no parameter default constructor.
	 */	
	public ReporterType(){
	}
	
	public String getName() throws Exception {
		if (isLanguageFrench()) {
			return getReporterTypeNameF();
		}
		return getReporterTypeNameE();		
	}

	public String getReporterTypeCode() {
		return reporterTypeCode;
	}

	public void setReporterTypeCode(String reporterTypeCode) {
		this.reporterTypeCode = reporterTypeCode;
	}

	public Long getReporterTypeID() {
		return reporterTypeID;
	}

	public void setReporterTypeID(Long reporterTypeID) {
		this.reporterTypeID = reporterTypeID;
	}

	public String getReporterTypeNameE() {
		return reporterTypeNameE;
	}

	public void setReporterTypeNameE(String reporterTypeNameE) {
		this.reporterTypeNameE = reporterTypeNameE;
	}

	public String getReporterTypeNameF() {
		return reporterTypeNameF;
	}

	public void setReporterTypeNameF(String reporterTypeNameF) {
		this.reporterTypeNameF = reporterTypeNameF;
	}

}
