package com.springdata.learning.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.springdata.learning.domains.Person;

public class PersonTemplates implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(Person.class).addTemplate("son goku", new Rule() {{
            add("name", "Son Goku");
            add("email", "son.goku@dragonball.com");
        }});

        Fixture.of(Person.class).addTemplate("iron man", new Rule() {{
            add("name", "Tony Stark");
            add("email", "tony.stark@avengers.com");
        }});

        Fixture.of(Person.class).addTemplate("xena", new Rule() {{
            add("name", "Xena");
            add("email", "xena@warriorprincess.com");
        }});
    }
}
