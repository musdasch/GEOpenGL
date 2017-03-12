package local.engine.renderes;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import local.engine.entities.Entity;
import local.engine.models.RawModel;
import local.engine.models.TexturedModel;
import local.engine.shaders.TerrainShader;
import local.engine.terrains.Terrain;
import local.engine.textures.ModelTexture;
import local.engine.utilities.Maths;

public class TerrainRenderer {
	
	private TerrainShader shader;
	
	public TerrainRenderer( TerrainShader shader, Matrix4f projectionMatrix ) {
		this.shader = shader;
		shader.start();
		shader.loadProjectionMatrix( projectionMatrix );
		shader.stop();
	}
	
	public void prepareTerrain( Terrain terrain ){
		RawModel rawModel = terrain.getModel();
		ModelTexture texture = terrain.getTexture();
		
		GL30.glBindVertexArray( rawModel.getVaoID() );
		GL20.glEnableVertexAttribArray( 0 );
		GL20.glEnableVertexAttribArray( 1 );
		GL20.glEnableVertexAttribArray( 2 );
		
		GL13.glActiveTexture( GL13.GL_TEXTURE0 );
		GL11.glBindTexture( GL11.GL_TEXTURE_2D, texture.getID() );
		shader.loadShine( texture.getShineDamper(), texture.getReflectivity() );
		
		shader.loadTiling( terrain.TILING );
	}
	
	public void loadModelMatrix( Terrain terrain ){
		Matrix4f transformationMatrix = Maths.createTransformationMatrix(
				new Vector3f( terrain.getX(), 0, terrain.getZ() ),
				new Vector3f( 0, 0, 0 ),
				new Vector3f( 1, 1, 1 )
		);
		
		shader.loadTransformationMatrix( transformationMatrix );
	}
	
	public void render( List<Terrain> terrains ){
		for( Terrain terrain : terrains ){
			prepareTerrain( terrain );
			loadModelMatrix( terrain );
			
			GL11.glDrawElements(
					GL11.GL_TRIANGLES,
					terrain.getModel().getVertexCount(),
					GL11.GL_UNSIGNED_INT,
					0
			);
			
			unbindTextureModel();
		}
	}
	
	public void unbindTextureModel(){
		GL20.glDisableVertexAttribArray( 0 );
		GL20.glDisableVertexAttribArray( 1 );
		GL20.glDisableVertexAttribArray( 2 );
		
		GL30.glBindVertexArray( 0 );
	}

}
