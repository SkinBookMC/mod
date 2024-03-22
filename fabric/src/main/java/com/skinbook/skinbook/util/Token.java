package com.skinbook.skinbook.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Token {
    private static final Logger LOGGER = LoggerFactory.getLogger("SkinBook");
    public static String getToken(String playerUUID) {
        String token = null;

        try {
            LOGGER.info("Getting token for player: " + playerUUID);
            URL url = new URL("https://backend.naibuu.dev/auth/token?uuid=" + playerUUID);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if(conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            StringBuilder response = new StringBuilder();
            while ((output = br.readLine()) != null) {
                response.append(output);
            }

            JsonParser parser = new JsonParser();
            JsonObject json = parser.parse(response.toString()).getAsJsonObject();
            token = json.get("token").getAsString();

        } catch (Exception e ) {
            LOGGER.error("Failed to get token for player: " + playerUUID);
            LOGGER.error(e.getMessage());
        }
        return token;
    }
}
