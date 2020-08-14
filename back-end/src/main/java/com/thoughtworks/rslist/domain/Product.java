package com.thoughtworks.rslist.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @NotNull
    private String name;

    @NotNull
    private String price;

    @NotNull

    private String unit;

    @NotNull
    private String imgUrl;
}
