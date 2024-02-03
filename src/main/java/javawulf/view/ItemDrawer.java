package javawulf.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javawulf.model.Collectable;
import javawulf.model.item.Cure;
import javawulf.model.item.CureMax;
import javawulf.model.item.ExtraHeart;
import javawulf.model.item.GreatSword;
import javawulf.model.item.Shield;

/**
 * Implementation to draw the items collectable.
 */
public final class ItemDrawer extends AbstractDrawer {

    private final Map<Class<? extends Collectable>, BufferedImage> images = new HashMap<>();
    private final List<Collectable> items;

    public ItemDrawer(final GamePanel gamePanel, final List<Collectable> items) {
        super(gamePanel);
        this.items = items;
        try {
            images.put(Cure.class, this.imageLoader(ImagePath.CURE));
            images.put(CureMax.class, this.imageLoader(ImagePath.CURE_MAX));
            images.put(ExtraHeart.class, this.imageLoader(ImagePath.EXTRA_HEART));
            images.put(GreatSword.class, this.imageLoader(ImagePath.GREATSWORD));
            images.put(Shield.class, this.imageLoader(ImagePath.CURE));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void draw(final Graphics2D graphics) {
        for (final Collectable item : items) {
            BufferedImage image = images.get(item.getClass());
            if (image != null) {
                // TODO waiting for the utility method in AbstractDrawer
                int itemX = 0;
                int itemY = 0;
                graphics.drawImage(image, itemX, itemY, GamePanel.tileSize, GamePanel.tileSize, null);
            }
        }
    }

}
