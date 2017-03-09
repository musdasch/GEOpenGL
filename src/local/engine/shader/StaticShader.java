package local.engine.shader;

public class StaticShader extends ShaderProgram {

	private static final String VERTEX_FILE = "src/local/engine/shader/vertexShader.glsl";
	private static final String FRAGMENT_FILE = "src/local/engine/shader/fragmentShader.glsl";
	
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute( 0, "position" );
	}
	
	
}
