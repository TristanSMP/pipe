package com.tristansmp;

import com.google.gson.JsonArray;
import express.Express;
import org.bukkit.plugin.java.JavaPlugin;

public final class api extends JavaPlugin {


    public static Express getApp() {
        return express;
    }

    @Override
    public void onEnable() {
    start();
    }

    @Override
    public void onDisable() {
    }

    public void start() {
        getLogger().info("Starting API");
        new ReqHandler(express);
        getLogger().info("API Started");
    }

    public static Express express = new Express();


}
