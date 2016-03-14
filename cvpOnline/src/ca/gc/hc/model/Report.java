package ca.gc.hc.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import ca.gc.hc.util.LocaleDependantObject;


public class Report extends LocaleDependantObject {

    private Long reportID;
    private String reportNumber;
    private Long versionNumber;
    private Date dateReceived;
    private Date initialDateReceived;
    private String mahNumber;
    // private String reportFeatureCode;
    // private String reportFeatureName;
    // private String reportFeatureNameE;
    // private String reportFeatureNameF;
    private String reportTypeCode;
    private String reportTypeName;
    private String reportTypeNameE;
    private String reportTypeNameF;
    private String genderCode;
    private String genderName;
    private String genderNameE;
    private String genderNameF;
    private Long age;
    private Double ageYears;
    private String ageUnitCode;
    private String ageUnitName;
    private String ageUnitNameE;
    private String ageUnitNameF;
    private String ageGroupCode;
    private String ageGroupName;
    private String ageGroupNameE;
    private String ageGroupNameF;
    private String outcomeCode;
    private String outcomeName;
    private String outcomeNameE;
    private String outcomeNameF;
    private Long weight;
    private String weightUnitCode;
    private String weightUnitName;
    private String weightUnitNameE;
    private String weightUnitNameF;
    private Long height;
    private String heightUnitCode;
    private String heightUnitName;
    private String heightUnitNameE;
    private String heightUnitNameF;
    private String seriousnessCode;
    private String seriousnessName;
    private String seriousnessNameE;
    private String seriousnessNameF;
    private String death;
    private String disability;
    private String congenitalAnomaly;
    private String lifeThreatening;
    private String hospitalRequired;
    private String otherMedicalCondition;
    private String reporterTypeCode;
    private String reporterTypeName;
    private String reporterTypeNameE;
    private String reporterTypeNameF;
    private String reportSourceCode;
    private String reportSourceName;
    private String reportSourceNameE;
    private String reportSourceNameF;
    private String recordTypeCode;
    private String reportLinkFlg;
    private Long ptName;
    private String ptNameE;
    private String ptNameF;
    private String socName;
    private String socNameE;
    private String socNameF;
    private Long duration;
    private String durationUnit;
    private String durationUnitE;
    private String durationUnitF;
    private String drugName;

    private Set drugs = new HashSet();
    private Set reactions = new HashSet();

    // variables to retrieve localized names of different objects
    private String drugsList;
    private String ptNames;
    private String searchedDrug;
    private Long aerID;

    /**
     * no parameter default constructor.
     */
    public Report() {}

    public Long getAerID() {
	return aerID;
    }

    public void setAerID(Long aerID) {
	this.aerID = aerID;
    }

    public String getAgeGroupCode() {
	return ageGroupCode;
    }

    public void setAgeGroupCode(String ageGroupCode) {
	this.ageGroupCode = ageGroupCode;
    }

    public Double getAgeYears() {
	return ageYears;
    }

