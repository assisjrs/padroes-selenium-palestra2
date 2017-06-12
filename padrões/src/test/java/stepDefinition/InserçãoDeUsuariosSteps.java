package stepDefinition;

import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.dbunit.DatabaseUnitException;
import pageObjects.CadastroPage;

import java.net.MalformedURLException;
import java.sql.SQLException;

import static config.RunCukesTest.dbUnit;
import static config.RunCukesTest.webDriver;
import static org.openqa.selenium.support.PageFactory.initElements;

/**
 * Created by assisjrs on 04/04/17.
 */
public class InserçãoDeUsuariosSteps {
    private CadastroPage cadastroPage;

    @Before
    public void iniciarWebDriver() {
        cadastroPage = initElements(webDriver(), CadastroPage.class);
    }

    @Before
    public void criarMassaDeDados() throws DatabaseUnitException, SQLException,
                                           MalformedURLException {
        dbUnit().cleanInsert("InserçãoDeUsuarios.xml");
    }

    @Dado("^que eu tenha o nome do usuário como \"([^\"]*)\" e o email como \"([^\"]*)\"$")
    public void queEuTenhaONomeDoUsuárioComoEOEmail(final String nome, final String email) {
        cadastroPage.setNome(nome);
        cadastroPage.setEmail(email);
    }

    @Quando("^eu insiro o usuário$")
    public void euInsiroOUsuário() {
        cadastroPage.novoUsuario();
    }

    @Então("^Deve exibir (\\d+) usuários na lista$")
    public void deveExibirNaLista(int usuariosCadastrados) {
        cadastroPage.assertThat()
                    .quantidaDeUsuarios(usuariosCadastrados);
    }
}
