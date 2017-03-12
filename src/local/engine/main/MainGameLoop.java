package local.engine.main;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import local.engine.entities.Camera;
import local.engine.entities.Entity;
import local.engine.entities.Light;
import local.engine.models.RawModel;
import local.engine.models.TexturedModel;
import local.engine.renderes.DisplayManager;
import local.engine.renderes.Loader;
import local.engine.renderes.Renderer;
import local.engine.shaders.StaticShader;
import local.engine.textures.ModelTexture;
import local.engine.utilities.OBJLoader;

public class MainGameLoop {
	
	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		
		StaticShader shader = new StaticShader();
		
		shader.start();
		shader.loadAmbiance( 0.07f );
		shader.stop();
		
		Renderer renderer = new Renderer( shader );
		
		Light light = new Light(
				new Vector3f( 0, 10, -20 ),
				new Vector3f( 0.7f, 0.7f, 0.7f )
		);
		
		RawModel model = OBJLoader.loadObjModel( "dragon", loader );
		
		ModelTexture texture = new ModelTexture( loader.loadTexture( "dragon" ) );
		texture.setShineDamper( 10 );
		texture.setReflectivity( 5 );
		
		TexturedModel texturedModel = new TexturedModel( model, texture );
		
		Entity entity = new Entity(
				texturedModel,
				new Vector3f( 0, -4, -20 ),
				new Vector3f( 0, 0, 0 ),
				new Vector3f( 1f, 1f, 1f )
		);
		
		Camera camera = new Camera(
				new Vector3f( 0, 0, 0 ),
				new Vector3f( 0, 0, 0 )
		);
		
		while(!Display.isCloseRequested()){
			
			entity.increaseRotation( 0, 0.5f, 0 );
			camera.move();
			
			renderer.prepare();
			shader.start();
			
			shader.loadViewMatrix( camera );
			shader.loadLigth( light );
			renderer.render( entity, shader );
			
			shader.stop();
			
			DisplayManager.updateDisplay();         
		}
		
		shader.cleanUp();
		loader.cleanUp();
		
		DisplayManager.closeDisplay();
		
	}
	
}
