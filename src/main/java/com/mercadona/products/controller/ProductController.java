package com.mercadona.products.controller;

import com.mercadona.products.custom.exception.InvalidEanCodeException;
import com.mercadona.products.custom.exception.InvalidNameException;
import com.mercadona.products.custom.exception.InvalidCodeException;
import com.mercadona.products.dto.*;
import com.mercadona.products.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping(path = "/v1/products")
@AllArgsConstructor
public class ProductController {

    private final static String EAN_CODE_REGEX = "^\\d{13}$";
    private final static String PRODUCT_CODE_REGEX = "^\\d{5}$";

    private final ProductService productService;

    /**
     * Return list of products
     * @return
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetProductDto> getProducts(){

        return productService.getProducts();
    }

    /**
     * Return the product by id
     * @param productId
     * @return
     */
    @GetMapping(path = "/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GetProductDto getProduct(@PathVariable Long productId){

        return productService.getProduct(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Return product by code EAN
     * @param completeEanCode
     * @return
     */
    @GetMapping(path = "/findByEan",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GetProductByEanDto getProductEan(@RequestParam BigInteger completeEanCode){

        eanCodeProductVerify(completeEanCode);

        return productService.findProductByCodeEan(completeEanCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Create product
     * @param dto
     * @return
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GetProductDto createProduct(@RequestBody PostProductDto dto) {
        nameProductVerify(dto);
        codeProductVerify(dto.getCode(), dto);

        return this.productService.createProduct(dto);
    }

    /**
     * Update an existent product
     * @param productId
     * @param dto
     * @return
     */
    @PutMapping(path = "/{productId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GetProductDto updateProduct(@PathVariable Long productId,
                                       @RequestBody PutProductDto dto){

        if(dto.getId() != null
                && !dto.getId().equals(productId)) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        nameProductVerify(dto);
        codeProductVerify(dto.getCode(), dto);

        return this.productService.updateProduct(dto);
    }

    /**
     * Delete a product by id
     * @param productId
     */
    @DeleteMapping(path =  "/{productId}")
    public void deleteProduct(@PathVariable Long productId) {

        this.productService.deleteProductById(productId);
    }

    private static void eanCodeProductVerify(BigInteger completeEanCode) {
        String eanCodeAsText = String.valueOf(completeEanCode);
        Pattern pattern = Pattern.compile(EAN_CODE_REGEX, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(String.valueOf(eanCodeAsText));

        if (!matcher.find()){
            throw new InvalidEanCodeException();
        }
    }

    private void codeProductVerify(Integer code, @RequestBody PutProductDto dto) {
        String productCodeAsText = String.valueOf(code);
        Pattern pattern = Pattern.compile(PRODUCT_CODE_REGEX, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(String.valueOf(productCodeAsText));

        if (!matcher.find()){
            throw new InvalidCodeException();
        }
    }

    private void codeProductVerify(Integer code, @RequestBody PostProductDto dto) {
        String productCodeAsText = String.valueOf(code);
        Pattern pattern = Pattern.compile(PRODUCT_CODE_REGEX, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(String.valueOf(productCodeAsText));

        if (!matcher.find()){
            throw new InvalidCodeException();
        }
    }
    private static void nameProductVerify(PutProductDto dto) {
        if (dto.getName()==null){
            throw new InvalidNameException();
        } else if(dto.getName().isBlank()){
            throw new InvalidNameException();
        }
    }

    private static void nameProductVerify(PostProductDto dto) {
        if (dto.getName()==null){
            throw new InvalidNameException();
        } else if(dto.getName().isBlank()){
            throw new InvalidNameException();
        }
    }
}
