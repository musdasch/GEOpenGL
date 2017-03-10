package local.engine.utilities;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class Maths {
	
	public static Matrix4f createTransformationMatrix( Vector3f translation, Vector3f rotation, Vector3f scale ){
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate( translation, matrix, matrix );
		Matrix4f.rotate( (float) Math.toRadians( rotation.x ), new Vector3f( 1, 0, 0 ), matrix, matrix );
		Matrix4f.rotate( (float) Math.toRadians( rotation.y ), new Vector3f( 0, 1, 0 ), matrix, matrix );
		Matrix4f.rotate( (float) Math.toRadians( rotation.z ), new Vector3f( 0, 0, 1 ), matrix, matrix );
		Matrix4f.scale( scale, matrix, matrix );
		return matrix;
	}
	
	public static Matrix4f createViewMatrix( Vector3f translation, Vector3f rotation ){
		return createTransformationMatrix( 
				new Vector3f( -translation.x, -translation.y, -translation.z ),
				new Vector3f( -rotation.x, -rotation.y, -rotation.z ),
				new Vector3f( 1, 1, 1 )
		);
	}
	
}
