package local.engine.shaders;

import org.lwjgl.util.vector.Matrix4f;

public class StaticShader extends ShaderProgram {

	private static final String VERTEX_FILE = "src/local/engine/shaders/vertexShader.glsl";
	private static final String FRAGMENT_FILE = "src/local/engine/shaders/fragmentShader.glsl";
	
	private int location_transformationMatrix;
	private int location_projectionMatrix;
	
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute( 0, "position" );
		super.bindAttribute(1, "pass_textureCoords");
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation( "transformationMatrix" );
		location_projectionMatrix = super.getUniformLocation( "projectionMatrix" );
	}
	
	public void loadTransformationMatrix( Matrix4f matrix ){
		super.loadMatrix( location_transformationMatrix, matrix );
	}
	
	public void loadProjectionMatrix( Matrix4f matrix ){
		super.loadMatrix( location_projectionMatrix, matrix );
	}
	
	
}
