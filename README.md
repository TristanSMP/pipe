# TristanSMPAPI

A helpful API to communicate directly to `tristansmp.com`.

You can use this on your own server, just keep in mind you may want to edit the code as it has no config. Default port is `25567`.

Fun Fact: When you go to the root link it responds with
```
this is tristan's very secret api for tristansmp, nick go away
```

Nick is one of my friends whos always poking around with my api stuff. Just an inside joke.

# Examples

https://api.tristansmp.com/players/uuid/f5e658ea-fe2a-4ea7-8df1-d5c08af78a69/mcmmo

```
{
	"repair": 24,
	"fishing": 1,
	"axes": 56,
	"swords": 620,
	"powerLevel": 3742,
	"alchemy": 1,
	"Herbalism": 210,
	"mining": 778,
	"error": false,
	"acrobatics": 1068,
	"woodcutting": 128,
	"excavation": 620,
	"unarmed": 13,
	"archery": 192,
	"taming": 31
}
```

https://api.tristansmp.com/players/username/twisttaan/stats(only works when player is online, will fix soon)

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

https://api.tristansmp.com/players/username/twisttaan/discord

```
{
	"discordId": "97470053615673344",
	"error": false,
	"uuid": "f5e658ea-fe2a-4ea7-8df1-d5c08af78a69",
	"discordTag": "tristan#0005",
	"discordName": "tristan",
	"username": "twisttaan"
}
```

https://api.tristansmp.com/discord/users/id/97470053615673344/player

```
{
	"discordId": "97470053615673344",
	"error": false,
	"uuid": "f5e658ea-fe2a-4ea7-8df1-d5c08af78a69",
	"discordTag": "tristan#0005",
	"discordName": "tristan",
	"username": "twisttaan"
}
```

# Support

If this helped you donating would be very kind as I do this as a hobby but hosting all this infrastructure costs quite a bit.
https://www.tristansmp.com/donate
