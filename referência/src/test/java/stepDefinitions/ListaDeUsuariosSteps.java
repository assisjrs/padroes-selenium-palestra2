package stepDefinitions;

import config.DBUnit;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.assertj.db.type.Request;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import pageObjects.CadastroPage;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;

/**
 * Created by assisjrs on 04/04/17.
 */
public class ListaDeUsuariosSteps {
    @Autowired
    private CadastroPage cadastroPage;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DBUnit dbUnit;

    private Request usuarios;

    @Dado("^que o sistema tenha o usuário \"([^\"]*)\" cadastrado$")
    public void queOSistemaTenhaOUsuárioCadastrado(final String usuario) throws Throwable {
        dbUnit.cleanInsert("VisualizarTodosOsOutrosUsuarios.xml");
    }

    @Quando("^eu visualizo a lista de usuários$")
    public void euVisualizoAListaDeUsuários() {}

    @Então("^Devo reconhecer na lista o usuário \"([^\"]*)\"$")
    public void devoReconhecerNaListaOUsuário(final String usuario) {
        final WebElement usuarioNaTabela = cadastroPage.getUsuarios().getRowBy(usuario);

        assertThat(usuarioNaTabela).isNotNull();
    }

    @Dado("^que o sistema deve ter o administrador sempre cadastrado$")
    public void queOSistemaDeveTerOAdministradorSempreCadastrado() throws Throwable {
        dbUnit.cleanInsert("EncontrarUsuarioPorEmail.xml");
    }

    @Quando("^eu consulto o banco de dados pelo email \"([^\"]*)\"$")
    public void euConsultoOBancoDeDadosPeloEmail(final String email) {
        usuarios = new Request(dataSource, "select nome from usuario where email = ?");
        usuarios.setParameters(email);
    }

    @Então("^Devo encontrar no o email associado ao usuário \"([^\"]*)\"$")
    public void devoEncontrarNoOEmailAssociadoAoUsuário(final String usuario) {
        assertThat(usuarios).column("nome")//
                            .value().isEqualTo(usuario);
    }
}
