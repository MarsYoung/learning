package com.marsyoung.value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Coder extends Human{

    @Value("2")
    int occupation;
}
