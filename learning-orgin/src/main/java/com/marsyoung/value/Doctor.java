package com.marsyoung.value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Doctor extends Human {

    @Value("1")
    int occupation;

    @Override
    public int getOccupation() {
        return super.occupation;
    }
}
