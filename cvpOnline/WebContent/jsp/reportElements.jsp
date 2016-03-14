<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ page import="org.apache.struts.Globals" %>
<%
	pageContext.setAttribute(Globals.XHTML_KEY, "true",
	PageContext.PAGE_SCOPE);
%>
<bean:define id="myAction">
	<logic:equal name="LANG" value="fra">
		/report-rapport-elements.do?lang=eng
	</logic:equal>
	<logic:equal name="LANG" value="eng">
		/report-rapport-elements.do?lang=fra
	</logic:equal>	
</bean:define>
<div class="row alignRight"><a href="<bean:message key='link.help.report.elements' />"><bean:message key="alt.help.section"/></a></div>

<html:form action="<%=myAction%>" method="post">
<p><bean:message key="comment.report.type" /></p>
<div class="row">
	<div class="column80 alignCenter">
		<html:radio name="reportElementForm" property="reportFormView" value="1" styleId="standard" /><label for="standard"><bean:message key="label.report.standard"/></label>
		<html:radio name="reportElementForm" property="reportFormView" value="2" styleId="custom" /><label for="custom"><bean:message key="label.report.custom"/></label><br/><br/>
		<html:submit property="action" styleClass="subject" >
			<bean:message key="button.report.go"/>
		</html:submit>		
	</div>
</div>
<div>
	<html:messages id="message" message="true" bundle="messageRes" header="messages.header" footer="messages.footer">
		<bean:message bundle="messageRes" key="messages.prefix" />
		<bean:write name="message" />
		<bean:message bundle="messageRes" key="messages.suffix" />
	</html:messages> <html:errors bundle="messageRes" />
</div>
<logic:present name="customReport">
<h2><bean:message key="title.report.elements2"/></h2>
<p><bean:message key="comment.element.max" /></p>
	<div class="row-clear"></div>
<fieldset><legend><strong><bean:message key="fieldset.report.elements"/></strong></legend>	
	<div class="row">
		<div class="column5">&nbsp;</div>
		<div class="column45">
			<input type="checkbox" checked="checked" disabled="disabled" id="AERnumber"/><label for="AERnumber">&nbsp;<bean:message key="label.reportInfo.reportId"/></label><br/>				
			<html:checkbox property="versionNo" styleId="versionNo"><label for="versionNo">&nbsp;<bean:message key="label.reportInfo.versionNumber"/></label></html:checkbox><br/>		
			<html:checkbox property="mahNumber" styleId="mahNumber"><label for="mahNumber">&nbsp;<bean:message key="label.reportInfo.ManufactId"/></label></html:checkbox><br/>		
			<html:checkbox property="initialDate" styleId="initialDate"><label for="initialDate">&nbsp;<bean:message key="label.initialDateReceived"/></label></html:checkbox><br/>		
			<html:checkbox property="latestDate" styleId="latestDate"><label for="latestDate">&nbsp;<bean:message key="label.latestDateReceived"/></label></html:checkbox><br/>		
			<html:checkbox property="age" styleId="age"><label for="age">&nbsp;<bean:message key="label.age"/></label></html:checkbox><br/>
			<html:checkbox property="gender" styleId="gender"><label for="gender">&nbsp;<bean:message key="label.gender"/></label></html:checkbox><br/>
<!--			<html:checkbox property="weight" styleId="weight"><label for="weight">&nbsp;<bean:message key="label.reportInfo.weight"/></label></html:checkbox><br/>-->
<!--			<html:checkbox property="height" styleId="height"><label for="height">&nbsp;<bean:message key="label.reportInfo.height"/></label></html:checkbox><br/>-->
		</div>		
		<div class="column45">
<!--			<html:checkbox property="typeOfReport" styleId="typeOfReport"><label for="typeOfReport">&nbsp;<bean:message key="label.reportType"/></label></html:checkbox><br/>		-->
			<html:checkbox property="reporterType" styleId="reporterType"><label for="reporterType">&nbsp;<bean:message key="label.reportInfo.reporterType"/></label></html:checkbox><br/>														
			<html:checkbox property="sourceOfReport" styleId="sourceOfReport"><label for="sourceOfReport">&nbsp;<bean:message key="label.reportSource"/></label></html:checkbox><br/>
