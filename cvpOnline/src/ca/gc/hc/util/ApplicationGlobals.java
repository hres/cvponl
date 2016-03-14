package ca.gc.hc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.util.LabelValueBean;
import org.hibernate.HibernateException;


/*******************************************************************************
 * An object used to store constants and other relatively static values that are
 * used throughout the application. "relatively static values" are lazy
 * initialized and are provided with a clear() method should an action occur
 * where their values could be outdated. They will require an active Hibernate
 * Session when initializing. Note that this is implemented as a Singleton.
 */
public final class ApplicationGlobals {

    private HashMap statusMap = new HashMap();
    public static final String APP_VERSION = "1.0 beta1";
    public static final String LANG_EN = Locale.ENGLISH.getLanguage();
    public static final String LANG_FR = Locale.FRENCH.getLanguage();

    public static final String DEBUG = "";
    // all, image, paging, memory, hibernate, excel

    // Keys used to store instances in Application, Session, or Request
    // attributes
    public static final String APP_GLOBALS = "appGlobals"; // Application
    static public final String SEARCH_RESULT_PAGE_NUMBER = "page";

    public static final String DISPLAY_ELEMENTS = "displayElements";
    public static final String SELECTED = "selected";
    public static final String DEFAULT_ELEMENTS = "default";

    public static final String DISPLAY_REPORT_NO = "displayReportNo";
    public static final String DISPLAY_DRUGNAME = "displayDrugName";
    public static final String DISPLAY_PTNAME = "displayPTName";
    public static final String DISPLAY_SOCNAME = "displaySOCName";
    public static final String DISPLAY_REACTION_DURATION = "displayReactionDuration";
    public static final String DISPLAY_REPORT_OUTCOME = "displayReportOutcome";
    public static final String DISPLAY_AGE = "displayAge";
    public static final String DISPLAY_GENDER = "displayGender";
    public static final String DISPLAY_WEIGHT = "displayWeight";
    public static final String DISPLAY_HEIGHT = "displayHeight";
    public static final String DISPLAY_AMOUNT = "displayAmount";
    public static final String DISPLAY_FREQUENCY = "displayFrequency";

    public static final String DISPLAY_LATEST_DATE = "displayLatestDate";
    public static final String DISPLAY_LATEST_VERSION = "displayLatestVersion";

    /**
     * The bean name for the toggle locale URL bean and the toggle locale
     * display cache bean. String constants defined for the locales for french
     * and english.
     */
    static public final String TOGGLE_LOCALE_URL_BEAN = "toggleURL";
    static public final String TOGGLE_LOCALE_DISPLAY_CACHE_BEAN = "displayCache";
    static public final String LOCALE_STRING_FRENCH = "FR_CA";
    static public final String LOCALE_STRING_ENGLISH = "EN_CA";
    static public final String PARAM_SELECTED_LANGUAGE = "selectedLanguage";

    /**
     * The name of the object in the HttpSession holding the search result
     * object and search criteria object.
     */

    static public final String SEARCH_RESULT_KEY = "search.results";
    static public final String SEARCH_RESULTS = "cvponline.search.results";
    static public final String SEARCH_CRITERIA = "SearchCriteria";
    public final static String SEARCH_ROWS_SIZE = "searchRowsSize";
    public final static String SEARCH_RESULTS_SIZE = "searchResultsSize";

    static public final String SELECTED_PRODUCT = "dpd.selected.product";
    static public final String SELECTED_STATUS = "dpd.selected.status";

    static public final String PAGER_FORM = "cvp.pager";
    public static final String SORT_PARAMETER = "search.sort.parameter";
    public static boolean SORT_BY_COLUMN = false;

    public final static String DRUG_PRODUCT = "1";
    public final static String INGREDIENT = "2";

    // FOR THE WILDCARD DROPDOWNS
    public final static String BEGINS_WITH = "1";
    public final static String CONTAINS = "2";

    public final static String ALL_REACTIONTERMS = "1";
    public final static String SPECIFY_REACTIONTERMS = "0";

    // MEDDRA TERM LEVEL
    public final static String SOC = "1";
    public final static String PT = "2";

    public final static String ACTIVE = "1";
    public final static String DISCONTINUE = "0";

