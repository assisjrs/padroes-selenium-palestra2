package work.assisjrs.selenium.exemplo;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Config.class)
@DatabaseSetup("CadastrarUsuario.xml")
public class Codigo2 {

    @Autowired
    private WebDriver driver;

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
