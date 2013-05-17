package com.mvc.jump;

import org.junit.Test;

import javax.servlet.ServletConfig;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class JumpContainerFactoryTest {

    @Test
    public void testName() throws Exception {
        ServletConfig servletConfig = mock(ServletConfig.class);
        given(servletConfig.getInitParameter("ContainerType")).willReturn("Guice");
        given(servletConfig.getInitParameter("ModulePath")).willReturn("com.mvc.example.GuiceConfig");

        Config config = new Config(servletConfig);

        JumpContainer container = new JumpContainerFactory().createContainer(config);

        assertThat(container.loadAllBeans(), notNullValue());

    }
}
