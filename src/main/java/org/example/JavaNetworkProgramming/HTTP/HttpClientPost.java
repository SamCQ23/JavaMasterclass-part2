package org.example.JavaNetworkProgramming.HTTP;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.time.Duration;
import java.util.stream.Stream;

import static java.net.HttpURLConnection.HTTP_OK;

public class HttpClientPost {

    public static void main(String[] args) {

        try {
            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofMinutes(1))
                    .version(HttpClient.Version.HTTP_1_1)
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(
                            "first=Joe&last=Smith"))
                    .uri(URI.create("http://localhost:5000"))
                    .header("Content-Type",
                            "application/x-www-form-urlencoded")
                    .build();

            HttpResponse<Path> response = client.send(request,
                    HttpResponse.BodyHandlers.ofFile(Path.of("test.html")));
            if (response.statusCode() != HTTP_OK) {
                System.out.println("Error reading web page " + request.uri());
                return;
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}