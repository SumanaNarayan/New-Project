package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import Util.BaseClass;
import Util.Reporting;

public class HomePage extends BaseClass {
	
	public HomePage(AppiumDriver<MobileElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
  
@AndroidFindBy(id="com.amazon.mShop.android.shopping:id/rs_search_src_text")
   public WebElement SearchField;

@AndroidFindBy(xpath="(//*[@resource-id='com.amazon.mShop.android.shopping:id/list_product_linear_layout'])[1]")
public WebElement SearchResult;

/*
 * Description: Method to search the product
 * Created By: Sumana
 * Attributes: report - class object for generating HTML report and logging
 *             ProductName - Product name to be searched
 */ 

   
   public void searchProduct(String ProductName, Reporting report)
   {   
	   waitForElementToBeClickable(SearchField);
	   report.extentReportPass("Home Page is displayed");
	   SearchField.sendKeys(ProductName,Keys.ENTER);
	   waitForElementToBeClickable(SearchResult);
	   report.extentReportPass("Product list is displayed");
	   Click(SearchResult,report);
   }
}