package com.qtpselenium.framework.datadriven.PortFolioTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.qtpselenium.framework.datadriven.TestBase;
import com.qtpselenium.framework.datadriven.util.Constants;
import com.qtpselenium.framework.datadriven.util.TestDataProvider;

public class AddStock extends TestBase {
	@Test(dataProviderClass=TestDataProvider.class,dataProvider="SuiteADataProvider")
		public void addStock(Hashtable<String,String> table) throws InterruptedException, ParseException{
			Hashtable<String,Integer> calmonths= new 	Hashtable<String,Integer>();
			putmonths(calmonths);
			validateRunmodes("AddStock", Constants.FIRST_SUITE, table.get(Constants.RUNMODE_COL));
			dologin(table.get(Constants.USER_NAME), table.get(Constants.LOGIN_PASSWORD),table.get(Constants.BROWSER_COL));
		d1.findElement(By.xpath(prop.getProperty("addstock_addstock_xpath"))).click();;
		d1.findElement(By.xpath(prop.getProperty("addstock_stockname_xpath"))).sendKeys(table.get(Constants.stockname));
		Thread.sleep(5000);
		d1.findElement(By.xpath("//*[@id='ajax_listOfOptions']/div")).click();
		d1.findElement(By.xpath(prop.getProperty("addstock_datefield"))).click();
		
		
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
		String month_year_required=month+" "+year;
		
		
		while(true){
		String month_year_displayed=d1.findElement(By.xpath("//*[@id='datepicker']/table/tbody/tr[1]/td[3]/div")).getText();
		if(month_year_required.equals(month_year_displayed)){
			break;
		}
		if(Current_date.after(datetobeselected))
		{
			d1.findElement(By.xpath("//*[@id='datepicker']/table/tbody/tr[1]/td[2]/button")).click();
		}
		else{
			d1.findElement(By.xpath("//*[@id='datepicker']/table/tbody/tr[1]/td[4]/button")).click();
		}
		}
		

List<WebElement> d2=  d1.findElements(By.xpath("//*[@class='dpTR']/td"));
for(int ir=0;ir<d2.size();ir++){
	
	Integer tt=mydate.get(Calendar.DATE);
	if((d2.get(ir).getText()).equals(tt.toString())){
		d2.get(ir).click();
		break;
	}
}

d1.findElement(By.xpath("//*[@id='addstockqty']")).sendKeys("10");
d1.findElement(By.xpath("//*[@id='addstockprice']")).sendKeys("102");
d1.findElement(By.xpath("//*[@id='addStockButton']")).click();		

List<WebElement> d3=  d1.findElements(By.xpath("//*[@id='stock']/tbody/tr/td[2]/span/a"));
System.out.println(d3.size());		
for(int j=0;j<d3.size();j++){
			System.out.println("ABC->"+d3.get(j).getText());
			if(d3.get(j).getText().contains(table.get(Constants.stockname))){
				d1.findElement(By.xpath("//*[@id='stock']/tbody/tr["+j+"]/td[1]")).click();
			}
		}
		d1.findElement(By.xpath("//*[@id='deleteEquity']")).click();
		
		
//Runtime.getRuntime().exec("D:\AutoIt\AutoItTest.exe");
		
		//d1.findElement(By.xpath("//*[@id='signin_info']/a")).click();
		
			}
	
	
	
	
	


}
