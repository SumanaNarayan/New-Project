
package pages;

import java.util.concurrent.TimeUnit;

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

public class ProductPage extends BaseClass {

	public ProductPage(AppiumDriver<MobileElement> driver)
{
	PageFactory.initElements(new AppiumFieldDecorator(driver), this);
}
	@AndroidFindBy(xpath="//*[@resource-id='title_feature_div']//*[@class='android.view.View']")
	   public WebElement ProductName;

	@AndroidFindBy(xpath="//*[contains(@resource-id,'add-to-cart-button')]")
	public WebElement AddToCartButton;
	
	@AndroidFindBy(id="com.amazon.mShop.android.shopping:id/action_bar_cart_count")
	public WebElement CartIcon;
	
	/*
	 * Description: Method to get the product details
	 * Created By: Sumana
	 * Attributes: report - class object for generating HTML report and logging
	 *             
	 */ 
	
	public String getProductDetails(Reporting report)
	{ 
		
		String Product=ProductName.getText();
		report.extentReportPass("Product Page is displayed");
		scrollNClick(AddToCartButton,report);
		return Product;
	}
	
	/*
	 * Description: Method to add the product to cart
	 * Created By: Sumana
	 * Attributes: report - class object for generating HTML report and logging
	 *             
	 */ 
	
	public void NavigateToCart(Reporting report)
	{
		Click(CartIcon,report);
	}
}

