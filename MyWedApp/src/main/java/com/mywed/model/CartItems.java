package com.mywed.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItems {
    private Integer productId;
    private String name;
    private int price;
    private String picture;
    private int quantity = 1;
}
