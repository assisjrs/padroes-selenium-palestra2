package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.AbstractList;

import static org.openqa.selenium.By.tagName;

public class UsuariosPageElement extends AbstractList<WebElement>{

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "dataTable_data")
    private WebElement dataTable_data;

    public UsuariosPageElement(final WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 60);
    }

    @Override
    public int size() {
        driver.get("http://localhost:9090/index.xhtml");

        return dataTable_data.findElements(tagName("tr")).size();
    }

    @Override
    public WebElement get(int i) {
        driver.get("http://localhost:9090/index.xhtml");

        return dataTable_data.findElements(tagName("tr")).get(i);
    }
}
