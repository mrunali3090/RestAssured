package cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)

@CucumberOptions(features="src/test/java/features",plugin="json:target/jsonReports/cucumber-report.json",glue= {"stepDefinitions"}) //tests can be added to run tests based on tags 

public class TestRunner {
	
	//,tags={"@DeletePlace"}
// to create a json report-plugin="json:target/jsonReports/cucumber-report.json"	
//mvn compile mvn test and mvn verify 

}
