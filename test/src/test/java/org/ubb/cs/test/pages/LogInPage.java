package org.ubb.cs.test.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public final class LogInPage extends PageObject {
    public void enter_username(final String userName) {
        this.find(By.id("mat-input-1")).sendKeys(userName);
    }

    public void enter_password(final String password) {
        this.find(By.id("mat-input-2")).sendKeys(password);
    }

    public void press_log_in() {
        this.find(By.id("login-button")).click();
    }

    public String get_snack_bar_message() {
        return this.find(By.className("mat-mdc-snack-bar-label")).getTextContent();
    }
}
