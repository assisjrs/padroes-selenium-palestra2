package app.config;

import org.springframework.boot.context.embedded.ServletContextInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by assis on 03/03/17.
 */
public class Initializer implements ServletContextInitializer {
    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        sc.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", "true");
        sc.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
        sc.setInitParameter("com.sun.faces.forceLoadConfiguration", "false");

        sc.addServlet("Faces Servlet", "javax.faces.webapp.FacesServlet");
        sc.setInitParameter("javax.faces.STATE_SAVING_METHOD", "client");

        sc.setInitParameter("primefaces.THEME", "bootstrap");
    }
}