<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

<%@ page import="org.apache.struts.Globals" %>
<%
	pageContext.setAttribute(Globals.XHTML_KEY, "true",
	PageContext.PAGE_SCOPE);
%>
<bean:define id="myAction">
	<logic:equal name="LANG" value="fra">
		/result-resultat.do?lang=eng
	</logic:equal>
	<logic:equal name="LANG" value="eng">
		/result-resultat.do?lang=fra
	</logic:equal>	
</bean:define>

<bean:define id="lang" >
	<bean:message bundle="clfRes" key="meta_lang" />
</bean:define>
<bean:define id="pageNumber" name="cvp.pager" property="page" />
<% 
   java.util.HashMap params= new java.util.HashMap();
   params.put("page", pageNumber);
   params.put("lang", lang);
   pageContext.setAttribute("paramsName", params);
   
 %>

<h2><bean:message key="title.search.result.section"/></h2>
<div class="row alignRight"><a href="<bean:message key='link.help.search.results' />"><bean:message key="alt.help.searchResults"/></a><br/><br/></div>

<html:form action="<%=myAction%>" method="post">
<div>
	<html:messages id="message" message="true" bundle="messageRes" header="messages.header" footer="messages.footer">
		<bean:message bundle="messageRes" key="messages.prefix" />
		<bean:write name="message" />
		<bean:message bundle="messageRes" key="messages.suffix" />
	</html:messages> <html:errors bundle="messageRes" />
</div>

	<div class="row alignCenter">
		<html:cancel styleClass="subject" property="action" altKey="button.exportOption"><bean:message key="button.exportOption"/></html:cancel>
		&nbsp;&nbsp;&nbsp;
		<html:submit styleClass="subject" property="action" altKey="button.back"><bean:message key="button.back"/></html:submit>		 
		&nbsp;&nbsp;&nbsp;
		<html:submit styleClass="subject" property="action" altKey="button.newSearch"><bean:message key="button.newSearch"/></html:submit>		 
	</div>

	<bean:define type="java.lang.String" id="myOffset" name="cvp.pager" property="offset" scope="session" toScope="page" />
	<bean:define type="java.lang.String" id="myPageSize" name="cvp.pager" property="pageSize" scope="session" toScope="page" />
	<div><br /></div>
	<logic:greaterThan name="cvp.pager" property="totalCount" scope="session" value="0">
		<div class="row"><bean:message key="link.caveat"/><br/><br/></div>
		<div class="row">
			<div>
			<!-- <span class="infolabel"><strong><bean:write name="cvp.pager" property="totalCount" /></strong>&nbsp;<bean:message key="label.results.count1" /></span><br/> -->
				<strong><bean:message key="label.results.current" /></strong>
				<span class="infolabel"><strong><bean:write name="cvp.pager" property="startIndex" /><bean:message key="label.results.to"/><bean:write name="cvp.pager" property="endIndex" /></strong></span>
				<strong><bean:message key="label.results.of"/>&nbsp;<bean:write name="cvp.pager" property="totalCount" />&nbsp;<bean:message key="label.results.text"/></strong>
			</div>
			<br/>
		</div>
		<!-- <div class="row"><strong><bean:message key="label.results.click"/></strong></div> -->

<div class="indent1">
		
	<logic:greaterThan name="cvp.pager" property="passedPages" value="0">
		<html:link action="previous-precedent" name="paramsName" >
			<bean:message key="label.results.previous" />
		</html:link>
		<logic:greaterThan name="cvp.pager" property="pagesLeft" value="0">
			&nbsp;|&nbsp; 
		</logic:greaterThan> 		
	</logic:greaterThan>	

	<logic:greaterThan name="cvp.pager" property="pagesLeft" value="0">
		<html:link action="next-suivant" name="paramsName" >
			<bean:message key="label.results.next" />
		</html:link><br/>
	</logic:greaterThan> 
	
