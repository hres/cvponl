/*
 * Created on 24-June-2009
 */
package ca.gc.hc.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


/**
 * @author Ahasan Exports to an Excel file based on the raw search results
 * 
 */
public class ExcelExport {

    private HSSFWorkbook wb = new HSSFWorkbook();
    private HSSFSheet sheet = wb.createSheet("Export");
    private static int colNum;

    /**
     * Method takes the raw search results and writes them directly to an Excel
     * worksheet
     * 
     * @param searchResultsRaw
     * @return HSSFWorkbook
     * @throws Exception
     */

    /*
     * - Row Legend (note some columns are no longer being used) 0 ADR_ID 1
     * REPORT_ID 2 REPORT_NO 3 VERSION_NO 4 DATRECEIVED 5 DATINTRECEIVED 6
     * MAH_NO 7 REPORT_TYPE_ENG 8 REPORT_TYPE_FR 9 GENDER_ENG 10 GENDER_FR 11
     * AGE 12 AGE_UNIT_ENG 13 AGE_UNIT_FR 14 AGE_GROUP_CODE 15 OUTCOME_ENG 16
     * OUTCOME_FR 17 WEIGHT 18 WEIGHT_UNIT_ENG 19 WEIGHT_UNIT_FR 20 HEIGHT 21
     * HEIGHT_UNIT_ENG 22 HEIGHT_UNIT_FR 23 SERIOUSNESS_ENG 24 SERIOUSNESS_FR 25
     * DEATH 26 DISABILITY 27 CONGENITAL_ANOMALY 28 LIFE_THREATENING 29
     * HOSP_REQUIRED 30 OTHER_MEDICALLY_IMP_COND 31 REPORTER_TYPE_ENG 32
     * REPORTER_TYPE_FR 33 SOURCE_ENG 34 SOURCE_FR 35 REPORT_LINK_FLG 36
     * DURATION 37 DURATION_UNIT_ENG 38 DURATION_UNIT_FR 39 PT_NAME_ENG 40
     * PT_NAME_FR 41 SOC_NAME_ENG 42 SOC_NAME_FR 43 MEDDRA_VERSION 44
     * REPORT_DRUG_ID 45 DRUGNAME 46 DRUGINVOLV_ENG 47 DRUGINVOLV_FR 48
     * ROUTEADMIN_ENG 49 ROUTEADMIN_FR 50 UNIT_DOSE_QTY 51 DOSE_UNIT_ENG 52
     * DOSE_UNIT_FR 53 FREQUENCY 54 FREQ_TIME_UNIT_ENG 55 FREQ_TIME_UNIT_FR 56
     * THERAPY_DURATION 57 THERAPY_DURATION_UNIT_ENG 58 THERAPY_DURATION_UNIT_FR
     * 59 DOSAGEFORM_ENG 60 DOSAGEFORM_FR 61 DRUG_PRODUCT_ID 62 FREQ_TIME 63
     * FREQUENCY_TIME_ENG 64 FREQUENCY_TIME_FR 65 AGE_GROUP_ENG 66 AGE_GROUP_FR
     * 67 REPORT_LINK 68 RECORD_TYPE_ENG 69 RECORD_TYPE_FR INDICATION_NAME_ENG
     * 70 INDICATION_NAME_FR 71
     */

