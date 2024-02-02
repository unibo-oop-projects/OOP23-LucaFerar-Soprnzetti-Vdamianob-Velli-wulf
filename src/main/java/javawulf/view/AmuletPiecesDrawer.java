package javawulf.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.imageio.ImageIO;

import javawulf.model.item.AmuletPiece;

/**
 * Implementation to draw the amulet pieces.
 */
public final class AmuletPiecesDrawer implements Drawer {

    private BufferedImage amuletPiece;

    private List<AmuletPiece> amuletPieces;

    /**
     * The AmuletPieces coming from the Controller.
     * 
     * @param amuletPieces A list of th Amulet Pieces that must be drawn
     */
    public AmuletPiecesDrawer(final List<AmuletPiece> amuletPieces) {
        this.amuletPieces = amuletPieces;
        try {
            this.amuletPiece = ImageIO.read(getClass().getResourceAsStream(ImagePath.AMULET_PIECE.getPath()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D graphics) {
        for (AmuletPiece Piece : amuletPieces) {
            graphics.drawImage(this.amuletPiece, Piece.getPosition().getX() * GamePanel.scale,
                    Piece.getPosition().getY() * GamePanel.scale, GamePanel.tileSize, GamePanel.tileSize, null);
        }
    }
}
