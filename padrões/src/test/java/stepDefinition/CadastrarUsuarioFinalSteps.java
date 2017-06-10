package stepDefinition;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import config.Config;
import work.assisjrs.selenium.exemplo.SeleniumTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import pageObjects.CadastroPage;

/**
 * Created by assisjrs on 04/04/17.
 */
@ContextConfiguration(classes = Config.class)
@RunWith(SpringRunner.class)
@DatabaseSetup("CadastrarUsuario.xml")
@SeleniumTestCase(webDriver = ChromeDriver.class,
                  url = "http://localhost:9090/index.xhtml",
                  pageObject = CadastroPage.class)
public class CadastrarUsuarioFinalSteps {
    @Autowired
    private CadastroPage cadastroPage;

    @Dado("^que eu tenha o nome do usuário como \"([^\"]*)\" e o email como \"([^\"]*)\"$")
    public void queEuTenhaONomeDoUsuárioComoEOEmail(String nome, String email) {
        cadastroPage.setNome(nome);
        cadastroPage.setEmail(email);
    }

    @Quando("^eu insiro o usuário$")
    public void euInsiroOUsuário() throws InterruptedException {
        cadastroPage.novoUsuario();
    }

    @Então("^Deve exibir (\\d+) usuários na lista$")
    public void deveExibirNaLista(int usuariosCadastrados) {
        cadastroPage.assertThat()
                    .quantidaDeUsuarios(usuariosCadastrados);
    }

    @Test
    public void test(){}
}