<!--			<html:checkbox property="featureOfReport" styleId="featureOfReport"><label for="featureOfReport">&nbsp;<bean:message key="label.reportFeature"/></label></html:checkbox><br/>		-->
			<html:checkbox property="reportOutcome" styleId="reportOutcome"><label for="reportOutcome">&nbsp;<bean:message key="label.outcome"/></label></html:checkbox><br/>
			<html:checkbox property="serious" styleId="serious"><label for="serious">&nbsp;<bean:message key="label.seriousness"/></label></html:checkbox><br/>
			<html:checkbox property="drugName" styleId="drugName"/><label for="drugName">&nbsp;<bean:message key="column.header.drugName"/></label><br/>
			<html:checkbox property="meddraPTName" styleId="meddraPTName"><label for="meddraPTName">&nbsp;<bean:message key="column.header.meddraPT"/></label></html:checkbox><br/>
			<html:checkbox property="meddraSOCname" styleId="meddraSOCname"><label for="meddraSOCname">&nbsp;<bean:message key="column.header.meddraSOC"/></label></html:checkbox><br/>
			<html:checkbox property="reactionDuration" styleId="reactionDuration"><label for="reactionDuration">&nbsp;<bean:message key="column.header.reactionDuration"/></label></html:checkbox><br/>

	<!--  	<html:checkbox property="death" styleId="death"><label for="death">&nbsp;<bean:message key="label.reportInfo.death"/></label></html:checkbox><br/>
			<html:checkbox property="disability" styleId="disability"><label for="disability">&nbsp;<bean:message key="label.reportInfo.disability"/></label></html:checkbox><br/>
			<html:checkbox property="congenitalAnomaly" styleId="congenitalAnomaly"><label for="congenitalAnomaly">&nbsp;<bean:message key="label.reportInfo.congenitalAnomaly"/></label></html:checkbox><br/>
			<html:checkbox property="lifeThreatening" styleId="lifeThreatening"><label for="lifeThreatening">&nbsp;<bean:message key="label.reportInfo.lifeThreat"/></label></html:checkbox><br/>											
			<html:checkbox property="hospitalization" styleId="hospitalization"><label for="hospitalization">&nbsp;<bean:message key="label.reportInfo.hospitalization"/></label></html:checkbox><br/>
			<html:checkbox property="otherMedCondition" styleId="otherMedCondition"><label for="otherMedCondition">&nbsp;<bean:message key="label.reportInfo.otherMedicalImp"/></label></html:checkbox><br/><br/>	
	-->				

	<!-- 
			<html:checkbox property="dosageForm" styleId="dosageForm"><label for="dosageForm">&nbsp;<bean:message key="label.reportInfo.prodInfo.dosForm"/></label></html:checkbox><br/>	
			<html:checkbox property="productRole" styleId="productRole"><label for="productRole">&nbsp;<bean:message key="column.header.productRole"/></label></html:checkbox><br/>			
			<html:checkbox property="route" styleId="route"><label for="route">&nbsp;<bean:message key="column.header.routeOfAdministration"/></label></html:checkbox><br/>			
			<html:checkbox property="amount" styleId="amount"><label for="amount">&nbsp;<bean:message key="column.header.amount"/></label></html:checkbox><br/>
			<html:checkbox property="frequency" styleId="frequency"><label for="frequency">&nbsp;<bean:message key="column.header.frequency"/></label></html:checkbox><br/>
			<html:checkbox property="therapyDuration" styleId="therapyDuration"><label for="therapyDuration">&nbsp;<bean:message key="column.header.therapyDuration"/></label></html:checkbox><br/>
	 -->		
		</div>		
		<div class="column3">&nbsp;</div>	
	</div>
</fieldset>
	<div class="row-clear"><br/><br/></div>
	
	<div class="row alignCenter">
		<html:submit property="action" styleClass="subject">
			<bean:message key="button.searchResults" />
		</html:submit>
	</div>
</logic:present>

</html:form>