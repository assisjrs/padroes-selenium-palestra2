package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import static config.RunCukesTest.webDriverWait;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.support.PageFactory.initElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

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
    private WebElement novoUsuarioButton;

    @FindBy(id = "usuarioForm")
    private WebElement usuarioForm;

    private UsuariosPageElement usuarios;

    public CadastroPage(WebDriver driver){
        this.driver = driver;
        wait = webDriverWait();

        usuarios = initElements(driver, UsuariosPageElement.class);

        driver.get("http://localhost:9090/index.xhtml");
    }

    public void novoUsuario() {
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
        return new CadastroPageAssert(this);
    }

    public UsuariosPageElement getUsuarios() {
        return usuarios;
    }
}
