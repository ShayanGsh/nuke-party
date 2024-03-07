package dev.gash.game;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import dev.gash.game.controller.OrthoCamController;
import dev.gash.game.map.PerlinMapGenerator;
import dev.gash.game.map.TileSelector;
import dev.gash.game.map.layers.GroundLayer;

public class Game extends ApplicationAdapter {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private TiledMap map;
	private MapRenderer mapRenderer;
	private OrthoCamController cameraController;
	private Stage stage;
	@Override
	public void create() {

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1920, 1080);
		cameraController = new OrthoCamController(camera);
		batch = new SpriteBatch();
		stage = new Stage(new ScreenViewport(camera));

		var inputMultiplexer = new InputMultiplexer(stage, cameraController);
		Gdx.input.setInputProcessor(inputMultiplexer);

		map = new PerlinMapGenerator(200, 200)
				.generate(new GroundLayer());
		mapRenderer = new OrthogonalTiledMapRenderer(map);
	}

	@Override
	public void render() {
		// process user input
		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
		}


		ScreenUtils.clear(Color.valueOf("#4f42b5 "));
		camera.update();
		stage.draw();
	}

	@Override
	public void dispose() {
		batch.dispose();
		map.dispose();
	}
}
