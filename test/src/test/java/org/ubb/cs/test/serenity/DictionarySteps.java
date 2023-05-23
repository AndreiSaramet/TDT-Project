package org.ubb.cs.test.serenity;

import net.thucydides.core.annotations.Step;
import org.ubb.cs.test.pages.DictionaryPage;

public class DictionarySteps {
    DictionaryPage dictionaryPage;

    @Step
    public void go_to_home_page() {
        dictionaryPage.open();
    }

    @Step
    public void type(String keyword) {
        dictionaryPage.enter_keywords(keyword);
    }

    @Step
    public void start_search() {
        dictionaryPage.lookup_terms();
    }

    @Step
    public void look_for(String term) {
        type(term);
        start_search();
    }
}