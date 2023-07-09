package ac.uk.bolton.ecommercebackend.service;

/*
    @author DanujaV
    @created 7/8/23 - 9:20 PM   
*/

import ac.uk.bolton.ecommercebackend.dto.OrdersDTO;
import ac.uk.bolton.ecommercebackend.dto.common.ResponsePayload;

public interface OrdersService {
    ResponsePayload placeOrder(OrdersDTO ordersDTO);

    ResponsePayload getAllApprovalOrders();
}
