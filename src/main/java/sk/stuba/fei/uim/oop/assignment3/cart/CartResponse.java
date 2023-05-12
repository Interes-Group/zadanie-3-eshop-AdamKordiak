package sk.stuba.fei.uim.oop.assignment3.cart;


import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.cart.data.Cart;
import sk.stuba.fei.uim.oop.assignment3.product.lists.ProductListResponse;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CartResponse {

    private Long id;
    private List<ProductListResponse> shoppingList ;
    private boolean payed ;
    public CartResponse(Cart cart) {
            this.id = cart.getId();
            this.payed = cart.isPayed();

        this.shoppingList = cart.getShoppingList()
                .stream()
                .map(product -> new ProductListResponse(product))
                .collect(Collectors.toList());

    }
}
