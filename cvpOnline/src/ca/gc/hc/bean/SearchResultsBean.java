package ca.gc.hc.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import ca.gc.hc.util.LocaleDependantObject;

public class SearchResultsBean extends LocaleDependantObject {

	private String meddraVersion;
	
	private String reportNo;
	private String versionNo;	
	private String mahNumber;
	private String initialDateReceived;
	private String latestDateReceived;	
	private Date initialDateReceivedD;	// for formatting the displayed dates
	private Date latestDateReceivedD;	// for formatting the displayed dates
	private String featureOfReport;
	private String typeOfReport;
	private String serious;
	private String age;
	private String gender;
	private String weight;
	private String height;
	private String reportOutcome;
	private String reporterType;
	private String sourceOfReport;
	
	private String death;
	private String disability;
	private String congenitalAnomaly;
	private String lifeThreatening;
	private String hospitalization;
	private String otherMedCondition;
	
	private String drugName;
	private String dosageForm;
	private String routeOfAdministration;
	private String amount;
	private String frequency;
	private String therapyDuration;
	
	private String meddraPTName;
	private String meddraSOCname;
	private String reactionDuration;
	private String activeIngredient;

	private String reportFeatureE;
	private String reportFeatureF;
	private String reportTypeE;
	private String reportTypeF;
	private String genderNameE;
	private String genderNameF;
	private String ageUnit;
	private String ageUnitE;
	private String ageUnitF;
	private String outcomeNameE;
	private String outcomeNameF;
	private String weightUnit;
	private String weightUnitE;
	private String weightUnitF;
	private String heightUnit;
	private String heightUnitE;
	private String heightUnitF;
	private String seriousnessE;
	private String seriousnessF;
	private String reporterTypeE;
	private String reporterTypeF;
	private String sourceE;
	private String sourceF;
	private String therapyDurationUnit;
	private String therapyDurationUnitE;
	private String therapyDurationUnitF;
	private String ptNameE;
	private String ptNameF;
	private String socNameE;
	private String socNameF;
	private String productRole;
	private String drugInvolveE;
	private String drugInvolveF;
	private String dosageFormE;
	private String dosageFormF;
	private String routeAdminE;
	private String routeAdminF;
	private String doseUnit;	
	private String doseUnitE;
	private String doseUnitF;
	private String freqTimeUnit;
	private String freqTimeUnitE;
	private String freqTimeUnitF;
	private String reactionDurationUnit;
	private String reactionDurationUnitE;
	private String reactionDurationUnitF;
	
	DateFormat df = new SimpleDateFormat("yyyy/MM/dd");	
	
	// default constructor
	public SearchResultsBean(){		
	}
	
	public void resetProperties(){
		this.reportNo = "";
		this.meddraVersion = "";
		this.drugName = "";	
		this.meddraPTName = "";
		this.meddraSOCname = "";
		this.reactionDuration = "";
		
		this.reportOutcome = "";
		this.age = "";
		this.gender = "";
		this.weight = "";
		this.height = "";
		this.amount = "";
		this.frequency = "";
		this.activeIngredient = "";
		this.therapyDuration = "";
		this.dosageForm = "";
		this.serious = "";
		this.reporterType = "";
		this.sourceOfReport = "";
		this.mahNumber = "";
		this.initialDateReceived = "";
		this.latestDateReceived = "";
		this.versionNo = "";
		this.featureOfReport = "";
		this.typeOfReport = "";
		this.meddraPTName = "";
	}
	
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getActiveIngredient() {
		return activeIngredient;
	}

