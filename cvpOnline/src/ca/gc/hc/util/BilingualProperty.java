package ca.gc.hc.util;

import java.util.Locale;

/**
 * Container for the English and French equivalents of a property.
 * 
 * @version 1.0, 29-Sep-05
 * @author Sylvain Larivière
 */
public class BilingualProperty {
	private String english;
	private String french;
	
	public BilingualProperty() {
		this.english= "";
		this.french= "";
	}
	
	public BilingualProperty(String english,String french) {
		this.english= english;
		this.french= french;
	}
	

	/**
	 * Returns the english.
	 * @return String
	 */
	public String getEnglish() {
		return english;
	}

	/**
	 * Returns the french.
	 * @return String
	 */
	public String getFrench() {
		return french;
	}

	/**
	 * Sets the english.
	 * @param english The english to set
	 */
	public void setEnglish(String english) {
		this.english = english;
	}

	/**
	 * Sets the french.
	 * @param french The french to set
	 */
	public void setFrench(String french) {
		this.french = french;
	}
	
	public String getLocalized(Locale locale) throws Exception  {
		String result= null;
		
		if (locale != null) {
			if (locale.getLanguage().equals(new Locale("en", "", "").getLanguage())) {
				result= this.english;
			} else if (locale.getLanguage().equals(new Locale("fr", "", "").getLanguage())) {
				result= this.french;
			} else {
				throw new Exception("The language provided is unknown. The appropriate property cannot be returned");
			}
		} else {
			throw new Exception("No language was provided. The appropriate property cannot be returned");
			}
		return result;			
	}

	public void setLocalized(String newValue, Locale locale) throws Exception {
		
		if (locale != null) {
			if (locale.getLanguage().equals(new Locale("en", "", "").getLanguage())) {
				this.english= newValue;
			} else if (locale.getLanguage().equals(new Locale("fr", "", "").getLanguage())) {
				this.french= newValue;
			} else {
				throw new Exception("The language provided is unknown. The appropriate property cannot be set");
			}
		} else {
			throw new Exception("No language was provided. The appropriate property cannot be set");
			}
	}
}
