package sk.stuba.fei.uim.oop.assignment3.product.datas;

import sk.stuba.fei.uim.oop.assignment3.product.web.ProductRequest;

import javax.persistence.*;

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;
    private Long amount;
    private String unit;
    private Long price;

    public Product(ProductRequest m) {

        this.name = m.getName();
        this.description = m.getDescription();
        this.unit = m.getUnit();
    }
}