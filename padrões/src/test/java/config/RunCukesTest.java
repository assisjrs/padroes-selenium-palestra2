package config;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by assis on 24/02/17.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber"},
        glue = "stepDefinition",
        features = "src/test/java/features",
        snippets = SnippetType.CAMELCASE,
        strict = true)
public class RunCukesTest {
    private static WebDriver driver;
    private static WebDriverWait wait;

    private static DBUnit dbUnit;

    public static WebDriver webDriver() {
        if(driver == null)
            driver = new ChromeDriver();

        return driver;
    }

    public static WebDriverWait webDriverWait() {
        if(wait == null)
            wait = new WebDriverWait(webDriver(), 60);

        return wait;
    }

    public static DBUnit dbUnit() {
        if(dbUnit == null)
            dbUnit = new DBUnit();

        return dbUnit;
    }

    @AfterClass
    public static void destruirWebDriver() {
        driver.close();
        driver.quit();
    }
}