    @SuppressWarnings("deprecation")
    public HSSFWorkbook exportDataToExcel(List searchResultsRaw, Locale locale) {
	HSSFRow row = null;
	writeColumnTitles(wb, sheet, locale);
	Object[] searchResultRow = null;
	if (ApplicationGlobals.DEBUG == "excel" || ApplicationGlobals.DEBUG == "all") {
	    System.out.println("START EXCEL EXPORT exportDataToExcel ");
	}

	// try {
	for (int rowNum = 1, recordNum = 0; rowNum <= searchResultsRaw.size(); rowNum++, recordNum++) {
	    searchResultRow = (Object[]) searchResultsRaw.get(recordNum);
	    // create row
	    row = sheet.createRow(rowNum);
	    colNum = 0;

	    if (locale.getDisplayLanguage() == Locale.CANADA.getDisplayLanguage()) {
		// include only the English fields
		// row.createCell((int) colNum).setCellValue(arg0));
		// HSSFRichTextString stringinput = new
		// HSSFRichTextString((String)searchResultRow[2]);
		// row.createCell((int) colNum).setCellValue(stringinput);
		row.createCell((int) colNum).setCellValue((String) searchResultRow[2]);
		row.createCell((int) ++colNum).setCellValue(
			checkNull(convertBigDecimalToLong((BigDecimal) searchResultRow[3])));
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[6]);
		row.createCell((int) ++colNum).setCellValue(formatDateDisplay((Date) searchResultRow[5]));
		row.createCell((int) ++colNum).setCellValue(formatDateDisplay((Date) searchResultRow[4]));
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[7]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[23]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[65]);
		row.createCell((int) ++colNum).setCellValue(
			checkNull(convertBigDecimalToLong((BigDecimal) searchResultRow[11])));
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[12]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[9]);
		row.createCell((int) ++colNum).setCellValue(
			checkNull(convertBigDecimalToLong((BigDecimal) searchResultRow[17])));
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[18]);
		row.createCell((int) ++colNum).setCellValue(
			checkNull(convertBigDecimalToLong((BigDecimal) searchResultRow[20])));
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[21]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[15]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[31]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[33]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[25]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[26]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[27]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[28]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[29]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[30]);

		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[45]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[59]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[46]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[48]);
		row.createCell((int) ++colNum).setCellValue(
			checkNullDouble(convertBigDecimalToDouble((BigDecimal) searchResultRow[50])));
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[51]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[63]);
		row.createCell((int) ++colNum).setCellValue(
			checkNull(convertBigDecimalToLong((BigDecimal) searchResultRow[56])));
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[57]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[70]);
		
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[39]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[41]);
		row.createCell((int) ++colNum).setCellValue(
			checkNull(convertBigDecimalToLong((BigDecimal) searchResultRow[36])));
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[37]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[43]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[68]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[67]);

		if (ApplicationGlobals.DEBUG == "excel" || ApplicationGlobals.DEBUG == "all") {
		    System.out.println("Row Num --> " + rowNum);
		    System.out.println("Col Num --> " + colNum);
		    System.out.println("Record Num --> " + recordNum);

		    if (rowNum > 20000) {
			System.out.println("Free Memory -- " + Runtime.getRuntime().freeMemory());
			System.out.println("Total Memory -- " + Runtime.getRuntime().totalMemory());
		    }
		}

	    } else {
		// include only the French fields
		row.createCell((int) colNum).setCellValue((String) searchResultRow[2]);
		row.createCell((int) ++colNum).setCellValue(
			checkNull(convertBigDecimalToLong((BigDecimal) searchResultRow[3])));
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[6]);
		row.createCell((int) ++colNum).setCellValue(formatDateDisplay((Date) searchResultRow[5]));
		row.createCell((int) ++colNum).setCellValue(formatDateDisplay((Date) searchResultRow[4]));
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[8]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[24]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[66]);
		row.createCell((int) ++colNum).setCellValue(
			checkNull(convertBigDecimalToLong((BigDecimal) searchResultRow[11])));
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[13]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[10]);
		row.createCell((int) ++colNum).setCellValue(
			checkNull(convertBigDecimalToLong((BigDecimal) searchResultRow[17])));
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[19]);
		row.createCell((int) ++colNum).setCellValue(
			checkNull(convertBigDecimalToLong((BigDecimal) searchResultRow[20])));
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[22]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[16]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[32]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[34]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[25]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[26]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[27]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[28]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[29]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[30]);

		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[45]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[60]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[47]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[49]);
		row.createCell((int) ++colNum).setCellValue(
			checkNullDouble(convertBigDecimalToDouble((BigDecimal) searchResultRow[50])));
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[52]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[64]);
		row.createCell((int) ++colNum).setCellValue(
			checkNull(convertBigDecimalToLong((BigDecimal) searchResultRow[56])));
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[58]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[71]);

		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[40]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[42]);
		row.createCell((int) ++colNum).setCellValue(
			checkNull(convertBigDecimalToLong((BigDecimal) searchResultRow[36])));
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[38]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[43]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[69]);
		row.createCell((int) ++colNum).setCellValue((String) searchResultRow[67]);
	    }
	}
	// } catch (RuntimeException e) {
	// e.printStackTrace();
	// StringBuffer message = new
	// StringBuffer("ExcelExport exportDataToExcel failed");
	// throw new Exception(message.toString());
	// }

	if (ApplicationGlobals.DEBUG == "excel" || ApplicationGlobals.DEBUG == "all") {
	    System.out.println("END EXCEL EXPORT exportDataToExcel ");
	}

	return wb;
    }

    /**
     * Writes the first row containing all of the column headers
     * 
     * @param wb
     * @param sheet
     * @param locale
     */
    private void writeColumnTitles(HSSFWorkbook wb, HSSFSheet sheet, Locale locale) {

	ResourceBundle resBundle = ResourceBundle.getBundle("resources.ApplicationResources", locale);

	List<String> columnTitles = new Vector<String>();
	columnTitles.add(resBundle.getString("label.reportInfo.reportIdfull"));
	columnTitles.add(resBundle.getString("label.reportInfo.versionNumber"));
	columnTitles.add(resBundle.getString("label.reportInfo.ManufactId"));
	columnTitles.add(resBundle.getString("label.initialDateReceived"));
	columnTitles.add(resBundle.getString("label.latestDateReceived"));
	// columnTitles.add(resBundle.getString("label.reportFeature"));
	columnTitles.add(resBundle.getString("label.reportType"));
	columnTitles.add(resBundle.getString("label.seriousness"));
	columnTitles.add(resBundle.getString("label.reportInfo.ageGroupCode"));
	columnTitles.add(resBundle.getString("label.age"));
	columnTitles.add(resBundle.getString("column.header.ageUnit"));
	columnTitles.add(resBundle.getString("label.gender"));
	columnTitles.add(resBundle.getString("label.reportInfo.weight"));
	columnTitles.add(resBundle.getString("column.header.weightUnit"));
	columnTitles.add(resBundle.getString("label.reportInfo.height"));
	columnTitles.add(resBundle.getString("column.header.heightUnit"));
	columnTitles.add(resBundle.getString("label.outcome"));
	columnTitles.add(resBundle.getString("label.reportInfo.reporterType"));
	columnTitles.add(resBundle.getString("label.reportSource"));
	columnTitles.add(resBundle.getString("label.reportInfo.death"));
	columnTitles.add(resBundle.getString("label.reportInfo.disability"));
	columnTitles.add(resBundle.getString("label.reportInfo.congenitalAnomaly"));
	columnTitles.add(resBundle.getString("label.reportInfo.lifeThreat"));
	columnTitles.add(resBundle.getString("label.reportInfo.hospitalization"));
	columnTitles.add(resBundle.getString("label.reportInfo.otherMedicalImp"));

	columnTitles.add(resBundle.getString("column.header.productName"));
	columnTitles.add(resBundle.getString("label.reportInfo.prodInfo.dosForm"));
	columnTitles.add(resBundle.getString("column.header.productRole"));
	columnTitles.add(resBundle.getString("column.header.routeOfAdministration"));
	columnTitles.add(resBundle.getString("column.header.amount"));
	columnTitles.add(resBundle.getString("column.header.amountUnit"));
	// columnTitles.add(resBundle.getString("column.header.frequency"));
	// columnTitles.add(resBundle.getString("column.header.frequencyUnit"));
	columnTitles.add(resBundle.getString("column.header.frequency"));
	columnTitles.add(resBundle.getString("column.header.therapyDuration"));
	columnTitles.add(resBundle.getString("column.header.therapyDurationUnit"));
	columnTitles.add(resBundle.getString("column.header.indication"));

	columnTitles.add(resBundle.getString("column.header.meddraPT"));
	columnTitles.add(resBundle.getString("column.header.meddraSOC"));
	columnTitles.add(resBundle.getString("column.header.reactionDuration"));
	columnTitles.add(resBundle.getString("column.header.reactionDurationUnit"));
	columnTitles.add(resBundle.getString("meddra.version"));
	columnTitles.add(resBundle.getString("label.reportInfo.recordType"));
	columnTitles.add(resBundle.getString("label.reportInfo.linkAERNum"));

	HSSFRow row = sheet.createRow(0);
	for (int colNum = 1; colNum <= columnTitles.size(); colNum++) {
	    HSSFCell colTitle = row.createCell((int) (colNum - 1));
	    colTitle.setCellValue(columnTitles.get(colNum - 1));
	    colTitle.setCellStyle(setColHeadingStyle());
	}
    }

    public Long convertBigDecimalToLong(BigDecimal number) {
	Long convertedNumber = null;
	if (number != null) {
	    convertedNumber = Long.valueOf(number.longValue());
	}
	return convertedNumber;
    }

    public Double convertBigDecimalToDouble(BigDecimal number) {
	Double convertedNumber = null;
	// String formattedNumber = "";
	DecimalFormat df = new DecimalFormat("#.#####");
	if (number != null) {
	    convertedNumber = Double.valueOf(number.doubleValue());
	    // formattedNumber = df.format(convertedNumber);
	    // System.out.println("formatted number : " + formattedNumber);
	}
	return convertedNumber;
    }

    public String checkNull(Long property) {
	String value = "";
	if (property != null) {
	    value = property.toString();
	}
	return value;
    }

    public String checkNullDouble(Double property) {
	String value = "";
	if (property != null) {
	    value = property.toString();
	}
	return value;
    }

    /**
     * formats the style of the column headings
     * 
     * @return HSSFCellStyle
     */
    private HSSFCellStyle setColHeadingStyle() {
	HSSFCellStyle styleHeadingTitle = wb.createCellStyle();
	HSSFFont headingFont = wb.createFont();
	headingFont.setFontHeightInPoints((short) 10);
	headingFont.setFontName("Arial");
	headingFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	styleHeadingTitle.setFont(headingFont);
	return styleHeadingTitle;
    }

    public String formatDateDisplay(Date date) {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String formattedDate = null;
	formattedDate = dateFormat.format(date);
	return formattedDate;
    }

}
