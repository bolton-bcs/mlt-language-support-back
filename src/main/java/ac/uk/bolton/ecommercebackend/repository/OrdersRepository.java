package ac.uk.bolton.ecommercebackend.repository;

/*
    @author DanujaV
    @created 7/8/23 - 9:29 PM   
*/

import ac.uk.bolton.ecommercebackend.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    @Query("select o from Orders o where o.status = false")
    List<Orders> getAllApprovalOrders();
}
