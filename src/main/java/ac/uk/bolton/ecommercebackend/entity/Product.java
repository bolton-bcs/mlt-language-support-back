package ac.uk.bolton.ecommercebackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.GenerationType.AUTO;
@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column()
    private String name;

    @Column()
    private String categoryName;

    @Column()
    private String description;

    @Column()
    private String imageUrl;

    @Column()
    private Double price;

    @Column()
    private Integer qty;

    @Column()
    private Boolean status;



}
