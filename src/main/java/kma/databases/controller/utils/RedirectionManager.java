package kma.databases.controller.utils;

import kma.databases.exceptions.ServerException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class RedirectionManager {

    public static String REDIRECTION = "REDIRECTION";
    private static String MESSAGE_ENCODING = "UTF-8";

    private RedirectionManager() {}

    private static final class Holder {
        static final RedirectionManager INSTANCE = new RedirectionManager();
    }

    public static RedirectionManager getInstance() {
        return Holder.INSTANCE;
    }

    public void redirectWithParams(HttpWrapper httpWrapper, String redirectionPath, Map<String, String> urlParameters)
            throws IOException {
        String pathWithParams = redirectionPath + generateUrlParams(urlParameters);
        redirect(httpWrapper, pathWithParams);
    }

    public void redirect(HttpWrapper httpWrapper, String path) {
        try {
            httpWrapper.getResponse().sendRedirect(httpWrapper.getRequest().getContextPath() +
                    httpWrapper.getRequest().getServletPath() + path);
        } catch (IOException e) {
            throw new ServerException(e);
        }
    }

    public String generateUrlParams(Map<String, String> urlParameters) throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder("?");
        for (String urlParamName : urlParameters.keySet()) {
            stringBuilder.append(urlParamName).append("=")
                    .append(URLEncoder.encode(urlParameters.get(urlParamName), MESSAGE_ENCODING))
                    .append("&");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}

