package ca.gc.hc.bean;

import java.util.Date;

import ca.gc.hc.model.Report;
import ca.gc.hc.util.LocaleDependantObject;


public class SearchResultsRowObject extends LocaleDependantObject {

    private Long adrID;
    private Long reportID;
    private String reportNumber;
    private Long versionNumber;
    private Date dateReceived;
    private Date initialDateReceived;
    private String mahNumber;
    private String reportFeatureCode;
    private String reportFeatureE;
    private String reportFeatureF;
    private String reportTypeCode;
    private String reportTypeE;
    private String reportTypeF;
    private String genderCode;
    private String genderNameE;
    private String genderNameF;
    private Long age;
    private String ageUnitE;
    private String ageUnitF;
    private String ageGroupCode;
    private String outcomeCode;
    private String outcomeNameE;
    private String outcomeNameF;
    private Long weight;
    private String weightUnitE;
    private String weightUnitF;
    private Long height;
    private String heightUnitE;
    private String heightUnitF;

    private String seriousnessCode;
    private String seriousnessNameE;
    private String seriousnessNameF;
    private String death;
    private String disability;
    private String congenitalAnomaly;
    private String lifeThreatening;
    private String hospitalRequired;
    private String otherMedicalCondition;
    private String reporterTypeNameE;
    private String reporterTypeNameF;
    private String reportSourceCode;
    private String reportSourceNameE;
    private String reportSourceNameF;
    private Long reportLinkFlag;

    private Long reactionID;
    private Long duration;
    private String durationUnitCode;
    private String durationUnitE;
    private String durationUnitF;
    private Long ptCode;
    private String ptNameE;
    private String ptNameF;
    private Long socCode;
    private String socNameE;
    private String socNameF;
    private String meddraVersion;

    private Long reportDrugID;
    private String drugName;
    private String drugInvolvCode;
    private String drugInvolvNameE;
    private String drugInvolvNameF;
    private String routeAdminNameE;
    private String routeAdminNameF;
    private Double unitDoseQty;
    private String doseUnitNameE;
    private String doseUnitNameF;
    private Long frequency;
    private String freqTimeUnitNameE;
    private String freqTimeUnitNameF;
    private Double therapyDuration;
    private String therapyDurationUnitNameE;
    private String therapyDurationUnitNameF;
    private String indicationNameE;
    private String indicationNameF;
    private String dosageFormNameE;
    private String dosageFormNameF;
    private Report report = new Report();
    private Long drugProductID;

    // variables to retrieve localized names of different objects
    private String genderName = "";
    private String outcomeName = "";
    private String drugsList;
    private String ptNames;
    private String searchedDrug;

    /**
     * no parameter default constructor.
     */
    public SearchResultsRowObject() {}

