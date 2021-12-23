package com.tristansmp;

import org.bukkit.plugin.java.JavaPlugin;

public final class api extends JavaPlugin {

    @Override
    public void onEnable() {
    getLogger().info("Starting API");
    new ReqHandler();
     getLogger().info("API Started");
    }

    @Override
    public void onDisable() {
    }
}
