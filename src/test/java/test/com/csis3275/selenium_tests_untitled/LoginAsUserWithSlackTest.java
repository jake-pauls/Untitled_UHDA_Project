package test.com.csis3275.selenium_tests_untitled;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.csis3275.config_untitled.SpringBootApplicationInitializer_Untitled;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Jacob Pauls Student ID 300273666
 * @date Dec 1, 2020
 * LoginAsUserWithSlackTest.java
 * test.com.csis3275.selenium_tests_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApplicationInitializer_Untitled.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:test-slack-association-delete.sql")
public class LoginAsUserWithSlackTest {

	private final int port = 8080;	
	private String rootUrl;
		
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "c:/temp/chromedriver.exe");
		driver = new ChromeDriver();
		this.rootUrl = "http://localhost:" + port;
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    js = (JavascriptExecutor) driver;
	    vars = new HashMap<String, Object>();
	}
	
	@After
	public void tearDown() {
	  driver.quit();
	}
	
	@Test
	public void loginAsUserWithSlack() {
		driver.get(rootUrl);
		driver.manage().window().setSize(new Dimension(1936, 1056));
		driver.findElement(By.cssSelector(".uk-button")).click();
		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).sendKeys("uhda_testuser");
		driver.findElement(By.id("password")).sendKeys("u");
		driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
		assertEquals(driver.findElement(By.xpath("//p[contains(.,\'Slack profile found in workspace! UHDA is now connected to your Slack account.\')]")).getText(), "Slack profile found in workspace! UHDA is now connected to your Slack account.");
		{
		  WebElement element = driver.findElement(By.id("slack_override"));
		  Actions builder = new Actions(driver);
		  builder.moveToElement(element).perform();
		}
		{
		  List<WebElement> elements = driver.findElements(By.xpath("//a[@uk-tooltip=\'Currently connected with Slack as uhda.untitled.testuser@gmail.com\']"));
		  assert(elements.size() > 0);
		}
		{
		  WebElement element = driver.findElement(By.tagName("body"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element, 0, 0).perform();
	    }
	}
}
