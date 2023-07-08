package ac.uk.bolton.ecommercebackend.dto;

/*
    @author DanujaV
    @created 7/8/23 - 9:15 PM   
*/

import java.util.Date;

public record OrdersDTO(
        Integer qty,
        Double price,
        String country,
        String deliveryAddress,
        Date expectedDate
){}
