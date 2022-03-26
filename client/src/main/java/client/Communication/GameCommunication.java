package client.Communication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.inject.Inject;
import commons.GameState;
import commons.LeaderboardEntry;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class GameCommunication {
    private HttpClient client;

    private static Gson gson = new Gson();

    @Inject
    public GameCommunication(HttpClient client) {
        this.client = client;
    }

    /**
     * Sends an HTTP request to the server to create a game, gets the ID of the game.
     * @return the ID of the game state that is created, if an exception occurs, return -1;
     */
    public long createGame() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/game/create"))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            return Long.parseLong(client.send(request, HttpResponse.BodyHandlers.ofString()).body());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Sends the server the gameID that the user wants to join, together with the username they have chosen.
     * @param gameId the ID of the game, the user wants to join. Usually, this would not
     * necessary, since there is always only 1 game that can be joined at a time, but it is designed that way.
     * @param username The username that the client has chosen to join the game with.
     * @return the gameState to initially render the game with, returns null if an exception occurred.
     */
    public GameState joinGame(long gameId, String username) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/game/join/" + gameId + "?username=" + username))
                .GET()
                .build();
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            final ObjectMapper mapper = new ObjectMapper();
            final TypeReference<GameState> typeRef = new TypeReference<>() {};
            GameState gameState = mapper.readValue(response.body(), typeRef);
            System.out.println("Leaderboard: " + gameState.leaderboard.size());

            return gameState;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Sends a request to the server to initiate the game with id gameId.
     * @param gameId The id of the game to be initiated.
     */
    public void initiateGame(long gameId) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/game/init/" + gameId))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            client.send(request, HttpResponse.BodyHandlers.discarding());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<String> getPlayers(long gameId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/game/players/" + gameId))
                .GET()
                .build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        final ObjectMapper mapper = new ObjectMapper();
        final TypeReference<List<String>> typeRef = new TypeReference<>() {};
        return mapper.readValue(response.body(), typeRef);
    }

    /**
     * Method which returns the leaderboard for a multiplayer game
     * @param gameId the game's id
     * @return the list of leaderboard entries
     */
    public List<LeaderboardEntry> getLeaderboardMultiplayer(long gameId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/game/leaderboard/" + gameId))
                .GET()
                .build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        final ObjectMapper mapper = new ObjectMapper();
        final TypeReference<List<LeaderboardEntry>> typeRef = new TypeReference<>() {};
        return mapper.readValue(response.body(), typeRef);
    }
    /**
     * Method which returns the leaderboard for a singleplayer game. Since this method returns the global
     * leaderboard, it doesn't need the game's id
     * @return the list of leaderboard entries
     */
    public List<LeaderboardEntry> getLeaderboardSingleplayer() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/leaderboard/"))
                .GET()
                .build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        final ObjectMapper mapper = new ObjectMapper();
        final TypeReference<List<LeaderboardEntry>> typeRef = new TypeReference<>() {};
        return mapper.readValue(response.body(), typeRef);
    }
}
