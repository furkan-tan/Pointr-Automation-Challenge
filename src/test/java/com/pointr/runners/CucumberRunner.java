package com.pointr.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com/pointr/step_definitions",
        plugin = {
                "pretty",
                "html:target/report.html",
                "json:target/cucumber.json",
                "rerun:target/rerun.txt"
        },
        tags = "",
        dryRun = false
)

public class CucumberRunner {

}
