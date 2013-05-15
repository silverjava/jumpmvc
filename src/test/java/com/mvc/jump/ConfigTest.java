package com.mvc.jump;

import org.junit.Test;

import javax.servlet.ServletConfig;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ConfigTest {
    @Test
    public void should_get_container_type_based_on_servlet_config() throws Exception {
        ServletConfig servletConfig = mock(ServletConfig.class);
        given(servletConfig.getInitParameter("ContainerType")).willReturn("Guice");
        given(servletConfig.getInitParameter("ModulePath")).willReturn("ModulePath");

        Config config = new Config(servletConfig);

        assertThat(config.getContainerType(), is("Guice"));
        assertThat(config.getConfigPath(), is("ModulePath"));
    }
}
