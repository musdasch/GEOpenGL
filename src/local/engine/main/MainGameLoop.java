package local.engine.main;

import org.lwjgl.opengl.Display;

import local.engine.models.RawModel;
import local.engine.models.TexturedModel;
import local.engine.render.DisplayManager;
import local.engine.render.Loader;
import local.engine.render.Renderer;
import local.engine.shader.StaticShader;
import local.engine.texture.ModelTexture;

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
		
		RawModel model = loader.loadToVAO(vertices,indices);
		ModelTexture texture = new ModelTexture( loader.loadTexture( "img" ) );
		TexturedModel texturedModel = new TexturedModel( model, texture );
		
		while(!Display.isCloseRequested()){
			renderer.prepare();
			shader.start();
			renderer.render( texturedModel );
			shader.stop();
			DisplayManager.updateDisplay();         
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
	
}
