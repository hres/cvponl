package ca.gc.hc.model;

import ca.gc.hc.util.LocaleDependantObject;


public class ReportDrug extends LocaleDependantObject {

    private Long drugID;
    private Long reportID;
    private Long drugProductID;
    private String drugName;
    private String drugInvCode;
    private String drugInvDesc;
    private String drugInvDescE;
    private String drugInvDescF;
    private String routeAdminCode;
    private String routeAdminName;
    private String routeAdminNameE;
    private String routeAdminNameF;
    private Double unitDoseQty;
    private String doseUnitCode;
    private String doseUnitName;
    private String doseUnitNameE;
    private String doseUnitNameF;
    private Long frequency;
    private String freqTimeUnitCode;
    private String freqTimeUnitName;
    private String freqTimeUnitNameE;
    private String freqTimeUnitNameF;
    private String freqTime;
    private String freqTimeString;
    private String freqTimeStringE;
    private String freqTimeStringF;
    private Double therapyDuration;
    private String therapyDurationUnitCode;
    private String therapyDurationUnitName;
    private String therapyDurationUnitNameE;
    private String therapyDurationUnitNameF;
    private String indicationName;
    private String indicationNameE;
    private String indicationNameF;
    private String dosageFormCode;
    private String dosageFormName;
    private String dosageFormNameE;
    private String dosageFormNameF;
    private Long aerID;
    private Integer seqProduct;
    private Integer seqTherapy;
    private Long productID;

    private Report report = new Report();

    // default constructor
    public ReportDrug() {}

    public Long getFrequency() {
	return frequency;
    }

    public void setFrequency(Long frequency) {
	this.frequency = frequency;
    }

    public Long getReportID() {
	return reportID;
    }

