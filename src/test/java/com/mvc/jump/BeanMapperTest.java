package com.mvc.jump;

import com.mvc.example.SignForm;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class BeanMapperTest {

    private BeanMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new BeanMapper(SignForm.class, "form");
    }

    @Test
    public void should_create_bean_by_class() throws Exception {
        // when
        SignForm form = (SignForm) mapper.createAndMap(null);

        // then
        assertThat(form, notNullValue());
    }

    @Test
    public void should_create_bean_with_email() throws Exception {
        // given
        String email = "xx@sina.com";

        Map parameterMap = newHashMap();
        parameterMap.put("form.email", new String[] {email});

        // when
        SignForm form = (SignForm) mapper.createAndMap(parameterMap);

        // then
        assertThat(form.getEmail(), is(email));
    }

    @Test
    public void should_create_bean_with_email_and_password() throws Exception {
        // given
        String email = "xx@sina.com";
        String password = "123456";

        Map parameterMap = newHashMap();
        parameterMap.put("form.email", new String[] {email});
        parameterMap.put("form.password", new String[] {password});

        // when
        SignForm form = (SignForm) mapper.createAndMap(parameterMap);

        // then
        assertThat(form.getEmail(), is(email));
        assertThat(form.getPassword(), is(password));
    }

    @Test
    public void should_create_for_primitive() throws Exception {
        // given
        BeanMapper primitiveMapper = new BeanMapper(int.class, "int");
        Map parameterMap = newHashMap();
        parameterMap.put("int", new String[] {"1"});

        // when
        Object result = primitiveMapper.createAndMap(parameterMap);

        // then
        assertTrue(result instanceof Integer);
        assertThat((Integer) result, is(1));
    }

    @Test
    public void should_create_for_string() throws Exception {
        // given
        BeanMapper primitiveMapper = new BeanMapper(String.class, "string");
        Map parameterMap = newHashMap();
        parameterMap.put("string", new String[] {"abc"});

        // when
        Object result = primitiveMapper.createAndMap(parameterMap);

        // then
        assertThat(String.valueOf(result), is("abc"));
    }

    @Test
    public void should_create_for_integer() throws Exception {
        // given
        BeanMapper primitiveMapper = new BeanMapper(Integer.class, "integer");
        Map parameterMap = newHashMap();
        parameterMap.put("integer", new String[] {"123"});

        // when
        Object result = primitiveMapper.createAndMap(parameterMap);

        // then
        assertThat((Integer)result, is(123));
    }

    @Test
    public void should_not_null_for_complex_type() throws Exception {
        // given
        BeanMapper primitiveMapper = new BeanMapper(SignForm.class, "form");
        Map parameterMap = newHashMap();
        parameterMap.put("form.fullName.firstName", new String[] {"Yin"});
        parameterMap.put("form.fullName.lastName", new String[] {"Dawei"});

        // when
        SignForm result = (SignForm) primitiveMapper.createAndMap(parameterMap);

        // then
        assertThat(result.getFullName(), notNullValue());
        assertThat(result.getFullName().getFirstName(), is("Yin"));
        assertThat(result.getFullName().getLastName(), is("Dawei"));
    }
}
