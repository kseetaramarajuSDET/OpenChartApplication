package bridgelabz.TestClasses;

import org.openqa.selenium.WebDriver;

public class DriverManager {

	 private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	    public static void setDriver(WebDriver driver) {
	        tlDriver.set(driver);
	    }

	    public static WebDriver getDriver() {
	        return tlDriver.get();
	    }

	    public static void unload() {
	        tlDriver.remove();
	    }
	
}
