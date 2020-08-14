package com.thoughtworks.rslist.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @NotNull
    private String name;

    @NotNull
    private int quantity;

    @NotNull

    private String unit;

    @NotNull
    private String imgUrl;
}
