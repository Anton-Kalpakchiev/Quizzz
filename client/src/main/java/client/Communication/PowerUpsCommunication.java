package client.Communication;



import commons.PowerUp;
import commons.Quote;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import org.glassfish.jersey.client.ClientConfig;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;


public class PowerUpsCommunication {
    private static HttpClient client = HttpClient.newBuilder().build();

    public static void sendPowerUps(String message) throws IOException, InterruptedException {
        System.out.println("\nPower ups sent to server: \n" + message);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/powerup"))
                .POST(HttpRequest.BodyPublishers.ofString(message))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }



}

