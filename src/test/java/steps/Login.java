package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


import java.util.concurrent.TimeUnit;

public class Login {

    WebDriver driver;

/*    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\fedesama\\Documents\\Dev_Projects\\CucumberFramework\\src\\test\\resources\\drivers\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        this.driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
    }*/

    @Before
    public void setupFirefox() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\fedesama\\Documents\\Dev_Projects\\CucumberFramework\\src\\test\\resources\\drivers\\geckodriver.exe");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("marionette", true);
        this.driver = new FirefoxDriver(firefoxOptions);
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        this.driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        this.driver.manage().deleteAllCookies();
        this.driver.quit();
        this.driver = null;
    }

    @Given("^User navigates to stackoverflow website$")
    public void user_navigates_to_stackoverflow_website() throws Throwable {
        this.driver.get("https://stackoverflow.com/");
    }

    @And("^User clicks on the login button on homepage$")
    public void user_clicks_on_the_login_button_on_homepage() throws Throwable {
        Thread.sleep(2000);
        this.driver.findElement(By.xpath("//a[text()='Log In']")).click();
    }

    @And("^User enters a valid username$")
    public void user_enters_a_valid_username() throws Throwable {
        Thread.sleep(2000);
        this.driver.findElement(By.xpath("//input[@id='email']")).sendKeys("*****@gmail.com");
    }

    @And("^User enters a valid password$")
    public void user_enters_a_valid_password() throws Throwable {
        this.driver.findElement(By.xpath("//input[@id='password']")).sendKeys("*****");
    }

    @When("^User clicks on the login button$")
    public void user_clicks_on_the_login_button() throws Throwable {
        this.driver.findElement(By.xpath("//input[@id='submit-button']")).click();
    }

    @Then("^User should be taken to the successful login page$")
    public void user_should_be_taken_to_the_successful_login_page() throws Throwable {
        Thread.sleep(3000);
        WebElement askQuestionButton = this.driver.findElement(By.xpath("//a[text()='Ask Question']"));
        Assert.assertTrue(askQuestionButton.isDisplayed());
    }

}
