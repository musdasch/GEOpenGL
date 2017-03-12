package local.engine.renderes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import local.engine.entities.Camera;
import local.engine.entities.Entity;
import local.engine.entities.Light;
import local.engine.models.TexturedModel;
import local.engine.shaders.StaticShader;
import local.engine.shaders.TerrainShader;
import local.engine.terrains.Terrain;

public class MasterRenderer {
	
	private static final float FOV = 70;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000;

	private Matrix4f projectionMatrix;
	
	private StaticShader staticShader = new StaticShader();
	private TerrainShader terrainShader = new TerrainShader();
	
	private EntityRenderer entityRenderer;
	private TerrainRenderer terrainRenderer;
	
	private Map< TexturedModel, List< Entity > > entities = new HashMap< TexturedModel, List< Entity > >();
	private List< Terrain > terrains = new ArrayList< Terrain >();
	
	public MasterRenderer(){
		GL11.glEnable( GL11.GL_CULL_FACE );
		GL11.glCullFace( GL11.GL_BACK );
		
		createProjectionMatrix();
		
		entityRenderer = new EntityRenderer( staticShader, projectionMatrix );
		terrainRenderer = new TerrainRenderer( terrainShader, projectionMatrix );
	}
	
	public void render( Light sun, Camera camera, float ambiance ){
		prepare();
		
		staticShader.start();
		
		staticShader.loadLigth( sun );
		staticShader.loadViewMatrix( camera );
		staticShader.loadAmbiance( ambiance );
		
		entityRenderer.render( entities );
		
		staticShader.stop();
		
		terrainShader.start();
		
		terrainShader.loadLigth( sun );
		terrainShader.loadViewMatrix( camera );
		terrainShader.loadAmbiance( ambiance );
		
		terrainRenderer.render( terrains );
		
		terrainShader.stop();
		
		terrains.clear();
		entities.clear();
	}
	
	public void processTerrain( Terrain terrain ){
		terrains.add( terrain );
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
		staticShader.cleanUp();
		terrainShader.cleanUp();
	}
	
	public void prepare(){
		GL11.glEnable( GL11.GL_DEPTH_TEST );
		GL11.glClear( GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT );
		GL11.glClearColor( 0, 0, 0, 1 );
	}
	
	private void createProjectionMatrix(){
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustum_length = FAR_PLANE - NEAR_PLANE;
 
        projectionMatrix = new Matrix4f();
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
        projectionMatrix.m33 = 0;
	}

}
