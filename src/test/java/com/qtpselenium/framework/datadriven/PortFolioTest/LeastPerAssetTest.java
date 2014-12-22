package com.qtpselenium.framework.datadriven.PortFolioTest;

import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qtpselenium.framework.datadriven.TestBase;
import com.qtpselenium.framework.datadriven.util.Constants;
import com.qtpselenium.framework.datadriven.util.TestDataProvider;
import com.qtpselenium.framework.datadriven.util.Utility;
import com.qtpselenium.framework.datadriven.util.Xls_Reader;

public class LeastPerAssetTest extends TestBase{
	@Test(dataProviderClass=TestDataProvider.class,dataProvider="SuiteADataProvider")
	public void leastPerAssetTest(Hashtable<String,String> table) throws InterruptedException{
		validateRunmodes("LeastPerAssetTest", Constants.FIRST_SUITE, table.get(Constants.RUNMODE_COL));
		dologin(table.get(Constants.USER_NAME), table.get(Constants.LOGIN_PASSWORD),table.get(Constants.BROWSER_COL));
		
		//Validating whether least performance stock is in the list or not
	
		WebElement leaststock=d1.findElement(By.xpath(prop.getProperty("Leastperformercompnay_xpath")));
		APPLICATION_LOG.debug(leaststock.getText());
		
		List<WebElement> stocklist=d1.findElements(By.xpath(prop.getProperty("stockstable_xpath")));
		APPLICATION_LOG.debug(stocklist.size());
		for(int i=0;i<stocklist.size();i++){
			String chk=null;
			APPLICATION_LOG.debug(stocklist.get(i).getText());
			if(leaststock.getText().contains(stocklist.get(i).getText())){
				chk=d1.findElement(By.xpath(prop.getProperty("price_xpath"))).getText();
				APPLICATION_LOG.debug("after validation:::"+chk);
			}
			else
			{
				APPLICATION_LOG.debug("not matched so no chk");
			}
		}

	}
	
	@AfterMethod
	public void quitingbrowser(){
		quit(d1);
	}
	
	@BeforeTest
	public void runbeftest(){
		System.out.println("^^^^^^^^^^BEFORE TEST^^^^^^^");
		initLogs(this.getClass());
	}
	
}
