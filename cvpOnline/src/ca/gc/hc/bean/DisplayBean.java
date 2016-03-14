package ca.gc.hc.bean;

import ca.gc.hc.util.LocaleDependantObject;

public class DisplayBean extends LocaleDependantObject {

	private String displayReportNo;
	private String displayVersionNo;
	private String displayMahNumber;
	private String displayInitialDate;
	private String displayLatestDate;
	private String displayFeatureOfReport;
	private String displayTypeOfReport;
	private String displaySerious;
	private String displayAge;	
	private String displayGender;
	private String displayWeight;
	private String displayHeight;
	private String displayReportOutcome;
	private String displayReporterType;
	private String displaySourceOfReport;
	
	private String displayDeath;
	private String displayDisability;
	private String displayCongenitalAnomaly;
	private String displayLifeThreat;
	private String displayHospitalization;
	private String displayMedicalCondition;

	private String displayDrugName;	
	private String displayDosageForm;
	private String displayProductRole;
	private String displayRoute;
	private String displayAmount;	
	private String displayFrequency;
	private String displayTherapyDuration;
	
	private String displayMeddraPTName;
	private String displayMeddraSOCname;
	private String displayReactionDuration;
	private String displayActiveIngredient;


	
	// default constructor
	public DisplayBean(){		
	}
	
	public void resetProperties(){
		this.displayReportNo = "";
		this.displayDrugName = "";	
		this.displayMeddraPTName = "";
		this.displayMeddraSOCname = "";
		this.displayReactionDuration = "";
		
		this.displayReportOutcome = "";
		this.displayAge = "";
		this.displayGender = "";
		this.displayWeight = "";
		this.displayHeight = "";
		this.displayAmount = "";
		this.displayFrequency = "";
		this.displayActiveIngredient = "";
		this.displayTherapyDuration = "";
		this.displayDosageForm = "";
		this.displaySerious = "";
		this.displayReporterType = "";
		this.displaySourceOfReport = "";
		this.displayMahNumber = "";
		this.displayInitialDate = "";
		this.displayLatestDate = "";
		this.displayVersionNo = "";
		this.displayFeatureOfReport = "";
		this.displayTypeOfReport = "";
		this.displayMeddraPTName = "";
	}
	
	public String getDisplayDrugName() {
		return displayDrugName;
	}
	public void setDisplayDrugName(String drugName) {
		this.displayDrugName = drugName;
	}

	public String getDisplayActiveIngredient() {
		return displayActiveIngredient;
	}

	public void setDisplayActiveIngredient(String activeIngredient) {
		this.displayActiveIngredient = activeIngredient;
	}

	public String getDisplayAge() {
		return displayAge;
	}

	public void setDisplayAge(String age) {
		this.displayAge = age;
	}

	public String getDisplayAmount() {
		return displayAmount;
	}

	public void setDisplayAmount(String amount) {
		this.displayAmount = amount;
	}

	public String getDisplayDosageForm() {
		return displayDosageForm;
	}

	public void setDisplayDosageForm(String dosageForm) {
		this.displayDosageForm = dosageForm;
	}

	public String getDisplayFrequency() {
		return displayFrequency;
	}

	public void setDisplayFrequency(String frequency) {
		this.displayFrequency = frequency;
	}

	public void setDisplayGender(String gender) {
		this.displayGender = gender;
	}

	public String getDisplayHeight() {
		return displayHeight;
	}

	public void setDisplayHeight(String height) {
		this.displayHeight = height;
	}

	public String getDisplayInitialDate() {
		return displayInitialDate;
	}

	public void setDisplayInitialDate(String initialDateReceived) {
		this.displayInitialDate = initialDateReceived;
	}

	public String getDisplayLatestDate() {
		return displayLatestDate;
	}

	public void setDisplayLatestDate(String latestDateReceived) {
		this.displayLatestDate = latestDateReceived;
	}

	public String getDisplayMahNumber() {
		return displayMahNumber;
	}

	public void setDisplayMahNumber(String mahNumber) {
		this.displayMahNumber = mahNumber;
	}

