package local.engine.renderes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import local.engine.entities.Camera;
import local.engine.entities.Entity;
import local.engine.entities.Light;
import local.engine.models.TexturedModel;
import local.engine.shaders.StaticShader;

public class MasterRenderer {

	private StaticShader shader = new StaticShader();
	private Renderer renderer = new Renderer( shader );
	
	private Map< TexturedModel, List< Entity > > entities = new HashMap< TexturedModel, List< Entity > >();
	
	public void render( Light sun, Camera camera, float ambiance ){
		renderer.prepare();
		shader.start();
		
		shader.loadLigth( sun );
		shader.loadViewMatrix( camera );
		shader.loadAmbiance( ambiance );
		
		renderer.render( entities );
		
		shader.stop();
		entities.clear();
	}
	
	public void processEntity( Entity entity ){
		TexturedModel entityModel = entity.getModel();
		List<Entity> batch = entities.get( entityModel );
		if( batch != null ){
			batch.add( entity );
		} else {
			List<Entity> newBatch = new ArrayList<Entity>();
			newBatch.add( entity );
			entities.put( entityModel, newBatch );
		}
	}
	
	public void cleanUp(){
		shader.cleanUp();
	}

}
