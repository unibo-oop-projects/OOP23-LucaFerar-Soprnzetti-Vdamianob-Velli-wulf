package javawulf.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javawulf.model.Collectable;
import javawulf.model.GameElement;
import javawulf.model.enemy.Pawn;
import javawulf.model.item.AmuletPiece;
import javawulf.model.item.Cure;
import javawulf.model.item.CureMax;
import javawulf.model.item.ExtraHeart;
import javawulf.model.item.GreatSword;
import javawulf.model.item.Shield;
import javawulf.model.map.Map;
import javawulf.model.map.factory.MapFactoryImpl;
import javawulf.model.player.Player;
import javawulf.model.player.PlayerImpl;
import javawulf.view.GamePanel;

/**
 * @see GameLoop
 */
public final class GameLoopImpl implements GameLoop, Runnable {

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
    private Player gamePlayer;
    private boolean attacking = false;
    private long swordTime;
    private PlayerController playerController;
    private final List<Collectable> items;
    private final List<Pawn> pawns;
    private final List<AmuletPiece> pieces;

    /**
     * 
     * @param panel's view.
     */
    public GameLoopImpl(final GamePanel panel) {
        this.items = new ArrayList<>();
        this.pawns = new ArrayList<>();
        this.pieces = new ArrayList<>();
        playerInit();
        mapInit();
        this.playerController = new PlayerControllerImpl();
        this.gamePanel = panel;
    }

    private void mapInit() {
        this.gameMap = new MapFactoryImpl().getDefaultMap1(this.gamePlayer);
        var elements = this.gameMap.getAllElements();
        this.items.addAll(elements.stream()
            .filter(this::isItem)
            .map(e -> (Collectable) e)
            .collect(Collectors.toList()));
        this.pawns.addAll(elements.stream()
            .filter(e -> e instanceof Pawn)
            .map(e -> (Pawn) e)
            .collect(Collectors.toList()));
        this.pieces.addAll(elements.stream()
            .filter(e -> e instanceof AmuletPiece)
            .map(e -> (AmuletPiece) e)
            .collect(Collectors.toList()));
    }

    private boolean isItem(GameElement e) {
        return e instanceof Collectable && (e instanceof Cure || e instanceof CureMax
                || e instanceof ExtraHeart || e instanceof GreatSword || e instanceof Shield);
    }

    private void playerInit() {
        this.gamePlayer = new PlayerImpl(70, 70, 3, 0);
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
            System.out.println("GP height: " + this.gamePanel.getHeight()
                    + " GP width: " + this.gamePanel.getWidth());
            this.drawCount = 0;
            this.timer = 0;
        }
    }

    private void update() {
        if (this.playerController.getDirection().isPresent() && !this.attacking) {
            try {
                this.gamePlayer.move(this.playerController.getDirection().get(), this.gameMap);
            } catch (Exception e) {
                System.out.println("There is a wall");
            }
        } else if (this.playerController.isAttack() && !this.attacking) {
            this.gamePlayer.attack();
            this.attacking = true;
            this.swordTime = System.nanoTime();
        }

        if (System.nanoTime() - this.swordTime >= NANOSECONDS / 2 && attacking) {
            this.gamePlayer.getSword().deactivate();
            this.attacking = false;
            this.swordTime = 0;
        }

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

    @Override
    public Map getMap() {
        return this.gameMap;
    }

    @Override
    public Player getPlayer() {
        return this.gamePlayer;
    }

    @Override
    public PlayerController getPlayerController() {
        return this.playerController;
    }

}
