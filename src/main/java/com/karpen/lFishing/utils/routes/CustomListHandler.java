package com.karpen.lFishing.utils.routes;

import com.karpen.lFishing.utils.JsonUtils;
import com.karpen.lFishing.utils.MaterialUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CustomListHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            try {
                String request = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                List<String> customList = JsonUtils.parseJSONToList(request);

                if (customList == null) {
                    String response = "Error list is null";
                    exchange.getResponseHeaders().set("Content-Type", "application/text");
                    exchange.sendResponseHeaders(502, response.length());

                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }

                MaterialUtils.saveMaterials(customList, String.valueOf(exchange.getRequestHeaders().get("name")));

                String response = "Success saved";
                exchange.getResponseHeaders().set("Content-Type", "application/text");
                exchange.sendResponseHeaders(200, response.length());

                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } catch (Exception e) {
                String response = e.toString();
                exchange.getResponseHeaders().set("Content-Type", "application/text");
                exchange.sendResponseHeaders(405, response.length());

                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();

                throw new RuntimeException(e);
            }
        }
    }
}
