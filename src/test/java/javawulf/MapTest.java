package javawulf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import javawulf.model.BoundingBox;
import javawulf.model.BoundingBoxImpl;
import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.enemy.Pawn;
import javawulf.model.map.*;
import javawulf.model.map.factory.MapFactoryImpl;
import javawulf.model.player.PlayerImpl;

/**
 * Some tests for try rooms, corridors, biomes, and getTileTypes
 */
public final class MapTest {

    @Test
    void testRooms() {
        assertEquals(TileType.ROOM, Room.DEFAULT_TYPE);

        Space roomA = new Room(10, 10);
        assertEquals(10, roomA.getWidth());
        assertEquals(10, roomA.getHeight());

        Space roomB = new Room(6, 9);
        assertEquals(6, roomB.getWidth());
        assertEquals(9, roomB.getHeight());
    }

    @Test
    void testCorridors() {
        assertEquals(TileType.CORRIDOR, Corridor.DEFAULT_TYPE);

        Space corridor1 = new Corridor(10, 5);
        assertEquals(10, corridor1.getWidth());
        assertEquals(5, corridor1.getHeight());

        Space corridor2 = new Corridor(6, 3);
        assertEquals(6, corridor2.getWidth());
        assertEquals(3, corridor2.getHeight());
    }

    @Test
    void testBiomes() {
        Biome biome = new BiomeImpl();
        biome.addRoom(new TilePosition(5, 5), new Room(10, 10));
        biome.addRoom(new TilePosition(10, 5), new Room(7, 6));

        assertEquals(2, biome.getRooms().size());
        assertEquals(0, biome.getCorridors().size());

        assertThrows(IllegalArgumentException.class,
                () -> biome.addRoom(new TilePosition(200, 500), new Room(5, 4)));
    }

    private Map gameMapExample;
    private Biome firstBiome, secondBiome, thirdBiome, fourthBiome;

    /**
     * Setups four biomes and the Map
     */
    void setUp() {
        firstBiome = new BiomeImpl();
        firstBiome.addRoom(new TilePosition(1, 1), new Room(10, 10));

        secondBiome = new BiomeImpl();
        secondBiome.addRoom(new TilePosition(1, 1), new Room(7, 7));

        thirdBiome = new BiomeImpl();
        thirdBiome.addRoom(new TilePosition(1, 1), new Room(10, 10));

        fourthBiome = new BiomeImpl();
        fourthBiome.addRoom(new TilePosition(1, 1), new Room(10, 10));

        this.gameMapExample = new MapImpl(null, firstBiome, secondBiome, thirdBiome, fourthBiome);
    }

    void setUpWithEntities() {
        firstBiome = new BiomeImpl();
        Room roomA = new Room(10, 10);
        roomA.addGameElement(new Pawn(new CoordinateImpl(0, 0)));
        firstBiome.addRoom(new TilePosition(1, 1), roomA);

        secondBiome = new BiomeImpl();
        secondBiome.addRoom(new TilePosition(1, 1), new Room(7, 7));

        thirdBiome = new BiomeImpl();
        thirdBiome.addRoom(new TilePosition(1, 1), new Room(10, 10));

        fourthBiome = new BiomeImpl();
        fourthBiome.addRoom(new TilePosition(1, 1), new Room(10, 10));

        this.gameMapExample = new MapImpl(null, firstBiome, secondBiome, thirdBiome, fourthBiome);
    }

