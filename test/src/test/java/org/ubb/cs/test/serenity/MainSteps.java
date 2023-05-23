package org.ubb.cs.test.serenity;

import net.thucydides.core.annotations.Step;
import org.ubb.cs.test.pages.MainPage;

public class MainSteps {
    MainPage mainPage;

    @Step
    public void open() {
        mainPage.open();
    }

}