</div>
	<div><br/></div>

	<table class="subject" cellpadding="0" cellspacing="0" width="100%">
	<caption><bean:message key="table.caption.searchresults"/></caption>
		<tr>
			<td colspan="8" class="alignRight">
			<logic:present name="MEDDRA_VERSION">
				<strong>
					<bean:message key="meddra.version" /><bean:message key="format.colon" />
				</strong>
				&nbsp;<bean:write name="MEDDRA_VERSION" />			
			</logic:present>
			</td>
		</tr>	
		<tr class="subjectcolor">
			<th id="reportNumber" scope="col" class="alignLeft width15">
				<a href="search-recherche.do?lang=<%=lang%>&amp;colID=<bean:message key='id.reportNumber'/>"><span class="text-white-underline"><bean:message key="label.reportInfo.reportId" /></span></a>				
			</th>
			<logic:present name="displayElements" property="displayVersionNo">					
				<th id="versionNumber" scope="col" class="alignLeft width20">
					<a href="search-recherche.do?lang=<%=lang%>&amp;colID=<bean:message key='id.latestVersion'/>"><span class="text-white-underline"><bean:message key="label.reportInfo.versionNumber" /></span></a>
				</th>
			</logic:present>
			<logic:present name="displayElements" property="displayMahNumber">					
				<th id="mahNumber" scope="col" class="alignLeft width15">
					<a href="search-recherche.do?lang=<%=lang%>&amp;colID=<bean:message key='id.mahNumber'/>"><span class="text-white-underline"><bean:message key="label.reportInfo.ManufactId" /></span></a>
				</th>
			</logic:present>							
			<logic:present name="displayElements" property="displayInitialDate">					
				<th id="initialDate" scope="col" class="alignLeft width20">
					<a href="search-recherche.do?lang=<%=lang%>&amp;colID=<bean:message key='id.initialDate'/>"><span class="text-white-underline"><bean:message key="label.initialDateReceived" /></span></a>
				</th>
			</logic:present>			
			<logic:present name="displayElements" property="displayLatestDate">								
				<th id="latestDate" scope="col" class="alignLeft width20">
					<a href="search-recherche.do?lang=<%=lang%>&amp;colID=<bean:message key='id.latestDate'/>"><span class="text-white-underline"><bean:message key="label.latestDateReceived" /></span></a>
				</th>
			</logic:present>
