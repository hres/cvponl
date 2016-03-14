package ca.gc.hc.model;



public class ActiveIngredient {

	private Long activeIngredientID;	
	private String activeIngredientName;
	private Long primaryIngredientID;

	// default constructor
	public ActiveIngredient(){		
	}
	
	public String getActiveIngredientName() {
		return activeIngredientName;
	}
	public void setActiveIngredientName(String activeIngredientName) {
		this.activeIngredientName = activeIngredientName;
	}

	public Long getActiveIngredientID() {
		return activeIngredientID;
	}

	public void setActiveIngredientID(Long activeIngredientID) {
		this.activeIngredientID = activeIngredientID;
	}

	public Long getPrimaryIngredientID() {
		return primaryIngredientID;
	}

	public void setPrimaryIngredientID(Long primaryIngredientID) {
		this.primaryIngredientID = primaryIngredientID;
	}
		
}
