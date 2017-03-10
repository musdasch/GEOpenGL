package local.engine.renderes;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import local.engine.entities.Entity;
import local.engine.models.RawModel;
import local.engine.models.TexturedModel;
import local.engine.shaders.StaticShader;
import local.engine.utilities.Maths;

public class Renderer {
	
	public void prepare(){
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glClearColor(0, 0, 0, 1);
	}
	 
	public void render( Entity entity, StaticShader shader ){
		TexturedModel model = entity.getModel();
		RawModel rawModel = model.getRawModel();
		
		GL30.glBindVertexArray(rawModel.getVaoID());
		GL20.glEnableVertexAttribArray( 0 );
		GL20.glEnableVertexAttribArray( 1 );
		
		Matrix4f transformationMatrix = Maths.createTransformationMatrix(
				entity.getPosition(),
				entity.getRotation(),
				entity.getScale()
		);
		
		shader.loadTransformationMatrix( transformationMatrix );
		
		GL13.glActiveTexture( GL13.GL_TEXTURE0 );
		GL11.glBindTexture( GL11.GL_TEXTURE_2D, model.getTexture().getID() );
		GL11.glDrawElements( GL11.GL_TRIANGLES, rawModel.getVertexCount(), GL11.GL_UNSIGNED_INT, 0 );
		GL20.glDisableVertexAttribArray( 0 );
		GL20.glDisableVertexAttribArray( 1 );
		GL30.glBindVertexArray( 0 );
	}

}