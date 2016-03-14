<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:xhtml/>

<bean:define id="myAction">
	<logic:equal name="LANG" value="fra">
		/report-rapport?lang=eng
	</logic:equal>
	<logic:equal name="LANG" value="eng">
		/report-rapport?lang=fra
	</logic:equal>
</bean:define>
<html:form action="<%=myAction%>" method="post" >
<div>
	<html:messages id="message" message="true" bundle="messageRes" header="messages.header" footer="messages.footer">
		<bean:message bundle="messageRes" key="messages.prefix" />
		<bean:write name="message" />
		<bean:message bundle="messageRes" key="messages.suffix" />
	</html:messages> <html:errors bundle="messageRes" />
</div>
<div class="row"><bean:message key="label.reportInfo.aerDefinition"/><br/><br/></div>

<div class="row">
	<div class="column50 highlightwhite strong"><bean:message key="label.reportInfo.reportId" /><bean:message key="format.colon" /></div>
	<div class="column40fat highlightwhite"><bean:write name="report" property="reportNumber" /></div>
</div>
<div class="row">
	<div class="column50 highlight2 strong"><bean:message key="label.reportInfo.versionNumber" /><bean:message key="format.colon" /></div>
	<div class="column40fat highlight2"><bean:write name="report" property="versionNumber" /></div>
</div>
<div class="row">
	<div class="column50 highlightwhite strong"><bean:message key="label.reportInfo.ManufactId" /><bean:message key="format.colon" /></div>
	<div class="column40fat highlightwhite"><bean:write name="report" property="mahNumber" /></div>
</div>
<div class="row">
	<div class="column50 highlight2 strong"><bean:message key="label.initialDateReceived" /><bean:message key="format.colon" /></div>
	<div class="column40fat highlight2"><bean:write name="report" property="initialDateReceived" format="yyyy-MM-dd" /></div>
</div>
<div class="row">
	<div class="column50 highlightwhite strong"><bean:message key="label.latestDateReceived" /><bean:message key="format.colon" /></div>
	<div class="column40fat highlightwhite"><bean:write name="report" property="dateReceived" format="yyyy-MM-dd" /></div>
</div>
<div class="row">
	<div class="column50 highlight2 strong"><bean:message key="label.age" /><bean:message key="format.colon" /></div>
	<div class="column40fat highlight2">
		<bean:write name="report" property="age" />
		<bean:write name="report" property="ageUnitName" /></div>
</div>
<div class="row">
	<div class="column50 highlightwhite strong"><bean:message key="label.reportInfo.ageGroupCode" /><bean:message key="format.colon" /></div>
	<div class="column40fat highlightwhite"><bean:write name="report" property="ageGroupName" /></div>
</div>
<div class="row">
	<div class="column50 highlight2 strong"><bean:message key="label.reportType" /><bean:message key="format.colon" /></div>
	<div class="column40fat highlight2"><bean:write name="report" property="reportTypeName" /></div>
</div>
<div class="row">
	<div class="column50 highlightwhite strong"><bean:message key="label.reportInfo.reporterType" /><bean:message key="format.colon" /></div>
	<div class="column40fat highlightwhite"><bean:write name="report" property="reporterTypeName" /></div>
</div>
<div class="row">
	<div class="column50 highlight2 strong"><bean:message key="label.reportSource" /><bean:message key="format.colon" /></div>
	<div class="column40fat highlight2"><bean:write name="report" property="reportSourceName" /></div>
</div>
<div class="row">
	<div class="column50 highlight2 strong"><bean:message key="label.outcome" /><bean:message key="format.colon" /></div>
	<div class="column40fat highlight2"><bean:write name="report" property="outcomeName" /></div>
</div>
<div class="row">
	<div class="column50 highlightwhite strong"><bean:message key="label.gender" /><bean:message key="format.colon" /></div>
	<div class="column40fat highlightwhite"><bean:write name="report" property="genderName" /></div>
</div>
<div class="row">
	<div class="column50 highlight2 strong"><bean:message key="label.reportInfo.weight" /><bean:message key="format.colon" /></div>
	<div class="column40fat highlight2">
		<bean:write name="report" property="weight" />
		<bean:write name="report" property="weightUnitName" /></div>
</div>
<div class="row">
	<div class="column50 highlightwhite strong"><bean:message key="label.reportInfo.height" /><bean:message key="format.colon" /></div>
	<div class="column40fat highlightwhite">
		<bean:write name="report" property="height" />
		<bean:write name="report" property="heightUnitName" /></div><br/><br/>
</div>
<div class="row">
	<div class="column50 highlight2 strong"><bean:message key="label.seriousness" /></div>
	<div class="column40fat highlight2"><bean:write name="report" property="seriousnessName" /></div>
</div>

<div class="row-clear"></div>

<div><br /></div>
							
