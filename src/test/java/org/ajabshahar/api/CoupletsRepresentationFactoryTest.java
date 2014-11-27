package org.ajabshahar.api;

import org.ajabshahar.platform.models.Category;
import org.ajabshahar.platform.models.Couplet;
import org.ajabshahar.platform.models.PersonDetails;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class CoupletsRepresentationFactoryTest {
    private List<Couplet> couplets;
    private CoupletsRepresentationFactory coupletsRepresentationFactory;

    @Before
    public void setUp() throws Exception {
        coupletsRepresentationFactory = new CoupletsRepresentationFactory();
        couplets = new ArrayList<>();

        Couplet couplet = new Couplet();
        couplet.setId(1);
        couplet.setEnglishTranslationText("english translation text");
        couplet.setEnglishTransliterationText("englishTransliteration text");

        PersonDetails poet = new PersonDetails();
        poet.setFirstName("Roshik");
        couplet.setPoet(poet);

        Category category = new Category();
        category.setCategoryType("Couplet");
        couplet.setCategory(category);

        couplets.add(couplet);

    }

    @Test
    public void shouldGetCoupletsRepresentation() throws Exception {
       
        CoupletsRepresentation coupletsRepresentation = coupletsRepresentationFactory.create(couplets);
        assertThat(coupletsRepresentation.getCouplets().size(), IsEqual.equalTo(1));
        assertThat(coupletsRepresentation.getCouplets().get(0).getId(), IsEqual.equalTo(1L));
        assertThat(coupletsRepresentation.getCouplets().get(0).getEnglishTranslationText(), IsEqual.equalTo("english translation text"));
        assertThat(coupletsRepresentation.getCouplets().get(0).getEnglishTransliterationText(), IsEqual.equalTo("englishTransliteration text"));
        assertThat(coupletsRepresentation.getCouplets().get(0).getPoetName(), IsEqual.equalTo("Roshik"));
        assertThat(coupletsRepresentation.getCouplets().get(0).getCategory(), IsEqual.equalTo("Couplet"));

    }
}