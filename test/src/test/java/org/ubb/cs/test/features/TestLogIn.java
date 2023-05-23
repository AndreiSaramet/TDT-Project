package org.ubb.cs.test.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.ubb.cs.test.steps.LogInSteps;
import org.ubb.cs.test.steps.MainSteps;

@RunWith(SerenityRunner.class)
public class TestLogIn {

    @Managed(uniqueSession = true)
    private WebDriver webdriver;

    @Steps
    private MainSteps mainSteps;

    @Steps
    private LogInSteps logInSteps;

    @Test
    public void test_log_in() {
        this.mainSteps.open();
        this.mainSteps.go_to_log_in();
        this.logInSteps.enter_username_password("user1@email.com", "pass1");
        this.logInSteps.log_in();
    }

}
