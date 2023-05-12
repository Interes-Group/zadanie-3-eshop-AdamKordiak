package sk.stuba.fei.uim.oop.assignment3.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public List<ProductResponse> getAllProducts(){ return this.service.getAll().stream().map(ProductResponse::new).collect(Collectors.toList());}
    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest request){ return new ResponseEntity<>(new ProductResponse(this.service.create(request)), HttpStatus.CREATED);}
    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable("id") Long id){ return new ProductResponse(this.service.getProductById(id));}
    @PutMapping("/{id}")
    public ProductResponse putProduct(@PathVariable("id") Long id , @RequestBody ProductEditRequest editRequest){ return  new ProductResponse(this.service.updateProduct(id,editRequest));}
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        this.service.deleteProduct(id);
    }
    @GetMapping("/{id}/amount")
    public ProductAmountResponse getProductAmount(@PathVariable("id") Long id){ return new ProductAmountResponse(this.service.getProductById(id));}
    @PostMapping("/{id}/amount")
    public ProductAmountResponse addAmount(@PathVariable("id") Long id, @RequestBody ProductRequest request){ return new ProductAmountResponse(this.service.addmoreAmount(id,request));}
}
