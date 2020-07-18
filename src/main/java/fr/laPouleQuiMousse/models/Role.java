package fr.laPouleQuiMousse.models;

import fr.laPouleQuiMousse.models.Enums.ERoleName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ERoleName name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ERoleName getName() {
        return name;
    }

    public void setName(ERoleName name) {
        this.name = name;
    }
}

