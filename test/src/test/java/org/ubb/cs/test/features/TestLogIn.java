package org.ubb.cs.test.features;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.ubb.cs.test.steps.LogInSteps;
import org.ubb.cs.test.steps.MainSteps;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = "login_test_data.csv")
public final class TestLogIn {
    @Managed(uniqueSession = true)
    public WebDriver webdriver;
    @Steps
    private MainSteps mainSteps;

    @Steps
    private LogInSteps logInSteps;

    private String username;

    private String password;

    private String valid;

    @Test
    @Pending
    public void test_log_in() {
        this.mainSteps.open();
        this.mainSteps.go_to_log_in();
        this.logInSteps.enter_username_password(this.username, this.password);
        this.logInSteps.log_in();
        if ("y".equals(this.valid)) {
            this.mainSteps.check_snackbar_message("Logged in successfully!");
        } else {
            this.logInSteps.check_snackbar_message("Invalid login credentials!");
        }
    }
}
