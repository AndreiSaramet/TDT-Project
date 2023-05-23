package org.ubb.cs.test.pages;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

@DefaultUrl("https://en.wiktionary.org/wiki/Wiktionary")
public class DictionaryPage extends PageObject {
    public void enter_keywords(String keyword) {
        this.find(By.name("search")).type(keyword);
    }

    public void lookup_terms() {
        this.find(By.name("go")).click();
    }
}