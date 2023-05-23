package org.ubb.cs.test.steps;

import net.thucydides.core.annotations.Step;
import org.ubb.cs.test.pages.ProfilePage;

public class ProfileSteps {
    private ProfilePage profilePage;

    @Step
    public void add_product() {
        this.profilePage.add_product();
    }
}
