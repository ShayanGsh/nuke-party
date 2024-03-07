package dev.gash.game.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PerlinMapActor extends Actor {
    private final MapRenderer mapRenderer;

    public PerlinMapActor(MapRenderer mapRenderer) {
        this.mapRenderer = mapRenderer;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        mapRenderer.setView(((OrthographicCamera) getStage().getCamera()));
        mapRenderer.render();
        batch.begin();
    }
}