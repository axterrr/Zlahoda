package kma.databases.controller.utils;

import javax.servlet.http.HttpServletRequest;

public final class CommandKeyGenerator {

    private CommandKeyGenerator() {}

    public static String generateCommandKeyFromRequest(HttpServletRequest request) {
        String method = request.getMethod().toUpperCase();
        String path = request.getRequestURI().replaceAll(".*/controller/", "");
        return method + ':' + path;
    }
}
