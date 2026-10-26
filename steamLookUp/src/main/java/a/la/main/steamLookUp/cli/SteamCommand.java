package a.la.main.steamLookUp.cli;

import a.la.main.steamLookUp.client.SteamApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;


@Component
public class SteamCommand implements CommandLineRunner {

    private final SteamApiClient steamApiClient;

    @Value("${env.key}")
    private String key;

    public SteamCommand(SteamApiClient steamApiClient, JsonMapper jsonMapper) {
        this.steamApiClient = steamApiClient;
    }


    @Override
    public void run(String... args) throws Exception {
        String vanityUrl = args[0];
        String reponse = steamApiClient.resolveVanityUrl(key,vanityUrl);
        System.out.println(reponse);
        System.out.println(extractSteamIdToJson(reponse));
        String reponse2 = steamApiClient.getPlayerSummaries(key,extractSteamIdToJson(reponse));
        System.out.println(reponse2);
        return;
    }

    public String extractSteamIdToJson(String response){
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(response);
        JsonNode jsonNode2 = jsonNode.get("response");

        String steamId = jsonNode2.get("steamid").asText() ;

        return steamId;
    }

}
