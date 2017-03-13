package local.engine.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import local.engine.models.MeshDTO;
import local.engine.models.RawModel;
import local.engine.renderes.Loader;

public class OBJLoader {

	private static List<Vector3f> vertices = new ArrayList<Vector3f>();
	private static List<Vector2f> textures = new ArrayList<Vector2f>();
	private static List<Vector3f> normals = new ArrayList<Vector3f>();
	private static List<Integer> indices = new ArrayList<Integer>();

	private static float[] verticesArray = null;
	private static float[] normalsArray = null;
	private static float[] textureArray = null;
	private static int[] indicesArray = null;

	public static RawModel loadObjModel(String fileName, Loader loader) {
		fileName = "src/local/engine/res/" + fileName + ".obj";

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
					verticesArray = new float[vertices.size() * 3];
					textureArray = new float[vertices.size() * 2];
					normalsArray = new float[vertices.size() * 3];

				} else if (line.startsWith("f ")) {
					processVertex(currentLine[1].split("/"));
					processVertex(currentLine[2].split("/"));
					processVertex(currentLine[3].split("/"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		indicesArray = new int[indices.size()];

		for (int i = 0; i < indices.size(); i++) {
			indicesArray[i] = indices.get(i);
		}

		for (int i = 0; i < vertices.size(); i++) {
			verticesArray[i * 3] = vertices.get(i).x;
			verticesArray[i * 3 + 1] = vertices.get(i).y;
			verticesArray[i * 3 + 2] = vertices.get(i).z;
		}

		MeshDTO mesh = new MeshDTO();
		mesh.setIndices(indicesArray);
		mesh.setVertices(verticesArray);
		mesh.setUV(textureArray);
		mesh.setNormals(normalsArray);

		return loader.loadToVAO(mesh);
	}

	private static void processVertex(String[] vertexData) {
		int currentVertexPointer = Integer.parseInt(vertexData[0]) - 1;

		Vector2f currentTex = textures.get(Integer.parseInt(vertexData[1]) - 1);
		Vector3f currentNorm = normals.get(Integer.parseInt(vertexData[2]) - 1);

		indices.add(currentVertexPointer);

		textureArray[currentVertexPointer * 2] = currentTex.x;
		textureArray[currentVertexPointer * 2 + 1] = 1 - currentTex.y;

		normalsArray[currentVertexPointer * 3] = currentNorm.x;
		normalsArray[currentVertexPointer * 3 + 1] = currentNorm.y;
		normalsArray[currentVertexPointer * 3 + 2] = currentNorm.z;
	}

}
