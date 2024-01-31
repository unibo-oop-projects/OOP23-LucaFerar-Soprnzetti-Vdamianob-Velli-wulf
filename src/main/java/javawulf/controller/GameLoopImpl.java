package javawulf.controller;

import javawulf.model.map.Map;
import javawulf.model.map.factory.MapFactoryImpl;
// import javawulf.model.player.Player;
// import javawulf.model.player.PlayerImpl;
import javawulf.view.GamePanel;

public final class GameLoopImpl implements GameLoop, Runnable {

    private static final int FPS = 60;
    private static final int NANOSECONDS = 1_000_000_000;
    private long lastTime;
    private long currentTime;
    private long timer;
    private double delta;
    private double interval;
    private Thread gameLoopThread;
    private int drawCount = 0;
    private final GamePanel gamePanel;
    private Map gameMap;
    // private Player gamePlayer;


    public GameLoopImpl(final GamePanel panel) {
        mapInit();
        playerInit();
        this.gamePanel = panel;
    }

    private void mapInit() {
        this.gameMap = new MapFactoryImpl().getDefaultMap1();
    }

    private void playerInit() {
        // this.gamePlayer = new PlayerImpl(100, 100, 3, 10);
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
            System.out.println("GP height: " + this.gamePanel.getHeight());
            System.out.println("GP width: " + this.gamePanel.getWidth());
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
        return gameMap;
    }


}