<logic:equal name="serious" value="1">
<div class="row">
	<div class="column3"></div>
	<div class="column87">
		<fieldset><legend><strong><bean:message key="label.seriousReason" /></strong></legend>
			<div class="row">
				<div class="column3"></div>
				<div class="column45 strong">
					<bean:message key="label.reportInfo.death" /><bean:message key="format.colon" />
				</div>
				<div class="column10">
					<logic:equal name="death" value="1" scope="session">
						<bean:message key="label.yes" />
					</logic:equal>
				</div>
			</div>
			<div class="row-clear"></div>			
			<div class="row">
				<div class="column3"></div>
				<div class="column45 strong">
					<bean:message key="label.reportInfo.hospitalization" /><bean:message key="format.colon" />
				</div>
				<div class="column10">
					<logic:equal name="hospital" value="1" scope="session">
						<bean:message key="label.yes" />
					</logic:equal>
				</div>
			</div>
			<div class="row-clear"></div>			
			<div class="row">
				<div class="column3"></div>
				<div class="column45 strong">
					<bean:message key="label.reportInfo.lifeThreat" /><bean:message key="format.colon" />
				</div>
				<div class="column10">
					<logic:equal name="lifeThreat" value="1" scope="session">
						<bean:message key="label.yes" />
					</logic:equal>
				</div>
			</div>
			<div class="row-clear"></div>			
			<div class="row">			
				<div class="column3"></div>			
				<div class="column45 strong">
					<bean:message key="label.reportInfo.congenitalAnomaly" /><bean:message key="format.colon" />
				</div>
				<div class="column10">
					<logic:equal name="congenital" value="1" scope="session">
						<bean:message key="label.yes" />
					</logic:equal>
				</div>
			</div>
			<div class="row-clear"></div>			
			<div class="row">
				<div class="column3"></div>
				<div class="column45 strong">
					<bean:message key="label.reportInfo.disability" /><bean:message key="format.colon" />
				</div>
				<div class="column10">
					<logic:equal name="disability" value="1" scope="session">
						<bean:message key="label.yes" />
					</logic:equal>
				</div>
			</div>
			<div class="row-clear"></div>			
			<div class="row">				
				<div class="column3"></div>			
				<div class="column45 strong">
					<bean:message key="label.reportInfo.otherMedicalImp" /><bean:message key="format.colon" />
				</div>
				<div class="column10">
					<logic:equal name="otherMedical" value="1" scope="session">
						<bean:message key="label.yes" />
					</logic:equal>
				</div>
			</div>		
		</fieldset>
	</div>
</div>
<div class="row-clear"></div>
</logic:equal>

<div><br /></div>
<div><br /></div>
<table class="subject">
<caption><bean:message key="label.reportInfo.prodInfo"/></caption>
    <tr>
		<th scope="col"><bean:message key="label.reportInfo.prodInfo.prodDesc" /></th>
		<th scope="col"><bean:message key="label.reportInfo.prodInfo.dosForm" /></th>
		<th scope="col"><bean:message key="label.reportInfo.prodInfo.prodRole" /></th>
		<th scope="col"><bean:message key="label.reportInfo.prodInfo.route" /></th>
		<th scope="col"><bean:message key="label.reportInfo.prodInfo.dose" /></th>
		<th scope="col"><bean:message key="label.reportInfo.prodInfo.frequency" /></th>
		<th scope="col"><bean:message key="label.reportInfo.prodInfo.therapyDuration" /></th>
		<th scope="col"><bean:message key="label.reportInfo.prodInfo.indication" /></th>
	</tr>
	<% int d = 1; %>
	<logic:iterate name="reportDrugs" id="repdrug">
		<% if (d % 2 == 1){ %>
		<tr class="row-odd">
		<% }else{ %>
		<tr class="row-even">
		<% } %>
			<td><bean:write name="repdrug" property="drugName" /></td>
			<td><bean:write name="repdrug" property="dosageFormName" /></td>
			<td><bean:write name="repdrug" property="drugInvDesc" /></td>
			<td><bean:write name="repdrug" property="routeAdminName" /></td>
			<td>
				<bean:write name="repdrug" property="unitDoseQty" />
				&nbsp;
				<bean:write name="repdrug" property="doseUnitName" />
			</td>
			<td>
				<bean:write name="repdrug" property="freqTimeString" />
			</td>
			<td>
				<bean:write name="repdrug" property="therapyDuration" />
				&nbsp;
				<bean:write name="repdrug" property="therapyDurationUnitName" />
			</td>
			<td><bean:write name="repdrug" property="indicationName" /></td>
		</tr>
		<% d++; %>
	</logic:iterate>
</table>

<logic:notEmpty name="reactions">
	<div><br /></div>
	<div><br /></div>
	<div><br /></div>
	
	<table class="subject">
	<caption><bean:message key="label.reportInfo.reactInfo"/></caption>	
		<tr>
			<td colspan="2" class="alignRight">
				<strong><bean:message key="meddra.version" /><bean:message key="format.colon" /></strong>&nbsp;<bean:write name="MEDDRA_VERSION" />			
			</td>
		</tr>		
		<tr>
			<th scope="col"><bean:message key="label.reportInfo.reactInfo.advReaction" /></th>
			<th scope="col"><bean:message key="label.reportInfo.reactInfo.duration" /></th>
		</tr>
		<% int r = 1; %>
		<logic:iterate name="reactions" id="reaction">
			<% if (r % 2 == 1){ %>
			<tr class="row-odd">
			<% }else{ %>
			<tr class="row-even">
			<% } %>
				<td><bean:write name="reaction" property="ptName" /></td>
				<td>
					<bean:write name="reaction" property="duration" />
					&nbsp;
					<bean:write name="reaction" property="durationUnit" />
				</td>
			</tr>
			<% r++; %>
		</logic:iterate>
	</table>
</logic:notEmpty>

<logic:empty name="reactions">
	<div class="row">
		<div class="column3"></div>
		<div class="column35 strong"><bean:message key="label.reportInfo.noReact" /></div>
	</div>
</logic:empty>

<div><br /></div>
<div><br /></div>

<div class="row alignCenter">
	<html:cancel property="action" styleClass="subject">
		<bean:message key="button.backResult" />
	</html:cancel>
	&nbsp;&nbsp;&nbsp;
	<html:submit property="action" styleClass="subject" >
		<bean:message key="button.newSearch" />
	</html:submit>
</div>

</html:form>