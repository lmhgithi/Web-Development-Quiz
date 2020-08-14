package com.thoughtworks.rslist.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity {
    @Id
    @GeneratedValue
    private Integer productId;
    @NotNull
    private String name;

    @NotNull
    private int quantity;

    @NotNull
    private int price;

    @NotNull

    private String unit;

    @NotNull
    private String imgUrl;
}
