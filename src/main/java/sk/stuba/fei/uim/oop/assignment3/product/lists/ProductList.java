package sk.stuba.fei.uim.oop.assignment3.product.lists;

import javax.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class ProductList  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long productId;
    private int amount;
}
