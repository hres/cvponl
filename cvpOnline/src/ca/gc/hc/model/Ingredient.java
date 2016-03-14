package ca.gc.hc.model;



public class Ingredient {

	private Long ingredientID;
	private String actingIngredient;
	private String drugName;
	private Drug drugProduct = new Drug();
	private ActiveIngredient activeIngredient = new ActiveIngredient();
//	private Long productID;
//	private Long activeIngredientID;
		

	// default constructor
	public Ingredient(){		
	}
	
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public Long getIngredientID() {
		return ingredientID;
	}
	public void setIngredientID(Long ingredientID) {
		this.ingredientID = ingredientID;
	}
	public String getActingIngredient() {
		return actingIngredient;
	}
	public void setActingIngredient(String actingIngredient) {
		this.actingIngredient = actingIngredient;
	}

	public ActiveIngredient getActiveIngredient() {
		return activeIngredient;
	}

	public void setActiveIngredient(ActiveIngredient activeIngredient) {
		this.activeIngredient = activeIngredient;
	}

	public Drug getDrugProduct() {
		return drugProduct;
	}

	public void setDrugProduct(Drug drugProduct) {
		this.drugProduct = drugProduct;
	}

//	public Long getActiveIngredientID() {
//		return activeIngredientID;
//	}
//
//	public void setActiveIngredientID(Long activeIngredientID) {
//		this.activeIngredientID = activeIngredientID;
//	}
//
//	public Long getProductID() {
//		return productID;
//	}
//
//	public void setProductID(Long productID) {
//		this.productID = productID;
//	}
		
}
