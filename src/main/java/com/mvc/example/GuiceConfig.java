package com.mvc.example;

import com.google.inject.AbstractModule;

public class GuiceConfig extends AbstractModule {

    @Override
    protected void configure() {
        bind(HelloAction.class).asEagerSingleton();
    }
}
