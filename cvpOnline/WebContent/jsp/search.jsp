<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglibs-session.tld" prefix="sess" %>
<html:xhtml/>

<!--<script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar.js"></script><script type="text/javascript" src="<%=request.getContextPath()%><bean:message key="calendar.javascript" />"></script>
<script type="text/javascript" src="<%=request.getContextPath()%><bean:message key="calendar.javascript" />"></script>
<script type="text/javascript" src="<%=request.getContextPath()%><bean:message key="layout.javascript" />"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar-setup.js"></script>

--><bean:define id="myAction">
	<logic:equal name="LANG" value="fra">
		/search-recherche?lang=eng
	</logic:equal>
	<logic:equal name="LANG" value="eng">
		/search-recherche?lang=fra
	</logic:equal>	
</bean:define>

<bean:define id="quarter">
	<sess:attribute name="quarter"/>
</bean:define>
<html:form action="<%=myAction%>" method="post" >
<div>
    <!--Display error messages--> 
    <logic:present name="org.apache.struts.action.ACTION_MESSAGE" scope="request"> 
        <span class="text-red"><bean:message bundle="messageRes" key="errors.title" /><br /></span> 
		<bean:message bundle="messageRes" key="messages.header" />	
		
		<logic:messagesPresent property="noMatchingRecords" message="true">
		    	<bean:message bundle="messageRes" key="messages.prefix" /><bean:message bundle="messageRes" key="error.search.results" /><bean:message bundle="messageRes" key="messages.suffix" /> 
		</logic:messagesPresent>					
		<logic:messagesPresent property="nothingSelected" message="true">
		    	<bean:message bundle="messageRes" key="messages.prefix" /><bean:message bundle="messageRes" key="error.nothing.selected" /><bean:message bundle="messageRes" key="messages.suffix" /> 
		</logic:messagesPresent>
		<logic:messagesPresent property="selectAllError" message="true">
		    	<bean:message bundle="messageRes" key="messages.prefix" /><bean:message bundle="messageRes" key="error.go.notSelected" /><bean:message bundle="messageRes" key="messages.suffix" /> 
		</logic:messagesPresent>					
		
		<logic:messagesPresent property="dateMissingError" message="true">
		    	<bean:message bundle="messageRes" key="messages.prefix" /><bean:message bundle="messageRes" key="error.link.date.empty" /><bean:message bundle="messageRes" key="messages.suffix" /> 
		</logic:messagesPresent>					
		<logic:messagesPresent property="dateFormatError" message="true">
		    	<bean:message bundle="messageRes" key="messages.prefix" /><bean:message bundle="messageRes" key="error.link.date.format" /><bean:message bundle="messageRes" key="messages.suffix" /> 
		</logic:messagesPresent>
		<logic:messagesPresent property="dateRangeError" message="true">
		    	<bean:message bundle="messageRes" key="messages.prefix" /><bean:message bundle="messageRes" key="error.link.date.range" /><bean:message bundle="messageRes" key="messages.suffix" /> 
		</logic:messagesPresent>				
		<logic:messagesPresent property="dateMaxRangeError" message="true">
		    	<bean:message bundle="messageRes" key="messages.prefix" /><bean:message bundle="messageRes" key="error.link.toDate.maxRange" /><bean:message bundle="messageRes" key="messages.suffix" /> 
		</logic:messagesPresent>
		<logic:messagesPresent property="ageRangeError" message="true">
		    	<bean:message bundle="messageRes" key="messages.prefix" /><bean:message bundle="messageRes" key="error.link.age.range.invalid" /><bean:message bundle="messageRes" key="messages.suffix" /> 
		</logic:messagesPresent>		
		<logic:messagesPresent property="productKeywordsError" message="true">
		    	<bean:message bundle="messageRes" key="messages.prefix" /><bean:message bundle="messageRes" key="error.link.products.keywords" /><bean:message bundle="messageRes" key="messages.suffix" /> 
		</logic:messagesPresent>
		<logic:messagesPresent property="productSelectError" message="true">
		    	<bean:message bundle="messageRes" key="messages.prefix" /><bean:message bundle="messageRes" key="error.link.products.select" /><bean:message bundle="messageRes" key="messages.suffix" /> 
		</logic:messagesPresent>
		<logic:messagesPresent property="noMatch3" message="true">
		    	<bean:message bundle="messageRes" key="messages.prefix" /><bean:message bundle="messageRes" key="error.link.products.empty" /><bean:message bundle="messageRes" key="messages.suffix" /> 
		</logic:messagesPresent>
		<logic:messagesPresent property="productNoKeywordError" message="true">
		    	<bean:message bundle="messageRes" key="messages.prefix" /><bean:message bundle="messageRes" key="error.link.products.nokeywords" /><bean:message bundle="messageRes" key="messages.suffix" /> 
		</logic:messagesPresent>
		<logic:messagesPresent property="meddraSelectError" message="true">
		    	<bean:message bundle="messageRes" key="messages.prefix" /><bean:message bundle="messageRes" key="error.link.meddra.select" /><bean:message bundle="messageRes" key="messages.suffix" /> 
		</logic:messagesPresent>	
		<logic:messagesPresent property="meddraNoKeywordError" message="true">
		    	<bean:message bundle="messageRes" key="messages.prefix" /><bean:message bundle="messageRes" key="error.link.meddra.nokeywords" /><bean:message bundle="messageRes" key="messages.suffix" /> 
		</logic:messagesPresent>
		<logic:messagesPresent property="meddraKeywordError" message="true">
		    	<bean:message bundle="messageRes" key="messages.prefix" /><bean:message bundle="messageRes" key="error.link.meddra.keywords" /><bean:message bundle="messageRes" key="messages.suffix" /> 
		</logic:messagesPresent>																				
		<logic:messagesPresent property="noMatch4" message="true">
		    	<bean:message bundle="messageRes" key="messages.prefix" /><bean:message bundle="messageRes" key="error.link.meddra.empty" /><bean:message bundle="messageRes" key="messages.suffix" /> 
		</logic:messagesPresent>
								
		<bean:message bundle="messageRes" key="messages.footer" />
    </logic:present> 	    
