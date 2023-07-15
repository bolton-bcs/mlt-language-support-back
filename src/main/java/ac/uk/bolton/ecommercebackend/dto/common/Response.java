package ac.uk.bolton.ecommercebackend.dto.common;

/*
    @author DanujaV
    @created 7/16/23 - 1:56 AM   
*/

import java.util.Date;

public record Response(
        Long orderId,
        Integer qty,
        Double price,
        String country,
        String deliveryAddress,
        Date expectedDate,
        Boolean status,
        Long productId,
        String productName,
        String description
) {
}
