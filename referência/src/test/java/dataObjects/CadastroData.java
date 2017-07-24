package dataObjects;

import config.DataObject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;
import work.assisjrs.referencia.Usuario;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//@DataObject
//@Repository
//@EnableJpaRepositories(entityManagerFactoryRef = "DbEntityManagerFactory", transactionManagerRef = "DbTransactionManager")
public interface CadastroData extends CrudRepository<Usuario, Long> {
    //@Query(nativeQuery = true, value = "select nome from usuario where email = :email")
    //String findByEmail(@Param("email") final String email);


    /*
    public String getUsuarioPor(final String email) throws Throwable {
        Class.forName("com.mysql.cj.jdbc.Driver");

        final String url = "jdbc:mysql://localhost:3306/selenium_exemplo?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final Connection connection =  DriverManager.getConnection(url, "root", "1234");

        final PreparedStatement query = connection.prepareStatement("select nome from usuario where email = ?");

        query.setString(1, email);

        final ResultSet resultSet = query.executeQuery();

        resultSet.first();
        final String usuario = resultSet.getString(1);

        query.close();
        connection.close();

        return usuario;
    }

    public CadastroDataAssert assertThat() {
        return new CadastroDataAssert(this);
    }
    */
}
