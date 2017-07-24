package stepDefinition;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.*;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by assisjrs on 04/04/17.
 */
public class InserçãoDeUsuariosSteps {
    private WebDriver driver;
    private WebDriverWait wait;

    private String nome;
    private String email;

    @Before
    public void iniciarWebDriver() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 60);

        driver.get("http://localhost:9090/index.xhtml");
    }

    @Dado("^que já existe um usuário cadastrado$")
    public void queJáExisteUmUsuárioCadastrado() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        final String url = "jdbc:mysql://localhost:3306/selenium_exemplo?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final Connection connection =  DriverManager.getConnection(url, "root", "1234");
        connection.setAutoCommit(false);

        final Statement delete = connection.createStatement();

        delete.execute("delete from usuario");

        connection.commit();
        delete.close();

        final PreparedStatement update = connection.prepareStatement("insert into usuario (nome, email) values (?, ?)");

        update.setString(1, "Francisco de Assis");
        update.setString(2, "francisco.melo@concrete.com.br");

        update.execute();

        connection.commit();
        update.close();
        connection.close();
    }

    @Dado("^que o nome do usuário é \"([^\"]*)\" e o email \"([^\"]*)\"$")
    public void queEuTenhaONomeDoUsuárioComoEOEmail(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    @Quando("^o usuário é inserido$")
    public void euInsiroOUsuário() throws InterruptedException {
        final WebElement novoUsuario = driver.findElement(By.linkText("Novo Usuário"));
        novoUsuario.click();

        wait.until(visibilityOfElementLocated(id("usuarioForm")));

        final WebElement nomeInput = driver.findElement(xpath("//*[contains(@id,\"usuarioForm\")]/tbody/tr[2]/td[2]/input"));
        nomeInput.clear();

        wait.withTimeout(1, SECONDS);
        nomeInput.sendKeys(nome);

        wait.withTimeout(2, SECONDS);

        final WebElement emailInput = driver.findElement(xpath("//*[contains(@id,'usuarioForm')]/tbody/tr[3]/td[2]/input"));
        emailInput.clear();

        wait.withTimeout(1, SECONDS);
        emailInput.sendKeys(email);

        final WebElement salvar = driver.findElement(xpath("//*[contains(text(),'Salvar')]"));
        salvar.click();
    }

    @Então("^Devem existir (\\d+) usuários$")
    public void deveExibirNaLista(int usuariosCadastrados) {
        wait.until(visibilityOfElementLocated(xpath("//*[@id=\"dataTable_data\"]/tr")));
        wait.withTimeout(5, SECONDS);

        driver.get("http://localhost:9090/index.xhtml");

        final List<WebElement> usuarios = driver.findElements(xpath("//*[@id=\"dataTable_data\"]/tr"));
        assertThat(usuarios.size()).isEqualTo(usuariosCadastrados);
    }

    @After
    public void destruirWebDriver() {
        driver.close();
        driver.quit();
    }
}
