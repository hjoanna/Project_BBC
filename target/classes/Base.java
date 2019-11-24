package resources;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Base {
	
	public static WebDriver driver;
	public static Properties prop;
	public static Logger log;
	
	
	public WebDriver initializeDriver() throws IOException {
		
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/main/java/resources/data.properties");
		
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		if (browserName.equals("Chrome")){
			System.setProperty("webdriver.chrome.driver", "src/main/java/resources/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("Firefox")){
			System.setProperty("webdriver.gecko.driver", "src/main/java/resources/geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equals("InternetExplorer")){
			System.setProperty("webdriver.ie.driver", "src/main/java/resources/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
		return driver;
	}

	
	
	public static void acceptCookies(WebDriver driver) throws InterruptedException{
		if (driver.findElement(By.id("cookiePrompt")).isDisplayed() == true){
			driver.findElement(By.cssSelector("#cookiePrompt [style='display: block;'] [class*='continue-button']")).click();
			if (driver.findElement(By.id("cookiePrompt")).isDisplayed() == true){
				driver.findElement(By.cssSelector("#cookiePrompt [style='display: block;'] [class*='continue-button']")).click();
			}
		}
	}
	
	public static Logger createLogger() {
		log = LogManager.getLogger(Base.class.getName());
		return log;
	}

	public void getScreenshot(String result) throws IOException{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("src/test/screenshot"+result+"Screenshot.png"));
	}

	
	public static void newTab(Robot robot) throws InterruptedException{
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	

	
}
