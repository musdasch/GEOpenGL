package local.engine.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import local.engine.entities.Camera;
import local.engine.entities.Entity;
import local.engine.entities.Light;
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
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		
		float ambiance = 0.07f;
		
		Light sun = new Light(
				new Vector3f( 128, 20, 74 ),
				new Vector3f( 0.8f, 0.8f, 0.8f )
		);
		
		Camera camera = new Camera(
				new Vector3f( 128, 10, 128 ),
				new Vector3f( 0, 0, 0 )
		);
		
		Terrain terrain1 = new Terrain(
				0,
				0,
				loader,
				new ModelTexture(
						loader.loadTexture( "grass" )
				)
		);
		Terrain terrain2 = new Terrain(
				1,
				0,
				loader,
				new ModelTexture(
						loader.loadTexture( "grass" )
				)
		);
		
		RawModel model = OBJLoader.loadObjModel( "dragon", loader );
		
		ModelTexture texture = new ModelTexture( loader.loadTexture( "dragon" ) );
		texture.setShineDamper( 10 );
		texture.setReflectivity( 5 );
		
		TexturedModel texturedModel = new TexturedModel( model, texture );
		
		Entity entity = new Entity(
				texturedModel,
				new Vector3f( 128, 0, 64 ),
				new Vector3f( 0, 0, 0 ),
				new Vector3f( 1, 1, 1 )
		);
		
		MasterRenderer renderer = new MasterRenderer();
		
		while(!Display.isCloseRequested()){
			camera.move();
			
			renderer.processTerrain( terrain1 );
			renderer.processTerrain( terrain2 );
			
			renderer.processEntity( entity );
			entity.increaseRotation( 0, 1, 0);
			
			renderer.render( sun, camera, ambiance );
			DisplayManager.updateDisplay();         
		}
		
		renderer.cleanUp();
		loader.cleanUp();
		
		DisplayManager.closeDisplay();
		
	}
	
}
