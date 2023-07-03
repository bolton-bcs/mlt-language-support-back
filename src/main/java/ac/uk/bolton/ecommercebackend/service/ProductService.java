package ac.uk.bolton.ecommercebackend.service;

import ac.uk.bolton.ecommercebackend.dto.ProductDTO;
import ac.uk.bolton.ecommercebackend.dto.common.ResponsePayload;

public interface ProductService {
    ResponsePayload createProduct(ProductDTO productDto);
    ResponsePayload updateProduct(ProductDTO productDto);
    ResponsePayload getAllProduct();
    ResponsePayload deleteProduct(Long id);
}
