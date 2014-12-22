package com.qtpselenium.framework.datadriven.util;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.qtpselenium.framework.datadriven.TestBase;

public class TestDataProvider{

	
	@DataProvider(name="SuiteADataProvider")
		public static Object[][] getDataSuiteA(Method m){
		TestBase.init();
		Xls_Reader xls=new Xls_Reader(TestBase.prop.getProperty("filelocation")+Constants.FIRST_SUITE+".xlsx");
		System.out.println("Test method name   :::"+m.getName());
		return Utility.getData(m.getName(), xls);
			
		}
	
	@DataProvider(name="SuiteBDataProvider")
	public static Object[][] getDataSuiteB(Method m){
		TestBase.init();
	Xls_Reader xls=new Xls_Reader(TestBase.prop.getProperty("filelocation")+Constants.SECOND_SUITE+".xlsx");
	System.out.println("Test method name   :::"+m.getName());
	return Utility.getData(m.getName(), xls);
		
	}
}
