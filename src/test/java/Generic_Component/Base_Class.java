package Generic_Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class Base_Class {
	
	public Process process;
	public AppiumDriver driver;
	
	Utility_Class c1 = new Utility_Class();
	
	public void Start_Server() throws IOException, InterruptedException
	{
		String start_server = "E:\\Appium\\workspace\\Appium.exe E:\\Appium\\workspace\\node_modules\\appium\\bin\\appium.js";
		process = Runtime.getRuntime().exec(start_server);
		Thread.sleep(10000);
		
		if (process!=null)
		{
			System.out.println("Appium server is up and running");
		}
		else
		{
			System.out.println("Appium server is not Initialized");
		}
		
		Thread.sleep(12000);
	}
	
	public void Init_App() throws IOException
	{

       DesiredCapabilities capabilities= new DesiredCapabilities();
		
		capabilities.setCapability("deviceName", "XP7700");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "5.1");
		
		capabilities.setCapability("appPackage",c1.Reading_properties("Package_Name"));
		capabilities.setCapability("appActivity",c1.Reading_properties("Activity_Name"));
		
		 driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
	}


	public void Explicit_wait(WebElement ele, long T1)
	{
		WebDriverWait wait = new WebDriverWait(driver, T1);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	
	public void Snapshot_screen(String TC_ID, String Order) throws IOException
	{
		Date date = new Date();
		SimpleDateFormat d_format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		
		File file = new File(d_format.format(date) +".png");
		
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(screenshotAs, new File("C:\\workspace\\Eclipse\\Projects\\Appium_frm\\BB_Project\\Screenshot\\"+TC_ID+"-"+Order+"-"+file));
		
	}
	
	public void Stop_Server() throws InterruptedException
	{
		if (process!=null)
		{
			process.destroy();
			Thread.sleep(10000);
			System.out.println("Appium Server is stopped");
		}
			
	}
	
	
}