</div>

<div class="row">
	<div class="column90"><bean:message key="fields.help1"/>&nbsp;<html:img src="images/question.gif" alt="" />&nbsp;<bean:message key="fields.help2"/></div>	
</div>
<div class="row-clear"></div>
<div class="row">
	<bean:message key="section.mandatory"/>
</div>
<fieldset><legend><strong><bean:message key="subtitle.search.section1"/></strong></legend>
<div class="row alignRight"><a href="<bean:message key='link.help.section1' />"><bean:message key="alt.help.section1"/></a><br/><br/></div>

<div class="row-clear"></div>

<div class="row">
	<div class="column100 alignLeft"><span class="text-note-large"><strong><bean:message key="data.note" />&nbsp;<sess:attribute name="quarter"/>&nbsp;<bean:message key="data.note.end"/></strong></span></div>	
	<br/>
</div>

<div class="row-clear"></div>
<div class="row">
	<div class="column3"></div>
	<div class="column30">
		<html:radio name="searchForm" property="dateSelection" value="0" styleId="initialDateFrom">	
			<label for="initialDateFrom">
				<bean:message key="label.initialDateReceived" />
			</label>
		</html:radio>
		<a href="<bean:message key='link.help.initialDateReceived' />"><html:img src="images/question.gif" altKey="alt.help" /></a>
	</div>
	<div class="column5">
		<bean:message key="label.OR"/>
	</div>
	<div class="column33">		
		<html:radio name="searchForm" property="dateSelection" value="1" styleId="followUpDateFrom">		
			<label for="followUpDateFrom">
				<bean:message key="label.latestDateReceived" />
			</label>
		</html:radio>
		<a href="<bean:message key='link.help.latestDateReceived' />"><html:img src="images/question.gif" altKey="alt.help" /></a>		
	</div><br/><br/>
