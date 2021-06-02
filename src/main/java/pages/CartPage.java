package pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import Util.BaseClass;
import Util.Reporting;

public class CartPage extends BaseClass {
	
	public CartPage(AppiumDriver<MobileElement> driver)
{
	PageFactory.initElements(new AppiumFieldDecorator(driver), this);
}
	@AndroidFindBy(xpath="(//*[@resource-id='activeCartViewForm']//*[@class='android.widget.TextView'])[1]")
	   public WebElement ProductName;

	@AndroidFindBy(xpath="(//*[@resource-id='activeCartViewForm']//*[@class='android.widget.TextView'])[2]")
	public WebElement ProductCount;
	
	@AndroidFindBy(xpath="(//*[@resource-id='sc-mini-buy-box']//*[@class='android.widget.Button'])")
	public WebElement CheckOut;
	
	/*
	 * Description: Method to validate the product
	 * Created By: Sumana
	 * Attributes: report - class object for generating HTML report and logging
	 *             ProductName - Product name to be validate
	 *             Productcount- Product count to be validated
	 */ 
	public void ValidateProductDetails(String Productname,String Productcount,Reporting report)
	{
		String ActProductName=ProductName.getText();
		 report.extentReportPass("Cart Page is displayed");
		Assert.assertTrue(ActProductName.contains(Productname), "Product detail is wrong");
		
		String ActProductCount =ProductCount.getText();
		Assert.assertEquals(ActProductCount, Productcount);
	}
	/*
	 * Description: Method to add the product to cart
	 * Created By: Sumana
	 * Attributes: report - class object for generating HTML report and logging
	 *             
	 */ 
	public void NavigateCheckOut(Reporting report)
	{   waitForElementToBeClickable(CheckOut);
		Click(CheckOut,report);
	}

}