package com.mvc.jump;

import com.google.common.base.Objects;
import com.google.inject.Module;

public class JumpContainerFactory {

    public static final String GUICE_CONTAINER = "Guice";

    public JumpContainer createContainer(Config config) {
        if (Objects.equal(GUICE_CONTAINER, config.getContainerType())) {
            try {
                return new GuiceContainer((Module) Class.forName(config.getConfigPath()).newInstance());
            } catch (Exception e) {
            }
        }

        throw new IllegalArgumentException();
    }
}
