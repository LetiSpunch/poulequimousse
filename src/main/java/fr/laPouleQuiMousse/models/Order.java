package fr.laPouleQuiMousse.models;

import fr.laPouleQuiMousse.models.Enums.EOrderState;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EOrderState state;

    @ManyToMany
    @JoinTable(name = "order_product", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    @NotEmpty
    private List<Product> products = new ArrayList<>();

    private int totalCost;

    @DateTimeFormat
    private Date orderDate;

    @OneToOne
    private User customer;
}
