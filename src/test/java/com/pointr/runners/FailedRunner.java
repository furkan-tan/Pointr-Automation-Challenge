package com.pointr.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "@target/rerun.txt",
        glue = "com/pointr/step_definitions",
        plugin = {
                "html:target/failed-tests-rerun-report.html",
                "json:target/failed_tests-rerun-report.json",
        }

)
public class FailedRunner {
}
