package com.inar.reqres.user.management.pojo.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProductsRes {

    Integer id;
    String category;
    String name;
    Boolean inStock;
}
