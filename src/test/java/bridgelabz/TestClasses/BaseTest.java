package bridgelabz.TestClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {

	Properties properties;
	FileReader fr;

	@BeforeClass(groups = {"smoke","sanity","functional","datadriven"})
	@Parameters({"os","browser"})
	public void setUp(String os,String browser)
	{

		// Configuring config.properties file
		try {
			fr=new FileReader("./src/test/resources/config.properties");
			properties=new Properties();
			properties.load(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}

		WebDriver driver=null;

		if (properties.getProperty("grid_execution_env").equalsIgnoreCase("local"))
		{

			switch (browser.toLowerCase()) {
			case "chrome": 
				driver = new ChromeDriver();
				break;
			case "edge": 
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default: System.out.println(" Invalid Browser !! "); return;
			}
		}else if(properties.getProperty("grid_execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();

			// Setting  OS
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else {
				System.out.println("Invalid Platform Name Entered !! ");
				return;
			}

			// Setting Browser
			switch (browser.toLowerCase()) {
			case "chrome": 
				capabilities.setBrowserName("chrome");
				break;
			case "edge": 
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			case "firefox":
				capabilities.setBrowserName("firefox");
				break;
			default: System.out.println(" Invalid Browser !! "); return;
			}

			try {
				driver=new RemoteWebDriver(new URL(properties.getProperty("grid_url")), capabilities);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}	
		}

		DriverManager.setDriver(driver);

		DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		DriverManager.getDriver().get(properties.getProperty("url"));

		DriverManager.getDriver().manage().deleteAllCookies();
		DriverManager.getDriver().manage().window().maximize();
	}

	public static String captureScreenShot(String tname) {

		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot screenshot=(TakesScreenshot)DriverManager.getDriver();
		File sourceFile=screenshot.getScreenshotAs(OutputType.FILE);

		String targetFileName=System.getProperty("user.dir")+"\\screenshots"+tname+"_"+timeStamp+".png";
		File targetFile=new File(targetFileName);

		sourceFile.renameTo(targetFile);

		return targetFileName;
	}

	@AfterClass(groups = {"smoke","sanity","functional","datadriven"})
	public void tearDown() {
		DriverManager.getDriver().quit();
	}

}
