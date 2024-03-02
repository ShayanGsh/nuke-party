package dev.gash.game.window;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import dev.gash.game.Game;

public class WindowService {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    public WindowService() {
        config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setWindowedMode(1366, 768);
        config.setTitle("Nuke Party");
    }
    public void create() {
        new Lwjgl3Application(new Game(), config);
    }
}
