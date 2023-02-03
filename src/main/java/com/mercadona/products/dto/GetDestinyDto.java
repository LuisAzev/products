package com.mercadona.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetDestinyDto {

    private Long id;

    private String name;

    private Integer code;
}
