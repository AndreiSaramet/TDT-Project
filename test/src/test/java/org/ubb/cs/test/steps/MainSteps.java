package org.ubb.cs.test.steps;

import net.thucydides.core.annotations.Step;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.ubb.cs.test.pages.MainPage;

public class MainSteps {
    private MainPage mainPage;

    @Step
    public void open() {
        this.mainPage.open();
    }

    @Step
    public void go_to_log_in() {
        this.mainPage.log_in();
    }

    @Step
    public void check_snackbar_message(final String message) {
        MatcherAssert.assertThat(this.mainPage.get_snack_bar_message(), Matchers.containsString(message));
    }

    public void log_out() {
        this.mainPage.log_out();
    }
}