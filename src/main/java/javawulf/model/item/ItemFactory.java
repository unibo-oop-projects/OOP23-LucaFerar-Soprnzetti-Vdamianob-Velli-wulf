package javawulf.model.item;

import javawulf.model.Coordinate;

public class ItemFactory {
    public enum ItemType {
        AMULET_FRAGMENTS("Amulet Fragments") {
            @Override
            public Item create(Coordinate position, Integer points) {
                return new AmuletFragments(position, points);
            }
        },
        GREAT_SWORD("Great Sword") {
            @Override
            public Item create(Coordinate position, Integer points) {
                return new GreatSword(position, points);
            }
        },
        CURE("Cure") {
            @Override
            public Item create(Coordinate position, Integer points) {
                return new Cure(position, points);
            }
        },
        CURE_MAX("Cure Max") {
            @Override
            public Item create(Coordinate position, Integer points) {
                return new CureMax(position, points);
            }
        },
        EXTRA_HEART("Extra Heart") {
            @Override
            public Item create(Coordinate position, Integer points) {
                return new ExtraHeart(position, points);
            }
        },
        MINIMAP("Minimap") {
            @Override
            public Item create(Coordinate position, Integer points) {
                return new Minimap(position, points);
            }
        },
        SHIELD("Shield") {
            @Override
            public Item create(Coordinate position, Integer points) {
                return new Shield(position, points);
            }
        };

        private final String name;

        private ItemType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public abstract Item create(Coordinate position, Integer points);
    }

    public Item orderItem(ItemType type, Coordinate position, Integer points) {
        return type.create(position, points);
    }
}
