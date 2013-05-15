package com.mvc.jump;

import javax.servlet.ServletConfig;

public class Config {
    private String containerType;
    private String configPath;

    public Config(ServletConfig config) {
        this.containerType = config.getInitParameter("ContainerType");
        this.configPath = config.getInitParameter("ModulePath");
    }

    public String getContainerType() {
        return containerType;
    }

    public String getConfigPath() {
        return configPath;
    }
}
