package app;

import app.config.Initializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.faces.webapp.FacesServlet;

/**
 * Created by assis on 03/03/17.
 */
@Configuration
@ComponentScan(basePackages={"app.*"})
@EnableAutoConfiguration
public class Main extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Main.class, Initializer.class);
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean( new FacesServlet(), "*.xhtml");
    }
}
