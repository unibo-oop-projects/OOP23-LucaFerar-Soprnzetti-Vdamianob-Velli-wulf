package javawulf.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.imageio.ImageIO;

import javawulf.model.enemy.Pawn;

/**
 * Implementation to draw the pawn.
 */
public final class PawnDrawer implements Drawer {

    private BufferedImage pawnUp;
    private BufferedImage pawnDown;
    private BufferedImage pawnLeft;
    private BufferedImage pawnRight;

    private final List<Pawn> pawns;

    /**
     * The Pawns coming from the Controller.
     * 
     * @param pawns     The Pawns that must be drawn
     * @param gamePanel The game panel that must be updated
     */
    public PawnDrawer(final List<Pawn> pawns, final GamePanel gamePanel) {
        this.pawns = pawns;
        try {
            this.pawnUp = ImageIO.read(getClass().getResourceAsStream(ImagePath.PAWN_UP.getPath()));
            this.pawnDown = ImageIO.read(getClass().getResourceAsStream(ImagePath.PAWN_DOWN.getPath()));
            this.pawnLeft = ImageIO.read(getClass().getResourceAsStream(ImagePath.PAWN_LEFT.getPath()));
            this.pawnRight = ImageIO.read(getClass().getResourceAsStream(ImagePath.PAWN_RIGHT.getPath()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(final Graphics2D graphics) {
        for (final Pawn pawn : this.pawns) {
            BufferedImage imgPawn;
            switch (pawn.getDirection()) {
                case UP:
                    imgPawn = this.pawnUp;
                    break;
                case DOWN:
                    imgPawn = this.pawnDown;
                    break;
                case LEFT:
                    imgPawn = this.pawnLeft;
                    break;
                case RIGHT:
                    imgPawn = this.pawnRight;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid direction");
            }
            graphics.drawImage(imgPawn, pawn.getPosition().getX() * GamePanel.scale,
                    pawn.getPosition().getY() * GamePanel.scale, GamePanel.tileSize, GamePanel.tileSize, null);
        }
    }

}
