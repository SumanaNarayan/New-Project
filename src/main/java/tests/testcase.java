
package tests;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import Util.BaseClass;
import Util.Reporting;

public class testcase extends BaseClass{
	Reporting extentReport;
	/*
	 * Description: Before test method to initialize the drivers and reporting file
	 * Created By: Sumana 
	 */
	
	@BeforeTest
	public void intiateDriver()
	{
	
		extentReport=new Reporting();
		extentReport.extentReportInit();
		extentReport.logger=extentReport.report.createTest("Amazon Test");
		setup();
	}
	/*
	 * Description: Test method for executing the Amazon App
	 * Created By: Sumana
	 */
	
	@Test
	@Parameters({"ProductName","CartCount"})
	public void testcase(String ProductName,String CartCount)
	
	{ 
		HomePage home= new HomePage(driver);
		ProductPage Prdt= new ProductPage(driver);
		CartPage cart= new CartPage(driver);
		LoginPage log= new LoginPage(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.login(extentReport);
	    home.searchProduct(ProductName,extentReport);
	    String Product= Prdt.getProductDetails(extentReport);
	    Prdt.NavigateToCart(extentReport);
	    
	    cart.ValidateProductDetails(Product, CartCount,extentReport);
	    cart.NavigateCheckOut(extentReport);
	    
	    
	}
	
	/*
	 * Description: After Method to tear down the driver and check execution status
	 * Created By: Sumana
	 * Attribute: result - Class object of ITestResult to fetch the overall execution status
	 */
	@AfterTest
	public void teardown(ITestResult result)
	{
		if(ITestResult.FAILURE==result.getStatus())
		{
			extentReport.extentReportFail(result.getThrowable().getMessage());
			
		}
		driver.quit();
		extentReport.report.flush();
	}
	

}
