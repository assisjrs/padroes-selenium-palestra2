package stepDefinition;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import pageObjects.CadastroPage;

/**
 * Created by assisjrs on 04/04/17.
 */
@DatabaseSetup("CadastrarUsuario.xml")
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
}
