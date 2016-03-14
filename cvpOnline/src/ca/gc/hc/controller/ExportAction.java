/*
 * Created on June 8, 2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ca.gc.hc.controller;

// Import Java IO classes.
import java.awt.Color;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;

import ca.gc.hc.dao.LookUpDAO;
import ca.gc.hc.dao.ReactionDAO;
import ca.gc.hc.dao.ReportDrugDAO;
import ca.gc.hc.dao.SearchDAO;
import ca.gc.hc.model.Reaction;
import ca.gc.hc.model.Report;
import ca.gc.hc.model.ReportDrug;
import ca.gc.hc.model.lookups.ReportLink;
import ca.gc.hc.util.ApplicationGlobals;
import ca.gc.hc.util.ExcelExport;
import ca.gc.hc.util.SearchCriteria;
import ca.gc.hc.view.ExportForm;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;


/**
 * Action class that will populate the search criteria into the search bean and
 * then send to the search dao for retrieval of object results
 */
public class ExportAction extends LookupDispatchAction {
    /***************************************************************************
     * Maps the resources used for the button text to the related action
     * methods.
     */
    protected Map getKeyMethodMap() {
	Map<String, String> buttonActions = new HashMap<String, String>();

	buttonActions.put("button.exportOption", "dispatchExport");
	buttonActions.put("button.backResult", "dispatchBack");
	buttonActions.put("button.back", "dispatchModifyCriteria");
	buttonActions.put("button.newSearch", "dispatchNewSearch");
	buttonActions.put("button.medEffect", "dispatchWebsite");
	return buttonActions;
    }

    private String strLanguage = null;

    public ExportAction() {
	super();
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	    HttpServletResponse response) throws Exception {

	ActionMessages messages = new ActionMessages();
	HttpSession session = request.getSession(true);

	try {
	    if (session.getAttribute("sessionActive") != null) {
		if (request.getParameter("lang") == null) {
		    Locale currentLocale = (Locale) request.getSession().getAttribute(Globals.LOCALE_KEY);
		    strLanguage = currentLocale.getLanguage() + "_" + currentLocale.getCountry();
		} else {
		    strLanguage = request.getParameter("lang");
		    if ("eng".equals(strLanguage)) {
			session.setAttribute("org.apache.struts.action.LOCALE", new java.util.Locale("en", "CA"));
			ApplicationGlobals.setUserLocale(new Locale("en", "CA"));
		    } else if ("fra".equals(strLanguage)) {
			session.setAttribute("org.apache.struts.action.LOCALE", new java.util.Locale("fr", "CA"));
			ApplicationGlobals.setUserLocale(new Locale("fr", "CA"));
		    }
		}
		if (this.isCancelled(request)) {
		    return (mapping.findForward("backToSearch"));
		}
	    } else {
		return mapping.findForward("sessionTimeout");
	    }
	} catch (Exception e) {
	    // Report the error using the appropriate name and ID.
	    messages.add("name", new ActionMessage("id"));
	    return mapping.findForward("failure");
	}
	String action = request.getParameter("action");
	if (action.equals("2")) {
	    return dispatchBack(mapping, form, request, response);
	} else {
	    return super.execute(mapping, form, request, response);
	}

    }

    public ActionForward dispatchBack(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	    HttpServletResponse response) throws Exception {

	ActionForward forward = new ActionForward(); // return value
	HttpSession session = request.getSession(false);
	if (session.getAttribute("sessionActive") != null) {
	    String strLanguage = request.getParameter("lang");
	    if ("eng".equals(strLanguage)) {
		session.setAttribute("org.apache.struts.action.LOCALE", new java.util.Locale("en", "CA"));
		request.getSession().setAttribute(Globals.LOCALE_KEY, new Locale("en", "CA"));
	    } else if ("fra".equals(strLanguage)) {
		session.setAttribute("org.apache.struts.action.LOCALE", new java.util.Locale("fr", "CA"));
		request.getSession().setAttribute(Globals.LOCALE_KEY, Locale.CANADA_FRENCH);
	    }
	    forward = mapping.findForward("backToSearchResults");
	} else {
	    forward = mapping.findForward("sessionTimeout");
	}

	return (forward);
    }

    public ActionForward dispatchModifyCriteria(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	ActionForward forward = new ActionForward(); // return value
	HttpSession session = request.getSession(false);
	// SearchForm searchForm = (SearchForm) form;
	if (session.getAttribute("sessionActive") != null) {
	    String strLanguage = request.getParameter("lang");
	    if ("eng".equals(strLanguage)) {
		session.setAttribute("org.apache.struts.action.LOCALE", new java.util.Locale("en", "CA"));
		request.getSession().setAttribute(Globals.LOCALE_KEY, new Locale("en", "CA"));
	    } else if ("fra".equals(strLanguage)) {
		session.setAttribute("org.apache.struts.action.LOCALE", new java.util.Locale("fr", "CA"));
		request.getSession().setAttribute(Globals.LOCALE_KEY, Locale.CANADA_FRENCH);
	    }
	    // BeanUtils.copyProperties(searchForm,
	    // session.getAttribute(ApplicationGlobals.SEARCH_CRITERIA));
	    ApplicationGlobals.RESULTS_BLOCK = 1;
	    forward = mapping.findForward("modifyCriteria");
	} else {
	    forward = mapping.findForward("sessionTimeout");
	}
	return (forward);
    }

    public ActionForward dispatchNewSearch(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	    HttpServletResponse response) throws Exception {

	ActionForward forward = new ActionForward(); // return value
	HttpSession session = request.getSession(false);
	if (session.getAttribute("sessionActive") != null) {
	    if (session.getAttribute("sessionActive") != null) {
		String strLanguage = request.getParameter("lang");
		if ("eng".equals(strLanguage)) {
		    session.setAttribute("org.apache.struts.action.LOCALE", new java.util.Locale("en", "CA"));
		    request.getSession().setAttribute(Globals.LOCALE_KEY, new Locale("en", "CA"));
		} else if ("fra".equals(strLanguage)) {
		    session.setAttribute("org.apache.struts.action.LOCALE", new java.util.Locale("fr", "CA"));
		    request.getSession().setAttribute(Globals.LOCALE_KEY, Locale.CANADA_FRENCH);
		}
	    }
	    session.removeAttribute(ApplicationGlobals.SEARCH_RESULTS);
	    session.removeAttribute(ApplicationGlobals.SEARCH_RESULT_KEY);
	    forward = mapping.findForward("newSearch");
	} else {
	    forward = mapping.findForward("sessionTimeout");
	}
	return (forward);
    }

    public ActionForward dispatchWebsite(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	    HttpServletResponse response) throws Exception {

	ActionForward forward = new ActionForward(); // return value
	HttpSession session = request.getSession(false);
	if (session.getAttribute("sessionActive") != null) {
	    if (session.getAttribute("sessionActive") != null) {
		String strLanguage = request.getParameter("lang");
		if ("eng".equals(strLanguage)) {
		    session.setAttribute("org.apache.struts.action.LOCALE", new java.util.Locale("en", "CA"));
		    request.getSession().setAttribute(Globals.LOCALE_KEY, new Locale("en", "CA"));
		} else if ("fra".equals(strLanguage)) {
		    session.setAttribute("org.apache.struts.action.LOCALE", new java.util.Locale("fr", "CA"));
		    request.getSession().setAttribute(Globals.LOCALE_KEY, Locale.CANADA_FRENCH);
		}
	    }
	    Locale locale = (Locale) session.getAttribute("org.apache.struts.action.LOCALE");
	    ResourceBundle resBundle = ResourceBundle.getBundle("resources.ApplicationResources", locale);

	    String medEffectUrl = resBundle.getString("medEffect.URL");

	    session.removeAttribute(ApplicationGlobals.SEARCH_RESULTS);
	    session.removeAttribute(ApplicationGlobals.SEARCH_RESULT_KEY);
	    forward = mapping.findForward("newSearch");
	} else {
	    forward = mapping.findForward("sessionTimeout");
	}
	return (forward);
    }

