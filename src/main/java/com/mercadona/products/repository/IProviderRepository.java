package com.mercadona.products.repository;

import com.mercadona.products.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.MANDATORY)
public interface IProviderRepository extends JpaRepository<Provider,Long> {

}
