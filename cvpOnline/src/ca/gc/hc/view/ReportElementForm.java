package ca.gc.hc.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import ca.gc.hc.bean.DisplayBean;
import ca.gc.hc.util.ApplicationGlobals;

public class ReportElementForm extends ActionForm {
	
	private List selectedItems;
	private String[] selectedElements = {};

	private String reportID;
	private String versionNo;
	private String mahNumber;
	private String initialDate;
	private String latestDate;
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
	private String productRole;
	private String route;
	private String amount;
	private String frequency;
	private String therapyDuration;
	
	private String meddraPTName;
	private String meddraSOCname;
	private String reactionDuration;
	private String activeIngredient;
	
	private String reportFormView = "1";
	

	public void reset (ActionMapping mapping, HttpServletRequest request){
		// pre-selected default
		if ((request.getAttribute(ApplicationGlobals.DEFAULT_ELEMENTS)) != null) {
			if ((request.getAttribute(ApplicationGlobals.DEFAULT_ELEMENTS)).equals(ApplicationGlobals.DEFAULT_ELEMENTS)){
				this.mahNumber = "on";
				this.initialDate = "on";
				this.age = "on";	
				this.gender = "on";
				this.drugName = "on";
				this.meddraPTName = "on";			
			} else {
				this.mahNumber = "";
				this.initialDate = "";
				this.age = "";	
				this.gender = "";
				this.drugName = "";
				this.meddraPTName = "";			
			}
		} else {
			this.mahNumber = "";
			this.initialDate = "";
			this.age = "";	
			this.gender = "";
			this.drugName = "";
			this.meddraPTName = "";			
		}
		
		this.versionNo = "";
		this.latestDate = "";
		this.featureOfReport = "";
		this.typeOfReport = "";
		this.serious = "";
		this.weight = "";
		this.height = "";	
		this.reportOutcome = "";
		this.reporterType = "";
		this.sourceOfReport = "";
		this.death = "";
		this.disability = "";
		this.congenitalAnomaly = "";
		this.lifeThreatening = "";
		this.hospitalization = "";
		this.otherMedCondition = "";
		this.dosageForm = "";
		this.productRole = "";
		this.route = "";
		this.amount = "";
		this.frequency = "";
		this.therapyDuration = "";
		this.meddraSOCname = "";
		this.reactionDuration = "";
		this.activeIngredient = "";
		
		this.reportFormView = "1";
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors messages = new ActionErrors();
		HttpSession session = request.getSession();
		DisplayBean displayBean = new DisplayBean();
		int count = 0;
		if ((this.versionNo != null) && this.versionNo.length() > 0){
			count ++;
			displayBean.setDisplayVersionNo(ApplicationGlobals.SELECTED);
		}
		if ((this.mahNumber != null) && this.mahNumber.length() > 0){
			count ++;
			displayBean.setDisplayMahNumber(ApplicationGlobals.SELECTED);
		}
		if ((this.initialDate != null) && this.initialDate.length() > 0){
			count ++;
			displayBean.setDisplayInitialDate(ApplicationGlobals.SELECTED);
		}
		if ((this.latestDate != null) && this.latestDate.length() > 0){
			count ++;
			displayBean.setDisplayLatestDate(ApplicationGlobals.SELECTED);
		}
//		if ((this.featureOfReport != null) && this.featureOfReport.length() > 0){
//			count ++;
//			displayBean.setDisplayFeatureOfReport(ApplicationGlobals.SELECTED);
//		}		
//		if ((this.typeOfReport != null) && this.typeOfReport.length() > 0){
//			count ++;			
//			displayBean.setDisplayTypeOfReport(ApplicationGlobals.SELECTED);
//		}
		if ((this.serious != null) && this.serious.length() > 0){
			count ++;			
			displayBean.setDisplaySerious(ApplicationGlobals.SELECTED);
		}		
		if ((this.age != null) && this.age.length() > 0){
			count ++;			
			displayBean.setDisplayAge(ApplicationGlobals.SELECTED);
		}
		if ((this.gender != null) && this.gender.length() > 0){
			count ++;			
			displayBean.setDisplayGender(ApplicationGlobals.SELECTED);
		}
		if ((this.weight != null) && this.weight.length() > 0){
			count ++;			
			displayBean.setDisplayWeight(ApplicationGlobals.SELECTED);
		}
		if ((this.height != null) && this.height.length() > 0){
			count ++;			
			displayBean.setDisplayHeight(ApplicationGlobals.SELECTED);
		}
		if ((this.reportOutcome != null) && this.reportOutcome.length() > 0){
			count ++;			
			displayBean.setDisplayReportOutcome(ApplicationGlobals.SELECTED);
		}
		if ((this.reporterType != null) && this.reporterType.length() > 0){
			count ++;			
			displayBean.setDisplayReporterType(ApplicationGlobals.SELECTED);
		}
		if ((this.sourceOfReport != null) && this.sourceOfReport.length() > 0){
			count ++;			
			displayBean.setDisplaySourceOfReport(ApplicationGlobals.SELECTED);
		}
		if ((this.death != null) && this.death.length() > 0){
			count ++;			
			displayBean.setDisplayDeath(ApplicationGlobals.SELECTED);
		}
		if ((this.disability != null) && this.disability.length() > 0){
			count ++;			
			displayBean.setDisplayDisability(ApplicationGlobals.SELECTED);
		}
		if ((this.congenitalAnomaly != null) && this.congenitalAnomaly.length() > 0){
			count ++;			
			displayBean.setDisplayCongenitalAnomaly(ApplicationGlobals.SELECTED);
		}
		if ((this.lifeThreatening != null) && this.lifeThreatening.length() > 0){
			count ++;			
			displayBean.setDisplayLifeThreat(ApplicationGlobals.SELECTED);
		}
		if ((this.hospitalization != null) && this.hospitalization.length() > 0){
			count ++;			
			displayBean.setDisplayHospitalization(ApplicationGlobals.SELECTED);
		}
		if ((this.otherMedCondition != null) && this.otherMedCondition.length() > 0){
			count ++;			
			displayBean.setDisplayMedicalCondition(ApplicationGlobals.SELECTED);
		}		
		if ((this.drugName != null) && this.drugName.length() > 0){
			count ++;			
			displayBean.setDisplayDrugName(ApplicationGlobals.SELECTED);
		}
		if ((this.dosageForm != null) && this.dosageForm.length() > 0){
			count ++;			
			displayBean.setDisplayDosageForm(ApplicationGlobals.SELECTED);
		}
		if ((this.productRole != null) && this.productRole.length() > 0){
			count ++;			
			displayBean.setDisplayProductRole(ApplicationGlobals.SELECTED);
		}		
		if ((this.route != null) && this.route.length() > 0){
			count ++;			
			displayBean.setDisplayRoute(ApplicationGlobals.SELECTED);
		}
		if ((this.amount != null) && this.amount.length() > 0){
			count ++;			
			displayBean.setDisplayAmount(ApplicationGlobals.SELECTED);
		}
		if ((this.frequency != null) && this.frequency.length() > 0){
			count ++;			
			displayBean.setDisplayFrequency(ApplicationGlobals.SELECTED);
		}
		if ((this.therapyDuration != null) && this.therapyDuration.length() > 0){
			count ++;			
			displayBean.setDisplayTherapyDuration(ApplicationGlobals.SELECTED);
		}
		if ((this.meddraPTName != null) && this.meddraPTName.length() > 0){
			count ++;			
			displayBean.setDisplayMeddraPTName(ApplicationGlobals.SELECTED);
		}
		if ((this.meddraSOCname != null) && this.meddraSOCname.length() > 0){
			count ++;			
			displayBean.setDisplayMeddraSOCname(ApplicationGlobals.SELECTED);
		}
		if ((this.reactionDuration != null) && this.reactionDuration.length() > 0){
			count ++;			
			displayBean.setDisplayReactionDuration(ApplicationGlobals.SELECTED);
		}
		if ((this.activeIngredient != null) && this.activeIngredient.length() > 0){
			count ++;			
			displayBean.setDisplayActiveIngredient(ApplicationGlobals.SELECTED);
		}		
		
		if (count > 7){
			messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.selected.max"));			
		} else {
			session.setAttribute(ApplicationGlobals.DISPLAY_ELEMENTS, displayBean);
		}
		return messages;		
	}

