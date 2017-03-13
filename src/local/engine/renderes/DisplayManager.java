package local.engine.renderes;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {

	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	private static final int FPS = 120;
	private static final String TITLE = "GEOpenGL";

	public static void createDisplay() {

		ContextAttribs attribs = new ContextAttribs(3, 2);
		attribs.withForwardCompatible(true);
		attribs.withProfileCore(true);

		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create(new PixelFormat(), attribs);
			Display.setTitle(TITLE);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

	}

	public static void updateDisplay() {

		Display.sync(FPS);
		Display.update();

	}

	public static void closeDisplay() {

		Display.destroy();
	}

}
