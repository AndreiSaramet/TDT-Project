package org.ubb.cs.test.steps;

import net.thucydides.core.annotations.Step;
import org.ubb.cs.test.pages.AddProductPage;

public class AddProductSteps {
    private AddProductPage addProductPage;

    @Step
    public void enter_name(final String name) {
        this.addProductPage.enter_name(name);
    }

    @Step
    public void enter_description(final String description) {
        this.addProductPage.enter_description(description);
    }

    @Step
    public void enter_price(final String price) {
        this.addProductPage.enter_price(price);
    }

    @Step
    public void enter_category(final String category) {
        this.addProductPage.enter_category(category);
    }

    @Step
    public void enter_picture(final String path) {
        this.addProductPage.enter_picture(path);
    }

    @Step
    public void add_product() {
        this.addProductPage.add_product();
    }
}
