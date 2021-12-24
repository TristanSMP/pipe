# TristanSMPAPI

A helpful API to communicate directly to `tristansmp.com`.

You can use this on your own server, just keep in mind you may want to edit the code as it has no config. Default port is `25567`.

Fun Fact: When you go to the root link it responds with
```
this is tristan's very secret api for tristansmp, nick go away
```

Nick is one of my friends whos always poking around with my api stuff. Just an inside joke.

# Examples

https://api.tristansmp.com/player/f5e658ea-fe2a-4ea7-8df1-d5c08af78a69/mcmmo

```
{
   "repair":1050.0,
   "fishing":282.0,
   "axes":328.0,
   "swords":10735.0,
   "powerLevel":3741,
   "alchemy":573.0,
   "Herbalism":52.0,
   "mining":840.0,
   "error":false,
   "acrobatics":10656.0,
   "woodcutting":248.0,
   "excavation":6262.0,
   "unarmed":986.0,
   "archery":280.0,
   "taming":1213.0
}
```

https://api.tristansmp.com/player/twisttaan/stats (only works when player is online, will fix soon)

```
{
   "kills":40892,
   "world":"world",
   "level":9,
   "health":20.0,
   "jumps":266945,
   "experience":0.19999996,
   "uuid":"f5e658ea-fe2a-4ea7-8df1-d5c08af78a69",
   "food":20,
   "deaths":647,
   "username":"twisttaan"
}
```

https://api.tristansmp.com/player/twisttaan/discord

```
{
   "discordId":"97470053615673344",
   "error":false,
   "discordTag":"tristan#0005",
   "discordName":"tristan"
}
```

# Support

If this helped you donating would be very kind as I do this as a hobby but hosting all this infrastructure costs quite a bit.
https://www.tristansmp.com/donate
