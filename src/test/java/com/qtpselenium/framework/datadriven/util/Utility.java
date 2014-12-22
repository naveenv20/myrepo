package com.qtpselenium.framework.datadriven.util;

import java.util.Hashtable;

public class Utility {
	
	
	//Tells you wether test suite is runnable or not
	public static boolean isSuiterunnable(String suiteName,Xls_Reader xls){
		
		int rows=xls.getRowCount(Constants.SUITE_SHEET);
		for(int rNum=2;rNum<=rows;rNum++){
			String data=xls.getCellData(Constants.SUITE_SHEET, Constants.SUITENAME_COL, rNum);
			if(data.equalsIgnoreCase(suiteName)){
				String runmode=xls.getCellData(Constants.SUITE_SHEET, Constants.RUNMODE_COL, rNum);
				if(runmode.equalsIgnoreCase(Constants.RUNMODE_YES))
					return true;
					else
						return false;
			}
			//System.out.println(data);
		}
				
		return false;//default
		
	}
	
	//Tells you wether test case is runnable or not from the respective suite xl file
	public static boolean istestcaserunnable(String testcase,Xls_Reader xls){
		
		int rows=xls.getRowCount(Constants.TESTCASES_SHEET);
		for(int rNum=2;rNum<=rows;rNum++){
			String data=xls.getCellData(Constants.TESTCASES_SHEET, Constants.TESTCASES_COL, rNum);
			
			if(data.equalsIgnoreCase(testcase)){
				String runmode=xls.getCellData(Constants.TESTCASES_SHEET,Constants.RUNMODE_COL, rNum);
				if(runmode.equalsIgnoreCase(Constants.RUNMODE_YES))
					return true;
				else
					return false;
			}
		}
		return false;//default
		
	}
	
	
	public static Object[][] getData(String testName,Xls_Reader xls_testcase){
		
		int rows=xls_testcase.getRowCount(Constants.DATA_SHEET);
		System.out.println("Total rows:::     "+rows);
		int testCaseRowNum=1;
		for( testCaseRowNum=1;testCaseRowNum<=rows;testCaseRowNum++){
			String testNamexls=xls_testcase.getCellData(Constants.DATA_SHEET,0,testCaseRowNum);
			if(testNamexls.equalsIgnoreCase(testName)){
				break;
			}

		}
		System.out.println("Test starts from the row number:::     "+testCaseRowNum);
		int datastartrownum=testCaseRowNum+2;
		int colstartrownum=testCaseRowNum+1;
		// rows of data --pull out
		int  testrows=1;
		while(!xls_testcase.getCellData(Constants.DATA_SHEET, 0, datastartrownum+testrows).equals("")){
			testrows++;
		}
		System.out.println("Total rows of data are:::   "+testrows);

		//cols of data

		int testcolumns=0;
		while(!xls_testcase.getCellData(Constants.DATA_SHEET, testcolumns, colstartrownum).equals("")){
			testcolumns++;
		}
		System.out.println("Total columns of data are:::   "+testcolumns);

		Object[][] data1=new Object[testrows][1];
		int r=0;
		for (int rNum=datastartrownum;rNum<(datastartrownum+testrows);rNum++){
			Hashtable<String,String> table= new 	Hashtable<String,String>();
			for (int cNum=0;cNum<testcolumns;cNum++){
				//System.out.println(xls_testcase.getCellData(Constants.DATA_SHEET, cNum, rNum));
				//data[r][cNum]=xls_testcase.getCellData(Constants.DATA_SHEET, cNum, rNum);
				table.put(xls_testcase.getCellData(Constants.DATA_SHEET, cNum, colstartrownum), xls_testcase.getCellData(Constants.DATA_SHEET, cNum, rNum));
			}
			data1[r][0]=table;
			r++;
		}
return data1;// two dimentional array with all the hashtable created for each row of data
	}

}
