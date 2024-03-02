package dev.gash.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import dev.gash.game.Game;
import dev.gash.game.window.WindowService;

import javax.swing.*;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		WindowService window = new WindowService();
		window.create();
	}
}
