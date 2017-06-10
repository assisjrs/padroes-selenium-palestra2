package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by assis on 06/03/17.
 */
@Configuration
@ComponentScan({"config", "stepDefinition"})
public class Config {
}
