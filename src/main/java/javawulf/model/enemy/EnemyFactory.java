package javawulf.model.enemy;

import javawulf.model.Coordinate;

public class EnemyFactory {
    /** 
     * This enum represents the different types of enemies that can be created.
     * Each type has a name and a create method that creates an enemy of that type.
     */
    public enum EnemyType {

        PAWN("Pawn") {
            @Override
            public Enemy create(Coordinate position, Integer speed, int points) {
                return new Pawn(position, speed, points);
            }
        },
        GUARD("Guard") {
            @Override
            public Enemy create(Coordinate position, Integer speed, int points) {
                return new Guard(position, speed, points);
            }
        };

        private final String name;

        private EnemyType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public abstract Enemy create(Coordinate position, Integer speed, int points);
    }
    /**
     * This method creates an enemy of the type that it is called on.
     * @param type The type of enemy to be created.
     * @param position The position of the enemy.
     * @param speed The speed of the enemy.
     * @param points The points that the enemy is worth.
     * @return The enemy that was created.
     */
    public Enemy orderEnemy(EnemyType type, Coordinate position, Integer speed, int points) {
        return type.create(position, speed, points);
    }

}
