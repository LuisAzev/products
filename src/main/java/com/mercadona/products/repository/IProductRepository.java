package com.mercadona.products.repository;

import com.mercadona.products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(propagation = Propagation.MANDATORY)
public interface IProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findProductByCodeAndProviderCodeAndDestinyCode(
            Integer code,
            Integer providerCode,
            Integer destinyCode);

}
