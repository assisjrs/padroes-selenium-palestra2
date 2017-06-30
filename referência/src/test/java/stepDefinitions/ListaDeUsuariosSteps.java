package stepDefinitions;

import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import dataObjects.CadastroData;
import org.springframework.beans.factory.annotation.Autowired;
import pageObjects.CadastroPage;

import static config.RunCukesTest.dbUnit;

/**
 * Created by assisjrs on 04/04/17.
 */
public class ListaDeUsuariosSteps {
    @Autowired
    private CadastroPage cadastroPage;

    //@Autowired
    private CadastroData cadastroData = new CadastroData();
    private String email;

    @Dado("^que o sistema tenha o usuário \"([^\"]*)\" cadastrado$")
    public void queOSistemaTenhaOUsuárioCadastrado(final String usuario) throws Throwable {
        dbUnit().cleanInsert("VisualizarTodosOsOutrosUsuarios.xml");
    }

    @Quando("^eu visualizo a lista de usuários$")
    public void euVisualizoAListaDeUsuários() {}

    @Então("^Devo reconhecer na lista o usuário \"([^\"]*)\"$")
    public void devoReconhecerNaListaOUsuário(final String usuario) {
        cadastroPage.assertThat()
                    .existe(usuario);
    }

    @Dado("^que o sistema deve ter o administrador sempre cadastrado$")
    public void queOSistemaDeveTerOAdministradorSempreCadastrado() throws Throwable {
        dbUnit().cleanInsert("EncontrarUsuarioPorEmail.xml");
    }

    @Quando("^eu consulto o banco de dados pelo email \"([^\"]*)\"$")
    public void euConsultoOBancoDeDadosPeloEmail(final String email) {
        this.email = email;
    }

    @Então("^Devo encontrar no o email associado ao usuário \"([^\"]*)\"$")
    public void devoEncontrarNoOEmailAssociadoAoUsuário(final String francisco) throws Throwable {
        cadastroData.assertThat()
                    .existe(francisco, email);
    }
}
