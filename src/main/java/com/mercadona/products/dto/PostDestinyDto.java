package com.mercadona.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDestinyDto {

    @NotBlank
    @Size(max = 150)
    private String name;

    @NotNull
    @Digits(integer = 1, fraction = 0)
    private Integer code;
}
