package com.qtpselenium.framework.datadriven.suiteB;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qtpselenium.framework.datadriven.TestBase;
import com.qtpselenium.framework.datadriven.util.Constants;
import com.qtpselenium.framework.datadriven.util.TestDataProvider;
import com.qtpselenium.framework.datadriven.util.Utility;
import com.qtpselenium.framework.datadriven.util.Xls_Reader;

public class Test2 extends TestBase{
	@Test(dataProviderClass=TestDataProvider.class,dataProvider="SuiteBDataProvider")
	public void test2(Hashtable<String,String> table){
		validateRunmodes("Test2", Constants.SECOND_SUITE, table.get(Constants.RUNMODE_COL));
	}

	
	
}
