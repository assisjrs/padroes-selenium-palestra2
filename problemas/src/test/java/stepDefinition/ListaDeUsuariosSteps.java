package stepDefinition;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.*;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.openqa.selenium.By.xpath;

/**
 * Created by assisjrs on 04/04/17.
 */
public class ListaDeUsuariosSteps {
    private WebDriver driver;
    private List<WebElement> usuarios;
    private String email;

    @Before
    public void iniciarWebDriver() {
        driver = new ChromeDriver();

        driver.get("http://localhost:9090/index.xhtml");
    }

    @Dado("^que o sistema tenha o usuário \"([^\"]*)\" cadastrado$")
    public void queOSistemaTenhaOUsuárioCadastrado(final String usuario) throws Throwable {
        Class.forName("com.mysql.cj.jdbc.Driver");

        final String url = "jdbc:mysql://localhost:3306/selenium_exemplo?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final Connection connection =  DriverManager.getConnection(url, "root", "1234");
        connection.setAutoCommit(false);

        final Statement delete = connection.createStatement();

        delete.execute("delete from usuario");
        connection.commit();
        delete.close();

        final PreparedStatement update = connection.prepareStatement("insert into usuario (nome, email) values (?, ?)");

        update.setString(1, "Francisco");
        update.setString(2, "francisco.melo@concrete.com.br");

        update.execute();

        connection.commit();

        update.close();
        connection.close();
    }

    @Quando("^eu visualizo a lista de usuários$")
    public void euVisualizoAListaDeUsuários() {
        driver.get("http://localhost:9090/index.xhtml");
        usuarios = driver.findElements(xpath("//*[@id=\"dataTable_data\"]/tr"));
    }

    @Então("^Devo reconhecer na lista o usuário \"([^\"]*)\"$")
    public void devoReconhecerNaListaOUsuário(final String usuario) {
        boolean encontrou = false;

        for (final WebElement tr: usuarios) {
            for (final WebElement td: tr.findElements(xpath("td[2]"))) {
                final String text = td.getText();

                if(usuario.equalsIgnoreCase(text))
                    encontrou = true;
            }
        }

        if(!encontrou)
            fail(String.format("Não foi possível encontrar o usuário: %s", usuario));
    }

    @Dado("^que o sistema deve ter o administrador sempre cadastrado$")
    public void queOSistemaDeveTerOAdministradorSempreCadastrado() throws Throwable {
        Class.forName("com.mysql.cj.jdbc.Driver");

        final String url = "jdbc:mysql://localhost:3306/selenium_exemplo?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final Connection connection =  DriverManager.getConnection(url, "root", "1234");
        connection.setAutoCommit(false);

        final Statement delete = connection.createStatement();

        delete.execute("delete from usuario");

        connection.commit();
        delete.close();

        final PreparedStatement update = connection.prepareStatement("insert into usuario (nome, email) values (?, ?)");

        update.setString(1, "Administrador");
        update.setString(2, "administrador@concrete.com.br");

        update.execute();

        connection.commit();
        update.close();
        connection.close();
    }

    @Quando("^eu consulto o banco de dados pelo email \"([^\"]*)\"$")
    public void euConsultoOBancoDeDadosPeloEmail(String email) {
        this.email = email;
    }

    @Então("^Devo encontrar no o email associado ao usuário \"([^\"]*)\"$")
    public void devoEncontrarNoOEmailAssociadoAoUsuário(final String francisco) throws Throwable {
        Class.forName("com.mysql.cj.jdbc.Driver");

        final String url = "jdbc:mysql://localhost:3306/selenium_exemplo?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final Connection connection =  DriverManager.getConnection(url, "root", "1234");

        final PreparedStatement query = connection.prepareStatement("select nome from usuario where email = ?");

        query.setString(1, email);

        final ResultSet resultSet = query.executeQuery();

        resultSet.first();
        final String nome = resultSet.getString(1);

        assertThat(nome, is(francisco));

        query.close();
        connection.close();
    }

    @After
    public void destruirWebDriver() {
        driver.close();
        driver.quit();
    }
}
