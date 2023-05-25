package org.ubb.cs.test.features;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.ubb.cs.test.steps.AddProductSteps;
import org.ubb.cs.test.steps.LogInSteps;
import org.ubb.cs.test.steps.MainSteps;
import org.ubb.cs.test.steps.ProfileSteps;

import java.io.File;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = "add_test_data.csv")
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

    private String name;

    private String description;

    private Integer price;

    private String category;

    private String pathToPicture;

    @Test
    public void test_add_product() {
        webdriver.manage().window().maximize();
        this.mainSteps.open();
        this.mainSteps.go_to_log_in();
        this.logInSteps.enter_username_password("user1@email.com", "pass1");
        this.logInSteps.log_in();
        this.mainSteps.go_to_profile();
        this.profileSteps.add_product();
        this.addProductSteps.enter_name(name);
        this.addProductSteps.enter_description(description);
        this.addProductSteps.enter_price(Integer.toString(price));
        this.addProductSteps.enter_category(category);
        File file = new File(pathToPicture);
        this.addProductSteps.enter_picture(file.getAbsolutePath());
        this.addProductSteps.add_product();
    }
}
