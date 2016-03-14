package ca.gc.hc.view;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ReportDrugForm extends ActionForm {

	private String	fDrugId;
	private String	fDrugName;
	private String	fDosageFormCode;
	private String	fDrugInvolvCode;
	private String	fRouteAdminCode;
	private String	fUnitDoseQty;
	private String	fDoseUnitCode;
	private String	fFrequency;
	private String	fFreqTimeUnitCode;
	private String	fTherapyDuration;
	private String	fTherapyDurationUnitCode;
	private String	fProductID;
	private String	fReportID;
	
	// Used to display name value on reportInfo page
	private String	fDosageFormName;
	private String	fDrugInvolvName;
	private String	fRouteAdminName;
	private String	fDoseUnitName;
	private String	fFreqTimeUnitName;
	private String	fTherapyDurationUnitName;

	public void reset (ActionMapping mapping, HttpServletRequest request){
		fDrugId = "";
		fDrugName = "";
		fDosageFormCode = "";
		fDrugInvolvCode = "";
		fRouteAdminCode = "";
		fUnitDoseQty = "";
		fDoseUnitCode = "";
		fFrequency = "";
		fFreqTimeUnitCode = "";
		fTherapyDuration = "";
		fTherapyDurationUnitCode = "";
		fProductID = "";
		fReportID = "";

		// Used to display name value on reportInfo page
		fDrugInvolvName = "";
		fRouteAdminName = "";
		fDoseUnitName = "";
		fFreqTimeUnitName = "";
		fTherapyDurationUnitName = "";
		fDosageFormName = "";
	}

	
	public String getFDosageFormCode() {
		return fDosageFormCode;
	}

	public void setFDosageFormCode(String dosageFormCode) {
		fDosageFormCode = dosageFormCode;
	}

	public String getFDosageFormName() {
		return fDosageFormName;
	}

	public void setFDosageFormName(String dosageFormName) {
		fDosageFormName = dosageFormName;
	}

	public String getFDoseUnitCode() {
		return fDoseUnitCode;
	}

	public void setFDoseUnitCode(String doseUnitCode) {
		fDoseUnitCode = doseUnitCode;
	}

	public String getFDoseUnitName() {
		return fDoseUnitName;
	}

	public void setFDoseUnitName(String doseUnitName) {
		fDoseUnitName = doseUnitName;
	}

	public String getFDrugId() {
		return fDrugId;
	}

	public void setFDrugId(String drugId) {
		fDrugId = drugId;
	}

	public String getFDrugInvolvCode() {
		return fDrugInvolvCode;
	}

	public void setFDrugInvolvCode(String drugInvolvCode) {
		fDrugInvolvCode = drugInvolvCode;
	}

	public String getFDrugInvolvName() {
		return fDrugInvolvName;
	}

	public void setFDrugInvolvName(String drugInvolvName) {
		fDrugInvolvName = drugInvolvName;
	}

	public String getFDrugName() {
		return fDrugName;
	}

	public void setFDrugName(String drugName) {
		fDrugName = drugName;
	}

	public String getFFreqTimeUnitCode() {
		return fFreqTimeUnitCode;
	}

	public void setFFreqTimeUnitCode(String freqTimeUnitCode) {
		fFreqTimeUnitCode = freqTimeUnitCode;
	}

	public String getFFreqTimeUnitName() {
		return fFreqTimeUnitName;
	}

	public void setFFreqTimeUnitName(String freqTimeUnitName) {
		fFreqTimeUnitName = freqTimeUnitName;
	}

	public String getFFrequency() {
		return fFrequency;
	}

	public void setFFrequency(String frequency) {
		fFrequency = frequency;
	}

	public String getFProductID() {
		return fProductID;
	}

	public void setFProductID(String productID) {
		fProductID = productID;
	}

	public String getFReportID() {
		return fReportID;
	}

	public void setFReportID(String reportID) {
		fReportID = reportID;
	}

	public String getFRouteAdminCode() {
		return fRouteAdminCode;
	}

	public void setFRouteAdminCode(String routeAdminCode) {
		fRouteAdminCode = routeAdminCode;
	}

	public String getFRouteAdminName() {
		return fRouteAdminName;
	}


	public void setFRouteAdminName(String routeAdminName) {
		fRouteAdminName = routeAdminName;
	}

	public String getFTherapyDuration() {
		return fTherapyDuration;
	}

	public void setFTherapyDuration(String therapyDuration) {
		fTherapyDuration = therapyDuration;
	}

	public String getFTherapyDurationUnitCode() {
		return fTherapyDurationUnitCode;
	}

	public void setFTherapyDurationUnitCode(String therapyDurationUnitCode) {
		fTherapyDurationUnitCode = therapyDurationUnitCode;
	}

	public String getFTherapyDurationUnitName() {
		return fTherapyDurationUnitName;
	}

	public void setFTherapyDurationUnitName(String therapyDurationUnitName) {
		fTherapyDurationUnitName = therapyDurationUnitName;
	}

	public String getFUnitDoseQty() {
		return fUnitDoseQty;
	}

	public void setFUnitDoseQty(String unitDoseQty) {
		fUnitDoseQty = unitDoseQty;
	}
	
}
