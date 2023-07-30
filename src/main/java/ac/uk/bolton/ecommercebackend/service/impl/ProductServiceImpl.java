package ac.uk.bolton.ecommercebackend.service.impl;

import ac.uk.bolton.ecommercebackend.dto.ProductDTO;
import ac.uk.bolton.ecommercebackend.dto.common.ResponsePayload;
import ac.uk.bolton.ecommercebackend.entity.Product;
import ac.uk.bolton.ecommercebackend.exception.custom.UnmanagedException;
import ac.uk.bolton.ecommercebackend.repository.ProductRepository;
import ac.uk.bolton.ecommercebackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    @Override
    public ResponsePayload createProduct(ProductDTO productDto) {
        try {
            Product product = new Product();
            product.setName(productDto.getName());
            product.setCategoryId(productDto.getCategoryId());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setQty(productDto.getQty());
            product.setStatus(productDto.getStatus());

            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, productDto.getImageUrl().getOriginalFilename());
            fileNames.append(productDto.getImageUrl().getOriginalFilename());
            Files.write(fileNameAndPath, productDto.getImageUrl().getBytes());
            product.setImageUrl(fileNames.toString());

            Product result  = productRepository.save(product);
            return new ResponsePayload(HttpStatus.OK.getReasonPhrase(), result, HttpStatus.OK);
        }catch (Exception e){
            throw new UnmanagedException(e.getMessage());
        }

    }

    @Override
    public ResponsePayload updateProduct(ProductDTO productDto) {
        try {
            if (productRepository.findById(productDto.getId()).isPresent()) {
                Product product = productRepository.findProductById(productDto.getId());
                product.setName(productDto.getName());
                product.setCategoryId(productDto.getCategoryId());
                product.setDescription(productDto.getDescription());
//                product.setImageUrl(productDto.getImageUrl());
                product.setPrice(productDto.getPrice());
                product.setQty(productDto.getQty());
                product.setStatus(productDto.getStatus());
                Product result  = productRepository.save(product);
                return new ResponsePayload(HttpStatus.OK.getReasonPhrase(), result, HttpStatus.OK);
            }else{
                return new ResponsePayload(HttpStatus.NOT_FOUND.getReasonPhrase(), null, HttpStatus.NOT_FOUND);
            }


        }catch (Exception e){
            throw new UnmanagedException(e.getMessage());
        }


    }

    @Override
    public ResponsePayload getAllProduct() {
        try {
            List<Product> products = productRepository.findAll();
            List<ProductDTO> productDTOs = new ArrayList<>();

            for (Product product : products) {
                ProductDTO productDTO = mapper.map(product, ProductDTO.class);
                productDTOs.add(productDTO);
            }
            return new ResponsePayload(HttpStatus.OK.getReasonPhrase(), productDTOs, HttpStatus.OK);

        }catch (Exception e){
            throw new UnmanagedException(e.getMessage());
        }
    }

    @Override
    public ResponsePayload deleteProduct(Long id) {
        try {
            if (productRepository.findById(id).isPresent()) {
                productRepository.deleteById(id);
                return new ResponsePayload(HttpStatus.OK.getReasonPhrase(), null, HttpStatus.OK);
            }else{
                return new ResponsePayload(HttpStatus.NOT_FOUND.getReasonPhrase(), null, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            throw new UnmanagedException(e.getMessage());
        }
    }
}
