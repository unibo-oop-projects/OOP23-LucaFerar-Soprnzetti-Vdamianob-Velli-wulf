package javawulf.model.map.factory;

import java.util.ArrayList;
import java.util.Random;

import javafx.util.Pair;
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
 * Utility class used to populate a map with items, power-up and enemies.
 */
public final class Populator {
    private static final int halfTile = TileType.TILE_DIMENSION / 2;
    private static final int objectDuration = 10;
    private static final int points = 10;
    private static String type = "Collectable";

    enum Collectables {
        CURE, CUREMAX, EXTRAHEART, GREATSWORD, SHIELD, POWERUPATTACK, POWERUPDOUBLEPOINTS, POWERUPSPEED,
        POWERUPINVINCIBILITY;

        public static Collectables getRandomic() {
            Collectables[] colls = Collectables.values();
            Random r = new Random();
            return colls[r.nextInt(colls.length)];
        }
    }

    public static Map populate(Map map) {
        for (var biomeQuadrant : BiomeQuadrant.values()) {
            Biome biome = map.getBiomes().get(biomeQuadrant.getPos());
            Pair<TilePosition, Space> randRoom = getRandomicSpace(biome.getRooms());
            AmuletPiece biomeAmulet = new AmuletPiece(
                    getCentralPosition(randRoom.getKey(), randRoom.getValue(), biomeQuadrant));
            randRoom.getValue().addGameElement(biomeAmulet);

            for (var room : biome.getRooms()) {
                if (!room.getValue().getElements().contains(biomeAmulet)) {
                    room.getValue().addGameElement(
                            getRandomCollectable(getCentralPosition(room.getKey(), room.getValue(), biomeQuadrant)));
                }

            }

            for (var corridor : biome.getCorridors()) {
                corridor.getValue().addGameElement(
                        new Pawn(getCentralPosition(corridor.getKey(), corridor.getValue(), biomeQuadrant)));
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
    private static Coordinate getCentralPosition(TilePosition spacePos, Space space, BiomeQuadrant quadrant) {
        final int halfWidth = space.getWidth() / 2;
        final int halfHeight = space.getHeight() / 2;
        return new CoordinateImpl(
                (spacePos.getX() + quadrant.getOffset().getX() + halfWidth) * TileType.TILE_DIMENSION + halfTile,
                (spacePos.getY() + quadrant.getOffset().getY() + halfHeight) * TileType.TILE_DIMENSION + halfTile);
    }

    /**
     * Utility method used for obtain a lateral coordinate of a space, next to the
     * center (useful for put
     * guards).
     * 
     * @param space
     * @return lateral position (lateral left is 'leftRight' is True or lateral
     *         right if 'leftRight' is False).
     */
    private static Coordinate getlateralPosition(TilePosition spacePos, Space space, BiomeQuadrant quadrant,
            boolean leftRight) {
        final int offSetPos;
        if (leftRight) {
            offSetPos = TileType.TILE_DIMENSION;
        } else {
            offSetPos = -TileType.TILE_DIMENSION;
        }
        final int halfWidth = space.getWidth() / 2;
        final int halfHeight = space.getHeight() / 2;
        return new CoordinateImpl(
                (spacePos.getX() + quadrant.getOffset().getX() + halfWidth) * TileType.TILE_DIMENSION + halfTile
                        + offSetPos,
                (spacePos.getY() + quadrant.getOffset().getY() + halfHeight) * TileType.TILE_DIMENSION + halfTile);
    }

    private static Collectable getRandomCollectable(Coordinate coordColl) {
        Collectable coll;
        switch (Collectables.getRandomic()) {
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

    /**
     * 
     * @param arraylist of spaces
     * @return a randomic space from arraylist
     */
    private static Pair<TilePosition, Space> getRandomicSpace(ArrayList<Pair<TilePosition, Space>> spaces) {
        return spaces.get(new Random().nextInt(spaces.size()));
    }

}