package net.chamosmp.kuraac.Util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UpdateCheck {
    public void ModrinthVersionCheck(String pluginVer) throws Exception{
        // Base URL for the API
        String baseUrl = "https://api.modrinth.com/v2";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/project/veinminer/version"))
                .GET() // Can be left out as GET is the default request type
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            ConsoleLogger.console("Failed to fetch versions. HTTP status: " + response.statusCode());
            return;
        }

        //System.out.println("Raw Response:");
        //System.out.println(response.body());
        String responseBody = response.body();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(responseBody);
        if (!root.isArray() || root.isEmpty()) {
            ConsoleLogger.console("No versions found.");
            return;
        }

        String version = root.get(0)
                .path("version_number")
                .asText();

        //ConsoleLogger.console("KuraAC| " + version);
        if (isNewerVersion(pluginVer, version)) {
            ConsoleLogger.console("Please update to version " + version);
        } else {
            ConsoleLogger.console("You are up to date");
        }
    }
    private static int parseVersionPart(String part) {
        if (part == null || part.isEmpty()) {
            return 0;
        }

        int end = 0;
        while (end < part.length() && Character.isDigit(part.charAt(end))) {
            end++;
        }

        if (end == 0) {
            return 0;
        }

        try {
            return Integer.parseInt(part.substring(0, end));
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public static boolean isNewerVersion(String current, String latest) {
        String[] currentParts = current.split("\\.");
        String[] latestParts = latest.split("\\.");

        int maxLength = Math.max(currentParts.length, latestParts.length);

        for (int i = 0; i < maxLength; i++) {

            int currentValue =
                    i < currentParts.length
                            ? parseVersionPart(currentParts[i])
                            : 0;

            int latestValue =
                    i < latestParts.length
                            ? parseVersionPart(latestParts[i])
                            : 0;

            if (latestValue > currentValue) {
                return true;
            }

            if (latestValue < currentValue) {
                return false;
            }
        }

        return false;
    }
}
