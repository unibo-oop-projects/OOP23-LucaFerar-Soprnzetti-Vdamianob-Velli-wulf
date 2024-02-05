package javawulf.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javawulf.controller.PlayerStatus;
import javawulf.model.item.AmuletPiece;

/**
 * Implementation to draw the amulet pieces.
 */
public final class AmuletPiecesDrawer extends AbstractDrawer {

    private BufferedImage amuletPiece;

    private final List<AmuletPiece> amuletPieces;

    public AmuletPiecesDrawer(final GamePanel gamePanel, final PlayerStatus player,
            final List<AmuletPiece> amuletPieces) {
        super(gamePanel, player);
        this.amuletPieces = amuletPieces;
        try {
            this.amuletPiece = this.imageLoader(ImagePath.AMULET_PIECE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(final Graphics2D graphics) {
        for (final AmuletPiece piece : amuletPieces) {
            this.drawImage(graphics, this.amuletPiece, (int) piece.getBounds().getCollisionArea().getX(),
                    (int) piece.getBounds().getCollisionArea().getY());
        }
    }
}