    public void setReportID(Long reportID) {
	this.reportID = reportID;
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

    public Long getDrugID() {
	return drugID;
    }

    public void setDrugID(Long drugID) {
	this.drugID = drugID;
    }

    public Long getDrugProductID() {
	return drugProductID;
    }

    public void setDrugProductID(Long drugProductID) {
	this.drugProductID = drugProductID;
    }

    public String getDrugName() {
	return drugName;
    }

    public void setDrugName(String drugName) {
	this.drugName = drugName;
    }

    public Report getReport() {
	return report;
    }

    public void setReport(Report report) {
	this.report = report;
    }

    public String getDrugInvCode() {
	return drugInvCode;
    }

    public void setDrugInvCode(String drugInvCode) {
	this.drugInvCode = drugInvCode;
    }

    public String getDrugInvDescE() {
	return drugInvDescE;
    }

    public void setDrugInvDescE(String drugInvDescE) {
	this.drugInvDescE = drugInvDescE;
    }

    public String getDrugInvDescF() {
	return drugInvDescF;
    }

    public void setDrugInvDescF(String drugInvDescF) {
	this.drugInvDescF = drugInvDescF;
    }

    public String getRouteAdminCode() {
	return routeAdminCode;
    }

    public void setRouteAdminCode(String routeAdminCode) {
	this.routeAdminCode = routeAdminCode;
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

    public String getDoseUnitCode() {
	return doseUnitCode;
    }

    public void setDoseUnitCode(String doseUnitCode) {
	this.doseUnitCode = doseUnitCode;
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

    public String getFreqTimeUnitCode() {
	return freqTimeUnitCode;
    }

    public void setFreqTimeUnitCode(String freqTimeUnitCode) {
	this.freqTimeUnitCode = freqTimeUnitCode;
    }

    public String getFreqTimeUnitNameE() {
	return freqTimeUnitNameE;
    }

    public void setFreqTimeUnitNameE(String FreqTimeUnitNameE) {
	this.freqTimeUnitNameE = FreqTimeUnitNameE;
    }

    public String getFreqTimeUnitNameF() {
	return freqTimeUnitNameF;
    }

    public void setFreqTimeUnitNameF(String FreqTimeUnitNameF) {
	this.freqTimeUnitNameF = FreqTimeUnitNameF;
    }

    public String getTherapyDurationUnitCode() {
	return therapyDurationUnitCode;
    }

    public void setTherapyDurationUnitCode(String therapyDurationUnitCode) {
	this.therapyDurationUnitCode = therapyDurationUnitCode;
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

    public String getDosageFormCode() {
	return dosageFormCode;
    }

    public void setDosageFormCode(String dosageFormCode) {
	this.dosageFormCode = dosageFormCode;
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

    public void setDosageFormNameF(String dosageFornNameF) {
	this.dosageFormNameF = dosageFornNameF;
    }

    public Long getAerID() {
	return aerID;
    }

    public void setAerID(Long aerID) {
	this.aerID = aerID;
    }

    public Integer getSeqProduct() {
	return seqProduct;
    }

    public void setSeqProduct(Integer seqProduct) {
	this.seqProduct = seqProduct;
    }

    public Integer getSeqTherapy() {
	return seqTherapy;
    }

    public void setSeqTherapy(Integer seqTherapy) {
	this.seqTherapy = seqTherapy;
    }

    public Long getProductID() {
	return productID;
    }

    public void setProductID(Long productID) {
	this.productID = productID;
    }

    public String getFreqTime() {
	return freqTime;
    }

    public void setFreqTime(String freqTime) {
	this.freqTime = freqTime;
    }

    public String getFreqTimeStringE() {
	return freqTimeStringE;
    }

    public void setFreqTimeStringE(String freqTimeStringE) {
	this.freqTimeStringE = freqTimeStringE;
    }

    public String getFreqTimeStringF() {
	return freqTimeStringF;
    }

    public void setFreqTimeStringF(String freqTimeStringF) {
	this.freqTimeStringF = freqTimeStringF;
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

    /***************************************************************************
     * Bilingual fields adjustment based on the current Locale.
     */
    public String getDrugInvDesc() {
	if (isLanguageFrench()) {
	    return getDrugInvDescF();
	}
	return getDrugInvDescE();
    }

    public void setDrugInvDesc(String drugInvDesc) {
	this.drugInvDesc = drugInvDesc;
    }

    public String getRouteAdminName() {
	if (isLanguageFrench()) {
	    return getRouteAdminNameF();
	}
	return getRouteAdminNameE();
    }

    public void setRouteAdminName(String routeAdminName) {
	this.routeAdminName = routeAdminName;
    }

    public String getDoseUnitName() {
	if (isLanguageFrench()) {
	    return getDoseUnitNameF();
	}
	return getDoseUnitNameE();
    }

    public void setDoseUnitName(String doseUnitName) {
	this.doseUnitName = doseUnitName;
    }

    public String getFreqTimeUnitName() {
	if (isLanguageFrench()) {
	    return getFreqTimeUnitNameF();
	}
	return getFreqTimeUnitNameE();
    }

    public void setFreqTimeUnitName(String freqTimeUnitName) {
	this.freqTimeUnitName = freqTimeUnitName;
    }

    public String getDosageFormName() {
	if (isLanguageFrench()) {
	    return getDosageFormNameF();
	}
	return getDosageFormNameE();
    }

    public void setDosageFormName(String dosageFormName) {
	this.dosageFormName = dosageFormName;
    }

    public String getFreqTimeString() {
	if (isLanguageFrench()) {
	    return getFreqTimeStringF();
	}
	return getFreqTimeStringE();
    }

    public void setFreqTimeString(String freqTimeString) {
	this.freqTimeString = freqTimeString;
    }

    public String getTherapyDurationUnitName() {
	if (isLanguageFrench()) {
	    return getTherapyDurationUnitNameF();
	}
	return getTherapyDurationUnitNameE();
    }

    public void setTherapyDurationUnitName(String therapyDurationUnitName) {
	this.therapyDurationUnitName = therapyDurationUnitName;
    }

    public String getIndicationName() {
	if (isLanguageFrench()) {
	    return getIndicationNameF();
	}
	return getIndicationNameE();
    }

    public void setIndicationName(String indicationName) {
	this.indicationName = indicationName;
    }

}
