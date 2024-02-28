package dev.gash.game.map;

import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;

public class PerlinMapGenerator {
    PerlinNoiseCH perlinNoiseCH = new PerlinNoiseCH();
    PerlinNoiseG perlinNoiseG = new PerlinNoiseG(0);
    int x;
    int y;
    TiledMapTileSet tileSet;
    PerlinMapGenerator(int x, int y) {
        this.x = x;
        this.y = y;


    }

    public void generateCH() {
        perlinNoiseCH.noise(x, y, 0);
        TiledMap map = new TiledMap();
        map.getTileSets().
    }

    public void generateG() {
        perlinNoiseG.noise(x, y);
    }
}
