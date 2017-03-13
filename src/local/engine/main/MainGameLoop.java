package local.engine.main;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import local.engine.entities.Camera;
import local.engine.entities.Entity;
import local.engine.entities.Light;
import local.engine.files.OBJFile;
import local.engine.models.RawModel;
import local.engine.models.TexturedModel;
import local.engine.renderes.DisplayManager;
import local.engine.renderes.Loader;
import local.engine.renderes.MasterRenderer;
import local.engine.terrains.Terrain;
import local.engine.textures.ModelTexture;
import local.engine.utilities.OBJLoader;

public class MainGameLoop {

	public static void main(String[] args) {

		/**
		 * Create the Display
		 */
		DisplayManager.createDisplay();

		/**
		 * Crate the loader
		 */
		Loader loader = new Loader();

		/**
		 * The ambiance is the ambianc light.
		 */
		float ambiance = 0.07f;

		/**
		 * The sun is a light vector for the shader.
		 */
		Light sun = new Light(new Vector3f(128, 20, 74), new Vector3f(0.8f, 0.8f, 0.8f));

		/**
		 * The camera is used for the world position.
		 */
		Camera camera = new Camera(new Vector3f(128, 10, 128), new Vector3f(0, 0, 0));

		/**
		 * first terrain plain.
		 */
		Terrain terrain1 = new Terrain(0, 0, loader, new ModelTexture(loader.loadTexture("grass")));

		/**
		 * second terrain plain.
		 */
		Terrain terrain2 = new Terrain(1, 0, loader, new ModelTexture(loader.loadTexture("grass")));
		
		OBJFile objFile = new OBJFile("dragon", loader);

		/**
		 * Load the mesh dragon for a TexturedModel.
		 */
		RawModel model = objFile.process();

		/**
		 * Load the texture for the dragon mesh.
		 */
		ModelTexture texture = new ModelTexture(loader.loadTexture("dragon"));
		texture.setShineDamper(10);
		texture.setReflectivity(5);

		/**
		 * Create a textured model for the entity.
		 */
		TexturedModel texturedModel = new TexturedModel(model, texture);

		/**
		 * Create a entity.
		 */
		Entity entity = new Entity(texturedModel, new Vector3f(128, 0, 64), new Vector3f(0, 0, 0),
				new Vector3f(1, 1, 1));

		/**
		 * Create the master renderer.
		 */
		MasterRenderer renderer = new MasterRenderer();

		while (!Display.isCloseRequested()) {
			/**
			 * Move the camera.
			 */
			camera.move();

			/**
			 * Render the terrains.
			 */
			renderer.processTerrain(terrain1);
			renderer.processTerrain(terrain2);

			/**
			 * Render the dragon entity.
			 */
			renderer.processEntity(entity);

			/**
			 * Rotate the dragon entity.
			 */
			entity.increaseRotation(0, 1, 0);

			/**
			 * Render sun camera and ambiance light.
			 */
			renderer.render(sun, camera, ambiance);

			/**
			 * Update the display.
			 */
			DisplayManager.updateDisplay();
		}

		renderer.cleanUp();
		loader.cleanUp();

		DisplayManager.closeDisplay();

	}

}
