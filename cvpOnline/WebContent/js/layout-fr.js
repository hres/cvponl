
/**
 * Javascript created by Diane to calculate and validate table
 * Date: May 3, 2007
 */
 
  	function resetTime(size)
 	{
	 	for(var i = 0;i< size;i++){
			document.forms[0].elements["time["+ i + "].sunday"].value = "";
			document.forms[0].elements["time["+ i + "].monday"].value = "";
			document.forms[0].elements["time["+ i + "].tuesday"].value = "";
			document.forms[0].elements["time["+ i + "].wednesday"].value = "";
			document.forms[0].elements["time["+ i + "].thursday"].value = "";
			document.forms[0].elements["time["+ i + "].friday"].value = "";
			document.forms[0].elements["time["+ i + "].saturday"].value	= "";

			document.forms[0].elements["time["+ i + "].sundayRemark"].value = "";
			document.forms[0].elements["time["+ i + "].mondayRemark"].value = "";
			document.forms[0].elements["time["+ i + "].tuesdayRemark"].value = "";
			document.forms[0].elements["time["+ i + "].wednesdayRemark"].value = "";
			document.forms[0].elements["time["+ i + "].thursdayRemark"].value = "";
			document.forms[0].elements["time["+ i + "].fridayRemark"].value = "";
			document.forms[0].elements["time["+ i + "].saturdayRemark"].value = "";

		}
		document.forms[0].totalSunday.value = "";
		document.forms[0].totalMonday.value = "";
		document.forms[0].totalTuesday.value = "";
		document.forms[0].totalWednesday.value = "";
		document.forms[0].totalThursday.value = "";
		document.forms[0].totalFriday.value = "";
		document.forms[0].totalSaturday.value = "";

 	}
 	
 	function resetDailyTime(size, intDay)
 	{
	 	var zero= ""; 
 		for(var i = 0;i< size;i++){
			
			switch (intDay)
			{
				case 0 :
					document.forms[0].elements["time["+ i + "].sunday"].value = "";
					document.forms[0].elements["time["+ i + "].sundayRemark"].value = "";
					break;
				case 1 :
					document.forms[0].elements["time["+ i + "].monday"].value = "";
					document.forms[0].elements["time["+ i + "].mondayRemark"].value = "";
					break;
				case 2 :	
					document.forms[0].elements["time["+ i + "].tuesday"].value = "";
					document.forms[0].elements["time["+ i + "].tuesdayRemark"].value = "";					
					break;
					
				case 3 :	
					document.forms[0].elements["time["+ i + "].wednesday"].value = "";
					document.forms[0].elements["time["+ i + "].wednesdayRemark"].value = "";
					break;
				case 4 :	
					document.forms[0].elements["time["+ i + "].thursday"].value = "";
					document.forms[0].elements["time["+ i + "].thursdayRemark"].value = "";
					break;
				case 5 :	
					document.forms[0].elements["time["+ i + "].friday"].value = "";
					document.forms[0].elements["time["+ i + "].fridayRemark"].value = "";
					break;
				case 6 :	
					document.forms[0].elements["time["+ i + "].saturday"].value	= "";
					document.forms[0].elements["time["+ i + "].saturdayRemark"].value = "";
					break;
			}
		}
		switch (intDay)
		{
			case 0 :
				document.forms[0].totalSunday.value = "";
				break;
			case 1 :
				document.forms[0].totalMonday.value = "";
				break;
			case 2 :
				document.forms[0].totalTuesday.value = "";
				break;
			case 3 :
				document.forms[0].totalWednesday.value = "";
				break;
			case 4 :
				document.forms[0].totalThursday.value = "";
				break;
			case 5 :
				document.forms[0].totalFriday.value = "";
				break;
			case 6 :
				document.forms[0].totalSaturday.value = "";
				break;
		}
		
 	}
 	
	function saveTimetrak(size) {
		var count = 0;
	 	for(var i = 0;i< size;i++){
			var val  = document.forms[0].elements["time["+ i + "].sunday"].value;
			if (val.length > 0)
			{
				if (IsANumeric(val) == false)
				{
					count +=1;
				}
			}
			var val  = document.forms[0].elements["time["+ i + "].monday"].value;
			if (val.length > 0)
			{
				if (IsANumeric(val) == false)
				{
					count +=1;
				}
			}
			var val  = document.forms[0].elements["time["+ i + "].tuesday"].value;
			if (val.length > 0)
			{
				if (IsANumeric(val) == false)
				{
					count +=1;
				}
			}
			var val  = document.forms[0].elements["time["+ i + "].wednesday"].value;
			if (val.length > 0)
			{
				if (IsANumeric(val) == false)
				{
					count +=1;
				}
			}
			var val  = document.forms[0].elements["time["+ i + "].thursday"].value;
			if (val.length > 0)
			{
				if (IsANumeric(val) == false)
				{
					count +=1;
				}
			}
			var val  = document.forms[0].elements["time["+ i + "].friday"].value;
			if (val.length > 0)
			{
				if (IsANumeric(val) == false)
				{
					count +=1;
				}
			}
			var val  = document.forms[0].elements["time["+ i + "].saturday"].value;
			if (val.length > 0)
			{
				if (IsANumeric(val) == false)
				{
					count +=1;
				}
			}
		}
		if (count > 0)
		{
			alert("Les valeurs doivent être des chiffres seulement!");
			return false;
		}else
		{
			document.forms[0].action='timetrak.do?method=saveTimetrak';
		    document.forms[0].submit();
		}
	   	
	} 
	
	function saveDayTime(size, intDay) {
		var count = 0;

	 	for(var i = 0;i< size;i++)
	 	{
	 		switch (intDay)
			{
				case "0" :
					var val  = document.forms[0].elements["time["+ i + "].sunday"].value;
					if (val.length > 0)
					{
						if (IsANumeric(val) == false)
						{
							count +=1;
						}
					}
				case "1" :
					var val  = document.forms[0].elements["time["+ i + "].monday"].value;
					if (val.length > 0)
					{
						if (IsANumeric(val) == false)
						{
							count +=1;
						}
					}
				case "2" :
					var val  = document.forms[0].elements["time["+ i + "].tuesday"].value;
					if (val.length > 0)
					{
						if (IsANumeric(val) == false)
						{
							count +=1;
						}
					}
				case "3" :
					var val  = document.forms[0].elements["time["+ i + "].wednesday"].value;
					if (val.length > 0)
					{
						if (IsANumeric(val) == false)
						{
							count +=1;
						}
					}
				case "4" :
					var val  = document.forms[0].elements["time["+ i + "].thursday"].value;
					if (val.length > 0)
					{
						if (IsANumeric(val) == false)
						{
							count +=1;
						}
					}
				case "5" :
					var val  = document.forms[0].elements["time["+ i + "].friday"].value;
					if (val.length > 0)
					{
						if (IsANumeric(val) == false)
						{
							count +=1;
						}
					}
				case "6" :
					var val  = document.forms[0].elements["time["+ i + "].saturday"].value;
					if (val.length > 0)
					{
						if (IsANumeric(val) == false)
						{
							count +=1;
						}
					}
				}
		}
		if (count > 0)
		{
			alert("Les valeurs doivent être des chiffres seulement!");
			return false;
		}else
		{
			document.forms[0].action='dailyTime.do';
		    document.forms[0].submit();
		}
	   	
	}
	
	
	function calcSundayTotal(count)
	{
		
		var totalSun = 0;
			
		for (var i = 0; i < count; i++) {
	    	var val  = document.forms[0].elements["time["+ i + "].sunday"].value
			if (val.length > 0)
			{		
				if (IsANumeric(val) == false)
				{
					alert("\"" + val + "\"" + " doit être un numéro!");
					document.forms[0].elements["time["+ i + "].sunday"].focus();
					return false;
				}
				totalSun += parseFloat(val);
			}
		}
		
		document.forms[0].totalSunday.value = totalSun.toFixed(2);
		
		
	}

