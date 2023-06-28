package ac.uk.bolton.ecommercebackend.controller;

import ac.uk.bolton.ecommercebackend.dto.ProductDTO;
import ac.uk.bolton.ecommercebackend.dto.UserDTO;
import ac.uk.bolton.ecommercebackend.dto.common.ResponsePayload;
import ac.uk.bolton.ecommercebackend.service.ProductService;
import ac.uk.bolton.ecommercebackend.util.AjaxResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    /**
     * returns response entity of create product request
     *
     * @param productDto - product request Dto
     * @return {AjaxResponse<Object>}
     */
    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponsePayload> saveProduct(@RequestBody ProductDTO productDto) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.createProduct(productDto));
    }

    /**
     * returns response entity of update product request
     *
     * @param productDto - product request Dto
     * @return {AjaxResponse<Object>}
     */
    @PutMapping(path = "/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponsePayload> updateProduct(@RequestBody ProductDTO productDto) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(productDto));
    }

    /**
     * returns response entity of get all product request
     *
     * @return {AjaxResponse<Object>}
     */
    @GetMapping(path = "/get-all", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponsePayload> getAllProduct() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProduct());
    }

    /**
     * returns response entity of delete product request
     *
     * @return {AjaxResponse<Object>}
     */
    @DeleteMapping(path = "/delete/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponsePayload> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProduct(id));
    }



}
