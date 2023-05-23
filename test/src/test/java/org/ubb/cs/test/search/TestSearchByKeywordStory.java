package org.ubb.cs.test.search;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.ubb.cs.test.serenity.DefinitionSteps;
import org.ubb.cs.test.serenity.DictionarySteps;


@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = "test_data.csv")
public class TestSearchByKeywordStory {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    private DictionarySteps dictionarySteps;

    @Steps
    private DefinitionSteps definitionSteps;

    private String word;

    private String definition;

    @Test
    public void searching_by_keyword_apple_should_display_the_corresponding_article() {
        dictionarySteps.go_to_home_page();
        dictionarySteps.look_for(word);
        definitionSteps.check_definition(definition);

    }
} 