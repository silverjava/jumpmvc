package com.mvc.jump;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class URLMatcherTest {

    @Test
    public void should_match_regex_pattern_for_url() throws Exception {
        // given
        String urlPattern = "/hello/$1";
        URLMatcher matcher = new URLMatcher(urlPattern);

        String realUrl = "/hello/david";

        // when
        boolean isMatch = matcher.match(realUrl);

        // then
        assertThat(isMatch, is(true));
    }

    @Test
    public void should_not_match_for_wrong_url() throws Exception {
        // given
        String urlPattern = "/hello/$1";
        URLMatcher matcher = new URLMatcher(urlPattern);

        String realUrl = "/hello";

        // when
        boolean isMatch = matcher.match(realUrl);

        // then
        assertThat(isMatch, is(false));
    }

    @Test
    public void should_be_matched_for_same_url() throws Exception {
        // given
        String urlPattern = "/hello";
        URLMatcher matcher = new URLMatcher(urlPattern);

        String realUrl = "/hello";

        // when
        boolean isMatch = matcher.match(realUrl);

        // then
        assertThat(isMatch, is(true));
    }

    @Test
    public void should_get_parameters_for_the_url() throws Exception {
        // given
        String urlPattern = "/hello/$1";
        URLMatcher matcher = new URLMatcher(urlPattern);

        String realUrl = "/hello/david";

        // when
        boolean isMatch = matcher.match(realUrl);
        String[] params = matcher.getParams(realUrl);

        // then
        assertThat(isMatch, is(true));
        assertThat(params, notNullValue());
        assertThat(params.length, is(1));
        assertThat(params[0], is("david"));
    }

    @Test
    public void should_be_matched_for_more_than_one_params() throws Exception {
        // given
        String urlPattern = "/hello/$1/$2";
        URLMatcher matcher = new URLMatcher(urlPattern);

        String realUrl = "/hello/david/john";

        // when
        boolean isMatch = matcher.match(realUrl);
        String[] params = matcher.getParams(realUrl);

        // then
        assertThat(isMatch, is(true));
        assertThat(params, notNullValue());
        assertThat(params.length, is(2));
        assertThat(params[0], is("david"));
        assertThat(params[1], is("john"));
    }
}