<!--			<logic:present name="displayElements" property="displayFeatureOfReport">					-->
<!--				<th id="featureOfReport" scope="col" class="alignLeft width20">-->
<!--					<a href="search-recherche.do?lang=<%=lang%>&colID=<bean:message key='id.featureOfReport'/>"><span class="text-white-underline"><bean:message key="label.reportFeature" /></span></a>-->
<!--				</th>-->
<!--			</logic:present>	-->
<!--			<logic:present name="displayElements" property="displayTypeOfReport">					-->
<!--				<th id="typeOfReport" scope="col" class="alignLeft width20">-->
<!--					<a href="search-recherche.do?lang=<%=lang%>&colID=<bean:message key='id.typeOfReport'/>"><span class="text-white-underline"><bean:message key="label.reportType" /></span></a>-->
<!--				</th>-->
<!--			</logic:present>-->
			<logic:present name="displayElements" property="displaySerious">					
				<th id="serious" scope="col" class="alignLeft width15">
					<a href="search-recherche.do?lang=<%=lang%>&amp;colID=<bean:message key='id.serious'/>"><span class="text-white-underline"><bean:message key="label.seriousness" /></span></a>
				</th>
			</logic:present>
			<logic:present name="displayElements" property="displayAge">											
				<th id="age" scope="col" class="alignLeft width5">
					<a href="search-recherche.do?lang=<%=lang%>&amp;colID=<bean:message key='id.age'/>"><span class="text-white-underline"><bean:message key="label.age" /></span></a>
				</th>
			</logic:present>
			<logic:present name="displayElements" property="displayGender">														
				<th id="gender" scope="col" class="alignLeft width15">
					<a href="search-recherche.do?lang=<%=lang%>&amp;colID=<bean:message key='id.gender'/>"><span class="text-white-underline"><bean:message key="label.gender" /></span></a>
				</th>
			</logic:present>
			<logic:present name="displayElements" property="displayWeight">														
				<th id="weight" scope="col" class="alignLeft width15">
					<a href="search-recherche.do?lang=<%=lang%>&amp;colID=<bean:message key='id.weight'/>"><span class="text-white-underline"><bean:message key="label.reportInfo.weight" /></span></a>
				</th>
			</logic:present>
			<logic:present name="displayElements" property="displayHeight">														
				<th id="height" scope="col" class="alignLeft width15">
					<a href="search-recherche.do?lang=<%=lang%>&amp;colID=<bean:message key='id.height'/>"><span class="text-white-underline"><bean:message key="label.reportInfo.height" /></span></a>
				</th>
			</logic:present>
			<logic:present name="displayElements" property="displayReportOutcome">																	
				<th id="reportOutcome" scope="col" class="alignLeft width20">
					<a href="search-recherche.do?lang=<%=lang%>&amp;colID=<bean:message key='id.reportOutcome'/>"><span class="text-white-underline"><bean:message key="label.outcome" /></span></a>
				</th>
			</logic:present>
			<logic:present name="displayElements" property="displayReporterType">																	
				<th id="reporterType" scope="col" class="alignLeft width20">
					<a href="search-recherche.do?lang=<%=lang%>&amp;colID=<bean:message key='id.reporterType'/>"><span class="text-white-underline"><bean:message key="label.reportInfo.reporterType" /></span></a>
				</th>
			</logic:present>
			<logic:present name="displayElements" property="displaySourceOfReport">																	
				<th id="sourceOfReport" scope="col" class="alignLeft width15">
					<a href="search-recherche.do?lang=<%=lang%>&amp;colID=<bean:message key='id.sourceOfReport'/>"><span class="text-white-underline"><bean:message key="label.reportSource" /></span></a>
				</th>
			</logic:present>
			<logic:present name="displayElements" property="displayDeath">																	
				<th id="death" scope="col" class="alignLeft width15">
					<a href="search-recherche.do?lang=<%=lang%>&amp;colID=<bean:message key='id.death'/>"><span class="text-white-underline"><bean:message key="label.reportInfo.death" /></span></a>
				</th>
			</logic:present>
			<logic:present name="displayElements" property="displayDisability">																	
				<th id="disability" scope="col" class="alignLeft width20">
					<a href="search-recherche.do?lang=<%=lang%>&amp;colID=<bean:message key='id.disability'/>"><span class="text-white-underline"><bean:message key="label.reportInfo.disability" /></span></a>
				</th>
			</logic:present>
			<logic:present name="displayElements" property="displayCongenitalAnomaly">																	
				<th id="congenitalAnomaly" scope="col" class="alignLeft width20">
					<a href="search-recherche.do?lang=<%=lang%>&amp;colID=<bean:message key='id.congenitalAnomaly'/>"><span class="text-white-underline"><bean:message key="label.reportInfo.congenitalAnomaly" /></span></a>
				</th>
			</logic:present>
			<logic:present name="displayElements" property="displayLifeThreat">																	
				<th id="lifeThreat" scope="col" class="alignLeft width20">
					<a href="search-recherche.do?lang=<%=lang%>&amp;colID=<bean:message key='id.hospitalization'/>"><span class="text-white-underline"><bean:message key="label.reportInfo.lifeThreat" /></span></a>
				</th>
			</logic:present>
			<logic:present name="displayElements" property="displayHospitalization">																	
				<th id="hospitalization" scope="col" class="alignLeft width20">
					<a href="search-recherche.do?lang=<%=lang%>&amp;colID=<bean:message key='id.lifeThreatening'/>"><span class="text-white-underline"><bean:message key="label.reportInfo.hospitalization" /></span></a>
				</th>
			</logic:present>
			<logic:present name="displayElements" property="displayMedicalCondition">																	
				<th id="medicalCondition" scope="col" class="alignLeft width20">
					<a href="search-recherche.do?lang=<%=lang%>&amp;colID=<bean:message key='id.otherMedicallyCondition'/>"><span class="text-white-underline"><bean:message key="label.reportInfo.otherMedicalImp" /></span></a>
				</th>
			</logic:present>																				
			<logic:present name="displayElements" property="displayDrugName">																				
				<th id="brandName" scope="col" class="alignLeft width20">
					<strong><bean:message key="column.header.drugName" /></strong>
				</th>
			</logic:present>
			<logic:present name="displayElements" property="displayDosageForm">																				
				<th id="dosageForm" scope="col" class="alignLeft width20">
					<a href="search-recherche.do?lang=<%=lang%>&amp;colID=<bean:message key='id.dosageForm'/>"><span class="text-white-underline"><bean:message key="label.reportInfo.prodInfo.dosForm" /></span></a>				
				</th>
			</logic:present>
			<logic:present name="displayElements" property="displayProductRole">																				
				<th id="dosageForm" scope="col" class="alignLeft width20">
					<a href="search-recherche.do?lang=<%=lang%>&amp;colID=<bean:message key='id.productRole'/>"><span class="text-white-underline"><bean:message key="column.header.productRole" /></span></a>				
				</th>
			</logic:present>			
			<logic:present name="displayElements" property="displayRoute">																				
				<th id="route" scope="col" class="alignLeft width20">
					<a href="search-recherche.do?lang=<%=lang%>&amp;colID=<bean:message key='id.routeOfAdministration'/>"><span class="text-white-underline"><bean:message key="column.header.routeOfAdministration" /></span></a>								
				</th>
			</logic:present>
			<logic:present name="displayElements" property="displayAmount">																				
				<th id="amount" scope="col" class="alignLeft width20">
					<a href="search-recherche.do?lang=<%=lang%>&amp;colID=<bean:message key='id.amount'/>"><span class="text-white-underline"><bean:message key="column.header.amount" /></span></a>												
				</th>
			</logic:present>
			<logic:present name="displayElements" property="displayFrequency">																				
				<th id="frequency" scope="col" class="alignLeft width20">
					<a href="search-recherche.do?lang=<%=lang%>&amp;colID=<bean:message key='id.frequency'/>"><span class="text-white-underline"><bean:message key="column.header.frequency" /></span></a>				
				</th>
			</logic:present>
			<logic:present name="displayElements" property="displayTherapyDuration">																				
				<th id="therapyDuration" scope="col" class="alignLeft width20">
					<a href="search-recherche.do?lang=<%=lang%>&amp;colID=<bean:message key='id.therapyDuration'/>"><span class="text-white-underline"><bean:message key="column.header.therapyDuration" /></span></a>								
				</th>
			</logic:present>		
			<logic:present name="displayElements" property="displayMeddraPTName">			
				<th id="pTName" scope="col" class="alignLeft width20">
					<strong><bean:message key="column.header.meddraPT" /></strong>
				</th>																
			</logic:present>		
			<logic:present name="displayElements" property="displayMeddraSOCname">			
				<th id="sOCName" scope="col" class="alignLeft width20">
					<strong><bean:message key="column.header.meddraSOC" /></strong>
				</th>																
			</logic:present>		
			<logic:present name="displayElements" property="displayReactionDuration">			
				<th id="reactionDuration" scope="col" class="alignLeft width20">
					<a href="search-recherche.do?lang=<%=lang%>&amp;colID=<bean:message key='id.reactionDuration'/>"><span class="text-white-underline"><bean:message key="column.header.reactionDuration" /></span></a>				
				</th>																
			</logic:present>		
		</tr>
		<%int count = 0;%>
		<logic:iterate id="element" name="cvponline.search.results" offset='<%=myOffset%>' length='<%=myPageSize%>' >
		<% int r = 1; %>
			<% if (r % 2 == 1){ %>
			<tr class='<%=(count++ % 2 == 0 ? "" : "row-odd")%>'>			
			<% }else{ %>
			<tr class='<%=(count++ % 2 == 0 ? "" : "row-even")%>'>			
			<% } %>					
				<td>
					<bean:define id="reportNumber" name="element" property="reportNumber" />
					<% 
					   java.util.HashMap params2= new java.util.HashMap();
					   params2.put("id", reportNumber);
					   params2.put("lang", lang);
					   pageContext.setAttribute("paramsName2", params2);					   
					 %>
					<html:link action="report-rapport" name="paramsName2" titleKey="label.reportInfo.reportId" >
						<bean:write name="element" property="reportNumber" />
					</html:link>
				</td>
				<logic:present name="displayElements" property="displayVersionNo">									
					<td><logic:present name="element" property="versionNumber">
						<bean:write name="element" property="versionNumber" />
						<br/>
					</logic:present></td>
				</logic:present>
				<logic:present name="displayElements" property="displayMahNumber">					
					<td><logic:present name="element" property="mahNumber">
						<bean:write name="element" property="mahNumber" />
						<br/>
					</logic:present></td>
				</logic:present>
				<logic:present name="displayElements" property="displayInitialDate">					
					<td><logic:present name="element" property="initialDateReceived">
						<bean:write name="element" property="initialDateReceived" format="yyyy-MM-dd"/>
						<br/>
					</logic:present></td>
				</logic:present>								
				<logic:present name="displayElements" property="displayLatestDate">					
					<td><logic:present name="element" property="dateReceived">
						<bean:write name="element" property="dateReceived" format="yyyy-MM-dd"/>
						<br/>
					</logic:present></td>
				</logic:present>
