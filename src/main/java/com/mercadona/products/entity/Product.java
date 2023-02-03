package com.mercadona.products.entity;

import javax.persistence.*;
import lombok.Data;



@Data
@Entity
@Table(
        name = "product",
        indexes = {
                @Index(name = "uidx_product_code",
                        columnList = "code",
                        unique = true)
        }
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true , nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "code", nullable = false)
    private Integer code;

    /**
     * The provider linked to this product
     *
     * @see Provider
     */
    @ManyToOne(fetch = FetchType.LAZY,
            optional = false)
    @JoinColumn(name = "provider_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_provider"))
    private Provider provider;

    /**
     * The destiny linked to this product
     *
     * @see Destiny
     */
    @ManyToOne(fetch = FetchType.LAZY,
            optional = false)
    @JoinColumn(name = "destiny_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_destiny"))
    private Destiny destiny;


}
