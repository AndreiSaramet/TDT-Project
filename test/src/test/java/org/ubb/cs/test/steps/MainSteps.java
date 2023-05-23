package org.ubb.cs.test.steps;

import net.thucydides.core.annotations.Step;
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
}