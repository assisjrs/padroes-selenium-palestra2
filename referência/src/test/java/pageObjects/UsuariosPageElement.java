package pageObjects;

import config.PageElement;
import config.WaitMe;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.AbstractList;

import static org.openqa.selenium.By.tagName;

@PageElement
public class UsuariosPageElement extends AbstractList<WebElement>{

    @Autowired
    private WebDriver driver;

    @Autowired
    private WebDriverWait wait;

    @WaitMe
    @FindBy(id = "dataTable_data")
    private WebElement dataTable_data;

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
