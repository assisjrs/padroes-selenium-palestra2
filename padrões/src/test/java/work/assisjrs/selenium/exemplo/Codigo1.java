package work.assisjrs.selenium.exemplo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.*;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@RunWith(SpringRunner.class)
@SeleniumTestCase(url = "http://localhost:9090/index.xhtml",
                  webDriver = ChromeDriver.class)
public class Codigo1 {

    @Autowired
    private WebDriver driver;

    @Before
    public void criarMassaDeDados() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        final String url = "jdbc:mysql://localhost:3306/selenium_exemplo?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final Connection connection =  DriverManager.getConnection(url, "root", "1234");

        final Statement delete = connection.createStatement();

        delete.execute("delete from usuario");

        final PreparedStatement update = connection.prepareStatement("insert into usuario (nome, email) values (?, ?)");

        update.setString(1, "Francisco de Assis");
        update.setString(2, "francisco.melo@concrete.com.br");

        update.execute();
    }

    @Test
	public void DeveIncluirUmUsuario() {
        final WebElement novoUsuario = driver.findElement(By.linkText("Novo Usuário"));
        novoUsuario.click();

        final WebDriverWait wait = new WebDriverWait(driver, 60000);
        wait.until(visibilityOfElementLocated(id("usuarioForm")));

        final WebElement nomeInput = driver.findElement(xpath("//*[contains(@id,\"usuarioForm\")]/tbody/tr[2]/td[2]/input"));
        nomeInput.sendKeys("Assis Júnior");

        final WebElement emailInput = driver.findElement(xpath("//*[contains(@id,'usuarioForm')]/tbody/tr[3]/td[2]/input"));
        emailInput.sendKeys("assisjrs@gmail.com");

        final WebElement salvar = driver.findElement(xpath("//*[contains(text(),'Salvar')]"));
        salvar.click();

        wait.until(visibilityOfElementLocated(xpath("//*[@id=\"dataTable_data\"]/tr")));
        wait.withTimeout(5, SECONDS);

        final List<WebElement> usuarios = driver.findElements(xpath("//*[@id=\"dataTable_data\"]/tr"));
        assertThat(usuarios.size()).isEqualTo(2);
	}
}
