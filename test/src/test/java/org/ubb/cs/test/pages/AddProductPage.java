package org.ubb.cs.test.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public final class AddProductPage extends PageObject {
    public void enter_name(final String name) {
        this.find(By.id("name-input")).sendKeys(name);
    }

    public void enter_description(final String description) {
        this.find(By.id("description-input")).sendKeys(description);
    }

    public void enter_price(final String price) {
        this.find(By.id("price-input")).sendKeys(price);
    }

    public void enter_category(final String category) {
        this.find(By.id("category-input")).sendKeys(category);
    }

    public void enter_picture(final String path) {
        this.find(By.id("picture-input")).sendKeys(path);
    }

    public void add_product() {
        this.find(By.id("add-product-button")).click();
    }

}
