package local.engine.main;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import local.engine.entities.Entity;
import local.engine.models.RawModel;
import local.engine.models.TexturedModel;
import local.engine.renderes.DisplayManager;
import local.engine.renderes.Loader;
import local.engine.renderes.Renderer;
import local.engine.shaders.StaticShader;
import local.engine.textures.ModelTexture;

public class MainGameLoop {
	
	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
		
		float[] vertices = {            
			-0.5f,0.5f,0,
			-0.5f,-0.5f,0,
			0.5f,-0.5f,0,
			0.5f,0.5f,0
		};
		
		int[] indices = {
			0,1,3,
			3,1,2
		};
		
		float[] textureCoords = {
				0, 0,
				0, 1,
				1, 1,
				1, 0
		};
		
		RawModel model = loader.loadToVAO( vertices, textureCoords, indices );
		ModelTexture texture = new ModelTexture( loader.loadTexture( "img" ) );
		TexturedModel texturedModel = new TexturedModel( model, texture );
		Entity entity = new Entity(
				texturedModel,
				new Vector3f( -0.5f, 0.5f, 0 ),
				new Vector3f( 0, 0, 0 ),
				new Vector3f( 1f, 1f, 1f )
		);
		
		while(!Display.isCloseRequested()){
			renderer.prepare();
			shader.start();
			renderer.render( entity, shader );
			entity.increasePosition( 0.001f, -0.001f, 0f );
			shader.stop();
			DisplayManager.updateDisplay();         
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
		
	}
	
}
