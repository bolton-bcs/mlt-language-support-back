package ac.uk.bolton.ecommercebackend.entity;

/*
    @author DanujaV
    @created 7/8/23 - 8:21 PM   
*/

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, name = "product_id")
    private Long productId;
    @Column(nullable = false)
    private Integer qty;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false, columnDefinition = "varchar(255)")
    private String country;
    @Column(name = "delivery_address", nullable = false, columnDefinition = "TEXT")
    private String deliveryAddress;
    @Column(name = "expected_date", nullable = false)
    private Date expectedDate;
    @Column(columnDefinition = "boolean default false")
    private Boolean status;

    public Orders(Long productId, Integer qty, Double price, String country, String deliveryAddress, Date expectedDate) {
        this.productId = productId;
        this.qty = qty;
        this.price = price;
        this.country = country;
        this.deliveryAddress = deliveryAddress;
        this.expectedDate = expectedDate;
    }
}