</div>
<logic:messagesPresent property="dateMissingError" message="true">
	<div class="row-clear"> 
    	<div class="text-red alignCenter"><bean:message bundle="messageRes" key="error.date.empty" /></div> 
    </div> 
</logic:messagesPresent>
<logic:messagesPresent property="dateFormatError" message="true">
	<div class="row-clear"> 
    	<div class="text-red alignCenter"><bean:message bundle="messageRes" key="error.date.format" /></div> 
    </div> 
</logic:messagesPresent>
<logic:messagesPresent property="dateRangeError" message="true">
	<div class="row-clear"> 
    	<div class="text-red alignCenter"><bean:message bundle="messageRes" key="error.date.range" /></div> 
    </div> 
</logic:messagesPresent>
<logic:messagesPresent property="dateMaxRangeError" message="true">
	<div class="row-clear"> 
    	<div class="text-red alignCenter"><bean:message bundle="messageRes" key="error.toDate.maxRange" /></div> 
    </div> 
</logic:messagesPresent>
<div class="row-clear"></div>
<div class="row">
	<div class="column18"></div>
	<div class="column5"><label for="dateFrom"><bean:message key="label.from"/></label></div>
	<div class="column15"><span class="text-note"><bean:message key="date.format"/></span></div>
	<div class="column18"><html:text name="searchForm" property="dateFrom" size="10" styleId="dateFrom" /></div>
</div>
<div class="row-clear"></div>
<div class="row">
	<div class="column18"></div>
	<div class="column5"><label for="dateTo"><bean:message key="label.To"/></label></div>
	<div class="column15"><span class="text-note"><bean:message key="date.format"/></span></div>		
	<logic:empty name="searchForm" property="dateTo" scope="session">
		<div class="column20"><html:text name="searchForm" property="dateTo" size="10" styleId="dateTo" value="<%=quarter%>" /></div>		
	</logic:empty>	
	<logic:notEmpty name="searchForm" property="dateTo" scope="session">
		<div class="column20"><html:text name="searchForm" property="dateTo" size="10" styleId="dateTo" /></div>		
	</logic:notEmpty>
</div>

<div class="row-clear"></div>
<div class="row alignCenter"><br/></div>
<div class="row">
	<div class="column3"></div>
	<div class="column35">
		<label for="seriousnessCode">
			<bean:message key="label.seriousness" />
		</label>
		<a href="<bean:message key='link.help.seriousness' />"><html:img src="images/question.gif" altKey="alt.help" /></a>
	</div>
	<div class="column40">
		<html:select name="searchForm" property="seriousnessCode" styleId="seriousnessCode" style="width:290px;">
			<html:option value="0"><bean:message key="label.selectBoth" /></html:option>				      				
			<html:options collection="seriousness" property="seriousCodeAndName" labelProperty="name" />
		</html:select>	
	</div>
	<br/><br/>
</div>
<div class="row">
	<div class="column3"></div>
	<div class="column35">
		<label for="reportSourceCode">
			<bean:message key="label.reportSource" /><bean:message key="format.colon" />
		</label>
		<a href="<bean:message key='link.help.reportSource' />"><html:img src="images/question.gif" altKey="alt.help" /></a>
	</div>
	<div class="column40">
		<html:select name="searchForm" property="reportSourceCode" styleId="reportSourceCode" style="width:290px;">
			<html:option value="0"><bean:message key="label.selectAll" /></html:option>				      				
			<html:options collection="reportSources" property="reportSourceCodeAndName" labelProperty="name" />
		</html:select>				
	</div>
	<br/><br/>
