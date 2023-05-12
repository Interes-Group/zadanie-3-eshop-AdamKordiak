package sk.stuba.fei.uim.oop.assignment3.product.service;

import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import sk.stuba.fei.uim.oop.assignment3.product.web.ProductEditRequest;
import sk.stuba.fei.uim.oop.assignment3.product.web.ProductRequest;

import java.util.List;

public interface IproductService {

    List<Product> getAll();
    Product create(ProductRequest request);
    Product getProductById(Long id);
    Product getAmountById(Long id);
    Product addmoreAmount(Long id, ProductRequest request);
    Product updateProduct(Long id , ProductEditRequest editRequest);
    Product getById(Long id);
    Product save(Product product);
    void deleteProduct(Long id);
}
