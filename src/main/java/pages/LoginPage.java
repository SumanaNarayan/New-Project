package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import Util.BaseClass;
import Util.Reporting;

public class LoginPage extends BaseClass{
	public LoginPage(AppiumDriver<MobileElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
  
@AndroidFindBy(id="com.amazon.mShop.android.shopping:id/sso_continue")
   public WebElement Continue;
/*
 * Description: Method to navigate the Home page
 * Created By: Sumana
 * Attributes: report - class object for generating HTML report and logging
 */ 

public void login(Reporting report)
 {   
	 waitForElementToBeClickable(Continue);
     report.extentReportPass("Login Page is displayed");
	 Click(Continue,report);
 }
}