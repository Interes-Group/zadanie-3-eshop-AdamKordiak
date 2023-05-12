package sk.stuba.fei.uim.oop.assignment3.cart.service;

import sk.stuba.fei.uim.oop.assignment3.cart.data.Cart;
import sk.stuba.fei.uim.oop.assignment3.exceptions.RequestException;
import sk.stuba.fei.uim.oop.assignment3.product.lists.ProductListRequest;

import java.util.List;

public interface ICartService {
    List<Cart> getAll();
    Cart create();
    Cart findCartById(Long id);
    void deleteCart(Long id);
    Cart addProductById(Long id, ProductListRequest request) throws RequestException;
    String pay(Long id) throws RequestException;

}