	public void setDisplayMeddraPTName(String meddraPTName) {
		this.displayMeddraPTName = meddraPTName;
	}

	public void setDisplayMeddraSOCname(String meddraSOCname) {
		this.displayMeddraSOCname = meddraSOCname;
	}

	public void setDisplayReportOutcome(String patientOutcome) {
		this.displayReportOutcome = patientOutcome;
	}

	public String getDisplayReactionDuration() {
		return displayReactionDuration;
	}

	public void setDisplayReactionDuration(String reactionDuration) {
		this.displayReactionDuration = reactionDuration;
	}

	public void setDisplayReporterType(String reporterType) {
		this.displayReporterType = reporterType;
	}

	public void setDisplayFeatureOfReport(String reportFeature) {
		this.displayFeatureOfReport = reportFeature;
	}

	public String getDisplayReportNo() {
		return displayReportNo;
	}

	public void setDisplayReportNo(String reportNo) {
		this.displayReportNo = reportNo;
	}

	public void setDisplaySourceOfReport(String reportSource) {
		this.displaySourceOfReport = reportSource;
	}


	public void setDisplayTypeOfReport(String reportType) {
		this.displayTypeOfReport = reportType;
	}

	public void setDisplaySerious(String seriousness) {
		this.displaySerious = seriousness;
	}

	public String getDisplayTherapyDuration() {
		return displayTherapyDuration;
	}

	public void setDisplayTherapyDuration(String therapyDuration) {
		this.displayTherapyDuration = therapyDuration;
	}

	public String getDisplayVersionNo() {
		return displayVersionNo;
	}

	public void setDisplayVersionNo(String versionNo) {
		this.displayVersionNo = versionNo;
	}

	public String getDisplayWeight() {
		return displayWeight;
	}

	public void setDisplayWeight(String weight) {
		this.displayWeight = weight;
	}

	public String getDisplayCongenitalAnomaly() {
		return displayCongenitalAnomaly;
	}

	public void setDisplayCongenitalAnomaly(String displayCongenitalAnomaly) {
		this.displayCongenitalAnomaly = displayCongenitalAnomaly;
	}

	public String getDisplayDeath() {
		return displayDeath;
	}

	public void setDisplayDeath(String displayDeath) {
		this.displayDeath = displayDeath;
	}

	public String getDisplayDisability() {
		return displayDisability;
	}

	public void setDisplayDisability(String displayDisability) {
		this.displayDisability = displayDisability;
	}

	public String getDisplayHospitalization() {
		return displayHospitalization;
	}

	public void setDisplayHospitalization(String displayHospitalization) {
		this.displayHospitalization = displayHospitalization;
	}

	public String getDisplayLifeThreat() {
		return displayLifeThreat;
	}

	public void setDisplayLifeThreat(String displayLifeThreat) {
		this.displayLifeThreat = displayLifeThreat;
	}

	public String getDisplayMedicalCondition() {
		return displayMedicalCondition;
	}

	public void setDisplayMedicalCondition(String displayMedicalCondition) {
		this.displayMedicalCondition = displayMedicalCondition;
	}

	public String getDisplayRoute() {
		return displayRoute;
	}

	public void setDisplayRoute(String displayRoute) {
		this.displayRoute = displayRoute;
	}

	public String getDisplayFeatureOfReport() {
		return displayFeatureOfReport;
	}

	public String getDisplayGender() {
		return displayGender;
	}

	public String getDisplayMeddraPTName() {
		return displayMeddraPTName;
	}

	public String getDisplayMeddraSOCname() {
		return displayMeddraSOCname;
	}

	public String getDisplayReporterType() {
		return displayReporterType;
	}

	public String getDisplayReportOutcome() {
		return displayReportOutcome;
	}

	public String getDisplaySerious() {
		return displaySerious;
	}

	public String getDisplaySourceOfReport() {
		return displaySourceOfReport;
	}

	public String getDisplayTypeOfReport() {
		return displayTypeOfReport;
	}

	public String getDisplayProductRole() {
		return displayProductRole;
	}

	public void setDisplayProductRole(String displayProductRole) {
		this.displayProductRole = displayProductRole;
	}

}
