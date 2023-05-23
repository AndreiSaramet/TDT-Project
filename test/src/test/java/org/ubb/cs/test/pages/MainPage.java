package org.ubb.cs.test.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("http://localhost:4200")
public final class MainPage extends PageObject {
    @FindBy(id = "account-button")
    private WebElementFacade accountButton;

    public void log_in() {
        this.accountButton.click();
        this.find(By.id("log-in-button")).click();
    }

    public void go_to_profile() {
        this.accountButton.click();
        this.find(By.id("profile-button")).click();
    }

    public String get_snack_bar_message() {
        return this.find(By.className("mat-mdc-snack-bar-label")).getTextContent();
    }
}