    public SearchResultsRowObject(Long adrID, Long reportID, String reportNumber, Long versionNumber,
	    Date dateReceived, Date initialDateReceived, String mahNumber, String reportFeatureE,
	    String reportFeatureF, String reportTypeE, String reportTypeF, String genderNameE, String genderNameF,
	    Long age, String ageUnitE, String ageUnitF, String ageGroupCode, String outcomeNameE, String outcomeNameF,
	    Long weight, String weightUnitE, String weightUnitF, Long height, String heightUnitE, String heightUnitF,
	    String seriousnessNameE, String seriousnessNameF, String death, String disability,
	    String congenitalAnomaly, String lifeThreatening, String hospitalRequired, String otherMedicalCondition,
	    String reporterTypeNameE, String reporterTypeNameF, String reportSourceNameE, String reportSourceNameF,
	    Long reportLinkFlag, Long duration, String durationUnitE, String durationUnitF, String ptNameE,
	    String ptNameF, String socNameE, String socNameF, String meddraVersion, Long reportDrugID, String drugName,
	    String drugInvolvNameE, String drugInvolvNameF, String routeAdminNameE, String routeAdminNameF,
	    Double unitDoseQty, String doseUnitNameE, String doseUnitNameF, Long frequency, String freqTimeUnitNameE,
	    String freqTimeUnitNameF, Double therapyDuration, String therapyDurationUnitNameE,
	    String therapyDurationUnitNameF, String indicationNameE, String indicationNameF, String dosageFormNameE,
	    String dosageFormNameF, Long drugProductID) {
	super();
	this.adrID = adrID;
	this.reportID = reportID;
	this.reportNumber = reportNumber;
	this.versionNumber = versionNumber;
	this.dateReceived = dateReceived;
	this.initialDateReceived = initialDateReceived;
	this.mahNumber = mahNumber;
	this.reportFeatureE = reportFeatureE;
	this.reportFeatureF = reportFeatureF;
	this.reportTypeE = reportTypeE;
	this.reportTypeF = reportTypeF;
	this.genderNameE = genderNameE;
	this.genderNameF = genderNameF;
	this.age = age;
	this.ageUnitE = ageUnitE;
	this.ageUnitF = ageUnitF;
	this.ageGroupCode = ageGroupCode;
	this.outcomeNameE = outcomeNameE;
	this.outcomeNameF = outcomeNameF;
	this.weight = weight;
	this.weightUnitE = weightUnitE;
	this.weightUnitF = weightUnitF;
	this.height = height;
	this.heightUnitE = heightUnitE;
	this.heightUnitF = heightUnitF;
	this.seriousnessNameE = seriousnessNameE;
	this.seriousnessNameF = seriousnessNameF;
	this.death = death;
	this.disability = disability;
	this.congenitalAnomaly = congenitalAnomaly;
	this.lifeThreatening = lifeThreatening;
	this.hospitalRequired = hospitalRequired;
	this.otherMedicalCondition = otherMedicalCondition;
	this.reporterTypeNameE = reporterTypeNameE;
	this.reporterTypeNameF = reporterTypeNameF;
	this.reportSourceNameE = reportSourceNameE;
	this.reportSourceNameF = reportSourceNameF;
	this.reportLinkFlag = reportLinkFlag;
	this.duration = duration;
	this.durationUnitE = durationUnitE;
	this.durationUnitF = durationUnitF;
	this.ptNameE = ptNameE;
	this.ptNameF = ptNameF;
	this.socNameE = socNameE;
	this.socNameF = socNameF;
	this.meddraVersion = meddraVersion;
	this.reportDrugID = reportDrugID;
	this.drugName = drugName;
	this.drugInvolvNameE = drugInvolvNameE;
	this.drugInvolvNameF = drugInvolvNameF;
	this.routeAdminNameE = routeAdminNameE;
	this.routeAdminNameF = routeAdminNameF;
	this.unitDoseQty = unitDoseQty;
	this.doseUnitNameE = doseUnitNameE;
	this.doseUnitNameF = doseUnitNameF;
	this.frequency = frequency;
	this.freqTimeUnitNameE = freqTimeUnitNameE;
	this.freqTimeUnitNameF = freqTimeUnitNameF;
	this.therapyDuration = therapyDuration;
	this.therapyDurationUnitNameE = therapyDurationUnitNameE;
	this.therapyDurationUnitNameF = therapyDurationUnitNameF;
	this.indicationNameE = indicationNameE;
	this.indicationNameF = indicationNameF;
	this.dosageFormNameE = dosageFormNameE;
	this.dosageFormNameF = dosageFormNameF;
	this.drugProductID = drugProductID;
    }

    public Long getAge() {
	return age;
    }

    public void setAge(Long age) {
	this.age = age;
    }

    public String getAgeGroupCode() {
	return ageGroupCode;
    }

    public void setAgeGroupCode(String ageGroupCode) {
	this.ageGroupCode = ageGroupCode;
    }

    public String getCongenitalAnomaly() {
	return congenitalAnomaly;
    }

    public void setCongenitalAnomaly(String congenitalAnomaly) {
	this.congenitalAnomaly = congenitalAnomaly;
    }

