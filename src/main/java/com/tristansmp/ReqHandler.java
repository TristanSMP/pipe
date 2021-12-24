package com.tristansmp;

import express.Express;
import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.dependencies.jda.api.entities.User;
import github.scarsz.discordsrv.util.DiscordUtil;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

import java.util.UUID;

import static com.gmail.nossr50.api.ExperienceAPI.getLevelOffline;
import static com.gmail.nossr50.api.ExperienceAPI.getPowerLevelOffline;
import static com.tristansmp.PlayerLookup.getUUID;
import static org.bukkit.Bukkit.*;

public class ReqHandler {
    Express app = new Express();

    public ReqHandler() {

        app.get("/", (req, res) -> {
            res.send("this is tristan's very secret api for tristansmp, nick go away");
        });

        app.get("/player/:username/discord", (req, res) -> {
            final String username = req.getParams().get("username");
            final String discordId = DiscordSRV.getPlugin().getAccountLinkManager().getDiscordId(getUUID(username));

            if (discordId == null) {
                final JSONObject obj = new JSONObject();
                obj.put("error", true);
                obj.put("message", "Player not linked to discord");
                res.send(obj.toJSONString());
            } else {
                User user = DiscordUtil.getJda().getUserById(discordId);
                final JSONObject obj = new JSONObject();
                if (user == null) {
                    obj.put("error", true);
                    obj.put("message", "Couldn't find Discord User by ID. Maybe they left the tsmp server?");
                } else {
                    obj.put("error", false);
                    obj.put("discordId", discordId);
                    obj.put("discordTag", user.getAsTag());
                    obj.put("discordName", user.getName());
                }
                res.send(obj.toJSONString());
            }
        });

        app.get("/player/:username/mcmmo", (req, res) -> {
            final UUID uuid = getUUID(req.getParams().get("username"));

            try {
                final JSONObject obj = new JSONObject();

                // convert uuid to player
                final OfflinePlayer player = getOfflinePlayer(uuid);
                final Number powerLevel = getPowerLevelOffline(player.getUniqueId());

                obj.put("error", false);
                obj.put("powerLevel", powerLevel);
                obj.put("excavation", getLevelOffline(uuid, "Excavation"));
                obj.put("fishing", getLevelOffline(uuid, "Fishing"));
                obj.put("Herbalism", getLevelOffline(uuid, "Herbalism"));
                obj.put("mining", getLevelOffline(uuid, "Mining"));
                obj.put("woodcutting", getLevelOffline(uuid, "Woodcutting"));
                obj.put("archery", getLevelOffline(uuid, "Archery"));
                obj.put("axes", getLevelOffline(uuid, "Axes"));
                obj.put("swords", getLevelOffline(uuid, "Swords"));
                obj.put("taming", getLevelOffline(uuid, "Taming"));
                obj.put("unarmed", getLevelOffline(uuid, "Unarmed"));
                obj.put("acrobatics", getLevelOffline(uuid, "Acrobatics"));
                obj.put("alchemy", getLevelOffline(uuid, "Alchemy"));
                obj.put("repair", getLevelOffline(uuid, "Repair"));

                res.send(obj.toJSONString());
            } catch (Exception e) {
                final JSONObject obj = new JSONObject();
                obj.put("error", true);
                obj.put("message", e.getMessage());
                res.send(obj.toJSONString());
            }
        });

        app.get("/uuid/:username", (req, res) -> {
            getLogger().info("/uuid/" + req.getParams().get("username"));
            final String username = req.getParams().get("username");
            final UUID uuid = getUUID(username);
            final JSONObject obj = new JSONObject();
            if (uuid == null) {
                obj.put("error", true);
                obj.put("message", "Player not found");
                getLogger().info("Player not found");
                res.send(obj.toJSONString());
            } else {
                obj.put("error", false);
                obj.put("uuid", uuid.toString());
                getLogger().info("Player found");
                getLogger().info(uuid.toString());
                getLogger().info(obj.toJSONString());
                res.send(obj.toJSONString());
            }
        });


        app.get("/player/:username/stats", (req, res) -> {
            final String username = req.getParams().get("username");
            final JSONObject obj = new JSONObject();
            final Player player = getPlayer(username);

            try {
                // Player Username
                obj.put("username", player.getName());

                // Player UUID
                obj.put("uuid", player.getUniqueId().toString());

                // Player Health
                obj.put("health", player.getHealth());

                // Player Food
                obj.put("food", player.getFoodLevel());

                // Player World
                obj.put("world", player.getWorld().getName());

                // Player Experience
                obj.put("experience", player.getExp());

                // Player Level
                obj.put("level", player.getLevel());

                // Player Deaths
                obj.put("deaths", player.getStatistic(org.bukkit.Statistic.DEATHS));

                // Player Kills
                obj.put("kills", player.getStatistic(Statistic.MOB_KILLS));

                // Player Jumped
                obj.put("jumps", player.getStatistic(Statistic.JUMP));

                res.send(obj.toJSONString());
            } catch (Exception e) {
                if (e.getMessage().contains("Cannot invoke \"org.bukkit.entity.Player.getName()\" because \"player\" is null")) {
                    obj.put("error", true);
                    obj.put("message", "Player not online, or not found");
                } else {
                    getLogger().info("Error: " + e.getMessage());
                    res.send("Error: " + e.getMessage());
                }
            }
        }).listen(
                25567
        );
    }

    public Express getApp() {
        return app;
    }

}
