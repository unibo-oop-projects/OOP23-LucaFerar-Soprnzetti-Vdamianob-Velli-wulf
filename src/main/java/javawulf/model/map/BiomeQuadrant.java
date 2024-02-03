package javawulf.model.map;

    /**
     * Each element in BiomeQuadrant represent one of four biome of the map. Used
     * for get their offset positions.
     */
    public enum BiomeQuadrant {
        /**
         * First biome (upper-left).
         */
        FIRST(0, new TilePosition(0, 0)),
        /**
         * Second biome (upper-right).
         */
        SECOND(1, new TilePosition(Biome.SIZE + Map.WIDTH_CENTRAL_BIOME, 0)),
        /**
         * Third biome (downer-right).
         */
        THIRD(2, new TilePosition(Biome.SIZE + Map.WIDTH_CENTRAL_BIOME, Biome.SIZE + Map.WIDTH_CENTRAL_BIOME)),
        /**
         * Fourth biome (downer-left).
         */
        FOURTH(3, new TilePosition(0, Biome.SIZE + Map.WIDTH_CENTRAL_BIOME));

        private final int pos;
        private final TilePosition offset;

        BiomeQuadrant(final int pos, final TilePosition offset) {
            this.pos = pos;
            this.offset = offset;
        }

        public TilePosition getOffset() {
            return this.offset;
        }

        public int getPos() {
            return this.pos;
        }
    }