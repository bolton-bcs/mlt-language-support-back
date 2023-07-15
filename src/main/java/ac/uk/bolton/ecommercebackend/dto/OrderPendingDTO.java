package ac.uk.bolton.ecommercebackend.dto;

/*
    @author DanujaV
    @created 7/15/23 - 9:07 PM   
*/

import java.util.Date;

public record OrderPendingDTO(
        Long orderId,
        Long productId,
        String name,
        String description,
        Integer qty,
        Double price,
        String country,
        String deliveryAddress,
        Date expectedDate,
        Boolean status) {
}
