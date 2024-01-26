package javawulf.model.enemy;

import javawulf.model.Coordinate;

public class EnemyFactory {

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

        EnemyType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public abstract Enemy create(Coordinate position, Integer speed, int points);
    }

    public Enemy orderEnemy(EnemyType type, Coordinate position, Integer speed, int points) {
        return type.create(position, speed, points);
    }

}
