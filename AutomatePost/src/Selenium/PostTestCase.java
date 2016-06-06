package Selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class PostTestCase {

	   public WebDriver driver;
	
		public Utilities helper;
		
		public String  path = (System.getProperty("user.dir")+"//TestData//TestData.xlsx");
		
		@BeforeSuite(alwaysRun=true)
		public void Driver()
		{
		  driver = new FirefoxDriver();			
		}
		
		@BeforeTest(alwaysRun=true)
		public void BeforeTest() throws Throwable
		{
			helper = new Utilities();
					
		   driver.manage().deleteAllCookies();
			
	       driver.navigate().to(helper.getPropertyData("URL"));
		    
		   driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
		
		@DataProvider
		public Object[][] getData(){
			
		Object [][] data = helper.getRowData("Sheet1", path); 
				
			return data;			
		}		
		
	    @Test(dataProvider="getData")
		public void TestPost(String sCustName, String sTelephone,
				             String sEmail, String sSize,
				             String sToppings, String sDel_Time,
                             String Sdel_inst) throws Throwable
		{
			
	   helper.type(ObjectRepository.CUSTNAME , sCustName, driver);
			
	   helper.type(ObjectRepository.TELEPHONE , sTelephone, driver);
	 	
	   helper.type(ObjectRepository.EMAIL , sEmail, driver);
			
	   List<WebElement> allRadioButtons = driver.findElements(By.name("size"));
	        
	    for( int i = 0; i<allRadioButtons.size(); i++ )
	    {
	    String str =	allRadioButtons.get(i).getAttribute("value");
	    if(str.equalsIgnoreCase(sSize))	
	    { 
	    	allRadioButtons.get(i).click(); 
	    	break;
	    	}	
	               	
	    }
	    
	    List<WebElement> chkbox = driver.findElements(By.name("topping"));
	     
	    for( int i = 0; i<chkbox.size(); i++ )
	    {
	    String str = chkbox.get(i).getAttribute("value");
	    if(str.equalsIgnoreCase(sToppings))	
	    { 
	    	chkbox.get(i).click(); 
	    	break;
	    	}	
	               	
	    }
		
	   helper.type(ObjectRepository.DELTIME , sDel_Time, driver);
	   
	   
	    
	   helper.type(ObjectRepository.DELINST , Sdel_inst, driver);
	    
	   driver.findElement(By.xpath("html/body/form/p[6]/button")).click();	
	   	    
	   driver.navigate().to(helper.getPropertyData("URL"));
	    
		}

		@AfterTest(alwaysRun=true)
		public void closeBrowser(){
			driver.close();
		}
	  	
	
}
