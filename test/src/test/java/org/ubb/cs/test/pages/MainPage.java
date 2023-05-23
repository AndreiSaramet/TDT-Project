package org.ubb.cs.test.pages;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

@DefaultUrl("http://localhost:4200")
public class MainPage extends PageObject {
    public void log_in() {
        this.find(By.cssSelector(".user-icon")).click();
        this.find(By.cssSelector(".mat-mdc-menu-item")).click();
    }
}