package com.tristansmp;

import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;


public class PlayerLookup {
    static CloseableHttpClient httpclient = HttpClients.createDefault();
    static Gson gson = new Gson();

    // Find out uuid by getting https://api.mojang.com/users/profiles/minecraft/:username
    // Then return the uuid
    public static UUID getUUID(String username) {
        final HttpGet httpGet = new HttpGet("https://api.mojang.com/users/profiles/minecraft/" + username);
        try {
            final CloseableHttpResponse res = httpclient.execute(httpGet);
            final profilesRes json = gson.fromJson(EntityUtils.toString(res.getEntity(), StandardCharsets.UTF_8), profilesRes.class);
            return UUID.fromString(json.id);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