    public ActionForward dispatchExport(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	    HttpServletResponse response) throws Exception {

	ActionMessages messages = new ActionMessages();
	ActionForward forward = new ActionForward(); // return value
	HttpSession session = request.getSession(false);
	ExportForm exForm = (ExportForm) form;

	Locale currentLocale = (Locale) request.getSession().getAttribute(Globals.LOCALE_KEY);

	// Get timeStamp and date
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat timeStampForm = new SimpleDateFormat("yyyy-MM-dd - hh:mm:ss aaa");
	SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd");
	String timeStamp = timeStampForm.format(cal.getTime());
	String today = dateForm.format(cal.getTime());

	if (session.getAttribute("sessionActive") != null) {
	    if (session.getAttribute("sessionActive") != null) {
		String strLanguage = request.getParameter("lang");
		if ("eng".equals(strLanguage)) {
		    session.setAttribute("org.apache.struts.action.LOCALE", new java.util.Locale("en", "CA"));
		    request.getSession().setAttribute(Globals.LOCALE_KEY, new Locale("en", "CA"));
		} else if ("fra".equals(strLanguage)) {
		    session.setAttribute("org.apache.struts.action.LOCALE", new java.util.Locale("fr", "CA"));
		    request.getSession().setAttribute(Globals.LOCALE_KEY, Locale.CANADA_FRENCH);
		}
	    }

	    ActionErrors error = new ActionErrors();
	    error = exForm.validate(mapping, request);

	    if (!error.isEmpty()) {
		forward = mapping.getInputForward();
		messages.add(error);
	    } else {
		if (exForm.getFoption() != "") {
		    switch (Integer.valueOf(exForm.getFoption())) {
		    case 1: // PDF
			// step 1: creation of a document-object
			// Document document = new
			// Document(PageSize.A4.rotate(), 10, 10, 5, 5); //with
			// rotating
			Document document = new Document(PageSize.A4.rotate(), 36, 36, 18, 18);
			try {
			    // step 2:
			    // we create a writer that listens to the document
			    // and directs a PDF-stream to a file
			    // PdfWriter.getInstance(document, new
			    // FileOutputStream("C:/Documents and Settings/DShen/Desktop/HelloWorld.pdf"));

			    response.setContentType("application/pdf");
			    response.setHeader("Content-Disposition", "attachment; filename=" + today
				    + "_exportPDF.pdf");

			    // FileOutputStream out = new
			    // FileOutputStream("C:/Documents and Settings/DShen/Desktop/HelloWorld.pdf")
			    PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

			    // step 3: we open the document
			    document.open();
			    // step 4: we add a paragraph to the document

			    // Remark: You MUST know the number of columns when
			    // constructing a Table.
			    // The number of rows is not important.

			    // Footer
			    ResourceBundle resBundle = ResourceBundle.getBundle("resources.ApplicationResources",
				    currentLocale);
			    String newline = resBundle.getString("format.newline");
			    String space = resBundle.getString("format.pdf.space");
			    String colon = resBundle.getString("format.pdf.colon");
			    String to = resBundle.getString("label.pdf.report.to");
			    String yes = resBundle.getString("label.yes");
			    String no = resBundle.getString("label.no");
			    String pdfFootPage = resBundle.getString("label.pdf.report.foot.page");

			    HeaderFooter footer = new HeaderFooter(new Phrase(pdfFootPage + space), true);
			    footer.setAlignment(com.lowagie.text.Element.ALIGN_CENTER);
			    footer.setBorder(1);
			    document.setFooter(footer);

			    // Getting actual path on root server
			    String URL_ROOT = getServlet().getServletContext().getRealPath("");
			    String URL_IMAGES = URL_ROOT + "images";

			    String hcscName = resBundle.getString("img.pdf.hcscLogo");
			    String canadaName = resBundle.getString("img.pdf.canadaLogo");
			    if (ApplicationGlobals.DEBUG == "image" || ApplicationGlobals.DEBUG == "all") {
				System.out.println(">>>> HC Logo Path : " + URL_IMAGES + hcscName);
				System.out.println(">>>> CA Logo Path: " + URL_IMAGES + canadaName);

				// Properties props = new Properties();
				// props.put("mail.host", _mailhost);
				// props.put("mail.user", _mailuser);
				// Session mailSession =
				// Session.getDefaultInstance(props, null);
				// MimeMessage msg = new
				// MimeMessage(mailSession);
				// msg.setFrom(new
				// InternetAddress("Daniel_Shen@hc-sc.gc.ca",
				// "Daniel Shen"));
				// msg.addRecipient(Message.RecipientType.TO,
				// new
				// InternetAddress("Daniel_Shen@hc-sc.gc.ca",
				// "Daniel Shen"));
				// msg.setSubject("CVPOnline Image Path Email");
				// msg.setText("HC Logo Path : " + URL_IMAGES +
				// hcscName);
				// Transport.send(msg);
			    }
			    Image hcscWordmark = Image.getInstance(URL_IMAGES + hcscName);
			    Image canadaWordmark = Image.getInstance(URL_IMAGES + canadaName);

			    // Image canadaWordmark = Image.getInstance(new
			    // URL("/images/Canada_wordmark-2c.jpg"));
			    hcscWordmark.scalePercent(16);
			    canadaWordmark.scalePercent(12);

			    // Table Row 0: Search Criteria Page
			    String pdfR02C1 = resBundle.getString("searched.terms");
			    String pdfR03C1 = resBundle.getString("search.date.criteria");
			    String pdfR04C1 = resBundle.getString("searched.reaction.terms");
			    String pdfR05C1 = resBundle.getString("label.seriousness");
			    String pdfR06C1 = resBundle.getString("label.reportFeature");
			    String pdfR07C1 = resBundle.getString("label.reportType");
			    String pdfR08C1 = resBundle.getString("label.reportSource");
			    String pdfR09C1 = resBundle.getString("label.gender");
			    String pdfR010C1 = resBundle.getString("label.outcome");
			    String pdfR011C1 = resBundle.getString("label.age");
			    String pdfR012C1 = resBundle.getString("label.pdf.report.caveat");

			    // Table Row 1: Title Bar
			    String pdfReportTitle = resBundle.getString("label.pdf.report.title");
			    String pdfReportRuntime = resBundle.getString("label.pdf.report.repRuntime");
			    String pdfReportIniDate = resBundle.getString("label.initialDateReceived");
			    String pdfReportLatDate = resBundle.getString("label.latestDateReceived");
			    String pdfNotApplicable = resBundle.getString("label.pdf.report.notApplicable");
			    String pdfReportNumbers = resBundle.getString("label.pdf.report.numOfRep");
			    String pdfReport = resBundle.getString("label.pdf.report.rep");

			    // Table Row 3
			    String pdfReportInfo = resBundle.getString("label.pdf.report.info");
			    String pdfAerDef = resBundle.getString("label.reportInfo.aerDefinition");

			    // Table Row 4
			    String pdfR4C1 = resBundle.getString("label.reportInfo.reportId");
			    String pdfR4C2 = resBundle.getString("label.reportInfo.versionNumber");
			    String pdfR4C3 = resBundle.getString("label.initialDateReceived");
			    String pdfR4C4 = resBundle.getString("label.latestDateReceived");
			    String pdfR4C5 = resBundle.getString("label.reportSource");
			    String pdfR4C6 = resBundle.getString("label.reportInfo.ManufactId");
			    String pdfR4C7 = resBundle.getString("label.reportFeature");
			    String pdfR4C8 = resBundle.getString("label.reportType");
			    String pdfR4C9 = resBundle.getString("label.reportInfo.reporterType");

			    // Table Row 6
			    String pdfR6C1 = resBundle.getString("label.seriousness");
			    String pdfR6C3 = resBundle.getString("label.reportInfo.death");
			    String pdfR6C5 = resBundle.getString("label.reportInfo.disability");
			    String pdfR6C7 = resBundle.getString("label.reportInfo.congenitalAnomaly");

			    // Table Row 7
			    String pdfR7C3 = resBundle.getString("label.reportInfo.lifeThreat");
			    String pdfR7C5 = resBundle.getString("label.reportInfo.hospitalization");
			    String pdfR7C7 = resBundle.getString("label.reportInfo.otherMedicalImp");

			    // Table Row 8
			    String pdfR8C1 = resBundle.getString("label.pdf.report.patinfo");

			    // Table Row 9: Patient Information Label
			    String pdfR9C1 = resBundle.getString("label.age");
			    String pdfR9C2 = resBundle.getString("label.gender");
			    String pdfR9C3 = resBundle.getString("label.reportInfo.height");
			    String pdfR9C4 = resBundle.getString("label.reportInfo.weight");
			    String pdfR9C5 = resBundle.getString("label.outcome");

			    // Table Row 10: Patient Information Data

			    // Table Row 17: Link / Duplicate Report Info Label
			    String pdfR17C1 = resBundle.getString("label.reportInfo.linkDupRepInfo");

			    // Table Row 18: Link / Duplicate Report Info
			    String pdfR18C1 = resBundle.getString("label.reportInfo.recordType");
			    String pdfR18C2 = resBundle.getString("label.reportInfo.linkAERNum");

			    // Table Row 11: Product Information Label
			    String pdfR11C1 = resBundle.getString("label.reportInfo.prodInfo");

			    // Table Row 12: Product Information Table Header
			    String pdfR12C1 = resBundle.getString("label.reportInfo.prodInfo.prodDesc");
			    String pdfR12C2 = resBundle.getString("label.reportInfo.prodInfo.prodRole");
			    String pdfR12C3 = resBundle.getString("label.reportInfo.prodInfo.dosForm");
			    String pdfR12C4 = resBundle.getString("label.reportInfo.prodInfo.route");
			    String pdfR12C5 = resBundle.getString("label.reportInfo.prodInfo.dose");
			    String pdfR12C6 = resBundle.getString("label.reportInfo.prodInfo.frequency");
			    String pdfR12C7 = resBundle.getString("label.reportInfo.prodInfo.therapyDuration");
			    String pdfR12C8 = resBundle.getString("label.reportInfo.prodInfo.indication");

			    // Table Row 13: Product Information Data

			    // Table Row 14: Reaction Information Label
			    String pdfR14C1 = resBundle.getString("label.reportInfo.reactInfo");

			    // Table Row 15: Reaction Information Table Header
			    String pdfR15C1 = resBundle.getString("label.reportInfo.reactInfo.advReaction");
			    String pdfR15C2 = resBundle.getString("meddra.version");
			    String pdfR15C3 = resBundle.getString("label.reportInfo.reactInfo.duration");

			    // Table Row 16: Reaction Information Data

			    Locale locale;
			    locale = (Locale) session.getAttribute("org.apache.struts.action.LOCALE");
			    SearchDAO searchDAO = new SearchDAO();

			    SearchCriteria sc = (SearchCriteria) session
				    .getAttribute(ApplicationGlobals.SEARCH_CRITERIA);
			    // 200 is used for the pagesize for
			    // searchByReportQuery function because we would
			    // like to set the cap of search results to 1000
			    // records.
			    List searchResults = (List) searchDAO.searchByReportQuery(sc, locale, 200, 1, 1);

			    String IniDate, LatDate;

			    if ((sc.getInitialDateFrom()).equalsIgnoreCase("")) {
				IniDate = pdfNotApplicable;
				LatDate = sc.getFollowUpDateFrom() + space + to + space + sc.getFollowUpDateTo();
			    } else {
				IniDate = sc.getInitialDateFrom() + space + to + space + sc.getInitialDateTo();
				LatDate = pdfNotApplicable;
			    }

			    Iterator sr = searchResults.iterator();
			    int srLen = Integer.valueOf(session.getAttribute(ApplicationGlobals.SEARCH_RESULTS_SIZE)
				    .toString());
			    if (srLen >= 1000) {
				srLen = 1000;
			    }
			    int count = 0;

			    // PdfPTable tR0 = new PdfPTable(new float[] {1.7f,
			    // 2.3f, 1f, 1f});
			    PdfPTable tR0 = new PdfPTable(new float[] { 1.9f, 2f, 1f, 1.1f });
			    tR0.setWidthPercentage(100);

			    // Space row for first page with search criteria and
			    // caveat
			    PdfPCell r0c0 = new PdfPCell(new Paragraph("", new Font(Font.HELVETICA, 12, Font.NORMAL,
				    new Color(0, 0, 0))));
			    r0c0.setColspan(2);
			    r0c0.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
			    r0c0.setBorder(0);

			    // File name row for first page with search criteria
			    // and caveat
			    PdfPCell r0 = new PdfPCell(new Paragraph(today + "_exportPDF.pdf", FontFactory.getFont(
				    FontFactory.HELVETICA, 12)));
			    r0.setBorder(0);
			    r0.setColspan(4);
			    r0.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
			    tR0.addCell(r0);

			    // Space row for first page with search criteria and
			    // caveat
			    tR0.addCell(r0c0);
			    tR0.addCell(r0c0);

			    PdfPCell r0c1 = new PdfPCell(hcscWordmark);
			    r0c1.setBorder(2);
			    r0c1.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
			    tR0.addCell(r0c1);

			    PdfPCell r0c2 = new PdfPCell(new Paragraph(pdfReportTitle, FontFactory.getFont(
				    FontFactory.HELVETICA_BOLD, 12)));
			    r0c2.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
			    r0c2.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
			    r0c2.setBorder(2);
			    tR0.addCell(r0c2);

			    PdfPCell r0c3 = new PdfPCell(new Paragraph(pdfReportRuntime + colon + newline
				    + pdfReportIniDate + colon + newline + pdfReportLatDate + colon + newline
				    + pdfReportNumbers + colon, FontFactory.getFont(FontFactory.HELVETICA, 9)));
			    r0c3.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_RIGHT);
			    r0c3.setBorder(2);
			    tR0.addCell(r0c3);

			    PdfPCell r0c4 = new PdfPCell(new Paragraph(timeStamp + newline + IniDate + newline
				    + LatDate + newline + srLen + space + space + pdfReport, FontFactory.getFont(
				    FontFactory.HELVETICA, 9)));
			    r0c4.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_RIGHT);
			    r0c4.setBorder(2);
			    tR0.addCell(r0c4);

			    // Creating Horizontal Line Image
			    PdfContentByte horizontalLineContentByte = new PdfContentByte(writer);
			    horizontalLineContentByte.setLineWidth(1f);
			    horizontalLineContentByte.moveTo(0f, 0f);
			    horizontalLineContentByte.lineTo(100f, 0f);
			    PdfTemplate horizontalLineTemplate = horizontalLineContentByte.createTemplate(100f, 1f);
			    Image horizontalLineImage = Image.getInstance(horizontalLineTemplate);

			    PdfPCell r00c1 = new PdfPCell(new Paragraph(""));
			    r00c1.setColspan(4);
			    r00c1.addElement(horizontalLineImage);
			    r00c1.setBorder(0);
			    tR0.addCell(r00c1);

			    document.add(tR0);

			    // Display search criteria and caveat
			    PdfPTable tR01 = new PdfPTable(new float[] { 0.8f, 1.2f });
			    tR01.setWidthPercentage(100);

			    // Space row for first page with search criteria and
			    // caveat
			    PdfPCell r01c1 = new PdfPCell(new Paragraph("", new Font(Font.HELVETICA, 12, Font.NORMAL,
				    new Color(0, 0, 0))));
			    r01c1.setColspan(2);
			    r01c1.setBorder(0);

			    tR01.addCell(r01c1);
			    tR01.addCell(r01c1);

			    PdfPCell r02c1 = new PdfPCell(new Paragraph(pdfR02C1 + colon + space, new Font(
				    Font.HELVETICA, 16, Font.BOLD, new Color(0, 0, 0))));
			    r02c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_RIGHT);
			    r02c1.setBorder(0);
			    tR01.addCell(r02c1);

			    PdfPCell r02c2 = new PdfPCell(new Paragraph(sc.getSection3Criteria(), new Font(
				    Font.HELVETICA, 14, Font.NORMAL, new Color(0, 0, 0))));
			    r02c2.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_LEFT);
			    r02c2.setBorder(0);
			    tR01.addCell(r02c2);

			    PdfPCell r03c1 = new PdfPCell(new Paragraph(pdfR03C1 + colon + space, new Font(
				    Font.HELVETICA, 16, Font.BOLD, new Color(0, 0, 0))));
			    r03c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_RIGHT);
			    r03c1.setBorder(0);
			    tR01.addCell(r03c1);

			    String searchDate = "";

			    if (IniDate == pdfNotApplicable) {
				searchDate = LatDate;
			    } else {
				searchDate = IniDate;
			    }

			    PdfPCell r03c2 = new PdfPCell(new Paragraph(searchDate, new Font(Font.HELVETICA, 14,
				    Font.NORMAL, new Color(0, 0, 0))));
			    r03c2.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_LEFT);
			    r03c2.setBorder(0);
			    tR01.addCell(r03c2);