</div>
</fieldset>
<fieldset><legend><strong><bean:message key="subtitle.search.section2"/></strong></legend>
<div class="row alignRight"><a href="<bean:message key='link.help.section2' />"><bean:message key="alt.help.section2"/></a><br/><br/></div>

<div class="row">
	<div class="column3"></div>
	<div class="column35">
		<label for="genderCode">
			<bean:message key="label.gender" /><bean:message key="format.colon" />
		</label>
		<a href="<bean:message key='link.help.gender' />"><html:img src="images/question.gif" altKey="alt.help" /></a>
	</div>
	<div class="column40">
		<html:select name="searchForm" property="genderCode" styleId="genderCode" style="width:290px;">
			<html:option value="0"><bean:message key="label.selectAll" /></html:option>				      				
			<html:options collection="genders" property="genderCodeAndName" labelProperty="name" />
		</html:select>					
	</div>
	<br/><br/>
</div>
<div class="row">
	<div class="column3"></div>
	<div class="column35">
		<label for="outcomeCode">
			<bean:message key="label.outcome" /><bean:message key="format.colon" />
		</label>
		<a href="<bean:message key='link.help.outcome' />"><html:img src="images/question.gif" altKey="alt.help" /></a>
	</div>
	<div class="column40">
		<html:select name="searchForm" property="outcomeCode" styleId="outcomeCode" style="width:290px;">
			<html:option value="0"><bean:message key="label.selectAll" /></html:option>				      				
			<html:options collection="outcomes" property="outcomeCodeAndName" labelProperty="name" />
		</html:select>
	</div>
	<br/><br/>
</div>
<logic:messagesPresent property="ageRangeError" message="true">
	<div class="row-clear"> 
    	<div class="text-red alignCenter"><bean:message bundle="messageRes" key="error.age.range.invalid" /></div> 
    </div> 
</logic:messagesPresent>
<div class="row">
	<div class="column3"></div>
	<div class="column35">
			<bean:message key="label.age"/><bean:message key="format.colon" />
		<a href="<bean:message key='link.help.age' />"><html:img src="images/question.gif" altKey="alt.help" /></a>
	</div>
	<div class="column5"><label for="ageFrom"><bean:message key="label.from" /></label></div>				
	<div class="column15">
		<html:select name="searchForm" property="ageFrom" styleId="ageFrom" >
			<html:option value="0" >0</html:option>
			<html:optionsCollection name="ageDropdown" label="label" value="value"/>
		</html:select>
		<bean:message key="label.years"/>
	</div>
	<div class="column5"><label for="ageTo"><bean:message key="label.To"/></label></div>
	<div class="column15">
		<html:select name="searchForm" property="ageTo" styleId="ageTo" >
			<html:option value="0" ><bean:message key="age.all"/></html:option>
			<html:optionsCollection name="ageDropdown" label="label" value="value"/>
		</html:select>	
		<bean:message key="label.years"/>		
	</div>		
	<br/>
</div>
<div class="row-clear"></div>
</fieldset>
<fieldset id="section3"><legend><strong><bean:message key="subtitle.search.section3"/></strong></legend>
<div class="row alignRight"><a href="<bean:message key='link.help.section3' />"><bean:message key="alt.help.section3"/></a><br/><br/></div>
<div class="row">
	<div class="column3"></div>
	<div class="column90 alignLeft text-note"><strong><bean:message key="section3.mandatory"/></strong></div>
</div>
<div class="row-clear"></div>
<div class="row">
	<div class="column3"></div>
	<div class="column90 alignLeft">
		<span class="text-note">
			<bean:message key="label.section3n4.explain" />
		</span><br/><br/>
	</div>
</div>
<div class="row-clear"></div>
<div class="row">
	<div class="column3"></div>
	<div class="column60">
		<html:radio name="searchForm" property="nameLevel" value="0" styleId="allHealthProducts"><label for="allHealthProducts"><bean:message key="label.selectAll.healthProducts"/></label></html:radio>			
		<a href="<bean:message key='link.help.search.section3' />"><html:img src="images/question.gif" altKey="alt.help" /></a>		
	</div>
	<br/><br/>
