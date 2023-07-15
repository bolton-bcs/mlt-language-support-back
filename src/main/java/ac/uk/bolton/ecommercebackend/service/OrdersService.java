package ac.uk.bolton.ecommercebackend.service;

/*
    @author DanujaV
    @created 7/8/23 - 9:20 PM   
*/

import ac.uk.bolton.ecommercebackend.dto.OrdersDTO;
import ac.uk.bolton.ecommercebackend.dto.common.ResponsePayload;
import ac.uk.bolton.ecommercebackend.entity.Orders;

import java.util.List;

public interface OrdersService {
    ResponsePayload placeOrder(OrdersDTO ordersDTO);

    List<Orders> getAllApprovalOrders();
}
