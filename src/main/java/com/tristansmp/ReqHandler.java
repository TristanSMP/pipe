package com.tristansmp;

import express.Express;

public class ReqHandler {

    public ReqHandler(Express app) {

        new com.tristansmp.routes.PlayersRoute(app);
        new com.tristansmp.routes.DiscordRoute(app);

        app.get("/", (req, res) -> {
            res.redirect("https://github.com/twisttaan/TristanSMPAPI");
        }).listen(25567);
    }
}
