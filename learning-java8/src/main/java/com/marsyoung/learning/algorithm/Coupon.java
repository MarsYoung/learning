package com.marsyoung.learning.algorithm;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Coupon {
    String name;
    int full;
    int cut;
}
