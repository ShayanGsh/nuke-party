package dev.gash.game.map.layers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

public enum GroundTileDictionary {
    SEA(0, 0),
    OCEAN(0, 1),
    SAND(0, 2),
    LIGHT_GRASS(0, 3),
    DARK_GRASS(0, 4),
    DIRT(0, 5),
    MOUNTAIN(0, 6),
    SNOW(0, 7);

    private final int y;
    private final int x;

    GroundTileDictionary(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public static StaticTiledMapTile getTile(GroundTileDictionary tile, TextureRegion[][] region) {
        return new StaticTiledMapTile(region[tile.y][tile.x]);
    }
}
