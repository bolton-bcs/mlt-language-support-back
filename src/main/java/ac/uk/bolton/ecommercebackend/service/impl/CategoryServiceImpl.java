package ac.uk.bolton.ecommercebackend.service.impl;

import ac.uk.bolton.ecommercebackend.dto.CategoryDTO;
import ac.uk.bolton.ecommercebackend.dto.common.ResponsePayload;
import ac.uk.bolton.ecommercebackend.entity.Category;
import ac.uk.bolton.ecommercebackend.entity.Product;
import ac.uk.bolton.ecommercebackend.exception.custom.UnmanagedException;
import ac.uk.bolton.ecommercebackend.repository.CategoryRepository;
import ac.uk.bolton.ecommercebackend.repository.ProductRepository;
import ac.uk.bolton.ecommercebackend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;
    private final ProductRepository productRepository;

    @Override
    public ResponsePayload createCategory(CategoryDTO categoryDto) {
        try {
            Category category = new Category();
            category.setName(categoryDto.getName());
            Category result  = categoryRepository.save(category);
            return new ResponsePayload(HttpStatus.OK.getReasonPhrase(), result, HttpStatus.OK);
        }catch (Exception e){
            throw new UnmanagedException(e.getMessage());
        }

    }

    @Override
    public ResponsePayload getAllCategory() {
        try {
            List<Category> list = categoryRepository.findAll();
            List<CategoryDTO> categoryDTOs = new ArrayList<>();

            for (Category category : list) {
                CategoryDTO categoryDTO = mapper.map(category, CategoryDTO.class);
                categoryDTOs.add(categoryDTO);
            }
            return new ResponsePayload(HttpStatus.OK.getReasonPhrase(), categoryDTOs, HttpStatus.OK);

        }catch (Exception e){
            throw new UnmanagedException(e.getMessage());
        }
    }

    @Override
    public ResponsePayload getCategoryById(Long id) {
        try {
            if (categoryRepository.findById(id).isPresent()) {
                CategoryDTO categoryDTO = new CategoryDTO();
                Optional<Category> category = categoryRepository.findById(id);
                categoryDTO.setId(category.get().getId());
                categoryDTO.setName(category.get().getName());
                categoryDTO.setProductList(productRepository.findAllByCategoryId(category.get().getId()));
                return new ResponsePayload(HttpStatus.OK.getReasonPhrase(), categoryDTO, HttpStatus.OK);
            } else {
                return new ResponsePayload(HttpStatus.OK.getReasonPhrase(), null, HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            throw new UnmanagedException(e.getMessage());
        }
    }

}
