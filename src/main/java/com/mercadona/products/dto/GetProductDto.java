package com.mercadona.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetProductDto {

    private Long id;

    private String name;

    private Integer code;

    private Integer codProvider;

    private Integer codDestiny;
}
