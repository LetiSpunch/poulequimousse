package fr.laPouleQuiMousse.models;

import fr.laPouleQuiMousse.models.Enums.EOrderState;
import fr.laPouleQuiMousse.models.Enums.EProductState;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EProductState state;

    private int cost;

    private int quantity;

    private String title;

    private String description;
}
