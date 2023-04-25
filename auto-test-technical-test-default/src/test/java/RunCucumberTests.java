/*
 * Copyright © 2023 Mirada Medical Ltd.
 * All Rights Reserved.
 */

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

/**
 * Test runner for Cucumber tests.
 *
 * @author lottie.thomas
 */
@RunWith( Cucumber.class )//run
@CucumberOptions(
   features =  "src/test/resources",
        glue = "cucumber/steps",//glue helps Cucumber to locate the step definition file
        //report
        plugin = {"pretty", "html:target/cucumber-report/cucumber.html",
                "com.cucumber.listener.ExtentCucumberFormatter:target/Extent_Reports/report.html",
                "json:target/RunCuke/cucumber.json"},
        // tags = {"@Regression"}
        tags = "@Regression"

)
public class  RunCucumberTests {

    @AfterClass
    public static void setUp() {


    }
}