    public Date getDateReceived() {
	return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
	this.dateReceived = dateReceived;
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

    public Long getHeight() {
	return height;
    }

    public void setHeight(Long height) {
	this.height = height;
    }

    public String getHospitalRequired() {
	return hospitalRequired;
    }

    public void setHospitalRequired(String hospitalRequired) {
	this.hospitalRequired = hospitalRequired;
    }

    public Date getInitialDateReceived() {
	return initialDateReceived;
    }

    public void setInitialDateReceived(Date initialDateReceived) {
	this.initialDateReceived = initialDateReceived;
    }

    public String getMahNumber() {
	return mahNumber;
    }

    public void setMahNumber(String mahNumber) {
	this.mahNumber = mahNumber;
    }

    public String getLifeThreatening() {
	return lifeThreatening;
    }

    public void setLifeThreatening(String lifeThreatening) {
	this.lifeThreatening = lifeThreatening;
    }

    public String getOtherMedicalCondition() {
	return otherMedicalCondition;
    }

    public void setOtherMedicalCondition(String otherMedicalCondition) {
	this.otherMedicalCondition = otherMedicalCondition;
    }

    public String getReportFeatureCode() {
	return reportFeatureCode;
    }

    public void setReportFeatureCode(String reportFeatureCode) {
	this.reportFeatureCode = reportFeatureCode;
    }

    public String getReportNumber() {
	return reportNumber;
    }

    public void setReportNumber(String reportNumber) {
	this.reportNumber = reportNumber;
    }

    public String getReportSourceCode() {
	return reportSourceCode;
    }

    public void setReportSourceCode(String reportSourceCode) {
	this.reportSourceCode = reportSourceCode;
    }

    public String getReportTypeCode() {
	return reportTypeCode;
    }

    public void setReportTypeCode(String reportTypeCode) {
	this.reportTypeCode = reportTypeCode;
    }

    public String getSeriousnessCode() {
	return seriousnessCode;
    }

    public void setSeriousnessCode(String seriousnessCode) {
	this.seriousnessCode = seriousnessCode;
    }

    public Long getVersionNumber() {
	return versionNumber;
    }

    public void setVersionNumber(Long versionNumber) {
	this.versionNumber = versionNumber;
    }

    public Long getWeight() {
	return weight;
    }

    public void setWeight(Long weight) {
	this.weight = weight;
    }

    public Long getAdrID() {
	return adrID;
    }

    public void setAdrID(Long adrID) {
	this.adrID = adrID;
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

    public String getDrugsList() {
	return drugsList;
    }

    public void setDrugsList(String drugsList) {
	this.drugsList = drugsList;
    }

    public Long getDuration() {
	return duration;
    }

    public void setDuration(Long duration) {
	this.duration = duration;
    }

    public String getDurationUnitCode() {
	return durationUnitCode;
    }

    public void setDurationUnitCode(String durationUnitCode) {
	this.durationUnitCode = durationUnitCode;
    }

    public String getDurationUnitE() {
	return durationUnitE;
    }

    public void setDurationUnitE(String durationUnitE) {
	this.durationUnitE = durationUnitE;
    }

    public String getDurationUnitF() {
	return durationUnitF;
    }

    public void setDurationUnitF(String durationUnitF) {
	this.durationUnitF = durationUnitF;
    }

    public String getGenderCode() {
	return genderCode;
    }

    public void setGenderCode(String genderCode) {
	this.genderCode = genderCode;
    }

    public String getGenderName() {
	return genderName;
    }

    public void setGenderName(String genderName) {
	this.genderName = genderName;
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

    public void setHeightUnitE(String heightCodeE) {
	this.heightUnitE = heightCodeE;
    }

    public String getHeightUnitF() {
	return heightUnitF;
    }

    public void setHeightUnitF(String heightCodeF) {
	this.heightUnitF = heightCodeF;
    }

    public String getMeddraVersion() {
	return meddraVersion;
    }

    public void setMeddraVersion(String meddraVersion) {
	this.meddraVersion = meddraVersion;
    }

    public String getOutcomeCode() {
	return outcomeCode;
    }

    public void setOutcomeCode(String outcomeCode) {
	this.outcomeCode = outcomeCode;
    }

    public String getOutcomeName() {
	return outcomeName;
    }

    public void setOutcomeName(String outcomeName) {
	this.outcomeName = outcomeName;
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

    public Long getPtCode() {
	return ptCode;
    }

    public void setPtCode(Long ptCode) {
	this.ptCode = ptCode;
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

    public String getPtNames() {
	return ptNames;
    }

    public void setPtNames(String ptNames) {
	this.ptNames = ptNames;
    }

    public Long getReactionID() {
	return reactionID;
    }

    public void setReactionID(Long reactionID) {
	this.reactionID = reactionID;
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

    public Long getReportLinkFlag() {
	return reportLinkFlag;
    }

    public void setReportLinkFlag(Long reportLinkFlag) {
	this.reportLinkFlag = reportLinkFlag;
    }

    public String getReportSourceNameE() {
	return reportSourceNameE;
    }

    public void setReportSourceNameE(String reportSourceNameE) {
	this.reportSourceNameE = reportSourceNameE;
    }

    public String getReportSourceNameF() {
	return reportSourceNameF;
    }

    public void setReportSourceNameF(String reportSourceNameF) {
	this.reportSourceNameF = reportSourceNameF;
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

    public String getSearchedDrug() {
	return searchedDrug;
    }

    public void setSearchedDrug(String searchedDrug) {
	this.searchedDrug = searchedDrug;
    }

    public String getSeriousnessNameE() {
	return seriousnessNameE;
    }

    public void setSeriousnessNameE(String seriousnessNameE) {
	this.seriousnessNameE = seriousnessNameE;
    }

    public String getSeriousnessNameF() {
	return seriousnessNameF;
    }

    public void setSeriousnessNameF(String seriousnessNameF) {
	this.seriousnessNameF = seriousnessNameF;
    }

    public Long getSocCode() {
	return socCode;
    }

    public void setSocCode(Long socCode) {
	this.socCode = socCode;
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

    public String getWeightUnitE() {
	return weightUnitE;
    }

    public void setWeightUnitE(String weightCodeE) {
	this.weightUnitE = weightCodeE;
    }

    public String getWeightUnitF() {
	return weightUnitF;
    }

    public void setWeightUnitF(String weightCodeF) {
	this.weightUnitF = weightCodeF;
    }

    public String getDrugInvolvCode() {
	return drugInvolvCode;
    }

    public void setDrugInvolvCode(String drugCode) {
	this.drugInvolvCode = drugCode;
    }

    public Long getFrequency() {
	return frequency;
    }

    public void setFrequency(Long frequency) {
	this.frequency = frequency;
    }

    public Double getTherapyDuration() {
	return therapyDuration;
    }

    public void setTherapyDuration(Double therapyDuration) {
	this.therapyDuration = therapyDuration;
    }

    public Double getUnitDoseQty() {
	return unitDoseQty;
    }

    public void setUnitDoseQty(Double unitDoseQty) {
	this.unitDoseQty = unitDoseQty;
    }

    public Long getReportDrugID() {
	return reportDrugID;
    }

    public void setReportDrugID(Long drugID) {
	this.reportDrugID = drugID;
    }

    public String getDrugName() {
	return drugName;
    }

    public void setDrugName(String drugName) {
	this.drugName = drugName;
    }

    public Long getDrugProductID() {
	return drugProductID;
    }

    public void setDrugProductID(Long productID) {
	this.drugProductID = productID;
    }

    public Report getReport() {
	return report;
    }

    public void setReport(Report report) {
	this.report = report;
    }

    public String getDosageFormNameE() {
	return dosageFormNameE;
    }

    public void setDosageFormNameE(String dosageFormNameE) {
	this.dosageFormNameE = dosageFormNameE;
    }

    public String getDosageFormNameF() {
	return dosageFormNameF;
    }

    public void setDosageFormNameF(String dosageFormNameF) {
	this.dosageFormNameF = dosageFormNameF;
    }

    public String getDoseUnitNameE() {
	return doseUnitNameE;
    }

    public void setDoseUnitNameE(String doseUnitNameE) {
	this.doseUnitNameE = doseUnitNameE;
    }

    public String getDoseUnitNameF() {
	return doseUnitNameF;
    }

    public void setDoseUnitNameF(String doseUnitNameF) {
	this.doseUnitNameF = doseUnitNameF;
    }

    public String getDrugInvolvNameE() {
	return drugInvolvNameE;
    }

    public void setDrugInvolvNameE(String drugInvolvNameE) {
	this.drugInvolvNameE = drugInvolvNameE;
    }

    public String getDrugInvolvNameF() {
	return drugInvolvNameF;
    }

    public void setDrugInvolvNameF(String drugInvolvNameF) {
	this.drugInvolvNameF = drugInvolvNameF;
    }

    public String getFreqTimeUnitNameE() {
	return freqTimeUnitNameE;
    }

    public void setFreqTimeUnitNameE(String freqTimeUnitNameE) {
	this.freqTimeUnitNameE = freqTimeUnitNameE;
    }

    public String getFreqTimeUnitNameF() {
	return freqTimeUnitNameF;
    }

    public void setFreqTimeUnitNameF(String freqTimeUnitNameF) {
	this.freqTimeUnitNameF = freqTimeUnitNameF;
    }

    public String getRouteAdminNameE() {
	return routeAdminNameE;
    }

    public void setRouteAdminNameE(String routeAdminNameE) {
	this.routeAdminNameE = routeAdminNameE;
    }

    public String getRouteAdminNameF() {
	return routeAdminNameF;
    }

    public void setRouteAdminNameF(String routeAdminNameF) {
	this.routeAdminNameF = routeAdminNameF;
    }

    public String getTherapyDurationUnitNameE() {
	return therapyDurationUnitNameE;
    }

    public void setTherapyDurationUnitNameE(String therapyDurationUnitNameE) {
	this.therapyDurationUnitNameE = therapyDurationUnitNameE;
    }

    public String getTherapyDurationUnitNameF() {
	return therapyDurationUnitNameF;
    }

    public void setTherapyDurationUnitNameF(String therapyDurationUnitNameF) {
	this.therapyDurationUnitNameF = therapyDurationUnitNameF;
    }

    public String getIndicationNameE() {
	return indicationNameE;
    }

    public void setIndicationNameE(String indicationNameE) {
	this.indicationNameE = indicationNameE;
    }

    public String getIndicationNameF() {
	return indicationNameF;
    }

    public void setIndicationNameF(String indicationNameF) {
	this.indicationNameF = indicationNameF;
    }

    public Long getReportID() {
	return reportID;
    }

    public void setReportID(Long reportID) {
	this.reportID = reportID;
    }

}
