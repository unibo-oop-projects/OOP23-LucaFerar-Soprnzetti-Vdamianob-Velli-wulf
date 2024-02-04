package javawulf.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javawulf.controller.PlayerStatus;
import javawulf.model.Collectable;
import javawulf.model.powerUp.PowerUp;
import javawulf.model.powerUp.PowerUpAttack;
import javawulf.model.powerUp.PowerUpDoublePoints;
import javawulf.model.powerUp.PowerUpInvincibility;
import javawulf.model.powerUp.PowerUpSpeed;

/**
 *  Implementation for drawing all Power Ups
 */
public final class PowerUpsDrawer extends AbstractDrawer {
        
        private final Map<Class<? extends Collectable>, BufferedImage> images = new HashMap<>();
        private final List<PowerUp> powerUps;

        /**
         * Builds the power ups passed from the Controller.
         * 
         * @param gamePanel the Game Panel where the items must be drawn
         * @param items     a list of all the items to draw
         * @param player    the current status of the Player character
         */
        public PowerUpsDrawer(final GamePanel gamePanel, final List<PowerUp> powerUps, final PlayerStatus playerStatus) {
            super(gamePanel, playerStatus);
            this.powerUps = powerUps;
            try {
                images.put(PowerUpAttack.class, this.imageLoader(ImagePath.POWERUP_STRENGTH));
                images.put(PowerUpInvincibility.class, this.imageLoader(ImagePath.POWERUP_INVINCIBILITY));
                images.put(PowerUpDoublePoints.class, this.imageLoader(ImagePath.POWERUP_DOUBLEPOINTS));
                images.put(PowerUpSpeed.class, this.imageLoader(ImagePath.POWERUP_SPEED));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        @Override
        public void draw(final Graphics2D graphics) {
            for (final Collectable powerUp : powerUps) {
                final BufferedImage image = images.get(powerUp.getClass());
                if (image != null) {
                    this.drawImage(graphics, image,(int) powerUp.getBounds().getCollisionArea().getX(),
                    (int) powerUp.getBounds().getCollisionArea().getY());
                }
            }
        }


}
