package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.cart.data.Cart;
import sk.stuba.fei.uim.oop.assignment3.cart.service.ICartService;
import sk.stuba.fei.uim.oop.assignment3.exceptions.RequestException;
import sk.stuba.fei.uim.oop.assignment3.product.lists.ProductListRequest;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService service;
    @PostMapping()
    public ResponseEntity<CartResponse> createCart() {
        Cart cart = this.service.create();
        CartResponse cartResponse = new CartResponse(cart);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartResponse);
    }
    @GetMapping()
    public List<CartResponse> getCart() {
        return this.service.getAll()
                .stream()
                .map(cart -> new CartResponse(cart))
                .collect(Collectors.toList());
    }
    @GetMapping("{id}")
    public CartResponse getCartById(@PathVariable("id") Long id){
        CartResponse cartResponse = new CartResponse(this.service.findCartById(id));
        return cartResponse;
    }
    @DeleteMapping("{id}")
    public void deleteCardById(@PathVariable("id") Long id){
        this.service.deleteCart(id);
    }
    @PostMapping("{id}/add")
    public CartResponse addProduct(@PathVariable("id") Long id ,@RequestBody ProductListRequest request) throws RequestException {
        CartResponse cartResponse = new CartResponse(this.service.addProductById(id,request));
        return cartResponse ;
    }
    @GetMapping("{id}/pay")
    public String payForShoppingCart(@PathVariable("id") Long id)throws RequestException {
        return this.service.pay(id);
    }
}
