package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Properties p;

	@BeforeClass()
	@Parameters({ "os", "browser" })
	public void setUp(String os, String br) throws IOException {

		// WebDriverManager.chromedriver().setup();
		// driver = new ChromeDriver();

		FileReader file = new FileReader(".\\src\\test\\resources\\config.properties");
		p = new Properties();
		p.load(file);

		switch (br.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("Invalid Browser name...");
			return;
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		// driver.get("https://tutorialsninja.com/demo/");
		driver.get(p.getProperty("appURL")); // reading
		driver.manage().window().maximize();

	}

	@AfterClass()
	public void teardown() {
		driver.quit();
	}

	// Random String, Random Number, Random Alphanumeric
	public String randomString() {
		String generatedstring = RandomStringUtils.randomAlphabetic(5); // RandomStringUtils ->predefined class in
																		// commons.lang library
		return generatedstring;
	}

	public String randomNumber() {
		String generatednumber = RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}

	public String randomAlphaNumeric() {
		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		String generatednumber = RandomStringUtils.randomNumeric(10);
		return (generatedstring + "@" + generatednumber);
	}

	public String captureSceen(String tname) throws IOException{

		//Generate a Timestamp
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		//Capture the Screenshot
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		//Define Target File Path
		String targetFilePath = System.getProperty("user.dir")+ "\\screenshots\\" +tname + "_ " +timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		// Save the Screenshot to the desired location
		sourceFile.renameTo(targetFile);

		//Return the File Path
		return targetFilePath;
	}
}
