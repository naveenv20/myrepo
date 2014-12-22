package com.qtpselenium.framework.datadriven.PortFolioTest;

import java.util.Hashtable;

import org.eclipse.jetty.util.log.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qtpselenium.framework.datadriven.TestBase;
import com.qtpselenium.framework.datadriven.util.Constants;
import com.qtpselenium.framework.datadriven.util.ErrorUtil;
import com.qtpselenium.framework.datadriven.util.TestDataProvider;
import com.qtpselenium.framework.datadriven.util.Utility;
import com.qtpselenium.framework.datadriven.util.Xls_Reader;

public class LoginTest extends TestBase{
@Test(dataProviderClass=TestDataProvider.class,dataProvider="SuiteADataProvider")
public void logintest(Hashtable<String,String> table) throws InterruptedException{
	
	/*Xls_Reader xls=new Xls_Reader("C:\\Users\\othnvun\\workspace_framework\\TestData\\Suite.xlsx");
System.out.println(	Utility.isSuiterunnable("SuiteA",xls));
Xls_Reader xls_testcase=new Xls_Reader("C:\\Users\\othnvun\\workspace_framework\\TestData\\SuiteA.xlsx");
System.out.println(Utility.istestcaserunnable("Test1", xls_testcase));*/
	validateRunmodes("logintest", Constants.FIRST_SUITE, table.get(Constants.RUNMODE_COL));
	
	APPLICATION_LOG.debug(table.get(Constants.BROWSER_COL));
//login steps are given the common function dologin in test base class
//dologin(table.get(Constants.USER_NAME), table.get(Constants.LOGIN_PASSWORD),table.get(Constants.BROWSER_COL));

//Assert.assertTrue(verifytitle("myportfolio_title"),"Titles Do no match . Got the title as -"+d1.getTitle());
	try{
	Assert.assertEquals("A", "B");
	}catch (Throwable t){
		ErrorUtil.addVerificationFailure(t);
	}

	}

@AfterMethod
public void quitingbrowser(){
	quit(d1);
}


@BeforeTest
public void beFore(){
	System.out.println("^^^^^^^^^^BEFORE TEST^^^^^^^");
	initLogs(this.getClass());
}

}
