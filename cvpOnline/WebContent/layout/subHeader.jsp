<%----------------------------------------------------------------------------->
< This is a partial page intended to be used as the sub-header of Health Canada web application.
<-------------------------------------------------------------------------- --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


	<!-- SUB-HEADER BEGINS | DEBUT DU SOUS EN-TETE --> 
	
	<ul class="topnav">
		<li>
			<a href="<bean:message key='link.sidebar.guide'/>" title="<bean:message key='label.sidebar.guide'/>">
				<bean:message key="label.sidebar.guide" />
			</a>
		</li>
		<li>
			<a href='<bean:message key="link.sidebar.terminology"/>' title='<bean:message key="label.sidebar.terminology"/>'>
				<bean:message key="label.sidebar.terminology" />
			</a>
		</li>
		<li>
			<a href='<bean:message key="link.sidebar.compendium"/>' title='<bean:message key="label.sidebar.compendium"/>'>
				<bean:message key="label.sidebar.compendium" />
			</a>
		</li>
			
	</ul>
	
  	<!-- SUB-HEADER ENDS | FIN DU SOUS EN-TETE--> 
	
	

  