package ac.uk.bolton.ecommercebackend.controller;

import ac.uk.bolton.ecommercebackend.dto.CategoryDTO;
import ac.uk.bolton.ecommercebackend.dto.ProductDTO;
import ac.uk.bolton.ecommercebackend.dto.common.ResponsePayload;
import ac.uk.bolton.ecommercebackend.service.CategoryService;
import ac.uk.bolton.ecommercebackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    /**
     * returns response entity of create category request
     *
     * @param categoryDto - category request Dto
     * @return {AjaxResponse<Object>}
     */
    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponsePayload> saveCategory(@RequestBody CategoryDTO categoryDto) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.createCategory(categoryDto));
    }


    /**
     * returns response entity of get all category request
     *
     * @return {AjaxResponse<Object>}
     */
    @GetMapping(path = "/get-all", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponsePayload> getAllCategory() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategory());
    }

    /**
     * returns response entity of get category find by id request
     *
     * @return {AjaxResponse<Object>}
     */
    @GetMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponsePayload> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategoryById(id));
    }




}
