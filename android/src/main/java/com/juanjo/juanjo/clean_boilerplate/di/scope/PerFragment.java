package com.juanjo.juanjo.clean_boilerplate.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;
import javax.inject.Singleton;

/**
 * Created by juanj on 24/01/2018.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {
}
