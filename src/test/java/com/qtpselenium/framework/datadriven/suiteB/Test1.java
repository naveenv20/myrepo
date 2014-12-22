package com.qtpselenium.framework.datadriven.suiteB;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qtpselenium.framework.datadriven.TestBase;
import com.qtpselenium.framework.datadriven.util.Constants;
import com.qtpselenium.framework.datadriven.util.TestDataProvider;
import com.qtpselenium.framework.datadriven.util.Utility;
import com.qtpselenium.framework.datadriven.util.Xls_Reader;

public class Test1 extends TestBase{
	@Test(dataProviderClass=TestDataProvider.class,dataProvider="SuiteBDataProvider")
public void test1(Hashtable<String,String> table){
	
	/*Xls_Reader xls=new Xls_Reader("C:\\Users\\othnvun\\workspace_framework\\TestData\\Suite.xlsx");
System.out.println(	Utility.isSuiterunnable("SuiteA",xls));
Xls_Reader xls_testcase=new Xls_Reader("C:\\Users\\othnvun\\workspace_framework\\TestData\\SuiteA.xlsx");
System.out.println(Utility.istestcaserunnable("Test1", xls_testcase));*/
	validateRunmodes("Test1", Constants.SECOND_SUITE, table.get(Constants.RUNMODE_COL));
	}


 
}
