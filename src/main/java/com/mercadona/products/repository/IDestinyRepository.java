package com.mercadona.products.repository;

import com.mercadona.products.entity.Destiny;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.MANDATORY)
public interface IDestinyRepository extends JpaRepository<Destiny,Long> {

}
