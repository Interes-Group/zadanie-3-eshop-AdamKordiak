package sk.stuba.fei.uim.oop.assignment3.product.lists;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductListRepository extends CrudRepository<ProductList, Long> {

}