</div>

<div class="row">
	<div class="column3">&nbsp;</div>
	<div class="column25 alignCenter"><hr class="line"/></div>
	<div class="column5 alignCenter"><bean:message key="label.OR"/></div>	
	<div class="column25 alignCenter"><hr class="line"/></div>		
	<br/>
</div>


<div class="row-clear"></div>
<div class="row">
	<div class="column3">&nbsp;</div>
<!--	<div class="column25">&nbsp;</div>-->
	<div class="column70">
<!--		<label for="keywordsSection3">-->
<!--			<bean:message key="label.keywordSearch" /><bean:message key="format.colon" />-->
<!--		</label>	-->
		<html:radio name="searchForm" property="nameLevel" value="1" styleId="brandName"><label for="brandName"><bean:message key="dropdown.nameLevel.brandName"/></label></html:radio>	
		<a href="<bean:message key='link.help.search.section3.brand' />"><html:img src="images/question.gif" altKey="alt.help" /></a>		
		<html:radio name="searchForm" property="nameLevel" value="2" styleId="ingredient"><label for="ingredient"><bean:message key="dropdown.nameLevel.ingredient"/></label></html:radio>
		<a href="<bean:message key='link.help.search.section3.ingredient' />"><html:img src="images/question.gif" altKey="alt.help" /></a>				
	</div>
	<br/><br/>
</div>

<logic:messagesPresent property="productKeywordsError" message="true">
	<div class="row-clear"> 
    	<div class="text-red alignCenter"><bean:message bundle="messageRes" key="error.products.keywords" /></div> 
    </div> 
</logic:messagesPresent>
<logic:present name="displaySection3Search">
<div class="row-clear"><br/></div>
<div class="row">
	<div class="column3"></div>
	<div class="column23">
		<label for="wildcardSection3">
			<bean:message key="label.operator" /><bean:message key="format.colon" />
		</label>
	</div>
	<div class="column40">	
		<html:select name="searchForm" property="wildcardSection3" styleId="wildcardSection3" style="width:130px;">
			<html:option value="2" key="dropdown.wildcard.contains"/>
			<html:option value="1" key="dropdown.wildcard.beginsWith"/>
		</html:select>
	</div>
</div>
<logic:messagesPresent property="noMatch3" message="true">
	<div class="row-clear"> 
    	<div class="text-red alignCenter"><bean:message bundle="messageRes" key="error.products.empty" /></div>
    </div> 
</logic:messagesPresent>
<div class="row-clear"></div>
<div class="row">
	<div class="column3"></div>
	<div class="column23">
		<label for="keywordsSection3">
			<bean:message key="label.keywordSearch" /><bean:message key="format.colon" />
		</label>	
	</div>
	<div class="column60">					
		<html:text name="searchForm" property="keywordsSection3" styleId="keywordsSection3" style="width:170px;" />&nbsp;
		<html:submit property="action" styleClass="subjectMod" >
			<bean:message key="button.sec3find"/>
		</html:submit>			
		<br /><br />
		<!-- <div class="text-note alignLeft" style="margin-left: 240px"><bean:message key="label.minCharacter" /></div> -->
	</div>
	<div class="column30">	
					
	</div>	
</div>
</logic:present>
<div class="row-clear"></div>

<logic:messagesPresent property="productSelectError" message="true">
	<div class="row-clear"> 
    	<div class="text-red alignCenter"><bean:message bundle="messageRes" key="error.products.select" /></div> 
    </div> 
