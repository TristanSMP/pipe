package com.tristansmp.routes;

import express.Express;
import github.scarsz.discordsrv.dependencies.jda.api.entities.User;
import github.scarsz.discordsrv.util.DiscordUtil;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

import java.util.UUID;

import static com.gmail.nossr50.api.ExperienceAPI.getLevelOffline;
import static com.gmail.nossr50.api.ExperienceAPI.getPowerLevelOffline;
import static github.scarsz.discordsrv.DiscordSRV.getPlugin;
import static org.bukkit.Bukkit.*;

public class PlayersRoute {
    public PlayersRoute(Express app) {

        // Linked Discord //

        app.get("/players/username/:username/discord", (req, res) -> {
            final String username = req.getParams().get("username");
            final OfflinePlayer player = getOfflinePlayer(username);
            final String discordId = getPlugin().getAccountLinkManager().getDiscordId(player.getUniqueId());
            final JSONObject obj = new JSONObject();
            if (discordId == null) {
                obj.put("error", true);
                obj.put("message", "Player not linked to discord");
                res.send(obj.toJSONString());
                return;
            } else {
                User user = DiscordUtil.getJda().getUserById(discordId);
                if (user == null) {
                    obj.put("error", true);
                    obj.put("message", "Couldn't find Discord User by ID. Maybe they left the tsmp server?");
                    res.send(obj.toJSONString());
                    return;
                } else {
                    obj.put("error", false);
                    obj.put("username", username);
                    obj.put("uuid", player.getUniqueId().toString());
                    obj.put("discordId", discordId);
                    obj.put("discordTag", user.getAsTag());
                    obj.put("discordName", user.getName());
                    res.send(obj.toJSONString());
                    return;
                }
            }
        });

        // McMMO //

        app.get("/players/uuid/:uuid/mcmmo", (req, res) -> {
            final UUID uuid = UUID.fromString(req.getParams().get("uuid"));

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

        // Online Player Stats //

        app.get("/players/username/:username/stats", (req, res) -> {
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
        });
    }
}
