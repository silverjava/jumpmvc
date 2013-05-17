package com.mvc.jump;

import com.google.common.base.Objects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLMatcher {
    private static final String PARAM_REGEX = "\\$(\\d+)";
    private Pattern targetPattern;
    private String originalUrl;
    private String httpMethod;

    public URLMatcher(String urlPattern) {
        this.originalUrl = urlPattern;
        genUrlPattern();
    }

    public URLMatcher(String url, String httpMethod) {
        this(url);
        this.httpMethod = httpMethod;
    }

    private void genUrlPattern() {
        Pattern pattern = Pattern.compile(PARAM_REGEX);
        Matcher matcher = pattern.matcher(originalUrl);
        targetPattern = Pattern.compile(matcher.replaceAll("(.+)"));
        // /hello/$1  => /hello/(.+)
    }

    public boolean match(String realUrl, String method) {
        return targetPattern.matcher(realUrl).matches() && Objects.equal(method, httpMethod);
    }

    public String[] getParams(String realUrl) {
        Matcher matcher = targetPattern.matcher(realUrl);

        if (!matcher.matches()) {
            return new String[0];
        }

        int paramCount = matcher.groupCount();
        String[] params = new String[paramCount];

        for (int index = 1; index <= paramCount; index++) {
            params[index - 1] = matcher.group(index);
        }

        return params;
    }
}
