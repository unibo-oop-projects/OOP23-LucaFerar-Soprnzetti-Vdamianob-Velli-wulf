package javawulf.model.item;

import javawulf.model.Coordinate;

public class ItemFactory {
    /**
     * This enum contains all of the different types of items that can be created.
     * Each type has a name and a create method that creates an item of that type.
     * The create method is implemented in each enum value.
     */
    public enum ItemType {
        
        AMULET_PIECE("Amulet Piece") {
            @Override
            public Item create(Coordinate position, Integer points) {
                return new AmuletPiece(position, points);
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
    /**
     * This method creates an item of the type that it is called on.
     * @param type The type of item to be created.
     * @param position The position of the item.
     * @param points The points that the item is worth.
     * @return The item that was created.
     */
    public Item orderItem(ItemType type, Coordinate position, Integer points) {
        return type.create(position, points);
    }
}
