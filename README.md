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
   "repair":1050,
   "fishing":282,
   "axes":328,
   "swords":10735,
   "powerLevel":3741,
   "alchemy":573,
   "Herbalism":52,
   "mining":840,
   "error":false,
   "acrobatics":10656,
   "woodcutting":248,
   "excavation":6262,
   "unarmed":986,
   "archery":280,
   "taming":1213
}
```

https://api.tristansmp.com/player/twisttaan/stats (only works when player is online, will fix soon)

```
{
   "kills":40892,
   "world":"world",
   "level":9,
   "health":20,
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
