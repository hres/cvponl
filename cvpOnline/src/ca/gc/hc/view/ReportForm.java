package ca.gc.hc.view;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ReportForm extends ActionForm {

	private String	fReportId;
	private String	fVersionNum;
	private String	fMahNumber;
	private String	fInitDateReceived;
	private String	fReportFeatCode;
	private String	fReportTypeCode;
	private String	fRecordTypeCode;
	private String	fSeriousnessCode;
	private String	fDeath;
	private String	fDisability;
	private String	fCongenitalAnomaly;
	private String	fGenderCode;
	private String	fAge;
	private String	fAgeCode;
	private String	fAgeGroupCode;
	private String	fWeight;
	private String	fWeightCode;
	private String	fHeight;
	private String	fHeightCode;
	private String	fOutcomeCode;
	private String	fReporterTypeCode;
	private String	fReportSourceCode;
	private String	fAerId;
	private String	fLifeThreatening;
	private String	fHospitalRequired;
	private String	fOtherMedicalCondition;
	private String	fReportLink;

//	// variables to retrieve localized names of different objects
//	private String genderName = "";
//	private String outcomeName = "";
//	private String drugsList;
//	private String ptNames;
//	private String searchedDrug;
//
//	// Park first
//	private String fReportNum;
//	private Date fdateReceived;
//	private Long	fAgeYears;	
//	private Outcome foutcome = new Outcome();
//	private Set fdrugs = new HashSet();
//	private Set freactions = new HashSet();
	
	public void reset (ActionMapping mapping, HttpServletRequest request){
		fReportId = "";
		fVersionNum = "";
		fMahNumber = "";
		fInitDateReceived = "";
		fReportFeatCode = "";
		fReportTypeCode = "";
		fRecordTypeCode = "";
		fSeriousnessCode = "";
		fDeath = "";
		fDisability = "";
		fCongenitalAnomaly = "";
		fGenderCode = "";
		fAge = "";
		fAgeCode = "";
		fAgeGroupCode = "";
		fWeight = "";
		fWeightCode = "";
		fHeight = "";
		fHeightCode = "";
		fOutcomeCode = "";
		fReporterTypeCode = "";
		fReportSourceCode = "";
		fAerId = "";
		fLifeThreatening = "";
		fHospitalRequired = "";
		fOtherMedicalCondition = "";
		fReportLink = "";
	}

	public String getFAerId() {
		return fAerId;
	}

	public void setFAerId(String aerId) {
		fAerId = aerId;
	}

	public String getFAge() {
		return fAge;
	}

	public void setFAge(String age) {
		fAge = age;
	}

	public String getFAgeCode() {
		return fAgeCode;
	}

	public void setFAgeCode(String ageCode) {
		fAgeCode = ageCode;
	}

	public String getFAgeGroupCode() {
		return fAgeGroupCode;
	}

	public void setFAgeGroupCode(String ageGroupCode) {
		fAgeGroupCode = ageGroupCode;
	}

	public String getFCongenitalAnomaly() {
		return fCongenitalAnomaly;
	}

	public void setFCongenitalAnomaly(String congenitalAnomaly) {
		fCongenitalAnomaly = congenitalAnomaly;
	}

	public String getFDeath() {
		return fDeath;
	}

	public void setFDeath(String death) {
		fDeath = death;
	}

	public String getFDisability() {
		return fDisability;
	}

	public void setFDisability(String disability) {
		fDisability = disability;
	}

	public String getFGenderCode() {
		return fGenderCode;
	}

	public void setFGenderCode(String genderCode) {
		fGenderCode = genderCode;
	}

	public String getFHeight() {
		return fHeight;
	}

	public void setFHeight(String height) {
		fHeight = height;
	}

	public String getFHeightCode() {
		return fHeightCode;
	}

	public void setFHeightCode(String heightCode) {
		fHeightCode = heightCode;
	}

	public String getFHospitalRequired() {
		return fHospitalRequired;
	}

	public void setFHospitalRequired(String hospitalRequired) {
		fHospitalRequired = hospitalRequired;
	}

	public String getFInitDateReceived() {
		return fInitDateReceived;
	}

	public void setFInitDateReceived(String initDateReceived) {
		fInitDateReceived = initDateReceived;
	}

	public String getFLifeThreatening() {
		return fLifeThreatening;
	}

	public void setFLifeThreatening(String lifeThreatening) {
		fLifeThreatening = lifeThreatening;
	}

	public String getFMahNumber() {
		return fMahNumber;
	}

	public void setFMahNumber(String mahNumber) {
		fMahNumber = mahNumber;
	}

	public String getFOtherMedicalCondition() {
		return fOtherMedicalCondition;
	}

	public void setFOtherMedicalCondition(String otherMedicalCondition) {
		fOtherMedicalCondition = otherMedicalCondition;
	}

	public String getFOutcomeCode() {
		return fOutcomeCode;
	}

	public void setFOutcomeCode(String outcomeCode) {
		fOutcomeCode = outcomeCode;
	}

	public String getFRecordTypeCode() {
		return fRecordTypeCode;
	}

	public void setFRecordTypeCode(String recordTypeCode) {
		fRecordTypeCode = recordTypeCode;
	}

	public String getFReporterTypeCode() {
		return fReporterTypeCode;
	}

	public void setFReporterTypeCode(String reporterTypeCode) {
		fReporterTypeCode = reporterTypeCode;
	}

	public String getFReportFeatCode() {
		return fReportFeatCode;
	}

	public void setFReportFeatCode(String reportFeatCode) {
		fReportFeatCode = reportFeatCode;
	}

	public String getFReportId() {
		return fReportId;
	}

	public void setFReportId(String reportId) {
		fReportId = reportId;
	}

	public String getFReportLink() {
		return fReportLink;
	}

	public void setFReportLink(String reportLink) {
		fReportLink = reportLink;
	}

	public String getFReportSourceCode() {
		return fReportSourceCode;
	}

	public void setFReportSourceCode(String reportSourceCode) {
		fReportSourceCode = reportSourceCode;
	}

	public String getFReportTypeCode() {
		return fReportTypeCode;
	}

	public void setFReportTypeCode(String reportTypeCode) {
		fReportTypeCode = reportTypeCode;
	}

	public String getFSeriousnessCode() {
		return fSeriousnessCode;
	}

	public void setFSeriousnessCode(String seriousnessCode) {
		fSeriousnessCode = seriousnessCode;
	}

	public String getFVersionNum() {
		return fVersionNum;
	}

	public void setFVersionNum(String versionNum) {
		fVersionNum = versionNum;
	}

	public String getFWeight() {
		return fWeight;
	}

	public void setFWeight(String weight) {
		fWeight = weight;
	}

	public String getFWeightCode() {
		return fWeightCode;
	}

	public void setFWeightCode(String weightCode) {
		fWeightCode = weightCode;
	}	
	
}
