<%----------------------------------------------------------------------------->
< This is a partial page intended to be used as the top of Health Canada pages.
<-------------------------------------------------------------------------- --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

	<!-- FIP HEADER BEGINS | DEBUT DE L'EN-TETE PCIM --> 
	<div class="fip"><a name="tphp" id="tphp"><img src="<bean:message bundle="clfRes" key="label.hc.url"/>/images/templates/<bean:message bundle="clfRes" key="img.header.canada"/>" width="372" height="20" alt="<bean:message bundle="clfRes" key="alt.header" />" title="<bean:message bundle="clfRes" key="alt.header" />"  /></a>
	</div>
	<div class="cwm"><img src="<bean:message bundle="clfRes" key="label.hc.url"/>/images/templates/wmms.gif" width="83" height="20" alt="<bean:message bundle="clfRes" key="alt.header.symbol" />" title="<bean:message bundle="clfRes" key="alt.header.symbol" />" />
	</div>
	<!-- FIP HEADER ENDS | FIN DE L'EN-TETE PCIM --> 
	<!-- INSTITUTIONAL BANNER STARTS | DEBUT DE LA BANNIERE INSTITUTIONNELLE -->
	<div class="banner">
		<img src="<bean:message bundle="clfRes" key="label.hc.url"/>/images/templates/lffl.gif" class="lf pngfix" height="65" width="65" alt="" /> 
		<p class="main"><bean:message bundle="clfRes" key="header.healthcanada"/> </p>
		<p class="siteuri"><bean:message bundle="clfRes" key="header.healthcanadawebsite"/></p>
	</div>
	<!-- INSTITUTIONAL BANNER ENDS | FIN DE LA BANNIERE INSTITUTIONNELLE -->
	
	<!-- SKIP NAVIGATION BEGINS | DEBUT DU SAUT DE NAVIGATION -->
	<div class="navaid">
		<a href="#cont" title="<bean:message bundle="clfRes" key="header.cont"/>"><bean:message bundle="clfRes" key="header.cont"/></a> |
		<a href="#il" title="<bean:message bundle="clfRes" key="header.il"/>"><bean:message bundle="clfRes" key="header.il"/></a>
	</div>
	<!-- SKIP NAVIGATION ENDS | FIN DU SAUT DE NAVIGATION -->
	
	<!-- COMMON MENU BAR BEGINS | DEBUT DE LA BARRE DE MENU COMMUNE --> 
	<div class="fp">
		<!-- COMMON MENU BAR TITLE BEGINS | DEBUT DU TITRE DE LA BARRE DE MENU COMMUNE -->
		<h1 class="navaid"><bean:message bundle="clfRes" key="header.navaid"/></h1>
		<!-- COMMON MENU BAR TITLE ENDS | FIN DU TITRE DE LA BARRE DE MENU COMMUNE -->
		<ul class="commonbar">
          <!-- FRENCH LINK BEGINS | DEBUT DU LIEN FRANCAIS -->
          <li class="fiptexta">
            <div>
           		<bean:define id="formName" name="formname" />
				<bean:define id="lang" >
				<bean:message bundle="clfRes" key="label.app.switch.lang" />
				</bean:define>
				<% 
		           java.util.HashMap params= new java.util.HashMap();
		           params.put("url", formName);
		           params.put("lang", lang);
		           pageContext.setAttribute("paramsName", params);
		           
		         %>  
           		<span lang="<bean:message bundle='clfRes' key='label.lang'/>" xml:lang="<bean:message bundle='clfRes' key='label.lang'/>">
	               	<html:link page="/language-langage.do" name="paramsName" scope="page" titleKey="alt.switch.language">
            			<bean:message bundle="clfRes" key="header.switchLanguage"/>
            		</html:link>
            	</span>
		</div>
          </li>
		  <!-- FRENCH LINK ENDS | FIN DU LIEN FRANCAIS -->
          <li class="fiptext">
            <div><a href="<bean:message bundle="clfRes" key="href.home"/>" title="<bean:message bundle="clfRes" key="alt.home" />">
              <bean:message bundle="clfRes" key="header.home"/>
            </a></div>
          </li>
		  <li class="fiptextc">
            <div><a href="<bean:message bundle="clfRes" key="href.contactUs"/>" title="<bean:message bundle="clfRes" key="alt.contact.us" />">
              <bean:message bundle="clfRes" key="header.contactUs"/>
            </a></div>
	      </li>
		  <li class="fiptext">
            <div><a href="<bean:message bundle="clfRes" key="href.help" />" title="<bean:message bundle="clfRes" key="alt.help" />">
              <bean:message bundle="clfRes" key="header.help"/>
            </a></div>
	      </li>
		  <li class="fiptext">
            <div><a href="<bean:message bundle="clfRes" key="href.search"/>" title="<bean:message bundle="clfRes" key="alt.search" />">
              <bean:message bundle="clfRes" key="header.search"/>
            </a></div>
	      </li>
		  <li class="fiptextb">
            <div><a href="<bean:message bundle="clfRes" key="href.canadaSite"/>" title="<bean:message bundle="clfRes" key="alt.canada.site" />">
            <bean:message bundle="clfRes" key="header.canadaSite"/>
            </a></div>
	      </li>
	  </ul>
	</div>
	<!-- COMMON MENU BAR ENDS | FIN DE LA BARRE DE MENU COMMUNE --> 
	<!-- HEADER ENDS | FIN DE L'EN-TETE --> 
	<!-- BREAD CRUMB BEGINS | DEBUT DE LA PISTE DE NAVIGATION --> 
	<!--START CRUMBS-->
    <p class="breadcrumb" ><a href="<bean:message bundle="clfRes" key="href.home"/>"  title="<bean:message bundle="clfRes" key="breadCrumbs.home"/>">
      <bean:message bundle="clfRes" key="breadCrumbs.home"/>
    </a> &gt; 
    <a  href="<bean:message bundle="clfRes" key="href.drugHealthProducts"/>" title="<bean:message bundle="clfRes" key="breadCrumbs.drugHealthProducts"/>">
      <bean:message bundle="clfRes" key="breadCrumbs.drugHealthProducts"/>
    </a> &gt; 
    <a href="<bean:message bundle="clfRes" key="href.naturalHealthProducts"/>" title="<bean:message bundle="clfRes" key="breadCrumbs.naturalHealthProducts"/>">
      <bean:message bundle="clfRes" key="breadCrumbs.naturalHealthProducts"/>
    </a> &gt; 
     <bean:define id="currentLang" >
		<bean:message bundle="clfRes" key="meta_lang" />
	</bean:define>
	<% 
	   java.util.HashMap params2= new java.util.HashMap();
	   params2.put("lang", currentLang);
	   pageContext.setAttribute("paramsName2", params2);
	   
	 %>
    <html:link page="/start-debuter.do" name="paramsName2" titleKey="title.search">
    	 <bean:message bundle="clfRes" key="breadCrumbs.application"/>
    </html:link>
     </p>
    <!--END CRUMBS-->
	
	<!-- BREAD CRUMB ENDS | FIN DE LA PISTE DE NAVIGATION --> 
	<div id="subjectHeader"><bean:message bundle="clfRes" key="subheader"/></div>
	
			  <div id="tools" class="widthFull">
			  	<img src="<bean:message bundle="clfRes" key="label.hc.url"/>/images/templates/print.gif" alt='<bean:message bundle="clfRes" key="alt.tool.print" />' width="16" height="11" /> <a href="javascript:window.print(); " title='<bean:message bundle="clfRes" key="alt.tool.print" />'><bean:message bundle="clfRes" key="tool.print" /></a> | <img src="<bean:message bundle="clfRes" key="label.hc.url"/>/images/templates/text-size.gif" alt='<bean:message bundle="clfRes" key="tool.change.text.size"/>' width="16" height="11" /> <span id="textsize"><a href='<bean:message bundle="clfRes" key="href.need.larger.text"/>'><bean:message bundle="clfRes" key="tool.need.larger.text"/></a></span>
			  
			  </div>
  