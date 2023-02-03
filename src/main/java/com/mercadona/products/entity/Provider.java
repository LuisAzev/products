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
        name = "provider",
        indexes = {
                @Index(name = "uidx_provider_code",
                        columnList = "code",
                        unique = true)
        }
)
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true , nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "direction", length = 150)
    private String direction;

    @Column(name = "code", nullable = false)
    private Integer code;

    /**
     * Products linked to this provider
     * @see Product
     */
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "provider",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE,
            orphanRemoval = true)
    @ToString.Exclude
    private Set<Product> products = new HashSet<>();
}
