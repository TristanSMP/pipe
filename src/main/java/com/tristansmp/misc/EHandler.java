package com.tristansmp.misc;

import com.earth2me.essentials.Essentials;
import net.ess3.api.IEssentials;
import net.ess3.api.events.MuteStatusChangeEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerRiptideEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.ArrayList;
import java.util.List;

public class EHandler implements Listener {
    static Essentials ess = (Essentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials");

    @EventHandler
    public void onMute(MuteStatusChangeEvent event){
        if(event.getAffected() instanceof Player){
            Player player = (Player) event.getAffected();
            if(ess.getUser(player).isMuted()){
                player.sendMessage("§cYou have been muted!");
            } else {
                player.sendMessage("§aYou have been unmuted!");
            }
        }
    }


}
