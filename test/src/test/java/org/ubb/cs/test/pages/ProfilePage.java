package org.ubb.cs.test.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public final class ProfilePage extends PageObject {
    public void add_product() {
        this.find(By.id("add-button")).click();
    }
}
