package com.mercadona.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PutProviderDto {

    @NotNull
    private Long id;

    @NotBlank
    @Size(max = 150)
    private String name;

    @Size(max = 150)
    private String direction;

    @NotNull
    @Digits(integer = 7, fraction = 0)
    private Integer code;
}
