package org.ubb.cs.test.features;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.ubb.cs.test.steps.LogInSteps;
import org.ubb.cs.test.steps.MainSteps;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = "login_test_data.csv")
public class TestLogIn {

    @Managed(uniqueSession = true)
    private WebDriver webdriver;

    @Steps
    private MainSteps mainSteps;

    @Steps
    private LogInSteps logInSteps;

    private String username;

    private String password;

    private String valid;

    @Test
    public void test_log_in() {
        this.mainSteps.open();
        this.mainSteps.go_to_log_in();
        this.logInSteps.enter_username_password("user1@email.com", "pass1");
        this.logInSteps.log_in();
        if ("y".equals(this.valid)) {
            this.mainSteps.check_snackbar_message("Logged in successfully!");
        } else {
            this.logInSteps.check_snackbar_message("Invalid login credentials!");
        }
        this.mainSteps.log_out();
    }

}
