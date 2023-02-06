package com.mercadona.products.service;

import com.mercadona.products.custom.exception.EntityNotFoundException;
import com.mercadona.products.dto.*;
import com.mercadona.products.entity.Destiny;
import com.mercadona.products.entity.Product;
import com.mercadona.products.entity.Provider;
import com.mercadona.products.mapper.IProductMapper;
import com.mercadona.products.repository.IDestinyRepository;
import com.mercadona.products.repository.IProductRepository;
import com.mercadona.products.repository.IProviderRepository;
import com.mercadona.products.utility.EanCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ProductService {

    private final IProductRepository productRepository;

    private final IDestinyRepository destinyRepository;

    private final IProviderRepository providerRepository;

    private final IProductMapper productMapper;

    /**
     * Return list of products
     * @return
     */
    @Transactional(readOnly = true)
    public List<GetProductDto> getProducts(){

        return productRepository.findAll()
                .stream()
                .map(productMapper::entityToDto)
                .collect(Collectors.toList());
    }

    /**
     * Return the product by id
     * @param productId
     * @return
     */
    @Transactional(readOnly = true)
    public Optional<GetProductDto> getProduct(Long productId){

        return productRepository.findById(productId)
                .map(productMapper::entityToDto);
    }

    /**
     * Return product by code EAN
     * @param completeEanCode
     * @return
     */
    @Transactional
    public Optional<GetProductByEanDto> findProductByCodeEan(BigInteger completeEanCode){
        EanCode eanCode = new EanCode(completeEanCode);

        return productRepository.findProductByCodeAndProviderCodeAndDestinyCode(
                eanCode.getProductCode(),
                eanCode.getProviderCode(),
                eanCode.getDestinyCode())
                .map((Product p) -> productMapper.entityToDto(p,eanCode));
    }

    /**
     * Create product
     * @param dto
     * @return
     */
    @Transactional
    public GetProductDto createProduct(PostProductDto dto) {
        destinyRepository.findById(dto.getDestinyId() )
                .orElseThrow(()-> new EntityNotFoundException());
        providerRepository.findById(dto.getProviderId() )
                .orElseThrow(()->new EntityNotFoundException());
        Destiny destiny = destinyRepository.getReferenceById(dto.getDestinyId());
        Provider provider = providerRepository.getReferenceById(dto.getProviderId());
        Product entity = productMapper.postProductDtoToEntity(provider,dto,destiny);

        return productMapper.entityToDto(productRepository.save(entity));

    }

    /**
     * Update an existent product
     * @param dto
     * @return
     */
    @Transactional
    public GetProductDto updateProduct(PutProductDto dto) {

        productRepository.findById( dto.getId() )
                .orElseThrow(() -> new EntityNotFoundException());

        destinyRepository.findById(dto.getDestinyId() )
                .orElseThrow(()-> new EntityNotFoundException());
        providerRepository.findById(dto.getProviderId() )
                .orElseThrow(()->new EntityNotFoundException());
        Destiny destiny = destinyRepository.getReferenceById(dto.getDestinyId());
        Provider provider = providerRepository.getReferenceById(dto.getProviderId());
        Product entity = productMapper.putDtoToEntity(provider,dto,destiny);

        return productMapper.entityToDto( productRepository.save(entity) );
    }

    /**
     * Delete a product by id
     * @param productId
     */
    @Transactional
    public void deleteProductById(Long productId){
        productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException());

        productRepository.deleteById(productId);
    }

}
