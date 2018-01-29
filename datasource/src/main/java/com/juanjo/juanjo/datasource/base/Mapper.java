package com.juanjo.juanjo.datasource.base;

/**
 * Created by juanj on 24/01/2018.
 */

public interface Mapper<A extends Object,B extends Object> {
    A map(B model);
    B inverseMap(A model);
}
