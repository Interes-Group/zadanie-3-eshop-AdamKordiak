package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.cart.service.ICartService;
import sk.stuba.fei.uim.oop.assignment3.exceptions.BadRequestException;
import sk.stuba.fei.uim.oop.assignment3.product.lists.ProductListRequest;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService service;
    @PostMapping()
    public ResponseEntity<CartResponse> createCart(){
        return new ResponseEntity<>(new CartResponse(this.service.create()), HttpStatus.CREATED);
    }
    @GetMapping()
    public List<CartResponse> GetCart(){
        return  this.service.getAll().stream().map(CartResponse::new).collect(Collectors.toList());
    }
    @GetMapping("{id}")
    public CartResponse getCartById(@PathVariable("id") Long id){
        return new CartResponse(this.service.findCartById(id));
    }
    @DeleteMapping("{id}")
    public void deleteCardById(@PathVariable("id") Long id){
        this.service.deleteCart(id);
    }
    @PostMapping("{id}/add")
    public CartResponse addProduct(@PathVariable("id") Long id ,@RequestBody ProductListRequest request) throws BadRequestException {
        return new CartResponse(this.service.addProductById(id,request));
    }
    @GetMapping("{id}/pay")
    public String payForShoppingCart(@PathVariable("id") Long id)throws  BadRequestException{
        return this.service.pay(id);
    }
}
