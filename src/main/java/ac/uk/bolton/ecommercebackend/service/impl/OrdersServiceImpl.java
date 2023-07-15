package ac.uk.bolton.ecommercebackend.service.impl;

/*
    @author DanujaV
    @created 7/8/23 - 9:21 PM   
*/

import ac.uk.bolton.ecommercebackend.dto.OrdersDTO;
import ac.uk.bolton.ecommercebackend.dto.common.ResponsePayload;
import ac.uk.bolton.ecommercebackend.entity.Orders;
import ac.uk.bolton.ecommercebackend.entity.Product;
import ac.uk.bolton.ecommercebackend.repository.OrdersRepository;
import ac.uk.bolton.ecommercebackend.repository.ProductRepository;
import ac.uk.bolton.ecommercebackend.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;
    private final ProductRepository productRepository;
    @Override
    public ResponsePayload placeOrder(OrdersDTO ordersDTO) {
        Product proxyProduct = productRepository.getReferenceById(ordersDTO.productId());

        Product product = productRepository.findProductById(ordersDTO.productId());
        Orders orders = new Orders(
                ordersDTO.qty(),
                ordersDTO.price(),
                ordersDTO.country(),
                ordersDTO.deliveryAddress(),
                ordersDTO.expectedDate(),
                proxyProduct);
//        product.getOrdersList().add(orders);

        return new ResponsePayload(
                HttpStatus.OK.getReasonPhrase(),
                ordersRepository.save(orders),
                HttpStatus.OK
        );
    }

    @Override
    public List<Orders> getAllApprovalOrders() {
//        return new ResponsePayload(
//                HttpStatus.OK.getReasonPhrase(),
//productRepository.findAll(),
                return ordersRepository.getAllApprovalOrders();
//                ordersRepository.findAll(),
//                HttpStatus.OK
//        );
    }
}