	public void setActiveIngredient(String activeIngredient) {
		this.activeIngredient = activeIngredient;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDosageForm() {
		if (isLanguageFrench()) {
			return getDosageFormF();
		}
		return getDosageFormE();			
	}

	public void setDosageForm(String dosageForm) {
		this.dosageForm = dosageForm;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getGender() {
		if (isLanguageFrench()) {
			return getGenderNameF();
		}
		return getGenderNameE();		
	}		

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getInitialDateReceived() {
		return initialDateReceived;
	}

	public void setInitialDateReceived(String initialDateReceived) {
		this.initialDateReceived = initialDateReceived;
	}

	public String getLatestDateReceived() {
		return latestDateReceived;
	}

	public void setLatestDateReceived(String latestDateReceived) {
		this.latestDateReceived = latestDateReceived;
	}

	public String getMahNumber() {
		return mahNumber;
	}

	public void setMahNumber(String mahNumber) {
		this.mahNumber = mahNumber;
	}

	public String getMeddraPTName() {
		if (isLanguageFrench()) {
			return getPtNameF();
		}
		return getPtNameE();	
	}

	public void setMeddraPTName(String meddraPTName) {
		this.meddraPTName = meddraPTName;
	}

	public String getMeddraSOCname() {
		if (isLanguageFrench()) {
			return getSocNameF();
		}
		return getSocNameE();	
	}

	public void setMeddraSOCname(String meddraSOCname) {
		this.meddraSOCname = meddraSOCname;
	}

	public String getMeddraVersion() {
		return meddraVersion;
	}

	public void setMeddraVersion(String meddraVersion) {
		this.meddraVersion = meddraVersion;
	}

	public String getReportOutcome() {
		if (isLanguageFrench()) {
			return getOutcomeNameF();
		}
		return getOutcomeNameE();
	}

	public void setReportOutcome(String patientOutcome) {
		this.reportOutcome = patientOutcome;
	}

	public String getReactionDuration() {
		return reactionDuration;
	}

	public void setReactionDuration(String reactionDuration) {
		this.reactionDuration = reactionDuration;
	}

	public String getReporterType() {
		if (isLanguageFrench()) {
			return getReporterTypeF();
		}
		return getReporterTypeE();
	}

	public void setReporterType(String reporterType) {
		this.reporterType = reporterType;
	}

	public String getFeatureOfReport() {
		if (isLanguageFrench()) {
			return getReportFeatureF();
		}
		return getReportFeatureE();	
	}

	public void setFeatureOfReport(String reportFeature) {
		this.featureOfReport = reportFeature;
	}

	public String getReportNo() {
		return reportNo;
	}

	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}

	public String getSourceOfReport() {
		if (isLanguageFrench()) {
			return getSourceF();
		}
		return getSourceE();	
	}

	public void setSourceOfReport(String reportSource) {
		this.sourceOfReport = reportSource;
	}

	public String getTypeOfReport() {
		if (isLanguageFrench()) {
			return getReportTypeF();
		}
		return getReportTypeE();	
	}

	public void setTypeOfReport(String reportType) {
		this.typeOfReport = reportType;
	}

	public String getSerious() {
		if (isLanguageFrench()) {
			return getSeriousnessF();
		}
		return getSeriousnessE();	
	}

	public void setSerious(String seriousness) {
		this.serious = seriousness;
	}

	public String getTherapyDuration() {
		return therapyDuration;
	}

	public void setTherapyDuration(String therapyDuration) {
		this.therapyDuration = therapyDuration;
	}

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getAgeUnitE() {
		return ageUnitE;
	}

	public void setAgeUnitE(String ageUnitE) {
		this.ageUnitE = ageUnitE;
	}

	public String getAgeUnitF() {
		return ageUnitF;
	}

	public void setAgeUnitF(String ageUnitF) {
		this.ageUnitF = ageUnitF;
	}

	public String getDoseUnitE() {
		return doseUnitE;
	}

	public void setDoseUnitE(String doseUnitE) {
		this.doseUnitE = doseUnitE;
	}

	public String getDoseUnitF() {
		return doseUnitF;
	}

	public void setDoseUnitF(String doseUnitF) {
		this.doseUnitF = doseUnitF;
	}

	public String getDrugInvolveE() {
		return drugInvolveE;
	}

	public void setDrugInvolveE(String drugInvolveE) {
		this.drugInvolveE = drugInvolveE;
	}

	public String getDrugInvolveF() {
		return drugInvolveF;
	}

	public void setDrugInvolveF(String drugInvolveF) {
		this.drugInvolveF = drugInvolveF;
	}

	public String getTherapyDurationUnitE() {
		return therapyDurationUnitE;
	}

	public void setTherapyDurationUnitE(String durationUnitE) {
		this.therapyDurationUnitE = durationUnitE;
	}

	public String getTherapyDurationUnitF() {
		return therapyDurationUnitF;
	}

	public void setTherapyDurationUnitF(String durationUnitF) {
		this.therapyDurationUnitF = durationUnitF;
	}

	public String getFreqTimeUnitE() {
		return freqTimeUnitE;
	}

	public void setFreqTimeUnitE(String freqTimeUnitE) {
		this.freqTimeUnitE = freqTimeUnitE;
	}

	public String getFreqTimeUnitF() {
		return freqTimeUnitF;
	}

	public void setFreqTimeUnitF(String freqTimeUnitF) {
		this.freqTimeUnitF = freqTimeUnitF;
	}

	public String getGenderNameE() {
		return genderNameE;
	}

	public void setGenderNameE(String genderNameE) {
		this.genderNameE = genderNameE;
	}

	public String getGenderNameF() {
		return genderNameF;
	}

	public void setGenderNameF(String genderNameF) {
		this.genderNameF = genderNameF;
	}

	public String getHeightUnitE() {
		return heightUnitE;
	}

	public void setHeightUnitE(String heightUnitE) {
		this.heightUnitE = heightUnitE;
	}

	public String getHeightUnitF() {
		return heightUnitF;
	}

	public void setHeightUnitF(String heightUnitF) {
		this.heightUnitF = heightUnitF;
	}

	public String getOutcomeNameE() {
		return outcomeNameE;
	}

	public void setOutcomeNameE(String outcomeNameE) {
		this.outcomeNameE = outcomeNameE;
	}

	public String getOutcomeNameF() {
		return outcomeNameF;
	}

	public void setOutcomeNameF(String outcomeNameF) {
		this.outcomeNameF = outcomeNameF;
	}

	public String getPtNameE() {
		return ptNameE;
	}

	public void setPtNameE(String ptNameE) {
		this.ptNameE = ptNameE;
	}

	public String getPtNameF() {
		return ptNameF;
	}

	public void setPtNameF(String ptNameF) {
		this.ptNameF = ptNameF;
	}

	public String getReporterTypeE() {
		return reporterTypeE;
	}

	public void setReporterTypeE(String reporterTypeE) {
		this.reporterTypeE = reporterTypeE;
	}

	public String getReporterTypeF() {
		return reporterTypeF;
	}

	public void setReporterTypeF(String reporterTypeF) {
		this.reporterTypeF = reporterTypeF;
	}

	public String getReportFeatureE() {
		return reportFeatureE;
	}

	public void setReportFeatureE(String reportFeatureE) {
		this.reportFeatureE = reportFeatureE;
	}

	public String getReportFeatureF() {
		return reportFeatureF;
	}

	public void setReportFeatureF(String reportFeatureF) {
		this.reportFeatureF = reportFeatureF;
	}

	public String getReportTypeE() {
		return reportTypeE;
	}

	public void setReportTypeE(String reportTypeE) {
		this.reportTypeE = reportTypeE;
	}

	public String getReportTypeF() {
		return reportTypeF;
	}

	public void setReportTypeF(String reportTypeF) {
		this.reportTypeF = reportTypeF;
	}

	public String getRouteAdminE() {
		return routeAdminE;
	}

	public void setRouteAdminE(String routeAdminE) {
		this.routeAdminE = routeAdminE;
	}

	public String getRouteAdminF() {
		return routeAdminF;
	}

	public void setRouteAdminF(String routeAdminF) {
		this.routeAdminF = routeAdminF;
	}

	public String getSeriousnessE() {
		return seriousnessE;
	}

	public void setSeriousnessE(String seriousnessE) {
		this.seriousnessE = seriousnessE;
	}

	public String getSeriousnessF() {
		return seriousnessF;
	}

	public void setSeriousnessF(String seriousnessF) {
		this.seriousnessF = seriousnessF;
	}

	public String getSocNameE() {
		return socNameE;
	}

	public void setSocNameE(String socNameE) {
		this.socNameE = socNameE;
	}

	public String getSocNameF() {
		return socNameF;
	}

	public void setSocNameF(String socNameF) {
		this.socNameF = socNameF;
	}

	public String getSourceE() {
		return sourceE;
	}

	public void setSourceE(String sourceE) {
		this.sourceE = sourceE;
	}

	public String getSourceF() {
		return sourceF;
	}

	public void setSourceF(String sourceF) {
		this.sourceF = sourceF;
	}

	public String getWeightUnitE() {
		return weightUnitE;
	}

	public void setWeightUnitE(String weightUnitE) {
		this.weightUnitE = weightUnitE;
	}

	public String getWeightUnitF() {
		return weightUnitF;
	}

	public void setWeightUnitF(String weightUnitF) {
		this.weightUnitF = weightUnitF;
	}

	public String getCongenitalAnomaly() {
		return congenitalAnomaly;
	}

	public void setCongenitalAnomaly(String congenitalAnomaly) {
		this.congenitalAnomaly = congenitalAnomaly;
	}

	public String getDeath() {
		return death;
	}

	public void setDeath(String death) {
		this.death = death;
	}

	public String getDisability() {
		return disability;
	}

	public void setDisability(String disability) {
		this.disability = disability;
	}

	public String getHospitalization() {
		return hospitalization;
	}

	public void setHospitalization(String hospitalization) {
		this.hospitalization = hospitalization;
	}

	public String getLifeThreatening() {
		return lifeThreatening;
	}

	public void setLifeThreatening(String lifeThreatening) {
		this.lifeThreatening = lifeThreatening;
	}

	public String getOtherMedCondition() {
		return otherMedCondition;
	}

	public void setOtherMedCondition(String otherMedCondition) {
		this.otherMedCondition = otherMedCondition;
	}

	public String getRouteOfAdministration() {
		if (isLanguageFrench()) {
			return getRouteAdminF();
		}
		return getRouteAdminE();		
	}

	public void setRouteOfAdministration(String routeOfAdministration) {
		this.routeOfAdministration = routeOfAdministration;
	}

	public String getDosageFormE() {
		return dosageFormE;
	}

	public void setDosageFormE(String dosageFormE) {
		this.dosageFormE = dosageFormE;
	}

	public String getDosageFormF() {
		return dosageFormF;
	}

	public void setDosageFormF(String dosageFormF) {
		this.dosageFormF = dosageFormF;
	}

	public String getDoseUnit() {
		if (isLanguageFrench()) {
			return getDoseUnitF();
		}
		return getDoseUnitE();		
	}

	public String getFreqTimeUnit() {
		if (isLanguageFrench()) {
			return getFreqTimeUnitF();
		}
		return getFreqTimeUnitE();			
	}

	public String getHeightUnit() {
		if (isLanguageFrench()) {
			return getHeightUnitF();
		}
		return getHeightUnitE();			
	}

	public String getTherapyDurationUnit() {
		if (isLanguageFrench()) {
			return getTherapyDurationUnitF();
		}
		return getTherapyDurationUnitE();		
	}

	public String getWeightUnit() {
		if (isLanguageFrench()) {
			return getWeightUnitF();
		}
		return getWeightUnitE();	
	}

	public String getReactionDurationUnitE() {
		return reactionDurationUnitE;
	}

	public void setReactionDurationUnitE(String reactionDurationUnitE) {
		this.reactionDurationUnitE = reactionDurationUnitE;
	}

	public String getReactionDurationUnitF() {
		return reactionDurationUnitF;
	}

	public void setReactionDurationUnitF(String reactionDurationUnitF) {
		this.reactionDurationUnitF = reactionDurationUnitF;
	}

	public String getReactionDurationUnit() {
		if (isLanguageFrench()) {
			return getReactionDurationUnitF();
		}
		return getReactionDurationUnitE();	
	}

	public String getProductRole() {
		if (isLanguageFrench()) {
			return getDrugInvolveF();
		}
		return getDrugInvolveE();	
	}

	public String getAgeUnit() {
		if (isLanguageFrench()) {
			return getAgeUnitF();
		}
		return getAgeUnitE();
	}

	public void setAgeUnit(String ageUnit) {
		this.ageUnit = ageUnit;
	}

	public Date getInitialDateReceivedD() {
		if (!this.initialDateReceived.equals("")){
			try {
				initialDateReceivedD = df.parse(this.initialDateReceived);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return initialDateReceivedD;
	}

	public Date getLatestDateReceivedD() {
		if (!this.latestDateReceived.equals("")){
			try {
				latestDateReceivedD = df.parse(this.latestDateReceived);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return latestDateReceivedD;
	}

}
