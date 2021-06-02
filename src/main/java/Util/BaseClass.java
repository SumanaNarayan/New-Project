package Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class BaseClass {
	
	public static AppiumDriver<MobileElement> driver;
	DesiredCapabilities cap = new DesiredCapabilities();
	Properties prop=new Properties();
	/*
	 * Description: Reusable function initialize the driver
	 * Created By: Sumana 
	 */
	public void setup()
	{
		try {
		DesiredCapabilities cap = new DesiredCapabilities();
		prop=loadPropertyFile(System.getProperty("user.dir")+"\\src\\main\\resources\\PropertyFiles\\capability.properties");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, prop.getProperty("platformName"));
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("deviceName"));
		cap.setCapability(MobileCapabilityType.APP, prop.getProperty("app"));
		cap.setCapability("appPackage",prop.getProperty("appPackage"));
		cap.setCapability("appActivity",prop.getProperty("appActivity"));
		
		
		URL url = new URL(prop.getProperty("hubUrl"));
		driver= new AppiumDriver<MobileElement>(url,cap);
		driver= new AndroidDriver<MobileElement>(url,cap);
		
		
	}
	catch(Exception exp) {
	System.out.println("Cause is:"+exp.getCause());
	System.out.println("Message is:"+exp.getMessage());
	exp.printStackTrace();
	}
	}
	/*
	 * Description: Reusable function to wait for an element to be clickable
	 * Created By: Sumana 
	 * Attribute: Webelement- locator
	 * 			  
	 */

	
	public void waitForElementToBeClickable(WebElement identifier)
	{
		try
		{
			WebDriverWait wait=new WebDriverWait(driver, 60);
			  wait.until(ExpectedConditions.elementToBeClickable(identifier));
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	/*
	 * Description: Reusable function to scroll the page
	 * Created By: Sumana 
	 
	 */
	public static void scrollDown(){
        Dimension dimension = driver.manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.5);
        int scrollEnd = (int) (dimension.getHeight() * 0.2);

        new TouchAction((PerformsTouchActions)driver)
                .press(PointOption.point(0, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(0, scrollEnd))
                .release().perform();
    }
	
	/*
	 * Description: Reusable function to scroll and check and click an element is present in UI
	 * Created By: Sumana 
	 * Attribute: property- xpath value of the element to be identified
	 */
	public static void scrollNClick(WebElement el,Reporting report){
        int retry = 0;
        while(retry <= 5){
            try{
                el.click();
                report.extentReportPass(el+ "is clicked");
                break;
            }catch (org.openqa.selenium.NoSuchElementException e){
                scrollDown();
                retry++;
            }
        }
    }
	/*
	 * Description: Reusable function to load the property File
	 * Created By: Sumana
	 * Attribute: path- Path of the property file
	 */
	
	public Properties loadPropertyFile(String path)
	{
		try
		{
			FileInputStream fs=new FileInputStream(path);
			prop.load(fs);
		}
		catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
		}
		return prop;
	}
	/*
	 * Description: Reusable function to click on an element
	 * Created By: Sumana
	 * Attribute: WebElement- locator 
	 * 			  
	 * 			  report- Class object of Reporting to generate extent report
	 */
	public void Click(WebElement el,Reporting report) 
	{
		try
		{
			
			el.click();
			report.extentReportPass(el+ "is clicked");
		}
		catch (Exception e) {
			e.printStackTrace();
			report.extentReportFail(e.getMessage());
			Assert.assertTrue(false, e.getMessage());
		}
	}
	


}