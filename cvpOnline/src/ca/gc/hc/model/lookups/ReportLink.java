package ca.gc.hc.model.lookups;

import ca.gc.hc.util.LocaleDependantObject;

public class ReportLink extends LocaleDependantObject{
	
	private Long reportLinkID;
	private Long reportID;
	private String recordTypeCode;	
	private String recordTypeName;
	private String recordTypeNameE;
	private String recordTypeNameF;
	private String reportLink;
	private Long aerID;

	public ReportLink(){}
	
	public Long getAerID() {
		return aerID;
	}
	public void setAerID(Long aerID) {
		this.aerID = aerID;
	}
	public String getRecordTypeCode() {
		return recordTypeCode;
	}
	public void setRecordTypeCode(String recordTypeCode) {
		this.recordTypeCode = recordTypeCode;
	}
	public String getRecordTypeNameE() {
		return recordTypeNameE;
	}
	public void setRecordTypeNameE(String recordTypeNameE) {
		this.recordTypeNameE = recordTypeNameE;
	}
	public String getRecordTypeNameF() {
		return recordTypeNameF;
	}
	public void setRecordTypeNameF(String recordTypeNameF) {
		this.recordTypeNameF = recordTypeNameF;
	}
	public Long getReportID() {
		return reportID;
	}
	public void setReportID(Long reportID) {
		this.reportID = reportID;
	}
	public String getReportLink() {
		return reportLink;
	}
	public void setReportLink(String reportLink) {
		this.reportLink = reportLink;
	}
	public Long getReportLinkID() {
		return reportLinkID;
	}
	public void setReportLinkID(Long reportLinkID) {
		this.reportLinkID = reportLinkID;
	}

	/***************************************************************************
	 * Bilingual fields adjustment based on the current Locale.
	 */

	public String getRecordTypeName() {
		if (isLanguageFrench()) {
			return getRecordTypeNameF();
		}
		return getRecordTypeNameE();
	}
	
	public void setRecordTypeName(String recordTypeName) {
		this.recordTypeName = recordTypeName;
	}
	
}