			    PdfPCell r04c1 = new PdfPCell(new Paragraph(pdfR04C1 + colon + space, new Font(
				    Font.HELVETICA, 16, Font.BOLD, new Color(0, 0, 0))));
			    r04c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_RIGHT);
			    r04c1.setBorder(0);
			    tR01.addCell(r04c1);

			    PdfPCell r04c2 = new PdfPCell(new Paragraph(sc.getSection4Criteria(), new Font(
				    Font.HELVETICA, 14, Font.NORMAL, new Color(0, 0, 0))));
			    r04c2.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_LEFT);
			    r04c2.setBorder(0);
			    tR01.addCell(r04c2);

			    PdfPCell r05c1 = new PdfPCell(new Paragraph(pdfR05C1 + colon + space, new Font(
				    Font.HELVETICA, 16, Font.BOLD, new Color(0, 0, 0))));
			    r05c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_RIGHT);
			    r05c1.setBorder(0);
			    tR01.addCell(r05c1);

			    PdfPCell r05c2 = new PdfPCell(new Paragraph(sc.getSeriousName(), new Font(Font.HELVETICA,
				    14, Font.NORMAL, new Color(0, 0, 0))));
			    r05c2.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_LEFT);
			    r05c2.setBorder(0);
			    tR01.addCell(r05c2);

			    // removed Feature of Report field - AH April 13th,
			    // 2012
			    // PdfPCell r06c1 = new PdfPCell(new
			    // Paragraph(pdfR06C1 + colon + space,
			    // new Font(Font.HELVETICA, 16, Font.BOLD,
			    // new Color(0, 0, 0))));
			    // r06c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_RIGHT);
			    // r06c1.setBorder(0);
			    // tR01.addCell(r06c1);
			    //						   	   	
			    // PdfPCell r06c2 = new PdfPCell(new
			    // Paragraph(sc.getReportFeatureName(),
			    // new Font(Font.HELVETICA, 14, Font.NORMAL,
			    // new Color(0, 0, 0))));
			    // r06c2.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_LEFT);
			    // r06c2.setBorder(0);
			    // tR01.addCell(r06c2);

			    PdfPCell r07c1 = new PdfPCell(new Paragraph(pdfR07C1 + colon + space, new Font(
				    Font.HELVETICA, 16, Font.BOLD, new Color(0, 0, 0))));
			    r07c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_RIGHT);
			    r07c1.setBorder(0);
			    tR01.addCell(r07c1);

			    PdfPCell r07c2 = new PdfPCell(new Paragraph(sc.getReportTypeName(), new Font(
				    Font.HELVETICA, 14, Font.NORMAL, new Color(0, 0, 0))));
			    r07c2.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_LEFT);
			    r07c2.setBorder(0);
			    tR01.addCell(r07c2);

			    PdfPCell r08c1 = new PdfPCell(new Paragraph(pdfR08C1 + colon + space, new Font(
				    Font.HELVETICA, 16, Font.BOLD, new Color(0, 0, 0))));
			    r08c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_RIGHT);
			    r08c1.setBorder(0);
			    tR01.addCell(r08c1);

			    PdfPCell r08c2 = new PdfPCell(new Paragraph(sc.getReportSourceName(), new Font(
				    Font.HELVETICA, 14, Font.NORMAL, new Color(0, 0, 0))));
			    r08c2.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_LEFT);
			    r08c2.setBorder(0);
			    tR01.addCell(r08c2);

			    PdfPCell r09c1 = new PdfPCell(new Paragraph(pdfR09C1 + colon + space, new Font(
				    Font.HELVETICA, 16, Font.BOLD, new Color(0, 0, 0))));
			    r09c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_RIGHT);
			    r09c1.setBorder(0);
			    tR01.addCell(r09c1);

			    PdfPCell r09c2 = new PdfPCell(new Paragraph(sc.getGenderName(), new Font(Font.HELVETICA,
				    14, Font.NORMAL, new Color(0, 0, 0))));
			    r09c2.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_LEFT);
			    r09c2.setBorder(0);
			    tR01.addCell(r09c2);

			    PdfPCell r010c1 = new PdfPCell(new Paragraph(pdfR010C1 + colon + space, new Font(
				    Font.HELVETICA, 16, Font.BOLD, new Color(0, 0, 0))));
			    r010c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_RIGHT);
			    r010c1.setBorder(0);
			    tR01.addCell(r010c1);

			    PdfPCell r010c2 = new PdfPCell(new Paragraph(sc.getOutcomeName(), new Font(Font.HELVETICA,
				    14, Font.NORMAL, new Color(0, 0, 0))));
			    r010c2.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_LEFT);
			    r010c2.setBorder(0);
			    tR01.addCell(r010c2);

			    PdfPCell r011c1 = new PdfPCell(new Paragraph(pdfR011C1 + colon + space, new Font(
				    Font.HELVETICA, 16, Font.BOLD, new Color(0, 0, 0))));
			    r011c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_RIGHT);
			    r011c1.setBorder(0);
			    tR01.addCell(r011c1);

			    PdfPCell r011c2 = new PdfPCell(new Paragraph(sc.getAgeCrit(), new Font(Font.HELVETICA, 14,
				    Font.NORMAL, new Color(0, 0, 0))));
			    r011c2.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_LEFT);
			    r011c2.setBorder(0);
			    tR01.addCell(r011c2);

			    tR01.addCell(r01c1);
			    tR01.addCell(r01c1);

			    // The line below was added to try to fix bottom
			    // alignment
			    tR01.getDefaultCell().setVerticalAlignment(com.lowagie.text.Element.ALIGN_BOTTOM);
			    PdfPCell r012c1 = new PdfPCell(new Paragraph(pdfR012C1, new Font(Font.HELVETICA, 12,
				    Font.NORMAL, new Color(0, 0, 0))));
			    r012c1.setColspan(2);
			    r012c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_LEFT);
			    // The line below was added to try to fix bottom
			    // alignment
			    r012c1.setVerticalAlignment(com.lowagie.text.Element.ALIGN_BOTTOM);
			    r012c1.setBorder(0);
			    tR01.addCell(r012c1);
			    // The line below was added to try to fix bottom
			    // alignment
			    tR01.getDefaultCell().setVerticalAlignment(com.lowagie.text.Element.ALIGN_TOP);

			    tR01.addCell(r01c1);
			    tR01.addCell(r01c1);
			    tR01.addCell(r01c1);

			    // The line below was added to try to fix bottom
			    // alignment
			    tR01.getDefaultCell().setVerticalAlignment(com.lowagie.text.Element.ALIGN_BOTTOM);
			    PdfPCell r013c1 = new PdfPCell(canadaWordmark);
			    r013c1.setColspan(2);
			    r013c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_RIGHT);
			    // The line below was added to try to fix bottom
			    // alignment
			    r013c1.setVerticalAlignment(com.lowagie.text.Element.ALIGN_BOTTOM);
			    r013c1.setBorder(0);
			    tR01.addCell(r013c1);
			    // The line below was added to try to fix bottom
			    // alignment
			    tR01.getDefaultCell().setVerticalAlignment(com.lowagie.text.Element.ALIGN_TOP);
			    document.add(tR01);

			    // Switch to Landscape
			    // document.setPageSize(PageSize.A4.rotate());
			    // document.setMargins(36, 36, 18, 18);

			    document.newPage();

			    while (sr.hasNext()) {
				// OLD SearchResultsBean srb =
				// (SearchResultsBean) sr.next();
				Report rp = (Report) sr.next();
				PdfPTable tR1 = new PdfPTable(new float[] { 1.9f, 2f, 1f, 1.1f });
				tR1.setWidthPercentage(100);

				// File name row for each page after search
				// criteria and caveat
				tR1.addCell(r0);
				// File name Space row for each page after
				// search criteria and caveat
				tR1.addCell(r0c0);
				tR1.addCell(r0c0);

				PdfPCell r1c1 = new PdfPCell(new Paragraph(""));
				r1c1.setBorder(2);
				r1c1.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				tR1.addCell(r1c1);

				PdfPCell r1c2 = new PdfPCell(new Paragraph(pdfReportTitle, FontFactory.getFont(
					FontFactory.HELVETICA_BOLD, 12)));
				r1c2.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r1c2.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r1c2.setBorder(2);
				tR1.addCell(r1c2);

				PdfPCell r1c3 = new PdfPCell(new Paragraph(pdfReportRuntime + colon + newline
					+ pdfReportIniDate + colon + newline + pdfReportLatDate + colon + newline
					+ pdfReportNumbers + colon, FontFactory.getFont(FontFactory.HELVETICA, 9)));
				r1c3.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_RIGHT);
				r1c3.setBorder(2);
				tR1.addCell(r1c3);

				PdfPCell r1c4 = new PdfPCell(new Paragraph(timeStamp + newline + IniDate + newline
					+ LatDate + newline + srLen + space + space + pdfReport, FontFactory.getFont(
					FontFactory.HELVETICA, 9)));
				r1c4.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_RIGHT);
				r1c4.setBorder(2);
				tR1.addCell(r1c4);

				PdfPCell r2c1 = new PdfPCell(new Paragraph(""));
				r2c1.setColspan(4);
				r2c1.addElement(horizontalLineImage);
				r2c1.setBorder(0);
				tR1.addCell(r2c1);

				document.add(tR1);

				// Draws a separation space row
				document.add(new Paragraph(" ", new Font(Font.HELVETICA, 3, Font.NORMAL, new Color(0,
					0, 0))));

				// Table Row 3: Report Info Label in Black
				// BGcolor
				PdfPTable tR3 = new PdfPTable(new float[] { 2f, 8f });
				tR3.setWidthPercentage(100);

				PdfPCell rRepInfoLabel = new PdfPCell(new Paragraph(pdfReportInfo + newline, new Font(
					Font.HELVETICA, 10, Font.BOLD, new Color(255, 255, 255))));
				rRepInfoLabel.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				rRepInfoLabel.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_LEFT);
				rRepInfoLabel.setBackgroundColor(new Color(0, 0, 0));
				rRepInfoLabel.setBorder(0);
				tR3.addCell(rRepInfoLabel);

				PdfPCell r3c2 = new PdfPCell(new Paragraph(space + space + pdfAerDef, new Font(
					Font.HELVETICA, 10, Font.ITALIC, new Color(0, 0, 0))));
				r3c2.setBorder(0);
				tR3.addCell(r3c2);
				document.add(tR3);

				// Draws a separation space row
				document.add(new Paragraph(" ", new Font(Font.HELVETICA, 3, Font.NORMAL, new Color(0,
					0, 0))));

				// Table Row 4: contains AerNo, Version No.,
				// Initial Rec. Date, Latest Rec. Date,
				// Report Source, MAH Number, Type of Report,
				// Reporter Type
				// removed Feature of Report field - AH April
				// 13th, 2012 -> Report Feature 1.05f,
				PdfPTable tR4 = new PdfPTable(new float[] { 0.9f, 1f, 1.15f, 1.15f, 0.95f, 1.05f, 0.9f,
					0.85f });
				tR4.setWidthPercentage(100);

				PdfPCell r4c1 = new PdfPCell(new Paragraph(pdfR4C1, new Font(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0))));
				r4c1.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r4c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r4c1.setBackgroundColor(new Color(200, 200, 200));
				tR4.addCell(r4c1);

				PdfPCell r4c2 = new PdfPCell(new Paragraph(pdfR4C2, new Font(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0))));
				r4c2.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r4c2.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r4c2.setBackgroundColor(new Color(200, 200, 200));
				tR4.addCell(r4c2);

				PdfPCell r4c3 = new PdfPCell(new Paragraph(pdfR4C3, new Font(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0))));
				r4c3.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r4c3.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r4c3.setBackgroundColor(new Color(200, 200, 200));
				tR4.addCell(r4c3);

				PdfPCell r4c4 = new PdfPCell(new Paragraph(pdfR4C4, new Font(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0))));
				r4c4.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r4c4.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r4c4.setBackgroundColor(new Color(200, 200, 200));
				tR4.addCell(r4c4);

				PdfPCell r4c5 = new PdfPCell(new Paragraph(pdfR4C5, new Font(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0))));
				r4c5.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r4c5.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r4c5.setBackgroundColor(new Color(200, 200, 200));
				tR4.addCell(r4c5);

				PdfPCell r4c6 = new PdfPCell(new Paragraph(pdfR4C6, new Font(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0))));
				r4c6.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r4c6.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r4c6.setBackgroundColor(new Color(200, 200, 200));
				tR4.addCell(r4c6);

				// removed Feature of Report field - AH April
				// 13th, 2012
				// PdfPCell r4c7 = new PdfPCell(new
				// Paragraph(pdfR4C7,
				// new Font(Font.HELVETICA, 10, Font.BOLD,
				// new Color(0, 0, 0))));
				// r4c7.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				// r4c7.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				// r4c7.setBackgroundColor(new Color(200 ,200
				// ,200));
				// tR4.addCell(r4c7);

				PdfPCell r4c8 = new PdfPCell(new Paragraph(pdfR4C8, new Font(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0))));
				r4c8.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r4c8.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r4c8.setBackgroundColor(new Color(200, 200, 200));
				tR4.addCell(r4c8);

				PdfPCell r4c9 = new PdfPCell(new Paragraph(pdfR4C9, new Font(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0))));
				r4c9.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r4c9.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r4c9.setBackgroundColor(new Color(200, 200, 200));
				tR4.addCell(r4c9);

				// Table Row 5: Values
				PdfPCell r5c1 = new PdfPCell(new Paragraph(rp.getReportNumber(), new Font(
					Font.HELVETICA, 10, Font.NORMAL, new Color(0, 0, 0))));
				r5c1.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r5c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				tR4.addCell(r5c1);

				PdfPCell r5c2 = new PdfPCell(new Paragraph(rp.getVersionNumber().toString(), new Font(
					Font.HELVETICA, 10, Font.NORMAL, new Color(0, 0, 0))));
				r5c2.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r5c2.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				tR4.addCell(r5c2);

				PdfPCell r5c3 = new PdfPCell(new Paragraph(formatDateDisplay((Date) rp
					.getInitialDateReceived()), new Font(Font.HELVETICA, 10, Font.NORMAL,
					new Color(0, 0, 0))));
				r5c3.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r5c3.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				tR4.addCell(r5c3);

				PdfPCell r5c4 = new PdfPCell(new Paragraph(formatDateDisplay((Date) rp
					.getDateReceived()), new Font(Font.HELVETICA, 10, Font.NORMAL, new Color(0, 0,
					0))));
				r5c4.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r5c4.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				tR4.addCell(r5c4);

				PdfPCell r5c5 = new PdfPCell(new Paragraph(rp.getReportSourceName(), new Font(
					Font.HELVETICA, 10, Font.NORMAL, new Color(0, 0, 0))));
				r5c5.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r5c5.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				tR4.addCell(r5c5);

				PdfPCell r5c6 = new PdfPCell(new Paragraph(rp.getMahNumber(), new Font(Font.HELVETICA,
					10, Font.NORMAL, new Color(0, 0, 0))));
				r5c6.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r5c6.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				tR4.addCell(r5c6);

				// removed Feature of Report field - AH April
				// 13th, 2012
				// PdfPCell r5c7 = new PdfPCell(new
				// Paragraph(rp.getReportFeatureName(),
				// new Font(Font.HELVETICA, 10, Font.NORMAL,
				// new Color(0, 0, 0))));
				// r5c7.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				// r5c7.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				// tR4.addCell(r5c7);

				PdfPCell r5c8 = new PdfPCell(new Paragraph(rp.getReportTypeName(), new Font(
					Font.HELVETICA, 10, Font.NORMAL, new Color(0, 0, 0))));
				r5c8.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r5c8.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				tR4.addCell(r5c8);

				PdfPCell r5c9 = new PdfPCell(new Paragraph(rp.getReporterTypeName(), new Font(
					Font.HELVETICA, 10, Font.NORMAL, new Color(0, 0, 0))));
				r5c9.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r5c9.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				tR4.addCell(r5c9);

				document.add(tR4);

				// Draws a separation space row
				document.add(new Paragraph(" ", new Font(Font.HELVETICA, 3, Font.NORMAL, new Color(0,
					0, 0))));

				// Table Row 6: contains Process Pending, Record
				// Type, Link Aer Number, Serious Report,
				// Death, Life, Report Feature, Type of Report,
				// Reporter Type
				PdfPTable tR6 = new PdfPTable(new float[] { 1.4f, 0.2f, 1.4f, 0.3f, 1.4f, 0.3f, 1.8f,
					0.3f });
				tR6.setWidthPercentage(100);

				String pdfR6C4 = "";
				String pdfR6C6 = "";
				String pdfR6C8 = "";

				if (rp.getDeath() != null) {
				    if ((rp.getDeath()).equals("1")) {
					pdfR6C4 = yes;
				    } else {
					pdfR6C4 = no;
				    }
				}
				if (rp.getDisability() != null) {
				    if ((rp.getDisability()).equals("1")) {
					pdfR6C6 = yes;
				    } else {
					pdfR6C6 = no;
				    }
				}
				if (rp.getCongenitalAnomaly() != null) {
				    if ((rp.getCongenitalAnomaly()).equals("1")) {
					pdfR6C8 = yes;
				    } else {
					pdfR6C8 = no;
				    }
				}

				PdfPCell r6c1 = new PdfPCell(new Paragraph(pdfR6C1, new Font(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0))));
				r6c1.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r6c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r6c1.setBackgroundColor(new Color(200, 200, 200));
				tR6.addCell(r6c1);

				PdfPCell r6c2 = new PdfPCell(new Paragraph("", new Font(Font.HELVETICA, 10,
					Font.NORMAL, new Color(0, 0, 0))));
				r6c2.setBorder(0);
				tR6.addCell(r6c2);

				PdfPCell r6c3 = new PdfPCell(new Paragraph(pdfR6C3 + colon, new Font(Font.HELVETICA,
					10, Font.BOLD, new Color(6, 3, 6))));
				r6c3.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r6c3.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_RIGHT);
				r6c3.setBackgroundColor(new Color(200, 200, 200));
				r6c3.disableBorderSide(8);
				tR6.addCell(r6c3);

				PdfPCell r6c4 = new PdfPCell(new Paragraph(pdfR6C4, new Font(Font.HELVETICA, 10,
					Font.NORMAL, new Color(0, 0, 0))));
				r6c4.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r6c4.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r6c4.disableBorderSide(4);
				tR6.addCell(r6c4);

				PdfPCell r6c5 = new PdfPCell(new Paragraph(pdfR6C5 + colon, new Font(Font.HELVETICA,
					10, Font.BOLD, new Color(6, 3, 6))));
				r6c5.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r6c5.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_RIGHT);
				r6c5.setBackgroundColor(new Color(200, 200, 200));
				r6c5.disableBorderSide(8);
				tR6.addCell(r6c5);

				PdfPCell r6c6 = new PdfPCell(new Paragraph(pdfR6C6, new Font(Font.HELVETICA, 10,
					Font.NORMAL, new Color(0, 0, 0))));
				r6c6.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r6c6.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r6c6.disableBorderSide(4);
				tR6.addCell(r6c6);

				PdfPCell r6c7 = new PdfPCell(new Paragraph(pdfR6C7 + colon, new Font(Font.HELVETICA,
					10, Font.BOLD, new Color(6, 3, 6))));
				r6c7.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r6c7.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_RIGHT);
				r6c7.setBackgroundColor(new Color(200, 200, 200));
				r6c7.disableBorderSide(8);
				tR6.addCell(r6c7);

				PdfPCell r6c8 = new PdfPCell(new Paragraph(pdfR6C8, new Font(Font.HELVETICA, 10,
					Font.NORMAL, new Color(0, 0, 0))));
				r6c8.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r6c8.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r6c8.disableBorderSide(4);
				tR6.addCell(r6c8);

				// Table Row 7: Data Value
				String pdfR7c1 = "";
				String pdfR7C4 = "";
				String pdfR7c6 = "";
				String pdfR7C8 = "";

				if (rp.getSeriousnessName() != null) {
				    pdfR7c1 = rp.getSeriousnessName();
				}
				if (rp.getLifeThreatening() != null) {
				    if ((rp.getLifeThreatening()).equals("1")) {
					pdfR7C4 = yes;
				    } else {
					pdfR7C4 = no;
				    }
				}
				if (rp.getHospitalRequired() != null) {
				    if ((rp.getHospitalRequired()).equals("1")) {
					pdfR7c6 = yes;
				    } else {
					pdfR7c6 = no;
				    }
				}
				if (rp.getOtherMedicalCondition() != null) {
				    if ((rp.getOtherMedicalCondition()).equals("1")) {
					pdfR7C8 = yes;
				    } else {
					pdfR7C8 = no;
				    }
				}

				PdfPCell r7c1 = new PdfPCell(new Paragraph(pdfR7c1, new Font(Font.HELVETICA, 10,
					Font.NORMAL, new Color(0, 0, 0))));
				r7c1.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r7c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				tR6.addCell(r7c1);

				PdfPCell r7c2 = new PdfPCell(new Paragraph("", new Font(Font.HELVETICA, 10,
					Font.NORMAL, new Color(0, 0, 0))));
				r7c2.setBorder(0);
				tR6.addCell(r7c2);

				PdfPCell r7c3 = new PdfPCell(new Paragraph(pdfR7C3 + colon, new Font(Font.HELVETICA,
					10, Font.BOLD, new Color(6, 3, 6))));
				r7c3.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r7c3.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_RIGHT);
				r7c3.setBackgroundColor(new Color(200, 200, 200));
				r7c3.disableBorderSide(8);
				tR6.addCell(r7c3);

				PdfPCell r7c4 = new PdfPCell(new Paragraph(pdfR7C4, new Font(Font.HELVETICA, 10,
					Font.NORMAL, new Color(0, 0, 0))));
				r7c4.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r7c4.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r7c4.disableBorderSide(4);
				tR6.addCell(r7c4);

				PdfPCell r7c5 = new PdfPCell(new Paragraph(pdfR7C5 + colon, new Font(Font.HELVETICA,
					10, Font.BOLD, new Color(6, 3, 6))));
				r7c5.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r7c5.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_RIGHT);
				r7c5.setBackgroundColor(new Color(200, 200, 200));
				r7c5.disableBorderSide(8);
				tR6.addCell(r7c5);

				PdfPCell r7c6 = new PdfPCell(new Paragraph(pdfR7c6, new Font(Font.HELVETICA, 10,
					Font.NORMAL, new Color(0, 0, 0))));
				r7c6.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r7c6.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r7c6.disableBorderSide(4);
				tR6.addCell(r7c6);

				PdfPCell r7c7 = new PdfPCell(new Paragraph(pdfR7C7 + colon, new Font(Font.HELVETICA,
					10, Font.BOLD, new Color(6, 3, 6))));
				r7c7.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r7c7.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_RIGHT);
				r7c7.setBackgroundColor(new Color(200, 200, 200));
				r7c7.disableBorderSide(8);
				tR6.addCell(r7c7);

				PdfPCell r7c8 = new PdfPCell(new Paragraph(pdfR7C8, new Font(Font.HELVETICA, 10,
					Font.NORMAL, new Color(0, 0, 0))));
				r7c8.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r7c8.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r7c8.disableBorderSide(4);
				tR6.addCell(r7c8);
				document.add(tR6);

				// Draws a separation space row
				document.add(new Paragraph(" ", new Font(Font.HELVETICA, 5, Font.NORMAL, new Color(0,
					0, 0))));

				// Table Row 8: Patient Information Label
				PdfPTable tR8 = new PdfPTable(new float[] { 2f, 8f });
				tR8.setWidthPercentage(100);

				PdfPCell r8c1 = new PdfPCell(new Paragraph(pdfR8C1, new Font(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0))));
				r8c1.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r8c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_LEFT);
				r8c1.setBorderWidth(0.5f);
				tR8.addCell(r8c1);

				PdfPCell r8c2 = new PdfPCell(new Paragraph(""));
				r8c2.setBorder(0);
				tR8.addCell(r8c2);
				document.add(tR8);

				// Table Row 9: contains Age, Gender, Height,
				// Weight, Report Outcome
				PdfPTable tR9 = new PdfPTable(new float[] { 0.7f, 0.7f, 1f, 0.9f, 1.7f });
				tR9.setWidthPercentage(70);
				tR9.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_LEFT);

				PdfPCell r9c1 = new PdfPCell(new Paragraph(pdfR9C1, new Font(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0))));
				r9c1.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r9c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r9c1.setBackgroundColor(new Color(200, 200, 200));
				tR9.addCell(r9c1);

				PdfPCell r9c2 = new PdfPCell(new Paragraph(pdfR9C2, new Font(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0))));
				r9c2.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r9c2.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r9c2.setBackgroundColor(new Color(200, 200, 200));
				tR9.addCell(r9c2);

				PdfPCell r9c3 = new PdfPCell(new Paragraph(pdfR9C3, new Font(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0))));
				r9c3.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r9c3.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r9c3.setBackgroundColor(new Color(200, 200, 200));
				tR9.addCell(r9c3);

				PdfPCell r9c4 = new PdfPCell(new Paragraph(pdfR9C4, new Font(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0))));
				r9c4.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r9c4.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r9c4.setBackgroundColor(new Color(200, 200, 200));
				tR9.addCell(r9c4);

				PdfPCell r9c5 = new PdfPCell(new Paragraph(pdfR9C5, new Font(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0))));
				r9c5.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r9c5.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r9c5.setBackgroundColor(new Color(200, 200, 200));
				tR9.addCell(r9c5);

				String pdfR10C1 = "";
				String pdfR10C2 = "";
				String pdfR10C3 = "";
				String pdfR10C4 = "";
				String pdfR10C5 = "";

				if (rp.getAge() != null) {
				    pdfR10C1 = rp.getAge().toString();
				    if (rp.getAgeUnitName() != null) {
					pdfR10C1 = pdfR10C1 + space + rp.getAgeUnitName();
				    }
				}
				if (rp.getGenderName() != null) {
				    pdfR10C2 = rp.getGenderName();
				}
				if (rp.getHeight() != null) {
				    pdfR10C3 = rp.getHeight().toString();
				    if (rp.getHeightUnitName() != null) {
					pdfR10C3 = pdfR10C3 + space + rp.getHeightUnitName();
				    }
				}
				if (rp.getWeight() != null) {
				    pdfR10C4 = rp.getWeight().toString();
				    if (rp.getWeightUnitName() != null) {
					pdfR10C4 = pdfR10C4 + space + rp.getWeightUnitName();
				    }
				}
				if (rp.getOutcomeName() != null) {
				    pdfR10C5 = rp.getOutcomeName();
				}

				// Data
				PdfPCell r10c1 = new PdfPCell(new Paragraph(pdfR10C1, new Font(Font.HELVETICA, 10,
					Font.NORMAL, new Color(0, 0, 0))));
				r10c1.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r10c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				tR9.addCell(r10c1);

				PdfPCell r10c2 = new PdfPCell(new Paragraph(pdfR10C2, new Font(Font.HELVETICA, 10,
					Font.NORMAL, new Color(0, 0, 0))));
				r10c2.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r10c2.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				tR9.addCell(r10c2);

				PdfPCell r10c3 = new PdfPCell(new Paragraph(pdfR10C3, new Font(Font.HELVETICA, 10,
					Font.NORMAL, new Color(0, 0, 0))));
				r10c3.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r10c3.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				tR9.addCell(r10c3);

				PdfPCell r10c4 = new PdfPCell(new Paragraph(pdfR10C4, new Font(Font.HELVETICA, 10,
					Font.NORMAL, new Color(0, 0, 0))));
				r10c4.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r10c4.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				tR9.addCell(r10c4);

				PdfPCell r10c5 = new PdfPCell(new Paragraph(pdfR10C5, new Font(Font.HELVETICA, 10,
					Font.NORMAL, new Color(0, 0, 0))));
				r10c5.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r10c5.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				tR9.addCell(r10c5);

				document.add(tR9);

				// Draws a separation space row
				document.add(new Paragraph(" ", new Font(Font.HELVETICA, 3, Font.NORMAL, new Color(0,
					0, 0))));

				LookUpDAO repLinkDao = new LookUpDAO();
				List rLinks = repLinkDao.getReportLinksbyReportID(rp.getReportID());

				if (rLinks.isEmpty()) {
				    // Table Row 17: Link / Duplicate Report
				    // Information Label
				    PdfPTable tR17 = new PdfPTable(new float[] { 1f, 1f });
				    tR17.setWidthPercentage(70);
				    tR17.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_LEFT);

				    PdfPCell r17c1 = new PdfPCell(new Paragraph(pdfR17C1, new Font(Font.HELVETICA, 10,
					    Font.BOLD, new Color(0, 0, 0))));
				    r17c1.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				    r17c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_LEFT);
				    r17c1.setBorderWidth(0.5f);
				    tR17.addCell(r17c1);

				    PdfPCell r17c2 = new PdfPCell(new Paragraph(""));
				    r17c2.setBorder(0);
				    tR17.addCell(r17c2);
				    document.add(tR17);

				    // Table Row 18: contains MedDRA Preferred
				    // Term, MedDRA Version, Duration
				    PdfPTable tR18 = new PdfPTable(new float[] { 1f, 1f });
				    tR18.setWidthPercentage(70);
				    tR18.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_LEFT);

				    PdfPCell r18c1 = new PdfPCell(new Paragraph(pdfR18C1, new Font(Font.HELVETICA, 10,
					    Font.BOLD, new Color(0, 6, 9))));
				    r18c1.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				    r18c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				    r18c1.setBackgroundColor(new Color(200, 200, 200));
				    tR18.addCell(r18c1);

				    PdfPCell r18c2 = new PdfPCell(new Paragraph(pdfR18C2, new Font(Font.HELVETICA, 10,
					    Font.BOLD, new Color(0, 0, 0))));
				    r18c2.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				    r18c2.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				    r18c2.setBackgroundColor(new Color(200, 200, 200));
				    tR18.addCell(r18c2);

				    // Set repeated header row
				    tR18.setHeaderRows(1);

				    String pdfR19C1 = resBundle.getString("label.reportInfo.noLinkReport");

				    PdfPCell r19c1 = new PdfPCell(new Paragraph(pdfR19C1, new Font(Font.HELVETICA, 10,
					    Font.NORMAL, new Color(0, 6, 9))));
				    r19c1.setColspan(2);
				    r19c1.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				    r19c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_LEFT);
				    tR18.addCell(r19c1);

				    document.add(tR18);

				} else {

				    // Table Row 17: Link / Duplicate Report
				    // Information Label
				    PdfPTable tR17 = new PdfPTable(new float[] { 1f, 1f });
				    tR17.setWidthPercentage(70);
				    tR17.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_LEFT);

				    PdfPCell r17c1 = new PdfPCell(new Paragraph(pdfR17C1, new Font(Font.HELVETICA, 10,
					    Font.BOLD, new Color(0, 0, 0))));
				    r17c1.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				    r17c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_LEFT);
				    r17c1.setBorderWidth(0.5f);
				    tR17.addCell(r17c1);

				    PdfPCell r17c2 = new PdfPCell(new Paragraph(""));
				    r17c2.setBorder(0);
				    tR17.addCell(r17c2);
				    document.add(tR17);

				    // Table Row 18: contains MedDRA Preferred
				    // Term, MedDRA Version, Duration
				    PdfPTable tR18 = new PdfPTable(new float[] { 1f, 1f });
				    tR18.setWidthPercentage(70);
				    tR18.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_LEFT);

				    PdfPCell r18c1 = new PdfPCell(new Paragraph(pdfR18C1, new Font(Font.HELVETICA, 10,
					    Font.BOLD, new Color(0, 6, 9))));
				    r18c1.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				    r18c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				    r18c1.setBackgroundColor(new Color(200, 200, 200));
				    tR18.addCell(r18c1);

				    PdfPCell r18c2 = new PdfPCell(new Paragraph(pdfR18C2, new Font(Font.HELVETICA, 10,
					    Font.BOLD, new Color(0, 0, 0))));
				    r18c2.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				    r18c2.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				    r18c2.setBackgroundColor(new Color(200, 200, 200));
				    tR18.addCell(r18c2);

				    // Set repeated header row
				    tR18.setHeaderRows(1);

				    Iterator l = rLinks.iterator();

				    while (l.hasNext()) {
					ReportLink rl = (ReportLink) l.next();
					String pdfR19C1 = "";
					String pdfR19C2 = "";

					if (rl != null) {
					    if (rl.getRecordTypeName() != null) {
						pdfR19C1 = rl.getRecordTypeName();
					    }
					    if (rl.getReportLink() != null) {
						pdfR19C2 = rl.getReportLink();
					    }
					}

					PdfPCell r19c1 = new PdfPCell(new Paragraph(pdfR19C1, new Font(Font.HELVETICA,
						10, Font.NORMAL, new Color(0, 6, 9))));
					r19c1.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
					r19c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_LEFT);
					tR18.addCell(r19c1);

					PdfPCell r19c2 = new PdfPCell(new Paragraph(pdfR19C2, new Font(Font.HELVETICA,
						10, Font.NORMAL, new Color(0, 0, 0))));
					r19c2.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
					r19c2.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
					tR18.addCell(r19c2);

				    }
				    document.add(tR18);
				} // end else statement

				// Draws a separation space row
				document.add(new Paragraph(" ", new Font(Font.HELVETICA, 3, Font.NORMAL, new Color(0,
					0, 0))));

				// Table Row 11: Product Information Label
				PdfPTable tR11 = new PdfPTable(new float[] { 2f, 8f });
				tR11.setWidthPercentage(100);

				PdfPCell r11c1 = new PdfPCell(new Paragraph(pdfR11C1, new Font(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0))));
				r11c1.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r11c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_LEFT);
				r11c1.setBorderWidth(0.5f);
				tR11.addCell(r11c1);

				PdfPCell r11c2 = new PdfPCell(new Paragraph(""));
				r11c2.setBorder(0);
				tR11.addCell(r11c2);
				document.add(tR11);

				// Table Row 12: contains Product Description,
				// Product Role, Dosage Form, Route, Dosing,
				// Frequency, Therapy Duration
				PdfPTable tR12 = new PdfPTable(new float[] { 1.2f, 1f, 1f, 0.8f, 0.7f, 0.7f, 0.9f, 0.7f });
				tR12.setWidthPercentage(100);

				PdfPCell r12c1 = new PdfPCell(new Paragraph(pdfR12C1, new Font(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 6, 9))));
				r12c1.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r12c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r12c1.setBackgroundColor(new Color(200, 200, 200));
				tR12.addCell(r12c1);

				PdfPCell r12c2 = new PdfPCell(new Paragraph(pdfR12C2, new Font(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0))));
				r12c2.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r12c2.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r12c2.setBackgroundColor(new Color(200, 200, 200));
				tR12.addCell(r12c2);

				PdfPCell r12c3 = new PdfPCell(new Paragraph(pdfR12C3, new Font(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0))));
				r12c3.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r12c3.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r12c3.setBackgroundColor(new Color(200, 200, 200));
				tR12.addCell(r12c3);

				PdfPCell r12c4 = new PdfPCell(new Paragraph(pdfR12C4, new Font(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0))));
				r12c4.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r12c4.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r12c4.setBackgroundColor(new Color(200, 200, 200));
				tR12.addCell(r12c4);

				PdfPCell r12c5 = new PdfPCell(new Paragraph(pdfR12C5, new Font(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0))));
				r12c5.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r12c5.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r12c5.setBackgroundColor(new Color(200, 200, 200));
				tR12.addCell(r12c5);

				PdfPCell r12c6 = new PdfPCell(new Paragraph(pdfR12C6, new Font(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0))));
				r12c6.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r12c6.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r12c6.setBackgroundColor(new Color(200, 200, 200));
				tR12.addCell(r12c6);

				PdfPCell r12c7 = new PdfPCell(new Paragraph(pdfR12C7, new Font(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0))));
				r12c7.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r12c7.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r12c7.setBackgroundColor(new Color(200, 200, 200));
				tR12.addCell(r12c7);
				
				PdfPCell r12c8 = new PdfPCell(new Paragraph(pdfR12C8, new Font(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0))));
				r12c8.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r12c8.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r12c8.setBackgroundColor(new Color(200, 200, 200));
				tR12.addCell(r12c8);

				// Set repeated header row
				tR12.setHeaderRows(1);

				ReportDrugDAO repdrugDao = new ReportDrugDAO();
				List products = repdrugDao.getDrugsByReportID(rp.getReportID());
				Iterator p = products.iterator();

				while (p.hasNext()) {
				    ReportDrug rd = (ReportDrug) p.next();
				    String pdfR13C1 = "";
				    String pdfR13C2 = "";
				    String pdfR13C3 = "";
				    String pdfR13C4 = "";
				    String pdfR13C5 = "";
				    String pdfR13C6 = "";
				    String pdfR13C7 = "";
				    String pdfR13C8 = "";

				    if (rd != null) {
					if (rd.getDrugName() != null) {
					    pdfR13C1 = rd.getDrugName();
					}
					if (rd.getDrugInvDesc() != null) {
					    pdfR13C2 = rd.getDrugInvDesc();
					}
					if (rd.getDosageFormName() != null) {
					    pdfR13C3 = rd.getDosageFormName();
					}
					if (rd.getRouteAdminName() != null) {
					    pdfR13C4 = rd.getRouteAdminName();
					}
					if (rd.getUnitDoseQty() != null) {
					    pdfR13C5 = rd.getUnitDoseQty().toString();
					    if (rd.getDoseUnitName() != null) {
						pdfR13C5 = pdfR13C5 + space + rd.getDoseUnitName();
					    }
					}
					if (rd.getFreqTimeString() != null) {
					    pdfR13C6 = rd.getFreqTimeString();
					}
					if (rd.getTherapyDuration() != null) {
					    pdfR13C7 = rd.getTherapyDuration().toString();
					    if (rd.getTherapyDurationUnitName() != null) {
						pdfR13C7 = pdfR13C7 + space + rd.getTherapyDurationUnitName();
					    }
					}
					if (rd.getIndicationName() != null) {
					    pdfR13C8 = rd.getIndicationName().toString();
					}
				    }

				    PdfPCell r13c1 = new PdfPCell(new Paragraph(pdfR13C1, new Font(Font.HELVETICA, 10,
					    Font.NORMAL, new Color(0, 6, 9))));
				    r13c1.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				    r13c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_LEFT);
				    tR12.addCell(r13c1);

				    PdfPCell r13c2 = new PdfPCell(new Paragraph(pdfR13C2, new Font(Font.HELVETICA, 10,
					    Font.NORMAL, new Color(0, 0, 0))));
				    r13c2.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				    r13c2.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				    tR12.addCell(r13c2);

				    PdfPCell r13c3 = new PdfPCell(new Paragraph(pdfR13C3, new Font(Font.HELVETICA, 10,
					    Font.NORMAL, new Color(0, 0, 0))));
				    r13c3.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				    r13c3.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				    tR12.addCell(r13c3);

				    PdfPCell r13c4 = new PdfPCell(new Paragraph(pdfR13C4, new Font(Font.HELVETICA, 10,
					    Font.NORMAL, new Color(0, 0, 0))));
				    r13c4.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				    r13c4.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				    tR12.addCell(r13c4);

				    PdfPCell r13c5 = new PdfPCell(new Paragraph(pdfR13C5, new Font(Font.HELVETICA, 10,
					    Font.NORMAL, new Color(0, 0, 0))));
				    r13c5.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				    r13c5.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				    tR12.addCell(r13c5);

				    PdfPCell r13c6 = new PdfPCell(new Paragraph(pdfR13C6, new Font(Font.HELVETICA, 10,
					    Font.NORMAL, new Color(0, 0, 0))));
				    r13c6.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				    r13c6.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				    tR12.addCell(r13c6);

				    PdfPCell r13c7 = new PdfPCell(new Paragraph(pdfR13C7, new Font(Font.HELVETICA, 10,
					    Font.NORMAL, new Color(0, 0, 0))));
				    r13c7.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				    r13c7.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				    tR12.addCell(r13c7);
				    
				    PdfPCell r13c8 = new PdfPCell(new Paragraph(pdfR13C8, new Font(Font.HELVETICA, 10,
					    Font.NORMAL, new Color(0, 0, 0))));
				    r13c8.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				    r13c8.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				    tR12.addCell(r13c8);
				}
				document.add(tR12);

				// Draws a separation space row
				document.add(new Paragraph(" ", new Font(Font.HELVETICA, 3, Font.NORMAL, new Color(0,
					0, 0))));

				// Table Row 14: Reaction Information Label
				PdfPTable tR14 = new PdfPTable(new float[] { 2f, 8f });
				tR14.setWidthPercentage(100);

				PdfPCell r14c1 = new PdfPCell(new Paragraph(pdfR14C1, new Font(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0))));
				r14c1.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r14c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_LEFT);
				r14c1.setBorderWidth(0.5f);
				tR14.addCell(r14c1);

				PdfPCell r14c2 = new PdfPCell(new Paragraph(""));
				r14c2.setBorder(0);
				tR14.addCell(r14c2);
				document.add(tR14);

				// Table Row 15: contains MedDRA Preferred Term,
				// MedDRA Version, Duration
				PdfPTable tR15 = new PdfPTable(new float[] { 1.4f, 0.6f, 1f });
				tR15.setWidthPercentage(100);

				PdfPCell r15c1 = new PdfPCell(new Paragraph(pdfR15C1, new Font(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 6, 9))));
				r15c1.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r15c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r15c1.setBackgroundColor(new Color(200, 200, 200));
				tR15.addCell(r15c1);

				PdfPCell r15c2 = new PdfPCell(new Paragraph(pdfR15C2, new Font(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0))));
				r15c2.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r15c2.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r15c2.setBackgroundColor(new Color(200, 200, 200));
				tR15.addCell(r15c2);

				PdfPCell r15c3 = new PdfPCell(new Paragraph(pdfR15C3, new Font(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0))));
				r15c3.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				r15c3.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				r15c3.setBackgroundColor(new Color(200, 200, 200));
				tR15.addCell(r15c3);

				// Set repeated header row
				tR15.setHeaderRows(1);

				ReactionDAO reactDao = new ReactionDAO();
				List reactions = reactDao.getReactionsByReportID(rp.getReportID());
				Iterator r = reactions.iterator();

				while (r.hasNext()) {
				    Reaction re = (Reaction) r.next();
				    String pdfR16C1 = "";
				    String pdfR16C2 = "";
				    String pdfR16C3 = "";

				    if (re != null) {
					if (re.getPtName() != null) {
					    pdfR16C1 = re.getPtName();
					}
					if (re.getMeddraVersion() != null) {
					    pdfR16C2 = re.getMeddraVersion();
					}
					if (re.getDuration() != null) {
					    pdfR16C3 = re.getDuration().toString();
					    if (re.getDurationUnit() != null) {
						pdfR16C3 = pdfR16C3 + space + re.getDurationUnit();
					    }
					}
				    }

				    PdfPCell r16c1 = new PdfPCell(new Paragraph(pdfR16C1, new Font(Font.HELVETICA, 10,
					    Font.NORMAL, new Color(0, 6, 9))));
				    r16c1.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				    r16c1.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_LEFT);
				    tR15.addCell(r16c1);

				    PdfPCell r16c2 = new PdfPCell(new Paragraph(pdfR16C2, new Font(Font.HELVETICA, 10,
					    Font.NORMAL, new Color(0, 0, 0))));
				    r16c2.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				    r16c2.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				    tR15.addCell(r16c2);

				    PdfPCell r16c3 = new PdfPCell(new Paragraph(pdfR16C3, new Font(Font.HELVETICA, 10,
					    Font.NORMAL, new Color(0, 0, 0))));
				    r16c3.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
				    r16c3.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
				    tR15.addCell(r16c3);
				}
				document.add(tR15);
				document.newPage();
				count++;
			    }

			} catch (DocumentException de) {
			    System.err.println(de.getMessage());
			} catch (IOException ioe) {
			    System.err.println(ioe.getMessage());
			}

			// step 5: we close the document
			document.close();
			forward = null;
			long FreeMem = Runtime.getRuntime().freeMemory();
			Runtime.getRuntime().gc();
			if (ApplicationGlobals.DEBUG == "memory" || ApplicationGlobals.DEBUG == "all") {
			    System.out.println(">>>> PDF Memory Saved : "
				    + ((Runtime.getRuntime().freeMemory() - FreeMem) / 1000000) + "MB");
			}
			break;
		    case 2: // EXCEL
			List searchResults = null;
			SearchCriteria searchCrit = new SearchCriteria();
			SearchDAO searchDAO = new SearchDAO();
			// retrieve Search Criteria from the session
			searchCrit = (SearchCriteria) session.getAttribute(ApplicationGlobals.SEARCH_CRITERIA);
			try {
			    double startTimeStamp = System.currentTimeMillis();

			    // run search again to get the raw search results
			    searchResults = searchDAO.searchByRowQuery(searchCrit, (Locale) session
				    .getAttribute("org.apache.struts.action.LOCALE"), 20, 1, 1);
			    //System.out.println("@@@@ Oracle Row Time: " + (System.currentTimeMillis() - startTimeStamp)
			    //    / 1000 + " seconds");
			    startTimeStamp = System.currentTimeMillis(); // reset
			    // the
			    // timestamp
			    // to
			    // track
			    // excel
			    // processing
			    // time

			    // using these results, export to Excel
			    HSSFWorkbook wb = new HSSFWorkbook();
			    ExcelExport excelExport = new ExcelExport();
			    wb = excelExport.exportDataToExcel(searchResults, currentLocale);

			    // Initialize Excel filename
			    response.setContentType("application/vnd.ms-excel");
			    response.setHeader("Content-disposition", "attachment; filename =" + today
				    + "_exportExcel.xls");

			    ServletOutputStream out = response.getOutputStream();
			    // System.out.println("WRITE TO FILE XLS");
			    wb.write(out);
			    out.flush();
			    out.close();
			    response.flushBuffer();
			    //System.out.println("@@@@ Excel Process Time: "
			    //   + (System.currentTimeMillis() - startTimeStamp) / 1000 + " seconds");
			    // System.out.println("------- finished flushing everything ------");
			    forward = null;
			    FreeMem = Runtime.getRuntime().freeMemory();
			    Runtime.getRuntime().gc();
			    if (ApplicationGlobals.DEBUG == "memory" || ApplicationGlobals.DEBUG == "all") {
				System.out.println(">>>> EXCEL Memory Saved : "
					+ ((Runtime.getRuntime().freeMemory() - FreeMem) / 1000000) + "MB");
			    }

			} catch (RuntimeException e) {
			    e.printStackTrace();
			    System.out.println(e.getMessage());
			    messages
				    .add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.failure.excel.export"));
			}
			break;
		    default:
			forward = mapping.findForward("success");
			break;
		    }
		}
	    }

	} else {
	    forward = mapping.findForward("sessionTimeout");
	}

	if (!messages.isEmpty()) {
	    saveMessages(request, messages);
	    forward = mapping.findForward("excelFailure");
	}

	return (forward);
    }

    public String formatDateDisplay(Date date) {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String formattedDate = null;
	formattedDate = dateFormat.format(date);
	return formattedDate;
    }

}