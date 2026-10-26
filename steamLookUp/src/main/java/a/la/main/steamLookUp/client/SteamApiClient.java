package a.la.main.steamLookUp.client;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Component
public class SteamApiClient {

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public String getPlayerSummaries(String key, String steamids) throws IOException, InterruptedException {
        String url ="https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v2/?key="+key+"&steamids="+steamids;


        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request,
                HttpResponse.BodyHandlers.ofString()
        );

        return response.body();
    }

}
