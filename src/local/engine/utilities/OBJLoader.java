package local.engine.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import local.engine.models.RawModel;
import local.engine.renderes.Loader;

public class OBJLoader {
	
	private static List<Vector3f>	vertices	= new ArrayList<Vector3f>();
	private static List<Vector2f>	testures	= new ArrayList<Vector2f>();
	private static List<Vector3f>	normals		= new ArrayList<Vector3f>();
	private static List<Integer>	indices 	= new ArrayList<Integer>();
	
	private static float[]	verticesArray	= null;
	private static float[]	normalsArray	= null;
	private static float[]	textureArray	= null;
	private static int[]	indicesArray	= null;
	
	public static RawModel loadObjModel(String fileName, Loader loader ){
		fileName = "src/loacal/engine/res" + fileName + ".obj";
		
		
		try(BufferedReader br = new BufferedReader(new FileReader( fileName ) ) ) {
		    for(String line; (line = br.readLine()) != null; ) {
		        String[] currentLine = line.split( " " );
		    	
		    	if( line.startsWith( "v " ) ){
		    		
		    		vertices.add(
		    				new Vector3f(
		    						Float.parseFloat( currentLine[ 1 ] ),
		    						Float.parseFloat( currentLine[ 2 ] ),
		    						Float.parseFloat( currentLine[ 3 ] )
		    				)
		    		);
		    		
		    	} else if( line.startsWith( "vt " ) ){
		    		
		    		testures.add(
		    				new Vector2f(
		    						Float.parseFloat( currentLine[ 1 ] ),
		    						Float.parseFloat( currentLine[ 2 ] )
		    				)
		    		);
		    		
		    	} else if( line.startsWith( "vn ") ){
		    		
		    		normals.add(
		    				new Vector3f(
		    						Float.parseFloat( currentLine[ 1 ] ),
		    						Float.parseFloat( currentLine[ 2 ] ),
		    						Float.parseFloat( currentLine[ 3 ] )
		    				)
		    		);
		    		
		    	} else if( line.contains( "s off" ) ){
		    		verticesArray	= new float[ vertices.size() * 3 ];
		    		textureArray	= new float[ testures.size() * 2 ];
		    		normalsArray	= new float[ normals.size() * 3 ];
		    		
		    	} else if( line.startsWith( "f " ) ){
		    		String[] vertex1 = currentLine[ 1 ].split( "/" );
		    		String[] vertex2 = currentLine[ 2 ].split( "/" );
		    		String[] vertex3 = currentLine[ 3 ].split( "/" );
		    		processVertex( vertex1 );
		    	}
		    }
		    // line is not visible here.
		} catch ( Exception e ){
			e.printStackTrace();
		}
		
		return loader.loadToVAO( verticesArray, textureArray, indicesArray );
	}
	
	private static void processVertex( String[] vertexData ){
		indices.add( Integer.parseInt( vertexData[1] )-1 );
		System.out.println( indices.get(indices.size()-1 ) );
	}

}
