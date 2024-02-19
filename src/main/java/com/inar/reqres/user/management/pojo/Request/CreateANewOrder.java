package com.inar.reqres.user.management.pojo.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateANewOrder {

        String cartId;
        String customerName;
}
