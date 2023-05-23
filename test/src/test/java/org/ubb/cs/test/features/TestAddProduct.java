package org.ubb.cs.test.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.ubb.cs.test.steps.AddProductSteps;
import org.ubb.cs.test.steps.LogInSteps;
import org.ubb.cs.test.steps.MainSteps;
import org.ubb.cs.test.steps.ProfileSteps;

@RunWith(SerenityRunner.class)
public final class TestAddProduct {
    @Managed(uniqueSession = true)
    public WebDriver webdriver;
    @Steps
    private MainSteps mainSteps;

    @Steps
    private LogInSteps logInSteps;

    @Steps
    private ProfileSteps profileSteps;

    @Steps
    private AddProductSteps addProductSteps;

    @Test
    public void test_add_product() {
        webdriver.manage().window().maximize();
        this.mainSteps.open();
        this.mainSteps.go_to_log_in();
        this.logInSteps.enter_username_password("user1@email.com", "pass1");
        this.logInSteps.log_in();
        this.mainSteps.go_to_profile();
        this.profileSteps.add_product();
        this.addProductSteps.enter_name("my product no 1");
        this.addProductSteps.enter_description("my product is by far the best product ever");
        this.addProductSteps.enter_price(Integer.toString(43));
        this.addProductSteps.enter_category("CLOTHES");
        this.addProductSteps.enter_picture("/Users/andreisaramet/Desktop/Unknown.jpg");
        this.addProductSteps.add_product();
    }
}
