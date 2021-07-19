package com.bilicraft.spectatefixbukkit;

import java.util.UUID;

public class MessagePacket {
    private final UUID player;

    public MessagePacket(UUID player, UUID target) {
        this.player = player;
        this.target = target;
    }

    private final UUID target;

    public UUID getTarget() {
        return target;
    }

    public UUID getPlayer() {
        return player;
    }
}
