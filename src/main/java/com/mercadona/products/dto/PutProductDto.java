package com.mercadona.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PutProductDto {

    @NotNull
    private Long id;

    @NotBlank
    @Size(max = 150)
    private String name;

    @NotNull
    @Digits(integer = 5,fraction = 0)
    private Integer code;

    @NotNull
    private Long providerId;

    @NotNull
    private Long destinyId;
}
