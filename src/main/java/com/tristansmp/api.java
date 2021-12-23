package com.tristansmp;

import express.Express;
import org.bukkit.plugin.java.JavaPlugin;

public final class api extends JavaPlugin {

    @Override
    public void onEnable() {

        Express app = new Express();

        app.get("/", (req, res) -> {
            res.send("Hello World");
        }).listen(); // Will listen on port 80 which is set as default



    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
