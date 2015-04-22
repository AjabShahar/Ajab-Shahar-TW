package org.ajabshahar.factory;

import org.ajabshahar.api.CoupletsRepresentation;
import org.ajabshahar.api.CoupletsRepresentationFactory;
import org.ajabshahar.platform.models.Category;
import org.ajabshahar.platform.models.Couplet;
import org.ajabshahar.platform.models.PersonDetails;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;

public class CoupletsRepresentationFactoryTest {
    private Set<Couplet> couplets;
    private CoupletsRepresentationFactory coupletsRepresentationFactory;

    @Before
    public void setUp() throws Exception {
        coupletsRepresentationFactory = new CoupletsRepresentationFactory();
        couplets = new LinkedHashSet<>();

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
        assertThat(coupletsRepresentation.getCouplets().iterator().next().getId(), IsEqual.equalTo(1L));
        assertThat(coupletsRepresentation.getCouplets().iterator().next().getEnglishTranslationText(), IsEqual.equalTo("english translation text"));
        assertThat(coupletsRepresentation.getCouplets().iterator().next().getEnglishTransliterationText(), IsEqual.equalTo("englishTransliteration text"));
        assertThat(coupletsRepresentation.getCouplets().iterator().next().getPoetName(), IsEqual.equalTo("Roshik"));
        assertThat(coupletsRepresentation.getCouplets().iterator().next().getCategory(), IsEqual.equalTo("Couplet"));

    }
}