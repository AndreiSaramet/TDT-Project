package org.ubb.cs.test.pages;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

@DefaultUrl("http://localhost:4200")
public class MainPage extends PageObject {
    public void log_in() {
        this.find(By.id("account-button")).click();
        this.find(By.id("log-in-button")).click();
    }

    public String get_snack_bar_message() {
        return this.find(By.className("mat-mdc-snack-bar-label")).getTextContent();
    }
}