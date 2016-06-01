# Battle Royale Bukkit Plugin

This plugin creates a Battle Royal in a Minecraft Bukkit Hardcore world. You are given a starting gift and spread out across a small world enclosed
in a world border. Every 5 minutes an entire chunk of the world explodes. Be the last one standing to claim victory.

## Development Setup
- Great tutorial on general Bukkit development setup [here](http://wiki.bukkit.org/Plugin_Tutorial)
- Maven command to build and install the plugin jar:  `mvn clean install -DoutputDirectory=/Users/Grant/code/minecraft-servers/localhost`
- After Maven has built the plugin jar and placed it in the plugins folder of your local bukkit server you can setup an IntelliJ Jar Application configuration to run the server and allow remote debugging. I use the following values in my configuration.
    - location of spigot-server.jar (or other bukkit jar file)
    - JVM Options: -Xmx4G -XX:+UseG1GC -XX:MaxGCPauseMillis=50
    - Program Arguments: nogui

## Commands
- /beginBattleRoyale \[battleName] \{player]...
    - Starts a Battle Royale on the server with the given battleName which includes the players listed.
- /pauseBattleRoyale
    - Pauses current battle. Chunk destruction pauses. Players are put into adventure mode and pvp is turned off
- /resumeBattleRoyale
    - Resumes the current battle.

## Starting Player Gifts
Each player starts with a random player gift. Some are better than others.

- Angel
- AquaMan
- Berserker
- BlinkingSpirit
- Demolitionist
- Devil
- Ender
- Glutton
- MacGyver
- Mole
- Necromancer
- SeaWalker
- Stalker
- Übermensch

## Catastrophies
After a set amount of time the world will experience a catastrophy. Hopefully you've won by then.

- Flood
- Nether Invasion (Not finished)
- Enderdragon Invasion
- Worldborder Collapse
- Lightning Storm (Not finished)
- Increased Land Destruction

## Dependencies
- Minecraft 1.9.2 client profile
- Bukkit 1.9.2 server

## Upcoming features
- Victory celebration / powers
- Supply drops