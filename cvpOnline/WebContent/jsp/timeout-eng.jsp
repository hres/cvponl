<!-- TIMEOUT BEGINS --> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">

<head>	
	<!-- CLF 2.0 TEMPLATE VERSION 1.04 | VERSION 1.04 DU GABARIT NSI 2.0 -->
	<!-- HEADER BEGINS | DEBUT DE L'EN-TETE -->
	<!-- ENGLISH / FRENCH TITLE BEGINS | DEBUT DU TITRE ANGLAIS / FRANCAIS -->
	<title>Session Timeout</title>
	<!-- ENGLISH / FRENCH TITLE ENDS | FIN DU TITRE ANGLAIS / FRANCAIS -->
	
	<!-- METADATA BEGINS | DEBUT DES METADONNEES -->
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<meta name="dc.language" scheme="ISO639-2/T" content="eng" />
	<meta name="robots" content="none" />
	<!-- METADATA ENDS | FIN DES METADONNEES -->

	<!-- TEMPLATE SCRIPTS/CSS BEGIN | DEBUT DES SCRIPTS/CSS DU GABARIT -->
	<link href="<bean:message bundle='clfRes' key='label.hc.url' />/css/base.css" media="screen, print" rel="stylesheet" type="text/css" />
	<link href="<bean:message bundle='clfRes' key='label.hc.url' />/css/1col.css" media="screen, print" rel="stylesheet" type="text/css" />
	<style type="text/css" media="all">@import url(<bean:message bundle='clfRes' key='label.hc.url' />/css/base2.css);</style>
	<script src="<bean:message bundle='clfRes' key='label.hc.url' />/scripts/<bean:message bundle="clfRes" key="hc_scripts"/>" type="text/javascript"></script>
	<!-- TEMPLATE SCRIPTS/CSS END | FIN DES SCRIPTS/CSS DU GABARIT -->
	
	<!-- PROGRESSIVE ENHANCEMENT BEGINS | DEBUT DE L'AMELIORATION PROGRESSIVE -->
	<script src="<bean:message bundle='clfRes' key='label.hc.url' />/scripts/pe-ap.js" type="text/javascript"></script>
	<script type="text/javascript">
	/* <![CDATA[ */
		var params = {
			lng:"eng",
			pngfix:"<bean:message bundle='clfRes' key='label.hc.url' />/images/templates/inv.gif"
		};
		PE.progress(params);
	/* ]]> */
	</script>
	<!-- PROGRESSIVE ENHANCEMENT ENDS | FIN DE L'AMELIORATION PROGRESSIVE -->
	
	
	<!-- CUSTOM SCRIPTS/CSS BEGIN | DEBUT DES SCRIPTS/CSS PERSONNALISES -->
	<link href="<bean:message bundle='clfRes' key='label.hc.url' />/css/base-institution.css" media="screen, print" rel="stylesheet" type="text/css" />
	<link href="<bean:message bundle='clfRes' key='label.hc.url' />/css/institution.css" media="screen, print" rel="stylesheet" type="text/css" />
	<link href="<bean:message bundle='clfRes' key='label.hc.url' />/css/dhp-mps.css" media="screen, print" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/theme/application.css" media="screen, print" rel="stylesheet" type="text/css" />
	<!-- TEMPLATE PRINT CSS BEGINS | DEBUT DU CSS DU GABARIT POUR L'IMPRESSION -->
	<link href="<bean:message bundle='clfRes' key='label.hc.url' />/css/pf-if.css" rel="stylesheet" type="text/css" />
	<!-- TEMPLATE PRINT CSS ENDS | FIN DU CSS DU GABARIT POUR L'IMPRESSION -->
	<%session.invalidate();%>
</head>

<body>
<div class="page"><div class="core">
	<!-- FIP HEADER BEGINS | DEBUT DE L'EN-TETE PCIM --> 
	<div class="fip"><a name="tphp" id="tphp"><img src="<bean:message bundle="clfRes" key="label.hc.url"/>/images/templates/sig-eng.gif" width="372" height="20" alt="Government of Canada | Gouvernement du Canada" /></a></div>
	<div class="cwm"><img src="<bean:message bundle="clfRes" key="label.hc.url"/>/images/templates/wmms.gif" width="83" height="20" alt="Symbol of the Government of Canada | Symbole du gouvernement du Canada" /></div>
	<!-- FIP HEADER ENDS | FIN DE L'EN-TETE PCIM --> 
	
	<!-- SKIP NAVIGATION BEGINS | DEBUT DU SAUT DE NAVIGATION -->
	<div class="navaid">
		<a href="#conte">Skip to English content</a>
	</div>
	<!-- SKIP NAVIGATION ENDS | FIN DU SAUT DE NAVIGATION -->
	<!-- HEADER ENDS | FIN DE L'EN-TETE -->
	<!-- ONE COLUMN LAYOUT STARTS | DEBUT DE LA MISE EN PAGE DE UNE COLONNE -->
	<div class="colLayout" id="temp">
	<!-- CONTENT BEGINS | DEBUT DU CONTENU --> 
	<div class="center"><br/>
			<h1><a name="conte" id="conte"></a>
		
			<!-- ENGLISH CONTENT TITLE BEGINS | DEBUT DU TITRE DU CONTENU ANGLAIS -->
			Session Timeout
			<!-- ENGLISH CONTENT TITLE ENDS | FIN DU TITRE DU CONTENU ANGLAIS -->
			</h1>
			<div><br/></div>
			<p>Your session has expired</p>
			<p>(29 minutes of inactivity).</p>
			<p>Please try again!</p>
			<p><br/>
				<html:link action="/start-debuter.do?lang=eng" >Return to application</html:link>
			</p>
			<div><br/></div>
		</div>
		
	</div>
	<!-- CONTENT ENDS | FIN DU CONTENU -->
	
	<!-- FOOTER BEGINS | DEBUT DU PIED DE LA PAGE --> 
	<div class="footer">
		<div class="footerline"></div>		
		<div class="foot1">
		<!-- DATE MODIFIED BEGINS | DEBUT DE LA DATE DE MODIFICATION -->
		Date Modified:&nbsp;<span class="date"><bean:message bundle="clfRes" key="dcterms_modified"/></span>
		<!-- DATE MODIFIED ENDS | FIN DE LA DATE DE MODIFICATION -->
		</div>
		<div class="foot2">&nbsp;</div>
		<div class="foot3">
		<a href="<bean:message bundle="clfRes" key="important.link"/>">Important Notices</a></div>		
	</div>
	<!-- FOOTER ENDS | FIN DU PIED DE LA PAGE -->
</div></div>
</body>

</html>

<!-- TIMEOUT END --> 