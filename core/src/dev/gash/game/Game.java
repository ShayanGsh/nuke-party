package dev.gash.game;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import dev.gash.game.map.PerlinMapGenerator;

public class Game extends ApplicationAdapter {
	private Texture dropImage;
	private Texture bucketImage;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private TiledMap map;
	private MapRenderer mapRenderer;

	@Override
	public void create() {
		// load the images for the droplet and the bucket, 64x64 pixels each
		dropImage = new Texture(Gdx.files.internal("badlogic.jpg"));
		bucketImage = new Texture(Gdx.files.internal("badlogic.jpg"));

		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1366, 768);
		batch = new SpriteBatch();

		map = new PerlinMapGenerator(200, 200)
				.setTileset(new Texture(Gdx.files.internal("map_ground_texture.png")), 32, 32)
				.generate();
		mapRenderer = new OrthogonalTiledMapRenderer(map);
	}

	@Override
	public void render() {
//		// process user input
//		if(Gdx.input.isTouched()) {
//			Vector3 touchPos = new Vector3();
//			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//			camera.unproject(touchPos);
//			bucket.x = touchPos.x - (float) 64 / 2;
//		}

		ScreenUtils.clear(Color.valueOf("#4f42b5 "));
		camera.update();
		mapRenderer.setView(camera);
		mapRenderer.render();

	}

	@Override
	public void dispose() {
		// dispose of all the native resources
		dropImage.dispose();
		bucketImage.dispose();
		batch.dispose();
	}
}
