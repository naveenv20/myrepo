package com.qtpselenium.framework.roughwork;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Locale;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.qtpselenium.framework.datadriven.TestBase;
import com.qtpselenium.framework.datadriven.util.Constants;
import com.qtpselenium.framework.datadriven.util.TestDataProvider;

public class datechecker extends TestBase {

	@Test(dataProviderClass=TestDataProvider.class,dataProvider="SuiteADataProvider")
	public void addStock(Hashtable<String,String> table) throws ParseException{
		
		validateRunmodes("AddStock", Constants.FIRST_SUITE, table.get(Constants.RUNMODE_COL));
		//String defmonyear=d1.findElement(By.xpath("//*[@id='datepicker']/table/tbody/tr[1]/td[3]/div")).getText();
		//System.out.println(defmonyear.split(" ")[1]);
		Calendar mydate = new GregorianCalendar();
		String cc=table.get(Constants.dateofpurchase);
		SimpleDateFormat formatter= new SimpleDateFormat("MMMM dd yyyy");
		Date Current_date=new Date();		
		System.out.println("Current_date::"+Current_date);
		
		Date datetobeselected=null;
		datetobeselected=formatter.parse(cc);
		System.out.println("datetobeselected::"+datetobeselected);
		
		System.out.println(Current_date.after(datetobeselected));
		
		String month=new SimpleDateFormat("MMMM").format(datetobeselected);
		mydate.setTime(datetobeselected);
		int year=mydate.get(Calendar.YEAR);
		System.out.println(month+" "+year);
		
		
/*11-June-07
String str_date="11-June-07";
DateFormat formatter ; 
Date dte ; 
   formatter = new SimpleDateFormat("dd-MMM-yy");
   dte = formatter.parse(str_date);
   System.out.println(dte);*/

		
		/*January 20,1995
		String str_date="January 20,1995";
		DateFormat formatter ; 
		Date dte ; 
		   formatter = new SimpleDateFormat("MMMM d,yyyy");
		   dte = formatter.parse(str_date);
		   System.out.println(dte);*/
		
	}	
		
}
