package sk.stuba.fei.uim.oop.assignment3.product.web;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductAmountResponse {
    private int  amount;
    public ProductAmountResponse(Product product) {
        this.amount = product.getAmount();
    }
}
