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
import local.engine.textures.ModelTexture;
import local.engine.utilities.OBJLoader;

public class MainGameLoop {
	
	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		
		float ambiance = 0.07f;
		
		Light sun = new Light(
				new Vector3f( 0, 10, -20 ),
				new Vector3f( 0.7f, 0.7f, 0.7f )
		);
		
		Camera camera = new Camera(
				new Vector3f( 0, 0, 0 ),
				new Vector3f( 0, 0, 0 )
		);
		
		List<Entity> entities = new ArrayList<Entity>();
		Random random = new Random();
		
		RawModel model = OBJLoader.loadObjModel( "dragon", loader );
		
		ModelTexture texture = new ModelTexture( loader.loadTexture( "dragon" ) );
		texture.setShineDamper( 10 );
		texture.setReflectivity( 5 );
		
		TexturedModel texturedModel = new TexturedModel( model, texture );
		
		for( int i = 0; i < 200; i++ ){
			float x = random.nextFloat() * 100 - 50;
			float y = random.nextFloat() * 100 - 50;
			float z = random.nextFloat() * -500;
			
			entities.add(
					new Entity(
							texturedModel,
							new Vector3f( x, y, z ),
							new Vector3f( 0, 0, 0 ),
							new Vector3f( 1, 1, 1 )
					)
			);
		}
		
		MasterRenderer renderer = new MasterRenderer();
		
		while(!Display.isCloseRequested()){
			camera.move();
			
			for( Entity entity : entities ){
				renderer.processEntity( entity );
				entity.increaseRotation( 0, 1, 0);
			}
			
			renderer.render( sun, camera, ambiance );
			DisplayManager.updateDisplay();         
		}
		
		renderer.cleanUp();
		loader.cleanUp();
		
		DisplayManager.closeDisplay();
		
	}
	
}
