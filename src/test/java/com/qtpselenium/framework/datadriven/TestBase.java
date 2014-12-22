package com.qtpselenium.framework.datadriven;

import java.io.FileInputStream;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.SkipException;

import com.qtpselenium.framework.datadriven.util.Constants;
import com.qtpselenium.framework.datadriven.util.Utility;
import com.qtpselenium.framework.datadriven.util.Xls_Reader;

public class TestBase {
	public static Properties prop;
	public static Logger APPLICATION_LOG= Logger.getLogger("devpinoyLogger");
	public static WebDriver d1;
	
	
	///intialising the properties function
	public static void init(){
		if(prop==null){
			String path=System.getProperty("user.dir")+"\\src\\test\\resources\\project.properties";
			prop=new Properties();

				try {
					FileInputStream fs=new FileInputStream(path);
					prop.load(fs);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		}
		System.out.println("Filelocation:::"+prop.getProperty("filelocation"));
	}
	
	
	
	//******************************************
	//For creation of log file for each suite
	//*******************************************
	
	
	public void initLogs(Class<?> class1){

		FileAppender appender = new FileAppender();
		// configure the appender here, with file location, etc
		appender.setFile(System.getProperty("user.dir")+"//target//simplereports//"+CustomListener.Foldername+"//"+class1.getName()+".log");
		appender.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
		appender.setAppend(false);
		appender.activateOptions();

		APPLICATION_LOG = Logger.getLogger(class1);
		APPLICATION_LOG.setLevel(Level.DEBUG);
		APPLICATION_LOG.addAppender(appender);
	}
	
	
	public void validateRunmodes(String testName,String suiteName, String dataRunmode){
		
		init();
			
	
		//validate the suite run mode
		
		boolean issuiterunnable=(Utility.isSuiterunnable(suiteName,new Xls_Reader(prop.getProperty("filelocation")+"Suite.xlsx")));
		APPLICATION_LOG.debug("issuiterunnable  :::for "+issuiterunnable);
		boolean istestcaserunnable= Utility.istestcaserunnable(testName,new Xls_Reader(prop.getProperty("filelocation")+suiteName+".xlsx"));
		APPLICATION_LOG.debug("istestcaserunnable  :::"+istestcaserunnable);
		boolean dataSetRunMode=false;
		APPLICATION_LOG.debug("Data run mode returining here is ::  "+dataRunmode);
		if(dataRunmode.equalsIgnoreCase(Constants.RUNMODE_YES)){
			dataSetRunMode=true;
		}
		
		if(!(issuiterunnable&&istestcaserunnable&&dataSetRunMode)){
			throw new SkipException("Skipping the test::: "+testName+"    inside the ::: "+suiteName);
		}
	}
	
	
	public Hashtable<String,Integer> putmonths(Hashtable <String,Integer> T){
		T.put("January",0);
		T.put("February",1);
		T.put("March",2);
		T.put("April",3);
		T.put("May",4);
		T.put("June",5);
		T.put("July",6);
		T.put("August",7);
		T.put("September",8);
		T.put("October",9);
		T.put("November",10);
		T.put("December",11);
			return T;
		
	}
	
	
	/************************* Generic Function*************************************/
	
	public void click(String indentifier){
		
		if(indentifier.endsWith("xpath")){
			d1.findElement(By.xpath(prop.getProperty(indentifier))).click();;
		}
		else if (indentifier.endsWith("id")){
			d1.findElement(By.id(prop.getProperty(indentifier))).click();
		}
		
	}
	
	
	public void navigate(String URLKey){
		d1.get(prop.getProperty(URLKey));
		
	}
	
	public void quit(WebDriver d1){
		if(d1!=null){
			d1.quit();
			d1=null;
		}
		
	}
	
	public void input(String Identifier,String data){
		if(Identifier.endsWith("xpath"))
		d1.findElement(By.xpath(prop.getProperty(Identifier))).sendKeys(data);
		
	}
	
	public void openbrowser(String browsername){
		//d1 is declared globally
		//check for browser type  here i have choosen only firefox
		d1=new FirefoxDriver();
		//maximising the window
		d1.manage().window().maximize();
		d1.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	
	public boolean verifytitle(String Expected_tile_key){
		String Expected_title=prop.getProperty(Expected_tile_key);
		String actual_title=d1.getTitle();
		if(Expected_title.equals(actual_title))
			return true;
			else 
				return false;
		
	}
	
	public boolean iselementpresent(String identifier){
	int size=0;
		if(identifier.endsWith("xpath")){
	  size = d1.findElements(By.xpath(prop.getProperty(identifier))).size();
		}
		else if(identifier.endsWith("id")){
			  size = d1.findElements(By.id(prop.getProperty(identifier))).size();
				}
		//not the properties file . from test case we are sending the xpath
		else
			 size = d1.findElements(By.xpath(identifier)).size();
		
		if(size>0)
			return true;
			else
				return false;
		
	

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*****************************APPLICATION FUNCTIONS
	 * @throws InterruptedException *********************************/
	
	
	public void dologin(String Username,String Password,String Browser) throws InterruptedException{
		openbrowser(Browser);
		navigate("testsiteURL");
		Assert.assertTrue(iselementpresent("moneylink_xpath"),"Element not found-moneylink_xpath");
		click("moneylink_xpath");
		click("myportfoliolink_xpath");
		input("loginusername_xpath", Username);
		

		
		click("continue_login_xpath");
		input("loginpassword_xpath", Password);
		click("continue_passwordfill_login_xpath");
		
		//Assert.assertTrue(verifytitle("myportfolio_title"),"Titles Do no match . Got the title as -"+d1.getTitle());
		
	}
	
	
	
}
