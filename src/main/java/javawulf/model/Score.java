package javawulf.model;

/**
 * The class Score's objective is to take care of the current point total gotten
 * by the player during the game by increasing it accordingly considering the
 * current score multiplier 
 */
public interface Score {
    
    /**
     * Multiplier defines the current score multiplier applied to any added
     * points. It gets changed after obtaining a score boosting power-up.
     * Normally it is set to DEAFULT
     */
    public enum Multiplier{
        DEFAULT(1),
        DOUBLE(2);

        public final int value;

        private Multiplier(int value){
            this.value = value;
        }
        
        public int getValue(){
            return value;
        }
    }

    /**
     * @return The current point total
     */
    public int getPoints();

    /**
     * @return The current score multiplier the player is subject to
     */
    public int getMultiplier();

    /**
     * @param multiplier The multiplier the score must be subject to now
     */
    public void setMultiplier(Multiplier multiplier);

    /**
     * Add the point amount set as param. This amount is subject to the
     * current score multiplier, so the amount added is equal to
     * points * multiplier
     * @param points amount of points to add to the total
     */
    public void addPoints(int points);
}
