import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class ExplicitWait {

    static WebDriver driver;

    @BeforeMethod
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.foodpanda.bg/");
    }

    @Test
    public void explicitWaitTest() {
        WebElement search = driver.findElement(By.id("delivery-information-postal-index"));

        search.sendKeys("ulitsa \"Sevastokrator Kaloyan\" 32, Sofia");

        WebElement submit = driver.findElement(By.cssSelector("button.js-restaurants-search-submit"));
        submit.click();

        Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        WebElement message =wait.until(new ExpectedCondition<WebElement>() {
            @NullableDecl
            @Override
            public WebElement apply(@NullableDecl WebDriver input) {
                return driver.findElement(By.cssSelector("span.header-order-button-content"));
            }
        });

        assertTrue(message.getText().contains("ulitsa \"Sevastokrator Kaloyan\" 32, Sofia"));


    }

    @AfterMethod
    public void ShutDown() {
        driver.quit();
    }
}
