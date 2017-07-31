package pageObjects;

import config.Page;
import config.WaitMe;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

@Page
public class CadastroPage {
    @Autowired
    private WebDriver driver;

    @Autowired
    private WebDriverWait wait;

    private String nome;
    private String email;

    @WaitMe
    @FindBy(xpath = "//*[contains(@id,'usuarioForm')]/tbody/tr[2]/td[2]/input")
    private WebElement nomeInput;

    @WaitMe
    @FindBy(xpath = "//*[contains(@id,'usuarioForm')]/tbody/tr[3]/td[2]/input")
    private WebElement emailInput;

    @WaitMe(seconds = 10)
    @FindBy(xpath = "//*[contains(text(),'Salvar')]")
    private WebElement salvar;

    @WaitMe
    @FindBy(linkText = "Novo Usuário")
    private WebElement novoUsuarioButton;

    @WaitMe(seconds = 10)
    @FindBy(id = "usuarioForm")
    private WebElement usuarioForm;

    @Autowired
    private UsuariosPageElement usuarios;

    public void acessar() {
        driver.get("http://localhost:9090/index.xhtml");
    }

    public void novoUsuario() throws InterruptedException {
        novoUsuarioButton.click();

        Thread.sleep(1000L);

        setNomeInput(nome);

        setEmailInput(email);

        salvar.click();
    }

    private void setNomeInput(final String nome) {
        nomeInput.clear();
        nomeInput.sendKeys(nome);
    }

    private void setEmailInput(final String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UsuariosPageElement getUsuarios() {
        return usuarios;
    }
}
