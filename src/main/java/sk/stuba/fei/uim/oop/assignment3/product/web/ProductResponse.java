package sk.stuba.fei.uim.oop.assignment3.product.web;

import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private int amount;
    private Long price;
    private String unit;

    public ProductResponse(Product product) {
        if(product!=null) {
            this.id = product.getId();
            this.name = product.getName();
            this.description = product.getDescription();
            this.amount = product.getAmount();
            this.price = product.getPrice();
            this.unit = product.getUnit();
        }
    }
}
