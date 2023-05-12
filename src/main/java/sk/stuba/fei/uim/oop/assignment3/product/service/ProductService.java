package sk.stuba.fei.uim.oop.assignment3.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import sk.stuba.fei.uim.oop.assignment3.product.data.ProductRepository;
import sk.stuba.fei.uim.oop.assignment3.product.web.ProductEditRequest;
import sk.stuba.fei.uim.oop.assignment3.product.web.ProductRequest;

import java.util.List;

@Service
public class ProductService  implements IproductService {
    final private ProductRepository repository;
    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<Product> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Product create(ProductRequest request) {
        Product newProduct = new Product();

        newProduct.setPrice(request.getPrice());
        newProduct.setName(request.getName());
        newProduct.setUnit(request.getUnit());
        newProduct.setDescription(request.getDescription());
        newProduct.setAmount(request.getAmount());

        return this.repository.save(newProduct);
    }

    @Override
    public Product getProductById(Long id) {
        return this.repository.findById(id).orElseThrow();
    }

    @Override
    public Product getAmountById(Long id) { return this.repository.findById(id).orElseThrow();}

    @Override
    public Product addmoreAmount(Long id,ProductRequest request) {
        Product newAmountProduct = this.repository.findById(id).orElseThrow();
        newAmountProduct.setAmount(newAmountProduct.getAmount()+request.getAmount());

        return this.repository.save(newAmountProduct);
    }
    @Override
    public void  deleteProduct(Long id) {
        this.repository.findById(id).orElseThrow();
        this.repository.deleteById(id);

    }

    @Override
    public Product updateProduct(Long id, ProductEditRequest editRequest) {

        Product productForUpdate = this.repository.findById(id).orElseThrow();

        if(editRequest.getName()==null && editRequest.getDescription()==null){
            return this.repository.save(productForUpdate);
        }
        else if(editRequest.getDescription()==null){
            productForUpdate.setName(editRequest.getName());
        }
        else if(editRequest.getName()==null ){
            productForUpdate.setDescription(editRequest.getDescription());
        }

        else {
            productForUpdate.setName(editRequest.getName());
            productForUpdate.setDescription(editRequest.getDescription());
        }

        return this.repository.save(productForUpdate);
    }

    @Override
    public Product getById(Long id) {return this.repository.findProductById(id);}
    @Override
    public Product save(Product product) {return this.repository.save(product);}

}