</logic:messagesPresent>
<logic:present name="suspectedDrugs">
	<div class="row">
		<div class="column3">&nbsp;</div>	
		<div class="column50"><span class="text-note alignCenter"><label for="section3results"><bean:message key="note.multiselect"/></label></span></div>
	</div>
	<div class="row-clear"></div>	
	<div class="row">
		<div class="column3">&nbsp;</div>	
		<div class="column50">
			<html:select name="searchForm" property="section3results" styleId="section3results" multiple="true" size="5" style="width:320px;" >
				<html:option value="0"><bean:message key="label.selectAllBelow" /></html:option>
				<html:optionsCollection name="suspectedDrugs" label="label" value="value"/>				
			</html:select>			
		</div>
		<br/>
	</div>
</logic:present>

<logic:present name="activeIngredients">
	<div class="row">
		<div class="column3">&nbsp;</div>	
		<div class="column50"><span class="text-note alignCenter"><label for="section3results"><bean:message key="note.multiselect"/></label></span></div>
	</div>
	<div class="row-clear"></div>	
	<div class="row">
		<div class="column3">&nbsp;</div>	
		<div class="column50">
			<html:select name="searchForm" property="section3results" styleId="section3results" multiple="true" size="5" style="width:320px;" >
				<html:option value="0"><bean:message key="label.selectAllBelow" /></html:option>
				<html:options collection="activeIngredients" property="activeIngredientName" labelProperty="activeIngredientName"/>
			</html:select>			
		</div>
		<br/>
	</div>
</logic:present>

<div class="row-clear"></div>
</fieldset>
<fieldset id="section4"><legend><strong><bean:message key="subtitle.search.section4"/></strong></legend>
<div class="row alignRight"><a href="<bean:message key='link.help.section4' />"><bean:message key="alt.help.section4"/></a><br/><br/></div>
<div class="row">
	<div class="column3"></div>
	<div class="column90 alignLeft text-note"><strong><bean:message key="section4.mandatory"/></strong></div>
</div>
<div class="row-clear"></div>
<div class="row">
	<div class="column3"></div>
	<br/>
</div>

<div class="row">
	<div class="column3">&nbsp;</div>
	<div class="column60">
		<html:radio name="searchForm" property="meddraTermLevel" value="0" styleId="allReactions"><label for="allReactions"><bean:message key="label.selectAll.reactions"/></label></html:radio>	
		<a href="<bean:message key='link.help.search.section4' />"><html:img src="images/question.gif" altKey="alt.help" /></a>		
	</div>
	<br/><br/>	
</div>

<div class="row">
	<div class="column3">&nbsp;</div>
	<div class="column35 alignCenter"><hr class="line"/></div>
	<div class="column5 alignCenter"><bean:message key="label.OR"/></div>	
	<div class="column35 alignCenter"><hr class="line"/></div>		
	<br/>
</div>

<div class="row-clear"></div>
<div class="row">
	<div class="column3">&nbsp;</div>
	<div class="column80">
<!--		<label for="keywordsSection4">-->
<!--			<bean:message key="label.keywordSearch" /><bean:message key="format.colon" />-->
<!--		</label>	-->
		<html:radio name="searchForm" property="meddraTermLevel" value="2" styleId="reactionTerm"><label for="reactionTerm"><bean:message key="dropdown.meddra.pt"/></label></html:radio>
		<a href="<bean:message key='link.help.search.section4.term' />"><html:img src="images/question.gif" altKey="alt.help" /></a>				
		<html:radio name="searchForm" property="meddraTermLevel" value="1" styleId="soc"><label for="soc"><bean:message key="dropdown.meddra.soc"/></label></html:radio>
		<a href="<bean:message key='link.help.search.section4.class' />"><html:img src="images/question.gif" altKey="alt.help" /></a>				
	</div>
	<br/>
</div>

<logic:present name="displaySection4Search">
<div class="row-clear"><br/></div>

