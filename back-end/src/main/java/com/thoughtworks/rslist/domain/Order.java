package com.thoughtworks.rslist.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @NotNull
    private Integer productId;

    @NotNull
    private String name;

    @NotNull
    private int quantity;

    @NotNull
    private int price;

    @NotNull

    private String unit;
}
