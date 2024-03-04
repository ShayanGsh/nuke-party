package dev.gash.game.map;

import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

@FunctionalInterface
public interface TileSelector {
    StaticTiledMapTile select(float noise); // Expect the noise value to be between -1 and 1
}
