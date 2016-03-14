package ca.gc.hc.model;


public class Drug {

	private Long productID;
	private String drugName;
	private String cpdFlag;

	// default constructor
	public Drug(){		
	}
	
	public Drug(Long productID, String drugName, String cpdFlag) {
		this.productID = productID;
		this.drugName = drugName;
		this.cpdFlag = cpdFlag;
	}
	
	public String getCpdFlag() {
		return cpdFlag;
	}
	public void setCpdFlag(String drugCode) {
		this.cpdFlag = drugCode;
	}
	public Long getProductID() {
		return productID;
	}
	public void setProductID(Long drugID) {
		this.productID = drugID;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
		
}