    public void setAgeYears(Double ageYears) {
	this.ageYears = ageYears;
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

    public String getRecordTypeCode() {
	return recordTypeCode;
    }

    public void setRecordTypeCode(String recordTypeCode) {
	this.recordTypeCode = recordTypeCode;
    }

    public Long getReportID() {
	return reportID;
    }

    public void setReportID(Long reportID) {
	this.reportID = reportID;
    }

    public String getReportNumber() {
	return reportNumber;
    }

    public void setReportNumber(String reportNumber) {
	this.reportNumber = reportNumber;
    }

    public String getReportTypeCode() {
	return reportTypeCode;
    }

    public void setReportTypeCode(String reportTypeCode) {
	this.reportTypeCode = reportTypeCode;
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

    public Set getDrugs() {
	return drugs;
    }

    public void setDrugs(Set drugs) {
	this.drugs = drugs;
    }

    public Set getReactions() {
	return reactions;
    }

    public void setReactions(Set reactions) {
	this.reactions = reactions;
    }

    public String getReportTypeNameE() {
	return reportTypeNameE;
    }

    public void setReportTypeNameE(String reportTypeNameE) {
	this.reportTypeNameE = reportTypeNameE;
    }

    public String getReportTypeNameF() {
	return reportTypeNameF;
    }

    public void setReportTypeNameF(String reportTypeNameF) {
	this.reportTypeNameF = reportTypeNameF;
    }

    public String getGenderCode() {
	return genderCode;
    }

    public void setGenderCode(String genderCode) {
	this.genderCode = genderCode;
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

    public Long getAge() {
	return age;
    }

    public void setAge(Long age) {
	this.age = age;
    }

    public String getAgeUnitCode() {
	return ageUnitCode;
    }

    public void setAgeUnitCode(String ageUnitCode) {
	this.ageUnitCode = ageUnitCode;
    }

    public String getAgeUnitNameE() {
	return ageUnitNameE;
    }

    public void setAgeUnitNameE(String ageUnitNameE) {
	this.ageUnitNameE = ageUnitNameE;
    }

    public String getAgeUnitNameF() {
	return ageUnitNameF;
    }

    public void setAgeUnitNameF(String ageUnitNameF) {
	this.ageUnitNameF = ageUnitNameF;
    }

    public String getOutcomeCode() {
	return outcomeCode;
    }

    public void setOutcomeCode(String outcomeCode) {
	this.outcomeCode = outcomeCode;
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

    public String getWeightUnitCode() {
	return weightUnitCode;
    }

    public void setWeightUnitCode(String weightUnitCode) {
	this.weightUnitCode = weightUnitCode;
    }

    public String getWeightUnitNameE() {
	return weightUnitNameE;
    }

    public void setWeightUnitNameE(String weightUnitNameE) {
	this.weightUnitNameE = weightUnitNameE;
    }

    public String getWeightUnitNameF() {
	return weightUnitNameF;
    }

    public void setWeightUnitNameF(String weightUnitNameF) {
	this.weightUnitNameF = weightUnitNameF;
    }

    public String getHeightUnitCode() {
	return heightUnitCode;
    }

    public void setHeightUnitCode(String heightUnitCode) {
	this.heightUnitCode = heightUnitCode;
    }

    public String getHeightUnitNameE() {
	return heightUnitNameE;
    }

    public void setHeightUnitNameE(String heightUnitNameE) {
	this.heightUnitNameE = heightUnitNameE;
    }

    public String getHeightUnitNameF() {
	return heightUnitNameF;
    }

    public void setHeightUnitNameF(String heightUnitNameF) {
	this.heightUnitNameF = heightUnitNameF;
    }

    public String getSeriousnessCode() {
	return seriousnessCode;
    }

    public void setSeriousnessCode(String seriousnessCode) {
	this.seriousnessCode = seriousnessCode;
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

    public String getReporterTypeCode() {
	return reporterTypeCode;
    }

    public void setReporterTypeCode(String reporterTypeCode) {
	this.reporterTypeCode = reporterTypeCode;
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

    public String getReportSourceCode() {
	return reportSourceCode;
    }

    public void setReportSourceCode(String reportSourceCode) {
	this.reportSourceCode = reportSourceCode;
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

    public String getReportLinkFlg() {
	return reportLinkFlg;
    }

    public void setReportLinkFlg(String reportLinkFlg) {
	this.reportLinkFlg = reportLinkFlg;
    }

    public String getAgeGroupNameE() {
	return ageGroupNameE;
    }

    public void setAgeGroupNameE(String ageGroupNameE) {
	this.ageGroupNameE = ageGroupNameE;
    }

    public String getAgeGroupNameF() {
	return ageGroupNameF;
    }

    public void setAgeGroupNameF(String ageGroupNameF) {
	this.ageGroupNameF = ageGroupNameF;
    }

    public String getDrugName() {
	return drugName;
    }

    public void setDrugName(String drugName) {
	this.drugName = drugName;
    }

    public Long getDuration() {
	return duration;
    }

    public void setDuration(Long duration) {
	this.duration = duration;
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

    /***************************************************************************
     * Bilingual fields adjustment based on the current Locale.
     */
    public String getReportTypeName() {
	if (isLanguageFrench()) {
	    return getReportTypeNameF();
	}
	return getReportTypeNameE();
    }

    public void setReportTypeName(String reportTypeName) {
	this.reportTypeName = reportTypeName;
    }

    public String getGenderName() {
	if (isLanguageFrench()) {
	    return getGenderNameF();
	}
	return getGenderNameE();
    }

    public void setGenderName(String genderName) {
	this.genderName = genderName;
    }

    public String getAgeUnitName() {
	if (isLanguageFrench()) {
	    return getAgeUnitNameF();
	}
	return getAgeUnitNameE();
    }

    public void setAgeUnitName(String ageUnitName) {
	this.ageUnitName = ageUnitName;
    }

    public String getOutcomeName() {
	if (isLanguageFrench()) {
	    return getOutcomeNameF();
	}
	return getOutcomeNameE();
    }

    public void setOutcomeName(String outcomeName) {
	this.outcomeName = outcomeName;
    }

    public String getWeightUnitName() {
	if (isLanguageFrench()) {
	    return getWeightUnitNameF();
	}
	return getWeightUnitNameE();
    }

    public void setWeightUnitName(String weightUnitName) {
	this.weightUnitName = weightUnitName;
    }

    public String getHeightUnitName() {
	if (isLanguageFrench()) {
	    return getHeightUnitNameF();
	}
	return getHeightUnitNameE();
    }

    public void setHeightUnitName(String heightUnitName) {
	this.heightUnitName = heightUnitName;
    }

    public String getSeriousnessName() {
	if (isLanguageFrench()) {
	    return getSeriousnessNameF();
	}
	return getSeriousnessNameE();
    }

    public void setSeriousnessName(String seriousnessName) {
	this.seriousnessName = seriousnessName;
    }

    public String getReportSourceName() {
	if (isLanguageFrench()) {
	    return getReportSourceNameF();
	}
	return getReportSourceNameE();
    }

    public void setReportSourceName(String reportSourceName) {
	this.reportSourceName = reportSourceName;
    }

    public String getReporterTypeName() {
	if (isLanguageFrench()) {
	    return getReporterTypeNameF();
	}
	return getReporterTypeNameE();
    }

    public void setReporterTypeName(String reporterTypeName) {
	this.reporterTypeName = reporterTypeName;
    }

    public String getAgeGroupName() {
	if (isLanguageFrench()) {
	    return getAgeGroupNameF();
	}
	return getAgeGroupNameE();
    }

    public void setAgeGroupName(String ageGroupName) {
	this.ageGroupName = ageGroupName;
    }

    public String getDurationUnit() {
	if (isLanguageFrench()) {
	    return getDurationUnitF();
	}
	return getDurationUnitE();
    }

    public void setDurationUnit(String durationUnit) {
	this.durationUnit = durationUnit;
    }

    public String getSocName() {
	if (isLanguageFrench()) {
	    return getSocNameF();
	}
	return getSocNameE();
    }

    public void setSocName(String socName) {
	this.socName = socName;
    }

    public String getPtName() {
	if (isLanguageFrench()) {
	    return getPtNameF();
	}
	return getPtNameE();
    }

    public void setPtName(Long ptName) {
	this.ptName = ptName;
    }

}
