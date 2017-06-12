package dataObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CadastroData {

    public CadastroDataAssert assertThat() {
        return new CadastroDataAssert(this);
    }

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
}
