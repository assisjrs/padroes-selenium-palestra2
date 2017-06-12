package config;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by assis on 24/02/17.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber"},
        glue = "stepDefinition",
        features = "src/test/java/features",
        snippets = SnippetType.CAMELCASE,
        strict = true)
public class RunCukesTest {
    @BeforeClass
    public static void criarBancoDeDados() throws SQLException, ClassNotFoundException, IOException {
        final FileReader file = new FileReader("./src/test/resources/criarBanco.sql");
        final BufferedReader buffer = new BufferedReader(file);

        Class.forName("com.mysql.cj.jdbc.Driver");

        final String url = "jdbc:mysql://localhost:3306/selenium_exemplo?useSSL=false";
        final Connection connection =  DriverManager.getConnection(url, "root", "1234");

        final Statement statement = connection.createStatement();

        String line = buffer.readLine();
        buffer.readLine();

        while (line != null) {
            statement.addBatch(line);

            line = buffer.readLine();
        }

        file.close();

        statement.executeBatch();

        statement.close();
        connection.close();
    }

    @AfterClass
    public static void destruirBancoDeDados() throws SQLException, ClassNotFoundException, IOException {
        final FileReader file = new FileReader("./src/test/resources/destruirBanco.sql");
        final BufferedReader buffer = new BufferedReader(file);

        Class.forName("com.mysql.cj.jdbc.Driver");

        final String url = "jdbc:mysql://localhost:3306/selenium_exemplo?useSSL=false";
        final Connection connection =  DriverManager.getConnection(url, "root", "1234");

        final Statement statement = connection.createStatement();

        String line = buffer.readLine();
        buffer.readLine();

        while (line != null) {
            statement.addBatch(line);

            line = buffer.readLine();
        }

        file.close();

        statement.executeBatch();

        statement.close();
        connection.close();
    }
}
