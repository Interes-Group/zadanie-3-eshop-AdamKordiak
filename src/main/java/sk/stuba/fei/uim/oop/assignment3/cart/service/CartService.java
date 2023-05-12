package sk.stuba.fei.uim.oop.assignment3.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.cart.data.Cart;
import sk.stuba.fei.uim.oop.assignment3.cart.data.CartRepository;
import sk.stuba.fei.uim.oop.assignment3.exceptions.RequestException;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import sk.stuba.fei.uim.oop.assignment3.product.lists.ProductList;
import sk.stuba.fei.uim.oop.assignment3.product.lists.ProductListRepository;
import sk.stuba.fei.uim.oop.assignment3.product.lists.ProductListRequest;
import sk.stuba.fei.uim.oop.assignment3.product.service.IproductService;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;


@Service
public class CartService implements ICartService {

    final private CartRepository repository;
    final private IproductService iProductService;
    final private ProductListRepository productListRepository;


    @Autowired
    public CartService(CartRepository repository, IproductService iproductService , ProductListRepository productListRepository) {

        this.repository = repository;
        this.iProductService = iproductService;
        this.productListRepository = productListRepository;
    }
    @Override
    public Cart create() {
        Cart newCart = new Cart();
        return this.repository.save(newCart);
    }
    @Override
    public List<Cart> getAll() {
        return this.repository.findAll();
    }
    @Override
    public Cart findCartById(Long id) {
        return this.repository.findById(id).orElseThrow();
    }

    @Override
    public void deleteCart(Long id) {
        this.repository.findById(id).orElseThrow();
        this.repository.deleteById(id);
    }

    @Override
    public Cart addProductById(Long id, ProductListRequest productRequest) throws RequestException {
        Optional<Cart> cartId = this.repository.findById(id);
        Cart cart = cartId.orElseThrow();
        Optional<Product> product = Optional.ofNullable(this.iProductService.getById(productRequest.getProductId()));
        Product product1 = product.orElseThrow();

        if(cart.isPayed() || product1.getAmount() < productRequest.getAmount()){
            throw new RequestException();
        }
        List<ProductList> list = cart.getShoppingList();
        for (int i = 0; i < list.size(); i++) {
            ProductList productListIterator = list.get(i);
            if (productListIterator.getProductId().equals(productRequest.getProductId())) {
                int newAmount = productListIterator.getAmount() + productRequest.getAmount();
                productListIterator.setAmount(newAmount);
                this.iProductService.saveProduct(product1);
                return this.repository.save(cart);
            }
        }
        ProductList productList = new ProductList();

        this.productListRepository.save(productList);

        productList.setProductId(productRequest.getProductId());
        productList.setAmount(productRequest.getAmount());
        cart.getShoppingList().add(productList);

        int decreaseAmount = product1.getAmount() - productRequest.getAmount();
        product1.setAmount(decreaseAmount);

        this.iProductService.saveProduct(product1);

        return this.repository.save(cart);
    }

    @Override
    public String pay(Long id) throws RequestException {

        double price = 0;
        Cart cart = this.repository.findById(id).orElseThrow();

        if (cart.isPayed() == false) {
            List<ProductList> productList = cart.getShoppingList();
            for (int i = 0; i < productList.size(); i++) {
                ProductList List1 = productList.get(i);
                Optional<Product> product = Optional.ofNullable(this.iProductService.getById(List1.getProductId()));
                Product product1 = product.orElseThrow(new Supplier<RuntimeException>() {
                    public RuntimeException get() {
                        return new RuntimeException("Product not found.");
                    }
                });
                price += product1.getPrice() *List1.getAmount();
            }
            cart.setPayed(true);
            this.repository.save(cart);

            return String.valueOf(price);
        } else {
            throw new RequestException();
        }
    }
}
