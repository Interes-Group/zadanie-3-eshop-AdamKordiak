package sk.stuba.fei.uim.oop.assignment3.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import sk.stuba.fei.uim.oop.assignment3.product.service.IproductService;
import sk.stuba.fei.uim.oop.assignment3.product.web.ProductAmountResponse;
import sk.stuba.fei.uim.oop.assignment3.product.web.ProductEditRequest;
import sk.stuba.fei.uim.oop.assignment3.product.web.ProductRequest;
import sk.stuba.fei.uim.oop.assignment3.product.web.ProductResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IproductService service;
    @GetMapping()
    public List<ProductResponse> getAllProducts() {
        return this.service.getAll()
                .stream()
                .map(product -> new ProductResponse(product))
                .collect(Collectors.toList());
    }    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest request) {
        Product createdProduct = this.service.createProduct(request);
        ProductResponse productResponse = new ProductResponse(createdProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable("id") Long id) {
        Product product = this.service.getProductById(id);
        ProductResponse productResponse = new ProductResponse(product);
        return productResponse;
    }    @PutMapping("/{id}")
    public ProductResponse putProduct(@PathVariable("id") Long id, @RequestBody ProductEditRequest editRequest) {
        Product updatedProduct = this.service.updateProduct(id, editRequest);
        ProductResponse productResponse = new ProductResponse(updatedProduct);
        return productResponse;
    }    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        this.service.deleteProduct(id);
    }
    @GetMapping("/{id}/amount")
    public ProductAmountResponse getProductAmount(@PathVariable("id") Long id){
       ProductAmountResponse productAmountResponse = new ProductAmountResponse(this.service.getProductById(id));
        return productAmountResponse ;}
    @PostMapping("/{id}/amount")
    public ProductAmountResponse addAmount(@PathVariable("id") Long id, @RequestBody ProductRequest request){
        ProductAmountResponse productAmountResponse = new ProductAmountResponse(this.service.addmoreAmount(id,request));
        return productAmountResponse;
    }
}
