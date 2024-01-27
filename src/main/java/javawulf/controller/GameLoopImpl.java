package javawulf.controller;

import javawulf.model.Entity;
import javawulf.model.map.Biome;
import javawulf.model.map.BiomeImpl;
import javawulf.model.map.Map;
import javawulf.model.map.MapImpl;
import javawulf.model.map.Room;
import javawulf.model.map.TilePosition;
import javawulf.model.player.PlayerImpl;
import javawulf.view.GamePanel;
import javawulf.view.ViewImpl;

public class GameLoopImpl implements GameLoop, Runnable {

    private final static int FPS = 60;
    private final static int NANOSECONDS = 1_000_000_000;
    private long lastTime;
    private long currentTime;
    private long timer;
    private double delta;
    private double interval;
    private Thread gameLoopThread;
    private int drawCount = 0;
    ViewImpl view;
    private GamePanel gamePanel;
    public Map mappa;
    private Entity giocatore;


    public GameLoopImpl() {
        view = new ViewImpl(this);
        mapInit();
        playerInit();
    }

    private void mapInit() {
        Biome primoBioma, secondoBioma, terzoBioma, quartoBioma;

        primoBioma = new BiomeImpl();
        primoBioma.addRoom(new TilePosition(1, 1), new Room(10, 10));

        secondoBioma = new BiomeImpl();
        secondoBioma.addRoom(new TilePosition(1, 1), new Room(7, 7));

        terzoBioma = new BiomeImpl();
        terzoBioma.addRoom(new TilePosition(1, 1), new Room(10, 10));

        quartoBioma = new BiomeImpl();
        quartoBioma.addRoom(new TilePosition(1, 1), new Room(10, 10));

        this.mappa = new MapImpl(primoBioma, secondoBioma, terzoBioma, quartoBioma);
    }

    private void playerInit() {
        this.giocatore = new PlayerImpl(100, 100, 3, 10);
    }

    @Override
    public void run() {
            this.interval = NANOSECONDS / FPS;
            lastTime = System.nanoTime();

            while (this.gameLoopThread != null) {
                this.gameLoopBody();
            }
    }

    private void gameLoopBody() {    
        this.currentTime = System.nanoTime();
        delta += (this.currentTime - this.lastTime) / this.interval;
        this.timer += (this.currentTime - this.lastTime);
        this.lastTime = this.currentTime;

        if (delta >= 1) {
            this.update();
            this.reDraw();
            this.delta--;
            this.drawCount++;
        }

        if (this.timer >= NANOSECONDS) {
            System.out.println("FPS: " + drawCount);
            this.drawCount = 0;
            this.timer = 0;
        }
    }

    private void update() {
        // Qui l'update degli elementi di gioco (giocatore, nemici, ...)
    }

    private void reDraw() {
        // Qui l'update della view...
        this.gamePanel.repaint();
    }

    @Override
    public void startGameLoopThread() {
        this.gameLoopThread = new Thread(this);
        this.gameLoopThread.start();
    }

    public Map getMap() {
        return mappa;
    }

    public Entity getGiocatore() {
        return giocatore;
    }

}