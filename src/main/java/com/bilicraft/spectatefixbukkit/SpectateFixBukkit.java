package com.bilicraft.spectatefixbukkit;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpectateFixBukkit extends JavaPlugin {
    private final Gson gson = new Gson();

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "spectatefix:tp", (channel, receiver, message) -> {
            MessagePacket messagePacket = gson.fromJson(new String(message), MessagePacket.class);
            getLogger().info("Patching the spectate packet: "+ messagePacket.getPlayer() +" to "+messagePacket.getTarget());
            Player player = Bukkit.getPlayer(messagePacket.getPlayer());
            if(player == null){
                return;
            }
            if(player.getGameMode() != GameMode.SPECTATOR){
                return;
            }
            Player toPlayer = Bukkit.getPlayer(messagePacket.getTarget());
            if(toPlayer == null){
                return;
            }
            PlayerTeleportEvent event = new PlayerTeleportEvent(player,player.getLocation(),toPlayer.getLocation());
            Bukkit.getPluginManager().callEvent(event);
            if(event.isCancelled()){
                return;
            }
            player.teleport(toPlayer);
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
