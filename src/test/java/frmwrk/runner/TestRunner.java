package frmwrk.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/frmwrk/features",
plugin = {"json:target/jsonReports/cucumber-reports.json"},
glue = {"frmwrk.stepDef"},
//tags= {"@DeletePlace"},
strict = true,
monochrome = true
)
public class TestRunner {
	
}
