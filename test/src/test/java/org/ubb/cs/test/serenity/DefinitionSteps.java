package org.ubb.cs.test.serenity;

import net.thucydides.core.annotations.Step;
import org.ubb.cs.test.pages.DefinitionPage;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

public class DefinitionSteps {
    DefinitionPage definitionPage;

    @Step
    public void check_definition(String definition) {
        assertThat(definitionPage.getDefinitions(), hasItem(containsString(definition)));
    }
}