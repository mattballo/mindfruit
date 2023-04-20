package com.mattballo.mindfruit.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExternalApiUtil {

    public static HttpURLConnection getConnection(String url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");
        return conn;
    }

}
