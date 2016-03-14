package ca.gc.hc.model;

import ca.gc.hc.util.LocaleDependantObject;

public class Reaction extends LocaleDependantObject {

	private Long reactionID;
	private Long reportID;
	private Long duration;
	private String durationUnitCode;
	private String durationUnit;
	private String durationUnitE;
	private String durationUnitF;
	private Long ptCode;
	private String ptName;
	private String ptNameE;
	private String ptNameF;
	private Long socCode;
	private String socName;
	private String socNameE;
	private String socNameF;
	private String meddraVersion;
	private Long aerID;
	private Long seqReaction;
	
	
	/**
	 * no parameter default constructor.
	 */	
	public Reaction(){
	}

	public String getPTName() throws Exception {
		if (isLanguageFrench()) {
			return getPtNameF();
		}
		return getPtNameE();		
	}
	
	public String getSOCName() throws Exception {
		if (isLanguageFrench()) {
			return getSocNameF();
		}
		return getSocNameE();		
	}

	public String getdurationUnitName() throws Exception {
		if (isLanguageFrench()) {
			return getDurationUnitF();
		}
		return getDurationUnitE();		
	}	
	
	public Long getAerID() {
		return aerID;
	}


	public void setAerID(Long aerID) {
		this.aerID = aerID;
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


	public void setDurationUnitE(String durationUnit) {
		this.durationUnitE = durationUnit;
	}


	public String getDurationUnitCode() {
		return durationUnitCode;
	}


	public void setDurationUnitCode(String durationUnitCode) {
		this.durationUnitCode = durationUnitCode;
	}


	public String getDurationUnitF() {
		return durationUnitF;
	}


	public void setDurationUnitF(String durationUnitF) {
		this.durationUnitF = durationUnitF;
	}


	public String getMeddraVersion() {
		return meddraVersion;
	}


	public void setMeddraVersion(String meddraVersion) {
		this.meddraVersion = meddraVersion;
	}


	public Long getPtCode() {
		return ptCode;
	}


	public void setPtCode(Long code) {
		ptCode = code;
	}


	public String getPtNameE() {
		return ptNameE;
	}


	public void setPtNameE(String name) {
		ptNameE = name;
	}


	public String getPtNameF() {
		return ptNameF;
	}


	public void setPtNameF(String nameF) {
		ptNameF = nameF;
	}


	public Long getReactionID() {
		return reactionID;
	}


	public void setReactionID(Long reactionID) {
		this.reactionID = reactionID;
	}

	public Long getSeqReaction() {
		return seqReaction;
	}


	public void setSeqReaction(Long seqReaction) {
		this.seqReaction = seqReaction;
	}


	public Long getSocCode() {
		return socCode;
	}


	public void setSocCode(Long code) {
		socCode = code;
	}


	public String getSocNameE() {
		return socNameE;
	}


	public void setSocNameE(String name) {
		socNameE = name;
	}


	public String getSocNameF() {
		return socNameF;
	}


	public void setSocNameF(String nameF) {
		socNameF = nameF;
	}

	public Long getReportID() {
		return reportID;
	}

	public void setReportID(Long reportID) {
		this.reportID = reportID;
	}

	/***************************************************************************
	 * Bilingual fields adjustment based on the current Locale.
	 */

	public String getDurationUnit() {
		if (isLanguageFrench()) {
			return getDurationUnitF();
		}
		return getDurationUnitE();
	}

	public void setDurationUnit(String durationUnit) {
		this.durationUnit = durationUnit;
	}

	public String getPtName() {
		if (isLanguageFrench()) {
			return getPtNameF();
		}
		return getPtNameE();
	}

	public void setPtName(String ptName) {
		this.ptName = ptName;
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
	
	
	
}
