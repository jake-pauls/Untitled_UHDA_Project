/**
 * 
 */
package test.com.csis3275.selenium_tests_untitled;

import static org.junit.jupiter.api.Assertions.*;


import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.csis3275.config_untitled.SpringBootApplicationInitializer_Untitled;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApplicationInitializer_Untitled.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:test-master.sql")
class CommentingFeatureTest {
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/temp/chromedriver.exe");
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	  public void commentingFunctionBlackBoxTest() {
	    driver.get("http://localhost:8080/Untitled_UHDA_Project/login");
	    driver.manage().window().setSize(new Dimension(1920, 1040));
	    driver.findElement(By.id("username")).click();
	    driver.findElement(By.id("username")).sendKeys("user");
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).sendKeys("u");
	    driver.findElement(By.cssSelector(".uk-button-primary")).click();
	    driver.findElement(By.cssSelector(".uk-button:nth-child(9)")).click();
	    driver.findElement(By.id("value")).click();
	    driver.findElement(By.id("value")).sendKeys("This is a test comment");
	    {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(
					ExpectedConditions.presenceOfElementLocated(By.cssSelector(".uk-button-small")));
		}
	    driver.findElement(By.cssSelector(".uk-button-small")).click();
	    driver.findElement(By.cssSelector(".uk-button:nth-child(9)")).click();
	    driver.findElement(By.id("value")).click();
	    driver.findElement(By.id("value")).sendKeys("This is another, I will delete the other comment");
	    {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(
					ExpectedConditions.presenceOfElementLocated(By.cssSelector(".uk-button-small")));
		}
	    driver.findElement(By.cssSelector(".uk-button-small")).click();
	    driver.findElement(By.cssSelector(".uk-button:nth-child(9)")).click();
	    {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(
					ExpectedConditions.presenceOfElementLocated(By.cssSelector(".comment_organ:nth-child(1) em")));
		}
	    driver.findElement(By.cssSelector(".comment_organ:nth-child(1) em")).click();
	    driver.findElement(By.cssSelector(".uk-button:nth-child(9)")).click();
	    driver.findElement(By.cssSelector(".uk-modal-footer:nth-child(4) > .uk-button")).click();
	    {
	      WebElement element = driver.findElement(By.cssSelector(".uk-first-column > .uk-icon-button"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element).perform();
	    }
	    {
	      WebElement element = driver.findElement(By.tagName("body"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element, 0, 0).perform();
	    }
	    driver.findElement(By.cssSelector(".uk-icon-button > svg")).click();
	    driver.findElement(By.id("username")).click();
	    driver.findElement(By.id("username")).sendKeys("employee");
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).sendKeys("e");
	    driver.findElement(By.cssSelector(".uk-button-primary")).click();
	    {
	      WebElement element = driver.findElement(By.cssSelector(".uk-first-column > .uk-icon-button"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element).perform();
	    }
	    {
	      WebElement element = driver.findElement(By.tagName("body"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element, 0, 0).perform();
	    }
	    driver.findElement(By.cssSelector(".uk-first-column svg")).click();
	    driver.findElement(By.cssSelector("tr:nth-child(5) .uk-button")).click();
	    driver.findElement(By.cssSelector("#my3 #value")).click();
	    driver.findElement(By.cssSelector("#my3 #value")).sendKeys("Hello User");
	    {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(
					ExpectedConditions.presenceOfElementLocated(By.cssSelector("#my3 #comment .uk-button")));
		}
	    driver.findElement(By.cssSelector("#my3 #comment .uk-button")).click();
	    driver.findElement(By.cssSelector("tr:nth-child(5) .uk-button")).click();
	    
	    driver.findElement(By.cssSelector("#my3 .uk-modal-footer > .uk-button")).click();
	    {
	      WebElement element = driver.findElement(By.cssSelector("div:nth-child(3) > .uk-icon-button"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element).perform();
	    }
	    {
	      WebElement element = driver.findElement(By.tagName("body"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element, 0, 0).perform();
	    }
	    driver.findElement(By.cssSelector("div:nth-child(3) > .uk-icon-button > svg")).click();
	    driver.close();
	  }

}
