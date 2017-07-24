package pageObjects;

import config.Page;
import config.WaitMe;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.By.xpath;
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

        novoUsuarioButton.click();

        setNomeInput(nome);

        setEmailInput(email);

        salvar.click();
    }

    private void setNomeInput(final String nome) {
        nomeInput.clear();

        wait.withTimeout(1, SECONDS);
        nomeInput.sendKeys(nome);
    }

    private void setEmailInput(final String email) {
        emailInput.clear();

        wait.withTimeout(1, SECONDS);
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

    public WebElement getRowBy(final String usuario) {
        for (final WebElement tr: usuarios) {
            for (final WebElement td: tr.findElements(xpath("td[2]"))) {
                final String text = td.getText();

                if(usuario.equalsIgnoreCase(text))
                    return td;
            }
        }

        return null;
    }
}
