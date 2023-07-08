package ac.uk.bolton.ecommercebackend.service.impl;

/*
    @author DanujaV
    @created 7/8/23 - 9:21 PM   
*/

import ac.uk.bolton.ecommercebackend.dto.OrdersDTO;
import ac.uk.bolton.ecommercebackend.dto.common.ResponsePayload;
import ac.uk.bolton.ecommercebackend.entity.Orders;
import ac.uk.bolton.ecommercebackend.repository.OrdersRepository;
import ac.uk.bolton.ecommercebackend.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;
    @Override
    public ResponsePayload placeOrder(OrdersDTO ordersDTO) {
        return new ResponsePayload(
                HttpStatus.OK.getReasonPhrase(),
                ordersRepository.save(new Orders(
                        ordersDTO.qty(),
                        ordersDTO.price(),
                        ordersDTO.country(),
                        ordersDTO.deliveryAddress(),
                        ordersDTO.expectedDate()
                )),
                HttpStatus.OK
        );
    }
}
