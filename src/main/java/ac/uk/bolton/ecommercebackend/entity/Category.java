package ac.uk.bolton.ecommercebackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.GenerationType.AUTO;

@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column()
    private String name;
}