    @Test
    void testMap() {
        this.setUp();

        // Coordinate che ricadono nella prima Tile in alto a sx della Mappa, che devono
        // essere di tipo WALL
        assertEquals(TileType.WALL, gameMapExample.getTileType(new CoordinateImpl(0, 0)).get());
        assertEquals(TileType.WALL, gameMapExample.getTileType(new CoordinateImpl(23, 23)).get());

        // Coordinate che ricadono nelle Tiles della unica stanza contenuta nel primo
        // bioma
        assertEquals(TileType.ROOM, gameMapExample.getTileType(new CoordinateImpl(24, 24)).get());
        assertEquals(TileType.ROOM, gameMapExample.getTileType(new CoordinateImpl(100, 100)).get());

        // Coordinate che ricade al di fuori della stanza del primo bioma (quindi
        // dev'essere un WALL)
        assertEquals(TileType.WALL, gameMapExample.getTileType(new CoordinateImpl(1000, 1000)).get());

        // Coordinata che ricade nel bioma centrale (+) dove non ci sono corridoi
        // (quindi dev'essere un WALL)
        assertEquals(TileType.WALL,
                gameMapExample.getTileType(new CoordinateImpl(TileType.TILE_DIMENSION * Biome.SIZE, 0)).get());

        // Coordinate che ricadono nelle Tiles della unica stanza contenuta nel secondo
        // bioma
        assertEquals(TileType.ROOM,
                gameMapExample.getTileType(
                        new CoordinateImpl(TileType.TILE_DIMENSION * (Biome.SIZE + Map.WIDTH_CENTRAL_BIOME) + 24, 24))
                        .get());
        assertEquals(TileType.ROOM,
                gameMapExample.getTileType(
                        new CoordinateImpl(TileType.TILE_DIMENSION * (Biome.SIZE + Map.WIDTH_CENTRAL_BIOME
                                + secondBiome.getRooms().get(0).getValue().getWidth()) - 1, 24))
                        .get());

        // Coordinate che ricadono fuori dalla mappa (deve restituire quindi un
        // Optional.empty)
        assertEquals(Optional.empty(),
                gameMapExample.getTileType(new CoordinateImpl(Map.MAP_SIZE * TileType.TILE_DIMENSION, 0)));
    }

    @Test
    void testMapEntityInteraction() {
        this.setUp();

        // EntityBox coincide perfettamente con la tile di posizione (0, 0) che è un
        // muro
        assertEquals(Set.of(TileType.WALL),
                gameMapExample.getTileTypes(new BoundingBoxImpl(12, 12, 24, 24, BoundingBox.CollisionType.PLAYER)));

        // EntityBox interseca 4 tile differenti, 3 di tipo WALL e 1 di tipo ROOM
        assertEquals(Set.of(TileType.WALL, TileType.ROOM),
                gameMapExample.getTileTypes(new BoundingBoxImpl(13, 13, 24, 24, BoundingBox.CollisionType.PLAYER)));

        // EntityBox si trova dentro una stanza
        assertEquals(Set.of(TileType.ROOM),
                gameMapExample.getTileTypes(new BoundingBoxImpl(200, 200, 24, 24, BoundingBox.CollisionType.PLAYER)));

        // EntityBox passato è fuori dalla mappa: in tal caso, dovrebbe restituire un
        // Set vuoto
        assertEquals(Set.of(),
                gameMapExample.getTileTypes(new BoundingBoxImpl(Map.MAP_SIZE * TileType.TILE_DIMENSION + 24, 0, 24, 24,
                        BoundingBox.CollisionType.PLAYER)));
    }

    @Test
    void testEntitiesInMap() {

        // La mappa inizializzata su setUp() non ha elementi (0)
        this.setUp();
        final int numberOfElements = 0;
        assertEquals(numberOfElements, gameMapExample.getAllElements().size());

        // Aggiunto un elemento Pawn (enemy) nella prima stanza del primo bioma: Ci si
        // aspetta l'elemento corrispondente.
        gameMapExample.getBiomes().get(BiomeQuadrant.FIRST.getPos()).getRooms().get(0).getValue()
                .addGameElement(new Pawn(new CoordinateImpl(0, 0)));
        assertEquals(Pawn.class, gameMapExample.getAllElements().get(0).getClass());

        Coordinate playerStartingPos = new CoordinateImpl(TileType.TILE_DIMENSION*3, TileType.TILE_DIMENSION*3);
        int health = 2;
        int startingPoints = 1;
        // Si noti che nella mappa di default #1, sono presenti complessivamente 15 stanze,
        // distribuite nei 4 biomi.
        Map gameMapExample2 = new MapFactoryImpl().getDefaultMap1(
                new PlayerImpl(playerStartingPos.getX(), playerStartingPos.getY(), health, startingPoints));

        // Viene quindi aggiunto un elemento per ciascuna delle 15 stanze (gli elementi
        // attesi dovrebbero essere 15)
        final int expectedGameElem = 15;
        gameMapExample2.getBiomes().forEach(biome -> biome.getRooms()
                .forEach(roomPair -> roomPair.getValue().addGameElement(new Pawn(new CoordinateImpl(0, 0)))));
        assertEquals(expectedGameElem, gameMapExample2.getAllElements().size());
    }
}
