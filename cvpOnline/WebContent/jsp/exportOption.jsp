<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html:xhtml/>

<bean:define id="myAction">
	<logic:equal name="LANG" value="fra">
		/exportOption.do?lang=eng
	</logic:equal>
	<logic:equal name="LANG" value="eng">
		/exportOption.do?lang=fra
	</logic:equal>	
</bean:define>
<bean:define id="lang" >
	<bean:message bundle="clfRes" key="meta_lang" />
</bean:define>
<div class="row alignRight"><a href="<bean:message key='link.help.export.options' />"><bean:message key="alt.help.section"/></a><br/></div>

<html:form action="<%=myAction%>" method="post" >

<div>
	<html:messages id="message" message="true" bundle="messageRes" header="messages.header" footer="messages.footer">
		<bean:message bundle="messageRes" key="messages.prefix" />
		<bean:write name="message" />
		<bean:message bundle="messageRes" key="messages.suffix" />
	</html:messages> <html:errors bundle="messageRes" />
</div>

<div class="highlight2"><bean:message key="alternate.format.disclaimer"/></div> 
<div><br/></div>

<div class="row-clear"></div>
<div class="row"><bean:message key="label.exportOption.mandatory"/></div>
<fieldset><legend><strong><bean:message key="label.exportOption.option" /></strong></legend>
		<div class="row">
			<div class="column20"></div>
			<div class="column75"><!-- PDF -->				
				<logic:greaterThan name="searchResultsSize" value="1000">
					<html:radio name="exportForm" property="foption" value="1" disabled="true" styleId="pdf"/>
					<label for="pdf"><bean:message key="label.export.title" /><bean:message key="label.exportOption.option.pdf" /></label>
				</logic:greaterThan>
				<logic:lessEqual name="searchResultsSize" value="1000"> 
					<html:radio name="exportForm" property="foption" value="1" styleId="pdf"/>
					<label for="pdf"><bean:message key="label.export.title" /><bean:message key="label.exportOption.option.pdf" /></label>
				</logic:lessEqual><br/>
				<!-- EXCEL -->			
				<logic:greaterThan name="searchResultsSize" value="1000">
					<html:radio name="exportForm" property="foption" value="2" disabled="true" styleId="excel"/>
					<label for="excel"><bean:message key="label.export.title" /><bean:message key="label.exportOption.option.excel" /></label>
				</logic:greaterThan>	
				<logic:lessEqual name="searchResultsSize" value="1000">
					<html:radio name="exportForm" property="foption" value="2" styleId="excel"/>
					<label for="excel"><bean:message key="label.export.title" /><bean:message key="label.exportOption.option.excel" /></label>
				</logic:lessEqual>			
			</div>
		</div>		
</fieldset>

<div class="row-clear"></div>
<div></div>
<div><br /></div>

<div class="row alignCenter">
	<html:submit property="action" styleClass="subject" >
		<bean:message key="button.exportOption" />
	</html:submit>
	
</div>

<div class="alignCenter">
	<br/><br/>
	<html:submit property="action" styleClass="subject" >
		<bean:message key="button.backResult" />
	</html:submit>
	<html:submit property="action" styleClass="subject" >
		<bean:message key="button.back" />
	</html:submit>	
	<html:submit property="action" styleClass="subject" >
		<bean:message key="button.newSearch" />
	</html:submit><br/>
</div>

</html:form>