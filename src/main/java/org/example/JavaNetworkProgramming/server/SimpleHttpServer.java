package org.example.JavaNetworkProgramming.server;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import static java.net.HttpURLConnection.HTTP_OK;

public class SimpleHttpServer {

    private static long visitorCounter = 0;
    public static void main(String[] args) {

        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(5000),
                    0);
            // backlog is the man number of queued up incoming connections
            // allowed on the listening socket

            server.createContext("/", exchange -> {
                String requestMethod = exchange.getRequestMethod();
                System.out.println("Request Method: " + requestMethod);

                String response = """
                <html>
                    <body>
                        <h1>Hello world from my http server</h1>
                    </body>
                </html>
                        """;

                var bytes = response.getBytes();
                exchange.sendResponseHeaders(HTTP_OK, bytes.length);
                exchange.getResponseBody().write(bytes);
                exchange.close();
            });
            server.start();
            System.out.println("Server is listening on port 5000....");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
}
