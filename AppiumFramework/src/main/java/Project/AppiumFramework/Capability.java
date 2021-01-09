package Project.AppiumFramework;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Capability {
	
	protected static String appPackage;
	protected static String appActivity;
	protected static String deviceName;
	protected static String chromedriverExecutable;
	public AppiumDriverLocalService service;
	
	  public AppiumDriverLocalService startserver()
	    {
	        boolean flag = checkifserverisRunning(4723);
	        if(!flag)
	        {
	        //service = AppiumDriverLocalService.buildDefaultService();
	            service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
	                    .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
	                    .withAppiumJS(new File("C:\\Users\\SanjanaNayak\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
	                    .withIPAddress("0.0.0.0").usingPort(4723));
	        service.start();
	        }
	        return service;
	    }
	  
	  public static boolean checkifserverisRunning(int port)
	    {
	        boolean isserverRunning = false;
	        ServerSocket serversocket;
	        try
	        {
	            serversocket = new ServerSocket(port);
	            serversocket.close();
	        }
	        catch (Exception e) {
	            isserverRunning= true;
	        }
	        finally {
	            serversocket=null;
	        }
	        return isserverRunning;
	    }
	
	  public static void startEmulator() throws IOException, InterruptedException {
		  Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\emulator.bat");
		  Thread.sleep(20000);
	  }
	  
	  
	public static AndroidDriver<AndroidElement> capability(String appPackage,String appActivity, String deviceName, String chromedriverExecutable) throws IOException, InterruptedException {

		FileReader fis = new FileReader(System.getProperty("user.dir")+"//src//main//java//global.properties");
		Properties pro = new Properties();
		pro.load(fis);
		appPackage = pro.getProperty("appPackage");
		appActivity = pro.getProperty("appActivity");
		deviceName = pro.getProperty("deviceName");
		if(deviceName.contains("Sanjana")) {
			startEmulator();
		}
		chromedriverExecutable = pro.getProperty("chromedriverExecutable");
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, chromedriverExecutable);
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
		cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
		cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);
		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"),cap);
		return driver;

	}

}
