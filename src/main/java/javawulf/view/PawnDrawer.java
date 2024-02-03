package javawulf.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import javawulf.controller.PlayerStatus;
import javawulf.model.enemy.Pawn;

/**
 * Implementation to draw the pawn.
 */
public final class PawnDrawer extends AbstractDrawer {

    private BufferedImage pawn;

    private final List<Pawn> pawns;

    /**
     * The Pawn Drawer.
     * 
     * @param gamePanel the Game Panel where the pawn must be drawn
     * @param player    the current status of the Player character
     * @param pawns     a list of all the pawns to draw
     */
    public PawnDrawer(final GamePanel gamePanel, final PlayerStatus player, final List<Pawn> pawns) {
        super(gamePanel, player);
        this.pawns = pawns;
        try {
            this.pawn = this.imageLoader(ImagePath.PAWN_UP);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(final Graphics2D graphics) {
        for (final Pawn pawn : this.pawns) {
            String direction;
            switch (pawn.getDirection()) {
                case UP:
                    direction = "up";
                    break;
                case DOWN:
                    direction = "down";
                    break;
                case LEFT:
                    direction = "left";
                    break;
                case RIGHT:
                    direction = "right";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid direction");
            }
            final BufferedImage imgPawn = this.rotateImage(this.pawn, direction);
            this.drawImage(graphics, imgPawn, (int) pawn.getBounds().getCollisionArea().getX(),
                    (int) pawn.getBounds().getCollisionArea().getY());
        }
    }

}
