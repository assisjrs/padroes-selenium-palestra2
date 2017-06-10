package work.assisjrs.selenium.exemplo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.test.context.TestExecutionListeners;

import java.lang.annotation.*;

import static org.springframework.test.context.TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS;

/**
 * Created by assis on 15/03/17.
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@TestExecutionListeners(listeners = {SeleniumListener.class, DBUnitListener.class}, mergeMode = MERGE_WITH_DEFAULTS)
public @interface SeleniumTestCase {
    Class<? extends WebDriver> webDriver() default ChromeDriver.class;
    String url() default "";
    Class<?> pageObject() default Object.class;
}
