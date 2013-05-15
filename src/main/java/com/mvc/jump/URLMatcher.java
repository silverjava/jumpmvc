package com.mvc.jump;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLMatcher {
    private static final String PARAM_REGEX = "\\$(\\d+)";
    private Pattern targetPattern;
    private String originalUrl;

    public URLMatcher(String urlPattern) {
        this.originalUrl = urlPattern;
        genUrlPattern();
    }

    private void genUrlPattern() {
        String url = originalUrl;
        Pattern pattern = Pattern.compile(PARAM_REGEX);
        Matcher matcher = pattern.matcher(url);
        targetPattern = Pattern.compile(matcher.replaceAll("(.+)"));
    }

    public boolean match(String realUrl) {
        return targetPattern.matcher(realUrl).matches();
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
