package local.engine.dtos;

public class MeshDTO {

	private float[] vertices;
	private float[] normals;
	private float[] uvs;
	private int[] indices;

	public MeshDTO() {
	}

	public float[] getVertices() {
		return vertices;
	}

	public void setVertices(float[] vertices) {
		this.vertices = vertices;
	}

	public float[] getNormals() {
		return normals;
	}

	public void setNormals(float[] normals) {
		this.normals = normals;
	}

	public float[] getUVs() {
		return uvs;
	}

	public void setUVs(float[] uv) {
		this.uvs = uv;
	}

	public int[] getIndices() {
		return indices;
	}

	public void setIndices(int[] indices) {
		this.indices = indices;
	}

	public void addVetice(int index, float vetice) {
		this.vertices[index] = vetice;
	}

	public void addNormal(int index, float normal) {
		this.normals[index] = normal;
	}

	public void addUV(int index, float uv) {

	}

	public void addIndices(int index, int indice) {
		this.indices[index] = indice;
	}
}
