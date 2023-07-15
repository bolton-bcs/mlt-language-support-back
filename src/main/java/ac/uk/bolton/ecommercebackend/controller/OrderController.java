package ac.uk.bolton.ecommercebackend.controller;

/*
    @author DanujaV
    @created 7/8/23 - 8:14 PM   
*/

import ac.uk.bolton.ecommercebackend.dto.OrdersDTO;
import ac.uk.bolton.ecommercebackend.dto.common.Response;
import ac.uk.bolton.ecommercebackend.dto.common.ResponsePayload;
import ac.uk.bolton.ecommercebackend.entity.Orders;
import ac.uk.bolton.ecommercebackend.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrdersService ordersService;

    @PostMapping("/place")
    public ResponseEntity<ResponsePayload> placeOrder(@RequestBody OrdersDTO ordersDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.placeOrder(ordersDTO));
    }

    @GetMapping("/pending")
    public List<Response> getAllApprovalOrders() {
        List<Response> responses = new ArrayList<>();

        for (Orders orders : ordersService.getAllApprovalOrders()) {
            responses.add(
                    new Response(
                            orders.getId(),
                            orders.getQty(),
                            orders.getPrice(),
                            orders.getCountry(),
                            orders.getDeliveryAddress(),
                            orders.getExpectedDate(),
                            orders.getStatus(),
                            orders.getProduct().getId(),
                            orders.getProduct().getName(),
                            orders.getProduct().getDescription()
                    )
            );
        }
        return responses;

//        System.out.println(ordersList.get(1).getProduct());
//        return ResponseEntity.status(HttpStatus.OK).body(ordersService.getAllApprovalOrders());
    }
}
