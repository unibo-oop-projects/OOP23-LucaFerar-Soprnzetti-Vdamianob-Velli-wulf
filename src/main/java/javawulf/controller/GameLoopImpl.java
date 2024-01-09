package javawulf.controller;

import javawulf.view.GamePanel;

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
    private GamePanel gamePanel;

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
    }

    @Override
    public void startGameLoopThread() {
        this.gameLoopThread = new Thread(this);
        this.gameLoopThread.start();
        this.gamePanel = new GamePanel();
    }

}