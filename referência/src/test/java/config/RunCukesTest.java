package config;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

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
public class RunCukesTest {}
