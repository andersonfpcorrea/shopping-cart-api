package me.dio.sacola.model;

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double unitValue;
    @Builder Boolean available = true;
    @ManyToOne
    @JsonIgnore
    private Restaurant restaurant;
}
