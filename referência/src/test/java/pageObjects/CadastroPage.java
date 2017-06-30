package pageObjects;

import config.Page;
import config.WaitMe;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

@Page
public class CadastroPage {
    @Autowired
    private WebDriver driver;

    @Autowired
    private WebDriverWait wait;

    private String nome;
    private String email;

    @WaitMe(seconds = 10)
    @FindBy(xpath = "//*[contains(@id,'usuarioForm')]/tbody/tr[2]/td[2]/input")
    private WebElement nomeInput;

    @WaitMe(seconds = 10)
    @FindBy(xpath = "//*[contains(@id,'usuarioForm')]/tbody/tr[3]/td[2]/input")
    private WebElement emailInput;

    @WaitMe(seconds = 10)
    @FindBy(xpath = "//*[contains(text(),'Salvar')]")
    private WebElement salvar;

    @WaitMe(seconds = 10)
    @FindBy(linkText = "Novo Usuário")
    private WebElement novoUsuarioButton;

    @WaitMe(seconds = 10)
    @FindBy(id = "usuarioForm")
    private WebElement usuarioForm;

    @Autowired
    private UsuariosPageElement usuarios;

    public void novoUsuario() {
        driver.get("http://localhost:9090/index.xhtml");

        novoUsuarioButton().click();

        setNomeInput(nome);

        wait.withTimeout(2, SECONDS);

        setEmailInput(email);

        salvar().click();
    }

    private WebElement novoUsuarioButton() {
        wait.until(elementToBeClickable(novoUsuarioButton));
        return novoUsuarioButton;
    }

    private void setNomeInput(final String nome) {
        wait.until(elementToBeClickable(novoUsuarioButton));

        nomeInput.clear();

        wait.withTimeout(1, SECONDS);
        nomeInput.sendKeys(nome);
    }

    private void setEmailInput(final String email) {
        wait.until(elementToBeClickable(emailInput));

        emailInput.clear();

        wait.withTimeout(1, SECONDS);
        emailInput.sendKeys(email);
    }

    private WebElement salvar() {
        wait.until(elementToBeClickable(salvar));
        return salvar;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CadastroPageAssert assertThat() {
        driver.get("http://localhost:9090/index.xhtml");
        return new CadastroPageAssert(this);
    }

    public UsuariosPageElement getUsuarios() {
        return usuarios;
    }
}
