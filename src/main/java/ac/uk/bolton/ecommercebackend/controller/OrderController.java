package ac.uk.bolton.ecommercebackend.controller;

/*
    @author DanujaV
    @created 7/8/23 - 8:14 PM   
*/

import ac.uk.bolton.ecommercebackend.dto.OrdersDTO;
import ac.uk.bolton.ecommercebackend.dto.common.ResponsePayload;
import ac.uk.bolton.ecommercebackend.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrdersService ordersService;
    @PostMapping("/place")
    public ResponseEntity<ResponsePayload> placeOrder(@RequestBody OrdersDTO ordersDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.placeOrder(ordersDTO));
    }
}
