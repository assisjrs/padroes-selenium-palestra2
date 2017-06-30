package config;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class DBUnitHooks implements BeanFactoryAware {
    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;

        System.out.println("beanFactory");
    }

    @Before("@DatabaseSetup")
    public void before(Scenario s) {
        System.out.println(">>@DatabaseSetup: " + s.getSourceTagNames());
    }

    @After("@DatabaseSetup")
    public void after(Scenario s) {
        System.out.println("<<@DatabaseSetup: " + s.getSourceTagNames());
    }
}