function calcMondayTotal(count)
{
	var totalMon = 0;
	
	for(var i = 0;i< count;i++){
		var val  = document.forms[0].elements["time["+ i + "].monday"].value
		if (val.length > 0)
			{		
				if (IsANumeric(val) == false)
			{
				alert("\"" + val + "\"" + " doit être un numéro!");
				document.forms[0].elements["time["+ i + "].monday"].focus();
				return false;
			}
			totalMon += parseFloat(val);
		}
	}
	
	document.forms[0].totalMonday.value = totalMon.toFixed(2);
	
	
}

function calcTuesdayTotal(count)
{
	var totalTues = 0;
	
	for(var i = 0;i< count;i++){
		var val  = document.forms[0].elements["time["+ i + "].tuesday"].value
		if (val.length > 0)
		{		
			if (IsANumeric(val) == false)
			{
				alert("\"" + val + "\"" + " doit être un numéro!");
				document.forms[0].elements["time["+ i + "].tuesday"].focus();
				return false;
			}
			totalTues += parseFloat(val);
		}
	}

	document.forms[0].totalTuesday.value = totalTues.toFixed(2);
	
	
}	


function calcWednesdayTotal(count)
{
	var totalWed = 0;
	
	for(var i = 0;i< count;i++){
		var val  = document.forms[0].elements["time["+ i + "].wednesday"].value
		if (val.length > 0)
		{		
			if (IsANumeric(val) == false)
			{
				alert("\"" + val + "\"" + " doit être un numéro!");
				document.forms[0].elements["time["+ i + "].wednesday"].focus();
				return false;
			}
			totalWed += parseFloat(val);
		}
	}
	
	document.forms[0].totalWednesday.value = totalWed.toFixed(2);
	
	
}
function calcThursdayTotal(count)
{
	var totalThu = 0;
	
	
		for(var i = 0;i< count;i++){
			var val  = document.forms[0].elements["time["+ i + "].thursday"].value
			if (val.length > 0)
			{		
				if (IsANumeric(val) == false)
				{
					alert("\"" + val + "\"" + " doit être un numéro!");
					document.forms[0].elements["time["+ i + "].thursday"].focus();
					return false;
				}
				totalThu += parseFloat(val);
			}
		}
	
	document.forms[0].totalThursday.value = totalThu.toFixed(2);
	
	
}

