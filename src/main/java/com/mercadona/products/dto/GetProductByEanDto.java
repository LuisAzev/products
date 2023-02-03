package com.mercadona.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetProductByEanDto {

    private Long id;

    private String name;

    private BigInteger eanCode;

    private Integer code;

    private String codProvider;

    private String codDestiny;
}
