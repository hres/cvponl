<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html:xhtml/>

<logic:present name="maxExportLimit">
<div class="row text-red"><bean:message bundle="messageRes" key="warning.export.max"/></div><br/>
</logic:present>
<h2><bean:message key="title.search.criteria"/></h2>
<div><span class="infolabel"><strong><bean:message key="label.results.count1" />&nbsp;<bean:write name="cvp.pager" property="totalCount" />&nbsp;<bean:message key="label.results.count2" /></strong></span><br/></div>

<ul>
	<li><span class="strong"><bean:message key="searched.terms" /><bean:message key="format.colon"/></span>&nbsp;<bean:write name="SearchCriteria" property="section3Criteria"/></li>	
	<li><span class="strong"><bean:message key="label.initialDateReceived" /><bean:message key="format.colon"/></span>&nbsp;<bean:write name="SearchCriteria" property="initialDateFrom"/><logic:notEmpty name="SearchCriteria" property="initialDateFrom">&nbsp;<span class="strong"><bean:message key="label.to"/></span>&nbsp;</logic:notEmpty><bean:write name="SearchCriteria" property="initialDateTo"/></li>		
	<li><span class="strong"><bean:message key="label.latestDateReceived" /><bean:message key="format.colon"/></span>&nbsp;<bean:write name="SearchCriteria" property="followUpDateFrom"/><logic:notEmpty name="SearchCriteria" property="followUpDateFrom">&nbsp;<span class="strong"><bean:message key="label.to"/></span>&nbsp;</logic:notEmpty><bean:write name="SearchCriteria" property="followUpDateTo"/></li>		
	<li><span class="strong"><bean:message key="searched.reaction.terms" /><bean:message key="format.colon"/></span>&nbsp;<bean:write name="SearchCriteria" property="section4Criteria"/></li>
	<li><span class="strong"><bean:message key="label.seriousness" /></span>&nbsp;<bean:write name="SearchCriteria" property="seriousName"/></li>
	<li><span class="strong"><bean:message key="label.reportSource" /><bean:message key="format.colon"/></span>&nbsp;<bean:write name="SearchCriteria" property="reportSourceName"/></li>
	<li><span class="strong"><bean:message key="label.gender" /><bean:message key="format.colon"/></span>&nbsp;<bean:write name="SearchCriteria" property="genderName"/></li>
	<li><span class="strong"><bean:message key="label.outcome" /><bean:message key="format.colon"/></span>&nbsp;<bean:write name="SearchCriteria" property="outcomeName"/></li>
	<li><span class="strong"><bean:message key="label.age" /><bean:message key="format.colon"/></span>&nbsp;<bean:write name="SearchCriteria" property="ageCrit"/></li>									
	
</ul><!--
	<div class="indent1">
		<span class="strong"><bean:message key="searched.terms" /><bean:message key="format.colon"/></span>&nbsp;<bean:write name="SearchCriteria" property="section3Criteria"/><br/>
		<span class="strong"><bean:message key="label.initialDateReceived" /><bean:message key="format.colon"/></span>&nbsp;${sessionScope.SearchCriteria.initialDateFrom}<logic:notEmpty name="SearchCriteria" property="initialDateFrom">&nbsp;<span class="strong"><bean:message key="label.to"/></span>&nbsp;</logic:notEmpty>${sessionScope.SearchCriteria.initialDateTo}<br/>
		<span class="strong"><bean:message key="label.latestDateReceived" /><bean:message key="format.colon"/></span>&nbsp;${sessionScope.SearchCriteria.followUpDateFrom}<logic:notEmpty name="SearchCriteria" property="followUpDateFrom">&nbsp;<span class="strong"><bean:message key="label.to"/></span>&nbsp;</logic:notEmpty>${sessionScope.SearchCriteria.followUpDateTo}<br/>
		<span class="strong"><bean:message key="label.initialDateReceived" /><bean:message key="format.colon"/></span>&nbsp;<bean:write name="SearchCriteria" property="initialDateFrom"/><logic:notEmpty name="SearchCriteria" property="initialDateFrom">&nbsp;<span class="strong"><bean:message key="label.to"/></span>&nbsp;</logic:notEmpty><bean:write name="SearchCriteria" property="initialDateTo"/><br/>
		<span class="strong"><bean:message key="label.latestDateReceived" /><bean:message key="format.colon"/></span>&nbsp;<bean:write name="SearchCriteria" property="followUpDateFrom"/><logic:notEmpty name="SearchCriteria" property="followUpDateFrom">&nbsp;<span class="strong"><bean:message key="label.to"/></span>&nbsp;</logic:notEmpty><bean:write name="SearchCriteria" property="followUpDateTo"/><br/>
		<span class="strong"><bean:message key="searched.reaction.terms" /><bean:message key="format.colon"/></span>&nbsp;<bean:write name="SearchCriteria" property="section4Criteria"/><br/>
		<span class="strong"><bean:message key="label.seriousness" /></span>&nbsp;<bean:write name="SearchCriteria" property="seriousName"/><br/>
		<span class="strong"><bean:message key="label.reportFeature" /><bean:message key="format.colon"/></span>&nbsp;<bean:write name="SearchCriteria" property="reportFeatureName"/><br/>
		<span class="strong"><bean:message key="label.reportType" /><bean:message key="format.colon"/></span>&nbsp;<bean:write name="SearchCriteria" property="reportTypeName"/><br/>
		<span class="strong"><bean:message key="label.reportSource" /><bean:message key="format.colon"/></span>&nbsp;<bean:write name="SearchCriteria" property="reportSourceName"/><br/>
		<span class="strong"><bean:message key="label.gender" /><bean:message key="format.colon"/></span>&nbsp;<bean:write name="SearchCriteria" property="genderName"/><br/>
		<span class="strong"><bean:message key="label.outcome" /><bean:message key="format.colon"/></span>&nbsp;<bean:write name="SearchCriteria" property="outcomeName"/><br/>
		<span class="strong"><bean:message key="label.age" /><bean:message key="format.colon"/></span>&nbsp;<bean:write name="SearchCriteria" property="ageCrit"/><br/>				
	</div>

-->