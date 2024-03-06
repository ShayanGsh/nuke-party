package dev.gash.game.map.layers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import dev.gash.game.map.TileSelector;

public class GroundLayer implements TileSelector {
    private final String texturePath;
    private TextureRegion[][] splitTiles;

    public GroundLayer(String texturePath) {
        this.texturePath = texturePath;
        loadTexture();
    }

    public GroundLayer() {
        this("map_ground_texture.png");
    }

    private void loadTexture() {
        Texture texture = new Texture(Gdx.files.internal(texturePath));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        splitTiles = TextureRegion.split(texture,32, 32);
    }

    @Override
    public StaticTiledMapTile select(float noise) {
        if (noise < -0.1) {
            return GroundTileDictionary.getTile(GroundTileDictionary.OCEAN, splitTiles);
        } else if (noise < 0) {
            return GroundTileDictionary.getTile(GroundTileDictionary.SEA, splitTiles);
        } else if (noise < 0.2) {
            return GroundTileDictionary.getTile(GroundTileDictionary.SAND, splitTiles);
        } else if (noise < 0.3) {
            return GroundTileDictionary.getTile(GroundTileDictionary.LIGHT_GRASS, splitTiles);
        } else if (noise < 0.4) {
            return GroundTileDictionary.getTile(GroundTileDictionary.DARK_GRASS, splitTiles);
        } else if (noise < 0.5) {
            return GroundTileDictionary.getTile(GroundTileDictionary.DIRT, splitTiles);
        } else if (noise < 0.7) {
            return GroundTileDictionary.getTile(GroundTileDictionary.MOUNTAIN, splitTiles);
        } else {
            return GroundTileDictionary.getTile(GroundTileDictionary.SNOW, splitTiles);
        }
    }
}
