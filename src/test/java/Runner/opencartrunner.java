package Runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="C:\\Users\\manoj.ms\\eclipse-workspace\\DemoProjectOpenCart\\src\\test\\java\\Features\\opencart.feature",glue="Step",plugin="html:report/report.html",tags="@order")
public class opencartrunner {

}
