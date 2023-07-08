package ac.uk.bolton.ecommercebackend.repository;

/*
    @author DanujaV
    @created 7/8/23 - 9:29 PM   
*/

import ac.uk.bolton.ecommercebackend.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
