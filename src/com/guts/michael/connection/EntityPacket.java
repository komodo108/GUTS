package com.guts.michael.connection;

import com.guts.michael.game.Direction;
import com.guts.michael.game.Entity;
import com.guts.michael.game.EntityType;
import com.guts.michael.game.IEntity;

public class EntityPacket extends Packet {

    private IEntity entity;

    public EntityPacket(IEntity entity) {
        this.entity = entity;
    }

    public IEntity getEntity() {
        return entity;
    }

    public static EntityPacket fromDataString(String data) {
        EntityType type = null;
        int x = 0;
        int y = 0;
        Direction orientation = null;
        for (String line : data.split("\n")) {
            String[] keyValue = line.split(": ");
            String key = keyValue[0];
            String value = keyValue[1];
            switch (key) {
                case "TYPE":
                    type = EntityType.valueOf(value);
                    break;
                case "X":
                    x = Integer.valueOf(value);
                    break;
                case "Y":
                    y = Integer.valueOf(value);
                    break;
                case "ORIENTATION":
                    orientation = Direction.valueOf(value);
            }
        }
        return new EntityPacket(new Entity(type, x, y, orientation));
    }

    @Override
    public PacketType getType() {
        return PacketType.ENTITY;
    }

    @Override
    public String asDataString() {
        return  "TYPE: " + entity.getType().name() + "\n" +
                "X: " + entity.getX() + "\n" +
                "Y: " + entity.getY() + "\n" +
                "ORIENTATION: " + entity.getOrientation().name();
    }
}
