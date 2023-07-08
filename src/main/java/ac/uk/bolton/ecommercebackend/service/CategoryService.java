package ac.uk.bolton.ecommercebackend.service;

import ac.uk.bolton.ecommercebackend.dto.CategoryDTO;
import ac.uk.bolton.ecommercebackend.dto.common.ResponsePayload;

public interface CategoryService {
    ResponsePayload createCategory(CategoryDTO categoryDto);
    ResponsePayload getAllCategory();
    ResponsePayload getCategoryById(Long id);
}
