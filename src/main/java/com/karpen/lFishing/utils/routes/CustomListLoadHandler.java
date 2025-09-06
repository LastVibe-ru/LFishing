package com.karpen.lFishing.utils.routes;

import com.karpen.lFishing.utils.MaterialUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class CustomListLoadHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())) {
            try {
                List<String> customMaterials = MaterialUtils.loadMaterials(String.valueOf(exchange.getRequestHeaders().get("name")));

                assert customMaterials != null;
                String response = String.join(",", customMaterials);
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, response.length());

                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } catch (Exception e) {
                String response = e.toString();
                exchange.getResponseHeaders().set("Content-Type", "application/text");
                exchange.sendResponseHeaders(500, response.length());

                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();

                throw new RuntimeException(e);
            }
        }
    }
}
