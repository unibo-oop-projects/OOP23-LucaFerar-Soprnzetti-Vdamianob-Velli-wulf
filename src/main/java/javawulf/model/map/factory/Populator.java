package javawulf.model.map.factory;

import java.util.Random;

import javawulf.model.Collectable;
import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.map.Biome;
import javawulf.model.map.BiomeQuadrant;
import javawulf.model.map.Map;
import javawulf.model.map.Space;
import javawulf.model.map.TilePosition;
import javawulf.model.map.TileType;
import javawulf.model.enemy.*;
import javawulf.model.item.*;
import javawulf.model.powerUp.*;

/**
 * Static class used to populate a map with items, power-up and enemies.
 */
public final class Populator {
    private final int halfTile = TileType.TILE_DIMENSION / 2;
    private final int objectDuration = 10;
    private final int points = 10;
    private String type = "Collectable";

    enum Collectables {
        CURE, CUREMAX, EXTRAHEART, GREATSWORD, SHIELD, POWERUPATTACK, POWERUPDOUBLEPOINTS, POWERUPSPEED,
        POWERUPINVINCIBILITY;

        public static Collectables getRandomCollectable() {
            Collectables[] colls = Collectables.values();
            Random r = new Random();
            return colls[r.nextInt(colls.length)];
        }
    }

    public Map populate(Map map) {
        for (var biomeQuadrant : BiomeQuadrant.values()) {
            Biome biome = map.getBiomes().get(biomeQuadrant.getPos());
            for (var room : biome.getRooms()) {
                room.getValue().addGameElement(
                        this.getRandomCollectable(this.getCentralTilePosition(room.getKey(), room.getValue(), biomeQuadrant)));
            }
        }
        return map;
    }

    /**
     * Utility method used for obtain central coordinate of a space (useful for
     * rooms).
     * 
     * @param space
     * @return central position.
     */
    private Coordinate getCentralTilePosition(TilePosition spacePos, Space space, BiomeQuadrant quadrant) {
        final int halfWidth = space.getWidth() / 2;
        final int halfHeight = space.getHeight() / 2;
        return new CoordinateImpl(
                (spacePos.getX() + quadrant.getOffset().getX() + halfWidth) * TileType.TILE_DIMENSION - halfTile,
                (spacePos.getY() + quadrant.getOffset().getY() + halfHeight) * TileType.TILE_DIMENSION - halfTile);
    }

    private Collectable getRandomCollectable(Coordinate coordColl) {
        Collectable coll;
        switch (Collectables.getRandomCollectable()) {
            case CURE:
                coll = new Cure(coordColl);
                break;
            case CUREMAX:
                coll = new CureMax(coordColl);
                break;
            case EXTRAHEART:
                coll = new ExtraHeart(coordColl);
                break;
            case GREATSWORD:
                coll = new GreatSword(coordColl);
                break;
            case SHIELD:
                coll = new Shield(coordColl);
                break;
            case POWERUPATTACK:
                coll = new PowerUpAttack(coordColl, objectDuration, points, type);
                break;
            case POWERUPDOUBLEPOINTS:
                coll = new PowerUpDoublePoints(coordColl, objectDuration, points, type);
                break;
            case POWERUPSPEED:
                coll = new PowerUpSpeed(coordColl, objectDuration, points, type);
                break;
            case POWERUPINVINCIBILITY:
                coll = new PowerUpInvincibility(coordColl, objectDuration, points, type);
                break;
            default:
                coll = new Cure(coordColl);
        }
        return coll;
    }

}
