package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import static org.openqa.selenium.support.PageFactory.initElements;

@Component
public class PagePostBeanProcessor implements BeanPostProcessor {
    @Autowired
    private WebDriver driver;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean.getClass().isAnnotationPresent(Page.class)) {
            final WaitMeFieldDecorator decorator = new WaitMeFieldDecorator(new DefaultElementLocatorFactory(driver), driver);

            initElements(decorator, bean);
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