<!--				<logic:present name="displayElements" property="displayFeatureOfReport">					-->
<!--					<td><logic:present name="element" property="reportFeatureName">-->
<!--						<bean:write name="element" property="reportFeatureName" />-->
<!--						<br/>-->
<!--					</logic:present></td>-->
<!--				</logic:present>-->
<!--				<logic:present name="displayElements" property="displayTypeOfReport">					-->
<!--					<td><logic:present name="element" property="reportTypeName">-->
<!--						<bean:write name="element" property="reportTypeName" />-->
<!--						<br/>-->
<!--					</logic:present></td>-->
<!--				</logic:present>-->
				<logic:present name="displayElements" property="displaySerious">					
					<td><logic:present name="element" property="seriousnessName">
						<bean:write name="element" property="seriousnessName" />
						<br/>
					</logic:present></td>
				</logic:present>
				<logic:present name="displayElements" property="displayAge">				
					<td><logic:present name="element" property="ageYears">
						<bean:write name="element" property="age" />&nbsp;<bean:write name="element" property="ageUnitName" />
						<br/>
					</logic:present></td>
				</logic:present>
				<logic:present name="displayElements" property="displayGender">				
					<td><logic:present name="element" property="genderName">
						<bean:write name="element" property="genderName" />
						<br/>
					</logic:present></td>
				</logic:present>
				<logic:present name="displayElements" property="displayWeight">				
					<td><logic:present name="element" property="weight">
						<bean:write name="element" property="weight" />&nbsp;<bean:write name="element" property="weightUnitName" />
						<br/>
					</logic:present></td>
				</logic:present>
				<logic:present name="displayElements" property="displayHeight">				
					<td><logic:present name="element" property="height">
						<bean:write name="element" property="height" />&nbsp;<bean:write name="element" property="heightUnitName" />
						<br/>
					</logic:present></td>
				</logic:present>								
				<logic:present name="displayElements" property="displayReportOutcome">				
					<td><logic:present name="element" property="outcomeName">
						<bean:write name="element" property="outcomeName" />
						<br/>
					</logic:present></td>				
				</logic:present>	
				<logic:present name="displayElements" property="displayReporterType">				
					<td><logic:present name="element" property="reporterTypeName">
						<bean:write name="element" property="reporterTypeName" />
						<br/>
					</logic:present></td>				
				</logic:present>
				<logic:present name="displayElements" property="displaySourceOfReport">				
					<td><logic:present name="element" property="reportSourceName">
						<bean:write name="element" property="reportSourceName" />
						<br/>
					</logic:present></td>				
				</logic:present>
				<logic:present name="displayElements" property="displayDeath">				
					<td><logic:equal name="element" property="death" value="1">
							<bean:message key="label.yes" /><br/>
						</logic:equal>					
					</td>
				</logic:present>
				<logic:present name="displayElements" property="displayDisability">				
					<td><logic:equal name="element" property="disability" value="1">
							<bean:message key="label.yes" /><br/>
						</logic:equal>	
					</td>				
				</logic:present>
				<logic:present name="displayElements" property="displayCongenitalAnomaly">				
					<td><logic:equal name="element" property="congenitalAnomaly" value="1">
							<bean:message key="label.yes" /><br/>
						</logic:equal>	
					</td>				
				</logic:present>
				<logic:present name="displayElements" property="displayLifeThreat">				
					<td><logic:equal name="element" property="lifeThreatening" value="1">
							<bean:message key="label.yes" /><br/>
						</logic:equal>	
					</td>				
				</logic:present>
				<logic:present name="displayElements" property="displayHospitalization">				
					<td><logic:equal name="element" property="hospitalRequired" value="1">
							<bean:message key="label.yes" /><br/>
						</logic:equal>	
					</td>				
				</logic:present>
				<logic:present name="displayElements" property="displayMedicalCondition">				
					<td><logic:equal name="element" property="otherMedicalCondition" value="1">
							<bean:message key="label.yes" /><br/>
						</logic:equal>
					</td>				
				</logic:present>																																
				<logic:present name="displayElements" property="displayDrugName">					
					<td><logic:present name="element" property="drugName">
						<bean:write name="element" property="drugName" />
						<br/>
					</logic:present></td>
				</logic:present>
