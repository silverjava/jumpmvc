package com.mvc.jump;

import com.mvc.example.HelloAction;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class UrlMapperTest {

    @Test
    public void should_get_method_to_render_page() throws Exception {
        // given
        UrlMapper mapper = new UrlMapper();
        mapper.map(new HelloAction());
        String url = "/hello";

        // when
        MethodWrapper method = mapper.findMethod(url);

        // then
        assertThat(method, notNullValue());
    }
}