	public String[] getSelectedElements() {
		return selectedElements;
	}

	public void setSelectedElements(String[] selectedElements) {
		this.selectedElements = selectedElements;
	}

	public String getReportID() {
		return reportID;
	}

	public void setReportID(String reportID) {
		this.reportID = reportID;
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

	public String getDosageForm() {
		return dosageForm;
	}

	public void setDosageForm(String dosageForm) {
		this.dosageForm = dosageForm;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getFeatureOfReport() {
		return featureOfReport;
	}

	public void setFeatureOfReport(String featureOfReport) {
		this.featureOfReport = featureOfReport;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getGender() {
		return gender;
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

	public String getHospitalization() {
		return hospitalization;
	}

	public void setHospitalization(String hospitalization) {
		this.hospitalization = hospitalization;
	}

	public String getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(String initialDate) {
		this.initialDate = initialDate;
	}

	public String getLatestDate() {
		return latestDate;
	}

	public void setLatestDate(String latestDate) {
		this.latestDate = latestDate;
	}

	public String getLifeThreatening() {
		return lifeThreatening;
	}

	public void setLifeThreatening(String liftThreatening) {
		this.lifeThreatening = liftThreatening;
	}

	public String getMahNumber() {
		return mahNumber;
	}

	public void setMahNumber(String mahNumber) {
		this.mahNumber = mahNumber;
	}

	public String getMeddraPTName() {
		return meddraPTName;
	}

	public void setMeddraPTName(String meddraPTName) {
		this.meddraPTName = meddraPTName;
	}

	public String getMeddraSOCname() {
		return meddraSOCname;
	}

	public void setMeddraSOCname(String meddraSOCname) {
		this.meddraSOCname = meddraSOCname;
	}

	public String getOtherMedCondition() {
		return otherMedCondition;
	}

	public void setOtherMedCondition(String otherMedCondition) {
		this.otherMedCondition = otherMedCondition;
	}

	public String getReactionDuration() {
		return reactionDuration;
	}

	public void setReactionDuration(String reactionDuration) {
		this.reactionDuration = reactionDuration;
	}

	public String getReportOutcome() {
		return reportOutcome;
	}

	public void setReportOutcome(String reportOutcome) {
		this.reportOutcome = reportOutcome;
	}

	public String getSerious() {
		return serious;
	}

	public void setSerious(String serious) {
		this.serious = serious;
	}

	public String getSourceOfReport() {
		return sourceOfReport;
	}

	public void setSourceOfReport(String sourceOfReport) {
		this.sourceOfReport = sourceOfReport;
	}

	public String getTherapyDuration() {
		return therapyDuration;
	}

	public void setTherapyDuration(String therapyDuration) {
		this.therapyDuration = therapyDuration;
	}

	public String getTypeOfReport() {
		return typeOfReport;
	}

	public void setTypeOfReport(String typeOfReport) {
		this.typeOfReport = typeOfReport;
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

	public List getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(List selectedItems) {
		this.selectedItems = selectedItems;
	}

	public String getReporterType() {
		return reporterType;
	}

	public void setReporterType(String reporterType) {
		this.reporterType = reporterType;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getProductRole() {
		return productRole;
	}

	public void setProductRole(String productRole) {
		this.productRole = productRole;
	}

	public String getReportFormView() {
		return reportFormView;
	}

	public void setReportFormView(String reportFormView) {
		this.reportFormView = reportFormView;
	}
}
