package org.ubb.cs.test.steps;

import net.thucydides.core.annotations.Step;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.ubb.cs.test.pages.LogInPage;

public class LogInSteps {
    private LogInPage logInPage;

    @Step
    private void enter_username(final String username) {
        this.logInPage.enter_username(username);
    }

    @Step
    private void enter_password(final String password) {
        this.logInPage.enter_password(password);
    }

    @Step
    public void enter_username_password(final String username, final String password) {
        this.enter_username(username);
        this.enter_password(password);
    }

    @Step
    public void log_in() {
        this.logInPage.press_log_in();
    }

    @Step
    public void check_snackbar_message(final String message) {
        MatcherAssert.assertThat(this.logInPage.get_snack_bar_message(), Matchers.containsString(message));
    }
}
