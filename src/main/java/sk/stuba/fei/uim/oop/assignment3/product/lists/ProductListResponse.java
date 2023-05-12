package sk.stuba.fei.uim.oop.assignment3.product.lists;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductListResponse {

    private int amount;
    private Long productId;

    public ProductListResponse(ProductList productList){
        this.amount = productList.getAmount();
        this.productId = productList.getProductId();

    }
}