<div class="row">
	<div class="column3"></div>
	<div class="column23">
		<label for="wildcardSection4">
			<bean:message key="label.operator" /><bean:message key="format.colon" />
		</label>	
	</div>
	<div class="column40">		
		<html:select name="searchForm" property="wildcardSection4" styleId="wildcardSection4" style="width:130px;">
			<html:option value="2" key="dropdown.wildcard.contains"/>		
			<html:option value="1" key="dropdown.wildcard.beginsWith"/>
		</html:select>
	</div>		
</div>
<logic:messagesPresent property="noMatch4" message="true">
	<div class="row-clear"> 
    	<div class="text-red alignCenter"><bean:message bundle="messageRes" key="error.meddra.empty" /></div>
    </div> 
</logic:messagesPresent>
<logic:messagesPresent property="meddraNoKeywordError" message="true">
	<div class="row-clear"> 
    	<div class="text-red alignCenter"><bean:message bundle="messageRes" key="error.meddra.nokeywords" /></div> 
    </div> 
</logic:messagesPresent>
<logic:messagesPresent property="meddraKeywordError" message="true">
	<div class="row-clear"> 
    	<div class="text-red alignCenter"><bean:message bundle="messageRes" key="error.meddra.keywords" /></div> 
    </div> 
</logic:messagesPresent>
<div class="row-clear"></div>
<div class="row">
	<div class="column3"></div>
	<div class="column23">
		<label for="keywordsSection4">
			<bean:message key="label.keywordSearch" /><bean:message key="format.colon" />
		</label>
	</div>
	<div class="column60">				
		<html:text name="searchForm" property="keywordsSection4" styleId="keywordsSection4" style="width:170px;" />&nbsp;
		<html:submit property="action" styleClass="subjectMod" >
			<bean:message key="button.sec4find"/>
		</html:submit>
		<!-- <div class="text-note alignLeft" style="margin-left: 240px"><bean:message key="label.minCharacter" /></div> -->
	</div><br/><br/>
</div>
</logic:present>

<logic:messagesPresent property="meddraSelectError" message="true">
	<div class="row-clear"> 
    	<div class="text-red alignCenter"><bean:message bundle="messageRes" key="error.meddra.select" /></div> 
    </div> 
</logic:messagesPresent>
<div class="row-clear"></div>
<logic:present name="systemOrganClasses">
	<div class="row">
		<div class="column3">&nbsp;</div>	
		<div class="column50"><span class="text-note alignCenter"><label for="section4results"><bean:message key="note.multiselect4"/></label></span></div>
	</div>
	<div class="row-clear"></div>	
	<div class="row">
		<div class="column3">&nbsp;</div>	
		<div class="column50">
			<html:select multiple="multiple" name="searchForm" property="section4results" styleId="section4results" multiple="true" size="5" style="width:320px;" >
				<html:options collection="systemOrganClasses" property="socName" labelProperty="socName"/>
			</html:select>			
		</div>
		<br/><br/>
	</div>
</logic:present>

<logic:present name="preferredTerms">
	<div class="row">
		<div class="column3">&nbsp;</div>	
		<div class="column50"><span class="text-note alignCenter"><label for="section4results"><bean:message key="note.multiselect4"/></label></span></div>
	</div>
	<div class="row-clear"></div>	
	<div class="row">
		<div class="column3">&nbsp;</div>	
		<div class="column50">
			<html:select multiple="multiple" name="searchForm" property="section4results" styleId="section4results" multiple="true" size="5" style="width:320px;" >
				<html:option value="0"><bean:message key="label.selectAllBelow" /></html:option>
				<html:options collection="preferredTerms" property="ptName" labelProperty="ptName"/>
			</html:select>			
		</div>
		<br/><br/>
	</div>
</logic:present>

<div class="row-clear"></div>
</fieldset>

<div class="row-clear"></div>
<div class="row alignCenter">
		<html:submit property="action" styleClass="subject" ><bean:message key="button.search"/></html:submit>&nbsp;&nbsp;&nbsp;
		<html:submit property="action" styleClass="subject" ><bean:message key="button.reset" /></html:submit>		
</div>

</html:form>