package Project.AppiumFramework;

import java.io.IOException;
//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TC_01 extends Capability {

	AndroidDriver<AndroidElement>driver;
	
	@BeforeTest
	public void bt() throws IOException, InterruptedException
	{
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(5000);
		
	}
	
	
	//scenario1 - sign in to khan academy
	@Test(priority=1)
	public void testcase1() throws InterruptedException, IOException
	{
		service = startserver();
		driver = capability(appPackage,appActivity,deviceName,chromedriverExecutable);
		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Thread.sleep(20000);
		//click on dismiss button
		driver.findElement(By.xpath("//android.widget.Button[@index='2']")).click();
		Thread.sleep(2000);
		//sign in to app
		driver.findElement(By.xpath("//android.widget.Button[@index='3']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//android.widget.TextView[@text='Continue with Google']")).click();
		Thread.sleep(10000);
		driver.findElement(By.id("com.google.android.gms:id/account_display_name")).click();
		//sign in verify
		Thread.sleep(20000);
		driver.findElement(MobileBy.AccessibilityId("Settings")).click();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//android.widget.Button[@content-desc='Back']")).click();
		System.out.println("user is logged in succesfully");
	}
	
	
	//settings -> Manage teachers -> Add teacher
	@Test(priority=2)
	public void testcase2() throws InterruptedException
	{
		
		driver.findElement(MobileBy.AccessibilityId("Settings")).click();
		Thread.sleep(6000);
		//click on manage teacher
		driver.findElement(By.xpath("//android.widget.Button[@index='3']")).click();
		Thread.sleep(2000);
		//click on add teacher
		driver.findElement(By.xpath("//android.widget.TextView[@text='Add teacher']")).click();
		Thread.sleep(10000);
		driver.findElement(MobileBy.AccessibilityId("e.g. ABC123 or teacher@example.com")).sendKeys("ABC@gmail.com");
		Thread.sleep(10000);
		driver.findElement(By.xpath("//android.widget.TextView[@text='ADD']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//android.widget.TextView[@text='ADD']")).click();
		Thread.sleep(10000);
		driver.findElement(By.id("android:id/button1")).click();
		Thread.sleep(10000);
		System.out.println("Teacher is added successfully");
	}
	
	//remove teacher added
	@Test(priority=3)
	public void testcase3() throws InterruptedException
	{
		Thread.sleep(6000);
		//click on remove teacher
		driver.findElement(MobileBy.AccessibilityId("Delete")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("android:id/button1")).click();
		System.out.println("Teacher is removed");
		Thread.sleep(10000);
		driver.findElement(MobileBy.AccessibilityId("Dismiss")).click();
	}
	
	
	//verify the language and region
	@Test(priority=4)
	public void testcase4() throws InterruptedException
	{
		Thread.sleep(15000);
		driver.findElement(By.xpath("//android.widget.TextView[@text='Language & region']")).click();
		Thread.sleep(6000);
		//select english default
		driver.findElement(By.xpath("//android.widget.Button[@index='12']")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//android.widget.Button[@content-desc='Back']")).click();
		System.out.println("successfully changed to english universal language");
		
	}
		
	
	//signout of the app
	@Test(priority=5)
	public void testcase5() throws InterruptedException
	{
		Thread.sleep(15000);
		driver.findElement(By.xpath("//android.widget.Button[@index='4']")).click();
		Thread.sleep(6000);
		driver.findElement(By.id("android:id/button1")).click();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//android.widget.Button[@content-desc='Back']")).click();
		System.out.println("successfully signed out");
		service.stop();
	}
		
	

}


