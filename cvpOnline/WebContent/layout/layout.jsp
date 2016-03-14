<%----------------------------------------------------------------------------->
< This is the layout used to wrap Health Canada pages (all the pages of this
< application).
< Expected tiles attributes:
<   body        -contains the main content of the page.
<   breadCrumbs -(optional)displayed on the top of the page under the header.
<   browserCaching -(optional)whether the page contents should be cached by the
<                    browser. Default is "true".
<   footer      -displayed at the bottom of the page.
<   header      -displayed at the top of the page.
<   titleKey    -key to the resource String that will be displayed as the page
<                title in the browser window.
<-------------------------------------------------------------------------- --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html xmlns="http://www.w3.org/1999/xhtml" lang="<bean:message bundle='clfRes' key='label.app.lang'/>" xml:lang="<bean:message bundle='clfRes' key='label.app.lang'/>">

	<head>	
	<!-- CLF 2.0 TEMPLATE VERSION 1.04 | VERSION 1.04 DU GABARIT NSI 2.0 -->
		<link rel="schema.dc" href="http://purl.org/dc/elements/1.1/" />
		<link rel="schema.dcterms" href="http://purl.org/dc/terms/" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<meta name="dc.language" scheme="ISO639-2/T" content="<bean:message bundle="clfRes" key="meta_language" />" />
		<bean:define id="lang" >
			<bean:message bundle="clfRes" key="meta_lang" />
		</bean:define>
		<% 
	          java.util.HashMap params= new java.util.HashMap();
	          params.put("lang", lang);
	          request.setAttribute("paramsLang", params);
	    %> 
		
		<!-- HEADER BEGINS | DEBUT DE L'EN-TETE -->
		<!-- TITLE BEGINS | DEBUT DU TITRE -->
		
		<tiles:useAttribute name="formname" scope="session"/>
		<tiles:useAttribute name="titleKey"/>
		<title><bean:message name="titleKey"/></title>
		<!-- TITLE ENDS | FIN DU TITRE -->	
		<!-- METADATA BEGINS | DEBUT DES METADONNEES -->
	  	<tiles:insert attribute="metaData" />
	   	<!-- METADATA ENDS | FIN DES METADONNEES -->
	
		<!-- TEMPLATE SCRIPTS/CSS BEGIN | DEBUT DES SCRIPTS/CSS DU GABARIT -->
	    <logic:equal name="lang" value="eng"> 
	             <script src="<%=request.getContextPath()%>/js/sessionTimeout-eng.js" type="text/javascript"></script> 
	    </logic:equal> 
	    <logic:equal name="lang" value="fra"> 
	             <script src="<%=request.getContextPath()%>/js/sessionTimeout-fra.js" type="text/javascript"></script> 
	    </logic:equal>		
		<link href="<bean:message bundle="clfRes" key="label.hc.url"/>/css/base.css" media="screen, print" rel="stylesheet" type="text/css" />
		<link href="<bean:message bundle="clfRes" key="label.hc.url"/>/css/1col.css" media="screen, print" rel="stylesheet" type="text/css" />
		<style type="text/css" media="all">@import url(<bean:message bundle="clfRes" key="label.hc.url"/>/css/base2.css);</style>
		<script src="<bean:message bundle="clfRes" key="label.hc.url"/>/scripts/<bean:message bundle="clfRes" key="hc_scripts"/>" type="text/javascript"></script>
		<!-- TEMPLATE SCRIPTS/CSS END | FIN DES SCRIPTS/CSS DU GABARIT -->
		
		<!-- PROGRESSIVE ENHANCEMENT BEGINS | DEBUT DE L'AMELIORATION PROGRESSIVE -->
		<script src="<bean:message bundle="clfRes" key="label.hc.url"/>/scripts/pe-ap.js" type="text/javascript"></script>
		<script type="text/javascript">
		/* <![CDATA[ */
			var params = {
				lng:"eng",
				pngfix:"<bean:message bundle="clfRes" key="label.hc.url"/>/images/templates/inv.gif"
			};
			PE.progress(params);
		/* ]]> */
		</script>
		<!-- PROGRESSIVE ENHANCEMENT ENDS | FIN DE L'AMELIORATION PROGRESSIVE -->
		
		
		<!-- CUSTOM SCRIPTS/CSS BEGIN | DEBUT DES SCRIPTS/CSS PERSONNALISES -->
		<link href="<bean:message bundle="clfRes" key="label.hc.url"/>/css/base-institution.css" media="screen, print" rel="stylesheet" type="text/css" />
		<link href="<bean:message bundle="clfRes" key="label.hc.url"/>/css/institution.css" media="screen, print" rel="stylesheet" type="text/css" />
		<link href="<bean:message bundle="clfRes" key="label.hc.url"/>/css/dhp-mps.css" media="screen, print" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/theme/application.css" media="screen, print" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/theme/style.css" media="screen, print" rel="stylesheet" type="text/css" />	
		
		<style type="text/css">
			ul.topnav li {width:33%;} 
			ul.topnav li a { height:15px;} 
		</style>
		<!-- CUSTOM SCRIPTS/CSS END | FIN DES SCRIPTS/CSS PERSONNALISES -->
	
		<!-- TEMPLATE PRINT CSS BEGINS | DEBUT DU CSS DU GABARIT POUR L'IMPRESSION -->
		<link href="<bean:message bundle="clfRes" key="label.hc.url"/>/css/pf-if.css" rel="stylesheet" type="text/css" />
		<!-- TEMPLATE PRINT CSS ENDS | FIN DU CSS DU GABARIT POUR L'IMPRESSION -->
	   	
		<!-- TEMPLATE CALENDAR BEGIN | DEBUT DU CSS DU GABARIT POUR LE CALENDRIER -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/theme/calendar-win2k-1.css" type="text/css" />
		<!-- TEMPLATE CALENDAR ENDS | FIN DU CSS DU GABARIT POUR LE CALENDRIER -->   
	    <!-- HEADER ENDS | FIN DE L'EN-TETE -->	
	</head>


	<body onload="init(); window.setTimeout('ShowTimeoutWarning(60000)', 1680000)">

		<div class="page"><div class="core">
			
			<tiles:insert attribute="header" />
		
			<!-- ONE COLUMN LAYOUT STARTS | DEBUT DE LA MISE EN PAGE DE UNE COLONNE -->
			<div class="colLayout" id="temp">
				
			    <!-- CONTENT BEGINS | DEBUT DU CONTENU --> 
				<div class="center">
					<!--  <h1><bean:message bundle="clfRes" key="meta_title"/></h1>-->
					<a name="cont" id="cont"></a><a name="il" id="il"></a>					
					<h1><bean:message name="titleKey"/></h1>
					<tiles:insert attribute="criteria" />
					<!-- CONTENT BEGINS | DEBUT DU CONTENU -->					 
					<tiles:insert attribute="body" />
					<!-- CONTENT ENDS | FIN DU CONTENU -->
				</div>
			
			</div>
			<!-- ONE COLUMN LAYOUT ENDS | FIN DE LA MISE EN PAGE DE UNE COLONNE -->
		
			
			<tiles:insert attribute="footer" />
			
		</div></div>
	
	</body>
</html>

