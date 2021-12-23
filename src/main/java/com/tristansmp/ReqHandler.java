package com.tristansmp;

import express.Express;
import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.dependencies.jda.api.entities.User;
import github.scarsz.discordsrv.util.DiscordUtil;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

import static com.gmail.nossr50.api.ExperienceAPI.getPlayerRankSkill;
import static com.gmail.nossr50.api.ExperienceAPI.getPowerLevelOffline;
import static org.bukkit.Bukkit.*;

public class ReqHandler {
    Express app = new Express();

    public ReqHandler() {

        app.get("/", (req, res) -> {
            res.send("this is tristan's very secret api for tristansmp, nick go away");
        });

        app.get("/player/:username/discord", (req, res) -> {
            final String username = req.getParams().get("username");
            final OfflinePlayer player = getOfflinePlayer(username);
            final String discordId = DiscordSRV.getPlugin().getAccountLinkManager().getDiscordId(player.getUniqueId());
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
                    obj.put("discordId", discordId);
                    obj.put("discordTag", user.getAsTag());
                    obj.put("discordName", user.getName());
                    res.send(obj.toJSONString());
                    return;
                }
            }

        });

        app.get("/player/:username/mcmmo", (req, res) -> {
            // get uuid from username
            final String username = req.getParams().get("username");
            try {
                final Number powerLevel = getPowerLevelOffline(username);
                final JSONObject obj = new JSONObject();
                obj.put("error", false);
                obj.put("powerLevel", powerLevel);
                obj.put("excavation", getPlayerRankSkill(username, "Excavation"));
                obj.put("fishing", getPlayerRankSkill(username, "Fishing"));
                obj.put("Herbalism", getPlayerRankSkill(username, "Herbalism"));
                obj.put("mining", getPlayerRankSkill(username, "Mining"));
                obj.put("woodcutting", getPlayerRankSkill(username, "Woodcutting"));
                obj.put("archery", getPlayerRankSkill(username, "Archery"));
                obj.put("axes", getPlayerRankSkill(username, "Axes"));
                obj.put("swords", getPlayerRankSkill(username, "Swords"));
                obj.put("taming", getPlayerRankSkill(username, "Taming"));
                obj.put("unarmed", getPlayerRankSkill(username, "Unarmed"));
                obj.put("acrobatics", getPlayerRankSkill(username, "Acrobatics"));
                obj.put("alchemy", getPlayerRankSkill(username, "Alchemy"));
                obj.put("repair", getPlayerRankSkill(username, "Repair"));

                res.send(obj.toJSONString());
            } catch (Exception e) {
                final JSONObject obj = new JSONObject();
                obj.put("error", true);
                obj.put("message", e.getMessage());
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
