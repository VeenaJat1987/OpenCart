package testBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties prop;

	@BeforeClass(groups = { "Regression", "Master", "Sanity", "DataDriven" })
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws MalformedURLException {
		logger = LogManager.getLogger(this.getClass());

		try {
			prop = new Properties();
			FileReader file = new FileReader(".\\src\\test\\resources\\config.properties");
			prop.load(file);
		} catch (Exception e) {

			e.printStackTrace();
		}

		if (prop.getProperty("execution_env").equalsIgnoreCase("remote")) {

			
			  DesiredCapabilities capabilities = new DesiredCapabilities();
			  
			  // OS
			  if (os.equalsIgnoreCase("windows")) {
			  capabilities.setPlatform(Platform.WINDOWS); 
			  } 
			  else if (os.equalsIgnoreCase("linux")) {
			  capabilities.setPlatform(Platform.LINUX); 
			  } 
			  else if  (os.equalsIgnoreCase("mac")) { 
				  capabilities.setPlatform(Platform.MAC);
				  } 
			  else   { 
				  System.out.println(" No matching OS"); 
				  return;
			  }
			  
			  // Browser 
			  switch (br.toLowerCase()) { 
			  case "chrome": capabilities.setBrowserName("chrome"); break; 
			  case "firefox": capabilities.setBrowserName("firefox"); break;
			  default: System.out.println("invalid browser"); return; 
			  }
			  
			  driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),
			  capabilities);
			

			/*
			 * DesiredCapabilities capabilities = new DesiredCapabilities();
			 * capabilities.setPlatform(Platform.WINDOWS);
			 * capabilities.setBrowserName("firefox"); driver = new RemoteWebDriver(new
			 * URL("http://localhost:4444"), capabilities);
			 */

			// driver = new ChromeDriver();

		} else if (prop.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("invalid browser");
				return;
			}

		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(prop.getProperty("appurl"));
		driver.manage().window().maximize();
	}

	@AfterClass(groups = { "Regression", "Master", "Sanity", "DataDriven" })
	public void tearDown() {
		driver.quit();
	}

	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomNumber() {
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}

	public String randomAlphaNumber() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		String generatedNumber = RandomStringUtils.randomNumeric(4);
		return generatedString + "@" + generatedNumber;
	}

	public String captureScreen(String testcaseName) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		File sourcefile = takeScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilepath = System.getProperty("user.dir") + "\\screenshots\\" + testcaseName + " _" + timeStamp
				+ ".png";
		File targetfile = new File(targetFilepath);
		sourcefile.renameTo(targetfile);
		return targetFilepath;

	}

}
