package local.engine.files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import local.engine.dtos.MeshDTO;
import local.engine.models.RawModel;
import local.engine.renderes.Loader;

public class OBJFile {
	
	private final String name;
	private final Loader loader;
	private final MeshDTO mesh = new MeshDTO();

	public OBJFile(String name, Loader loader) {
		this.name = name;
		this.loader = loader;
	}
	
	public RawModel process(){
		
		List<Vector3f> vertices = new ArrayList<Vector3f>();
		List<Vector2f> textures = new ArrayList<Vector2f>();
		List<Vector3f> normals = new ArrayList<Vector3f>();
		List<Integer> indices = new ArrayList<Integer>();
		
		String fileName = "src/local/engine/res/" + name + ".obj";

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			for (String line; (line = br.readLine()) != null;) {
				String[] currentLine = line.split(" ");

				if (line.startsWith("v ")) {

					vertices.add(new Vector3f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]),
							Float.parseFloat(currentLine[3])));

				} else if (line.startsWith("vt ")) {

					textures.add(new Vector2f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2])));

				} else if (line.startsWith("vn ")) {

					normals.add(new Vector3f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]),
							Float.parseFloat(currentLine[3])));

				} else if (line.contains("s ")) {
					
					mesh.setVertices( new float[vertices.size() * 3] );
					mesh.setUVs(new float[vertices.size() * 3]);
					mesh.setNormals(new float[vertices.size() * 3]);

				} else if (line.startsWith("f ")) {
					indices.add(processVertex(currentLine[1].split("/"), textures, normals));
					indices.add(processVertex(currentLine[2].split("/"), textures, normals));
					indices.add(processVertex(currentLine[3].split("/"), textures, normals));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mesh.setIndices(new int[indices.size()]);

		for (int i = 0; i < indices.size(); i++) {
			mesh.addIndices(i, indices.get(i));
		}

		for (int i = 0; i < vertices.size(); i++) {
			mesh.addVetice( i * 3, vertices.get(i).x);
			mesh.addVetice( i * 3 + 1, vertices.get(i).y);
			mesh.addVetice( i * 3 + 2, vertices.get(i).z);
		}
		
		return loader.loadToVAO(mesh);
	}
	
	private int processVertex(String[] vertexData,List<Vector2f> textures, List<Vector3f> normals) {
		int currentVertexPointer = Integer.parseInt(vertexData[0]) - 1;

		Vector2f currentTex = textures.get(Integer.parseInt(vertexData[1]) - 1);
		Vector3f currentNorm = normals.get(Integer.parseInt(vertexData[2]) - 1);
		
		mesh.addUV(currentVertexPointer * 2, currentTex.x);
		mesh.addUV(currentVertexPointer * 2 + 1, currentTex.y);
		
		mesh.addNormal(currentVertexPointer * 3, currentNorm.x);
		mesh.addNormal(currentVertexPointer * 3 + 1, currentNorm.y);
		mesh.addNormal(currentVertexPointer * 3 + 2, currentNorm.z);
		
		return currentVertexPointer;
	}

}
