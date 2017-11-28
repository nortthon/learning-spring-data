package com.github.nortthon.learning.domains;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@EqualsAndHashCode(of = {"name", "email"})
@Document
public class Person {

    @Id
    private String id;

    private String name;

    private String email;
}
