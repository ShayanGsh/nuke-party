package dev.gash.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

import static com.badlogic.gdx.math.MathUtils.random;

public class PerlinMapGenerator {
    int width;
    int height;
    int octaveCount = 4;
    float amplitude = 0.5f;
    long seed = random.nextLong();
    TextureRegion[][] splitTiles;
    public PerlinMapGenerator(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public PerlinMapGenerator(int width, int height, int octaveCount, float amplitude, long seed) {
        this.width = width;
        this.height = height;
        this.octaveCount = octaveCount;
        this.amplitude = amplitude;
        this.seed = seed;
    }

    public PerlinMapGenerator setTileset(Texture tileSet, int width, int height) {
        this.splitTiles = TextureRegion.split(tileSet, width, height);
        return this;
    }

    public PerlinMapGenerator setTileset(TextureRegion[][] splitTiles) {
        this.splitTiles = splitTiles;
        return this;
    }

    public PerlinMapGenerator setOctaveCount(int octaveCount) {
        this.octaveCount = octaveCount;
        return this;
    }

    public PerlinMapGenerator setAmplitude(float amplitude) {
        this.amplitude = amplitude;
        return this;
    }

    public PerlinMapGenerator setSeed(long seed) {
        this.seed = seed;
        return this;
    }

    public TiledMap generate() {
        if (splitTiles == null) {
            throw new RuntimeException("Tileset not set");
        }
        TiledMap map = new TiledMap();
        MapLayers layers = map.getLayers();

        var perlinNoise = new PerlinNoise(width, height, octaveCount, amplitude, seed);
        var noiseGrid = perlinNoise.getNoiseGrid();
        TiledMapTileLayer groundLayer = new TiledMapTileLayer(width, height, 32, 32);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                float noise = noiseGrid[i][j];

                if (noise < 0.5) {
                    cell.setTile(new StaticTiledMapTile(splitTiles[0][1]));
                } else {
                    cell.setTile(new StaticTiledMapTile(splitTiles[0][0]));
                }

                groundLayer.setCell(i, j, cell);
            }
        }
        layers.add(groundLayer);

        return map;
    }
}
