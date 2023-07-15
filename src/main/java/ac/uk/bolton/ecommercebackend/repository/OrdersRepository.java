package ac.uk.bolton.ecommercebackend.repository;

/*
    @author DanujaV
    @created 7/8/23 - 9:29 PM   
*/

import ac.uk.bolton.ecommercebackend.dto.OrderPendingDTO;
import ac.uk.bolton.ecommercebackend.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    @Query("select \n" +
            "  o.id as order_id, \n" +
            "  p.id as product_id, \n" +
            "  p.name, \n" +
            "  p.description, \n" +
            "  o.qty, \n" +
            "  o.price, \n" +
            "  o.country, \n" +
            "  o.deliveryAddress, \n" +
            "  o.expectedDate, \n" +
            "  o.status \n" +
            "from \n" +
            "  Product p \n" +
            "  inner join Orders o on p.id = o.id")
    List<OrderPendingDTO> getAllApprovalOrders();
}