<!-- 			
				<logic:present name="displayElements" property="displayDosageForm">					
					<td><logic:present name="element" property="dosageForm">
						<bean:write name="element" property="dosageForm" />
						<br/>
					</logic:present></td>
				</logic:present>
				<logic:present name="displayElements" property="displayProductRole">					
					<td><logic:present name="element" property="productRole">
						<bean:write name="element" property="productRole" />
						<br/>
					</logic:present></td>
				</logic:present>				
				<logic:present name="displayElements" property="displayRoute">					
					<td><logic:present name="element" property="routeOfAdministration">
						<bean:write name="element" property="routeOfAdministration" />
						<br/>
					</logic:present></td>
				</logic:present>
				<logic:present name="displayElements" property="displayAmount">					
					<td><logic:present name="element" property="amount">
						<bean:write name="element" property="amount" />&nbsp;<bean:write name="element" property="doseUnit" />
						<br/>
					</logic:present></td>
				</logic:present>
				<logic:present name="displayElements" property="displayFrequency">					
					<td><logic:present name="element" property="frequency">
						<bean:write name="element" property="frequency" />&nbsp;<bean:write name="element" property="freqTimeUnit" />
						<br/>
					</logic:present></td>
				</logic:present>
				<logic:present name="displayElements" property="displayTherapyDuration">					
					<td><logic:present name="element" property="therapyDuration">
						<bean:write name="element" property="therapyDuration" />&nbsp;<bean:write name="element" property="therapyDurationUnit" />
						<br/>
					</logic:present></td>
				</logic:present>
 -->
				<logic:present name="displayElements" property="displayMeddraPTName">				
					<td><logic:present name="element" property="ptName">
						<bean:write name="element" property="ptName" />
						<br/>
					</logic:present></td>																				
				</logic:present>
				
				<logic:present name="displayElements" property="displayMeddraSOCname">				
					<td><logic:present name="element" property="socName">
						<bean:write name="element" property="socName" />
						<br/>
					</logic:present></td>																				
				</logic:present>
				<logic:present name="displayElements" property="displayReactionDuration">				
					<td><logic:present name="element" property="duration">
						<bean:write name="element" property="duration" />&nbsp;<bean:write name="element" property="durationUnit" />
						<br/>
					</logic:present></td>																				
				</logic:present>
			</tr>
			<% r++; %>
		</logic:iterate>
	</table>
	

	<div class="indent1"><br/>
		<logic:greaterThan name="cvp.pager" property="passedPages" value="0">
			<html:link action="previous-precedent" name="paramsName" >
				<bean:message key="label.results.previous" />
			</html:link>
			<logic:greaterThan name="cvp.pager" property="pagesLeft" value="0">
				&nbsp;|&nbsp; 
			</logic:greaterThan> 			
		</logic:greaterThan> 
		<logic:greaterThan name="cvp.pager" property="pagesLeft" value="0">
			<html:link action="next-suivant" name="paramsName" >
				<bean:message key="label.results.next" />
			</html:link>
		</logic:greaterThan> 
	</div>

	</logic:greaterThan>
	<div><br/></div>

<div class="row alignCenter">
	<html:cancel styleClass="subject" property="action" altKey="button.exportOption"><bean:message key="button.exportOption"/></html:cancel>
	&nbsp;&nbsp;&nbsp;
	<html:submit styleClass="subject" property="action" altKey="button.back"><bean:message key="button.back"/></html:submit>		 
	&nbsp;&nbsp;&nbsp;
	<html:submit styleClass="subject" property="action" altKey="button.newSearch"><bean:message key="button.newSearch"/></html:submit>		 
</div>
</html:form>