<%----------------------------------------------------------------------------->
< This is a partial page that inserts the appropriate meta tags into the page's
< head section.
< Expected request attributes:
<   ApplicationGlobals.META_SUBJECT -(optional)the String to be used in a second
<                                    dc.subject meta tag.
<   titleKey  -key to the resource String that will be included as the title
<              meta tag.
<-------------------------------------------------------------------------- --%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<!-- METADATA BEGINS | DEBUT DES METADONNEES -->
	<meta name="dc.title" content="<bean:message bundle="clfRes" key="meta_title" />" />
	<meta name="dc.description" content="<bean:message bundle="clfRes" key="meta_description" />" />
	<meta name="description" content="<bean:message bundle="clfRes" key="meta_description" />" />
	<meta name="keywords" content="<bean:message bundle="clfRes" key="meta_keywords" />" />
	<meta name="dc.subject" scheme="gchccv" content="<bean:message bundle="clfRes" key="meta_subject" />" />
	<meta name="dc.creator" content="<bean:message bundle="clfRes" key="meta_creator" />" />
	<meta name="dc.contributor" content="" />
	<meta name="dc.type" content="<bean:message bundle="clfRes" key="meta_type" />" />
	<meta name="owner" content="<bean:message bundle="clfRes" key="meta_owner" />" />
	<meta name="dcterms.audience" scheme="gcaudience" content="<bean:message bundle="clfRes" key="dcterms_audience1" />" />
	<meta name="dcterms.audience" scheme="gcaudience" content="<bean:message bundle="clfRes" key="dcterms_audience2" />" />		
	<meta name="dcterms.issued" scheme="W3CDTF" content="<bean:message bundle="clfRes" key="dcterms_issued" />" />
	<meta name="dcterms.modified" scheme="W3CDTF" content="<bean:message bundle="clfRes" key="dcterms_modified" />" />
	<meta name="review_date" content="<bean:message bundle="clfRes" key="meta_review_date" />" />
	<meta name="meta_date" content="<bean:message bundle="clfRes" key="meta_date" />" />
	<meta name="robots" content="noindex, nofollow" />	
	

<!-- METADATA ENDS | FIN DES METADONNEES -->
