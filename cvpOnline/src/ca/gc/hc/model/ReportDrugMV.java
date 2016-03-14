package ca.gc.hc.model;

public class ReportDrugMV {

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
    // private Drug drugProduct = new Drug();
    private Report report = new Report();
    private Long drugProductID;
    private Long reportID;

    // default constructor
    public ReportDrugMV() {
	this.drugInvolvCode = "";
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
