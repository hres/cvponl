
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
	
	<!-- FOOTER BEGINS | DEBUT DU PIED DE LA PAGE --> 
	<div class="footer">
		<div class="footerline"></div>		
		<div class="foot1">
		<!-- DATE MODIFIED BEGINS | DEBUT DE LA DATE DE MODIFICATION -->
		<bean:message bundle="clfRes" key="footer.lastUpdated"/> <span id="date"><bean:message bundle="clfRes" key="dcterms_modified"/></span>
		<!-- DATE MODIFIED ENDS | FIN DE LA DATE DE MODIFICATION -->
		</div>
		<div class="foot2"><a href="#tphp" title="<bean:message bundle="clfRes" key="alt.footer.top" />"><img class="uparrow" src="<bean:message bundle="clfRes" key="label.hc.url"/>/images/templates/tphp.gif" width="19" height="12" alt="<bean:message bundle="clfRes" key="alt.footer.up.arrow" />" /><br /><bean:message bundle="clfRes" key="footer.top"/></a></div>
		<div class="foot3"><a href="<bean:message bundle="clfRes" key='href.importantNotices'/>" >
		<bean:message bundle="clfRes" key="footer.importantNotices"/></a></div>
	</div>
	<!-- FOOTER ENDS | FIN DU PIED DE LA PAGE -->