    public static String MEDDRA_VERSION_VALUE = "";
    public static boolean DRUG_SEARCH = false;
    public static boolean INGREDIENT_SEARCH = false;

    public static long EXECUTION_TIME = 0;

    public static boolean NEXT_QUERY = false;
    public static boolean PREVIOUS_QUERY = false;
    public static int RESULTS_BLOCK = 1;

    // REPORT COLUMNS/FIELDS ID VALUES
    public static int REPORT_ID = 1;
    public static int VERSION_NO = 3;
    public static int DATE_RECEIVED_FOLLOWUP = 4;
    public static int DATE_RECEIVED_INITIAL = 5;
    public static int MAH_NO = 6;
    public static int REPORT_FEATURE_ENG = 7;
    public static int REPORT_FEATURE_FR = 8;
    public static int REPORT_TYPE_ENG = 9;
    public static int REPORT_TYPE_FR = 10;
    public static int GENDER_ENG = 11;
    public static int GENDER_FR = 12;
    public static int AGE = 13;
    public static int OUTCOME_ENG = 17;
    public static int OUTCOME_FR = 18;
    public static int WEIGHT = 19;
    public static int HEIGHT = 22;
    public static int SERIOUSNESS_ENG = 25;
    public static int SERIOUSNESS_FR = 26;
    public static int DEATH = 27;
    public static int DISABILITY = 28;
    public static int CONGENITAL_ANOMALY = 29;
    public static int LIFE_THREATENING = 30;
    public static int HOSP_REQUIRED = 31;
    public static int OTHER_MEDICALLY_IMP_COND = 32;
    public static int REPORTER_TYPE_ENG = 33;
    public static int REPORTER_TYPE_FR = 34;
    public static int SOURCE_ENG = 35;
    public static int SOURCE_FR = 36;
    public static int DURATION = 38;
    public static int DRUGINVOLV_ENG = 48;
    public static int DRUGINVOLV_FR = 49;
    public static int ROUTEADMIN_ENG = 50;
    public static int ROUTEADMIN_FR = 51;
    public static int UNIT_DOSE_QTY = 52;
    public static int FREQUENCY = 55;
    public static int THERAPY_DURATION = 58;
    public static int DOSAGEFORM_ENG = 61;
    public static int DOSAGEFORM_FR = 62;

    public static final String CURRENT_SORT_FIELD = "sort_field_value";

    /**
     * The page navigation action typ
     */
    public final static int INITIAL_ACTION = 0;
    public final static int NEXT_PAGE_ACTION = 1;
    public final static int PREVIOUS_PAGE_ACTION = 2;

    /**
     * String constants defined for the resource bundle for the redirect page.
     */
    public static final String REDIRECT_FILE = "Redirect";
    public static final String REDIRECT_ROOT = "root";

    private static Log log = LogFactory.getLog(ApplicationGlobals.class);

    private static ApplicationGlobals instance;
    private static ThreadLocal userLanguage = new ThreadLocal();
    private static ThreadLocal userLocale = new ThreadLocal();

    List status;

    static {
	instance = new ApplicationGlobals();

    }

    /***************************************************************************
     * Generic singleton constructor.
     */
    private ApplicationGlobals() {}

    /***************************************************************************
     * Gets the single instance of this class.
     */
    public static ApplicationGlobals instance() {

	return instance;
    }

    /***************************************************************************
     * Adds the singleton of this class to the attributes of the passed
     * ServletContext (application).
     */
    public static void connectToContext(ServletContext context) {
	log.info("Hooking up ApplicationGlobals");
	context.setAttribute(APP_GLOBALS, instance());
    }

    /***************************************************************************
     * Gets the language of the user based on a previous call to
     * initLocalization(). If it has not been set, this defaults it to LANG_EN.
     * 
     * @return the language previously set by a call to initLocalization().
     * @see #initLocalization()
     */
    public String getUserLanguage() {
	String language = (String) userLanguage.get();

	if (language == null) {
	    log.error("User Language requested without Localization being properly initialized.");
	    language = LANG_EN; // Default value
	    userLanguage.set(language);
	}

	return language;
    }

