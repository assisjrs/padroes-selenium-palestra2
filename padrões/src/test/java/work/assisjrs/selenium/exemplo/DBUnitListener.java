package work.assisjrs.selenium.exemplo;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.operation.DefaultDatabaseOperationLookup;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.File;
import java.sql.SQLException;

import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

/**
 * Created by assis on 15/03/17.
 */
public class DBUnitListener extends AbstractTestExecutionListener {
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }

    public DataSource dbUnitDatabaseConnection() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/selenium_exemplo?useSSL=false&nullCatalogMeansCurrent=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException {
        return new DataSourceTransactionManager(dbUnitDatabaseConnection());
    }

    @Override
    public void beforeTestClass(final TestContext testContext) throws Exception {
        final ApplicationContext context = testContext.getApplicationContext();

        if (context instanceof ConfigurableApplicationContext) {
            final DatabaseSetup annotation = findAnnotation(testContext.getTestClass(), DatabaseSetup.class);

            if (annotation == null) return;

            final FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
            builder.setColumnSensing(true);

            final IDataSet dataSet = builder.build(new File("src/test/resources/datasets/" + annotation.value()[0]));
            final DatabaseConnection databaseConnection = new DatabaseConnection(dbUnitDatabaseConnection().getConnection());

            new DefaultDatabaseOperationLookup().get(annotation.type()).execute(databaseConnection, dataSet);
        }
    }
}
