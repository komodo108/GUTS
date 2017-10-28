package com.guts.michael.connection;

import com.guts.michael.game.Entity;
import com.guts.michael.game.IEntity;

public class EntityPacket extends Packet {

    private IEntity entity;

    public EntityPacket(IEntity entity) {
        this.entity = entity;
    }

    public IEntity getEntity() {
        return entity;
    }

    @Override
    public PacketType getType() {
        return PacketType.ENTITY;
    }

    @Override
    public String asDataString() {
        return null;
    }
}
