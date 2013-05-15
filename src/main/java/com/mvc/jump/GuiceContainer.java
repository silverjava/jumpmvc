package com.mvc.jump;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.inject.*;

import java.util.List;
import java.util.Map;

public class GuiceContainer implements JumpContainer {
    private final Injector injector;

    public GuiceContainer(Module module) {
        this.injector = Guice.createInjector(module);
    }

    @Override
    public void init(Config config) {
    }

    @Override
    public List<Object> loadAllBeans() {
        return FluentIterable.from(injector.getAllBindings().keySet()).transform(new Function<Key<?>, Object>() {
            @Override
            public Object apply(Key<?> key) {
                return injector.getInstance(key);
            }
        }).toList();
    }
}
