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
            this.shoppingList = cart.getShoppinglist().stream().map(ProductListResponse::new).collect(Collectors.toList());
            this.payed = cart.isPayed();

    }
}