function calcFridayTotal(count)
{
	var totalFri = 0;
	
	for(var i = 0;i< count;i++){
		var val  = document.forms[0].elements["time["+ i + "].friday"].value
		if (val.length > 0)
		{		
			if (IsANumeric(val) == false)
			{
				alert("\"" + val + "\"" + " doit être un numéro!");
				document.forms[0].elements["time["+ i + "].friday"].focus();
				return false;
			}
			totalFri += parseFloat(val);
		}
	}
	
	document.forms[0].totalFriday.value = totalFri.toFixed(2);
	
	
}

function calcSaturdayTotal(count)
{
	var totalSat = 0;
	
	for(var i = 0;i< count;i++){
		var val  = document.forms[0].elements["time["+ i + "].saturday"].value
		if (val.length > 0)
		{		
			if (IsANumeric(val) == false)
			{
				alert("\"" + val + "\"" + " doit être un numéro!");
				document.forms[0].elements["time["+ i + "].saturday"].focus();
				return false;
			}
			totalSat += parseFloat(val);
		}
	}
	
	document.forms[0].totalSaturday.value = totalSat.toFixed(2);
	
	
}


function IsANumeric(val)
{
	var ValidChars = "0123456789.";
	var IsNumber=true;
	var Char;

	for (i = 0; i < val.length; i++) 
	{ 
		Char = val.charAt(i); 
		if (ValidChars.indexOf(Char) == -1)
		{
			return false; 
		}
	}
	
}


// This function is for stripping leading and trailing spaces
function trim(str)
{     
	if (str != null) 
	{        
		var i;         
		for (i=0; i<str.length; i++) 
		{           
			if (str.charAt(i)!=" ")
			{                
				str=str.substring(i,str.length);                 
				break;           
			}         
		}            
		for (i=str.length-1; i>=0; i--)
		{           
			if (str.charAt(i)!=" ")
		 	{               
				str=str.substring(0,i+1);                
				break;           
			}        
		}                
		if (str.charAt(0)==" ") 
		{            
			return "";        
		} else 
		{            
			return str;        
		}    
	}
}