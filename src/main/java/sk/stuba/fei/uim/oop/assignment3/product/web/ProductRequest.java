package sk.stuba.fei.uim.oop.assignment3.product.web;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductRequest {

    private Long id;
    private String name;
    private String description;
    private int amount;
    private String unit;
    private Long price;
}
