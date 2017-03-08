package local.engine.main;

import org.lwjgl.opengl.Display;

import local.engine.render.DisplayManager;
import local.engine.render.Loader;
import local.engine.render.RawModel;
import local.engine.render.Renderer;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
		float[] vertices = {
				-0.5f, 0.5f, 0,		//V0
				-0.5f, -0.5f, 0,	//V1
				0.5f, -0.5f, 0,		//V2
				0.5f, 0.5f, 0f,		//V3
		};
		
		int[] indices = {
				0, 1, 3,
				3, 1, 2
		};
		
		RawModel model = loader.LoadToVAO( vertices, indices );
		
		while( !Display.isCloseRequested() ){
			
			renderer.prepare();
			renderer.render(model);
			DisplayManager.updateDisplay();
			
		}
		
		DisplayManager.closeDisplay();
	
	}

}
