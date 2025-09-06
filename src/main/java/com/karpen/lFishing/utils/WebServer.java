package com.karpen.lFishing.utils;

import com.karpen.lFishing.utils.routes.CustomListHandler;
import com.karpen.lFishing.utils.routes.CustomListLoadHandler;
import com.karpen.lFishing.utils.routes.MaterialHandler;
import com.karpen.lFishing.utils.routes.StaticHandler;
import com.sun.net.httpserver.HttpsServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class WebServer {
    private final int port;
    private HttpsServer server;

    public WebServer(int port) throws IOException {
        this.port = port;
        this.server = HttpsServer.create(new InetSocketAddress(port), 0);
        setupRoutes();
    }

    private void setupRoutes() {
        server.createContext("/", new StaticHandler()); // Возвращает наш index
        server.createContext("/api/materials", new MaterialHandler()); // Возвращает все возможные материалы
        server.createContext("/api/custom-list-save", new CustomListHandler()); // Сохраняет ранее сохраненные материалы
        server.createContext("/api/custom-list-load", new CustomListLoadHandler()); // Возвразает ранее сохраненные материалы
        server.setExecutor(null);
    }

    public String start() {
        server.start();
        return STR."Web gui started at http://loaclhost:\{port}/";
    }

    public void stop() {
        server.stop(0);
    }
}