    /***************************************************************************
     * Gets the locale of the user based on a previous call to
     * initLocalization(). If it has not been set, this defaults it to Canadian
     * English.
     * 
     * @return the locale previously set by a call to initLocalization().
     * @see #initLocalization()
     */
    public Locale getUserLocale() {
	Locale locale = (Locale) userLocale.get();

	if (locale == null) {
	    log.error("User Locale requested without Localization being properly initialized.");
	    locale = new Locale("en", "CA"); // Default value
	    userLocale.set(locale);
	}

	return locale;
    }

    /***************************************************************************
     * A method borrowed from org.apache.struts.util.RequestUtils (not available
     * until version 1.2). -Dwight Hubley, 2006-05-08
     * <p>
     * Look up and return current user locale, based on the specified
     * parameters.
     * </p>
     * 
     * @param request
     *            The request used to lookup the Locale
     * @param localKey
     *            Name of the session attribute for our user's Locale. If this
     *            is <code>null</code>, the default locale key is used for the
     *            lookup.
     * @return current user locale
     */
    public Locale getUserLocale(HttpServletRequest request, String localKey) {
	Locale userLocale = null;
	HttpSession session = request.getSession(false);

	if (localKey == null) {
	    localKey = Globals.LOCALE_KEY;
	}

	// Only check session if sessions are enabled
	if (session != null) {
	    userLocale = (Locale) session.getAttribute(localKey);
	}

	if (userLocale == null) {
	    // Returns Locale based on Accept-Language header or the server
	    // default
	    userLocale = request.getLocale();
	}

	return userLocale;
    }

    /***************************************************************************
     * This should be called prior to any requests for collections of language-
     * specific items so that they will be returned in the correct language.
     * Typically called by Actions or at the top of a JSP, this stores the
     * language in a ThreadLocal object so that subsequent access to these
     * collections during the current Request will be in the appropriate
     * language. It determines the language requested by the user based on their
     * Locale. All Locales with a language other than French will use English.
     * 
     * @param request
     *            the servlet request we are processing.
     */
    public void initLocalization(HttpServletRequest request) {
	Locale locale = getUserLocale(request, null);

	if (locale == null) {
	    locale = new Locale("en", "CA"); // Default value
	}
	userLocale.set(locale);
	if (LANG_FR.equals(locale.getLanguage())) {
	    userLanguage.set(LANG_FR);
	} else {
	    userLanguage.set(LANG_EN); // Default value
	}

	log.info("Initializing Localization to " + getUserLocale());

    }

    public static void setUserLocale(Locale locale) {

	if (locale == null) {
	    locale = new Locale("en", "CA"); // Default value
	}
	userLocale.set(locale);
	if (LANG_FR.equals(locale.getLanguage())) {
	    userLanguage.set(LANG_FR);
	} else {
	    userLanguage.set(LANG_EN); // Default value
	}

    }

    /***************************************************************************
     * Gets a collection of all of the status that are in the system. These are
     * Locale dependant, so initLocalization(request) must have already been
     * called during the processing of this request.
     * 
     * @return a collection of valid status.
     * @see #initLocalization(request)
     */
    public List getStatus() throws HibernateException {
	if (statusMap.isEmpty()) {
	    loadStatus();
	}

	return (List) statusMap.get(getUserLanguage());
    }

    /***************************************************************************
     * Loads all of the number option to load the foods
     */
    private void loadStatus() throws HibernateException {

	clearStatusList(); // Just in case;

	log.info("Loading List of Status");
	status = new ArrayList();// Make a list in English
	status.add(new LabelValueBean("Active", "1"));
	status.add(new LabelValueBean("Discontinued", "0"));

	statusMap.put(LANG_EN, status); // Default order is English

	status = new ArrayList();// Make a list in French
	status.add(new LabelValueBean("Actif", "1"));
	status.add(new LabelValueBean("Discontinué", "0"));

	statusMap.put(LANG_FR, status);

    }

    /***************************************************************************
     * Clears all foodGroups so they will be reloaded the next time they are
     * requested.
     */
    public synchronized void clearStatusList() {
	log.info("Clearing Status List");
	statusMap.clear();
    }

    public static String parseString(String term) {
	String keyword = "";
	keyword = term.replace("'", "''");
	keyword = keyword.replace("%", "");
	keyword = keyword.replace("!", "");
	keyword = keyword.replace("*", "");
	return keyword;
    }

}
