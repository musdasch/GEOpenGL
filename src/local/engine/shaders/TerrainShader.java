package local.engine.shaders;

import org.lwjgl.util.vector.Matrix4f;

import local.engine.entities.Camera;
import local.engine.entities.Light;
import local.engine.utilities.Maths;

public class TerrainShader extends ShaderProgram{
	
	private static final String VERTEX_FILE = "src/local/engine/shaders/terrainVertexShader.glsl";
	private static final String FRAGMENT_FILE = "src/local/engine/shaders/terrainFragmentShader.glsl";
	
	private int location_transformationMatrix;
	private int location_viewMatrix;
	private int location_projectionMatrix;
	private int location_lightPosition;
	private int location_lightColour;
	private int location_shineDamper;
	private int location_reflectivity;
	private int location_ambianceLight;
	
	public TerrainShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute( 0, "position" );
		super.bindAttribute( 1, "pass_textureCoords" );
		super.bindAttribute( 2, "normal" );
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix	= super.getUniformLocation( "transformationMatrix" );
		location_viewMatrix				= super.getUniformLocation( "viewMatrix" );
		location_projectionMatrix		= super.getUniformLocation( "projectionMatrix" );
		location_lightPosition			= super.getUniformLocation( "lightPosition" );
		location_lightColour			= super.getUniformLocation( "lightColour" );
		location_shineDamper			= super.getUniformLocation( "shineDamper" );
		location_reflectivity			= super.getUniformLocation( "reflectivity" );
		location_ambianceLight			= super.getUniformLocation( "ambianceLight" );
	}
	
	public void loadTransformationMatrix( Matrix4f matrix ){
		super.loadMatrix( location_transformationMatrix, matrix );
	}
	
	public void loadViewMatrix( Camera camera ){
		super.loadMatrix(
				location_viewMatrix,
				Maths.createViewMatrix( camera.getPosition(), camera.getRotation() )
		);
	}
	
	public void loadProjectionMatrix( Matrix4f matrix ){
		super.loadMatrix( location_projectionMatrix, matrix );
	}
	
	public void loadLigth( Light light ){
		super.loadVector( location_lightPosition, light.getPosition() );
		super.loadVector( location_lightColour, light.getColor() );
	}
	
	public void loadShine( float shineDamper, float reflectivity ){
		super.loadFloat( location_shineDamper, shineDamper );
		super.loadFloat( location_reflectivity, reflectivity );
	}
	
	public void loadAmbiance( float ambianceLight ){
		super.loadFloat( location_ambianceLight, ambianceLight );
	}
	
}
