package com.mercadona.products.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(
        name = "destiny", indexes = {
                @Index(name = "uidx_destiny_code",
                        columnList = "code",
                        unique = true)
})
public class Destiny {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "code", nullable = false)
    private Integer code;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    /**
     * Products linked to this destiny
     * @see Product
     */
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "destiny",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE,
            orphanRemoval = true)
    @ToString.Exclude
    private Set<Product> products = new HashSet<>();
}
