package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class CadastroPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private String nome;
    private String email;

    @FindBy(xpath = "//*[contains(@id,'usuarioForm')]/tbody/tr[2]/td[2]/input")
    private WebElement nomeInput;

    @FindBy(xpath = "//*[contains(@id,'usuarioForm')]/tbody/tr[3]/td[2]/input")
    private WebElement emailInput;

    @FindBy(xpath = "//*[contains(text(),'Salvar')]")
    private WebElement salvar;

    @FindBy(linkText = "Novo Usuário")
    private WebElement novoUsuario;

    @FindBy(xpath = "//*[@id=\"dataTable_data\"]/tr")
    private List<WebElement> usuarios;

    public CadastroPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 60000);
    }

    public void novoUsuario() throws InterruptedException {
        novoUsuario.click();

        wait.until(visibilityOfElementLocated(id("usuarioForm")));

        nomeInput.sendKeys(nome);
        emailInput.sendKeys(email);

        salvar.click();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CadastroPageAssert assertThat() {
        return new CadastroPageAssert(this);
    }

    public List<WebElement> getUsuarios() {
        wait.until(visibilityOfElementLocated(xpath("//*[@id=\"dataTable_data\"]/tr")));
        wait.withTimeout(5, SECONDS);

        return usuarios;
    }
}
