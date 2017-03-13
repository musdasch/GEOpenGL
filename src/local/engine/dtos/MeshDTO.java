package local.engine.dtos;

public class MeshDTO {

	private float[] vertices;
	private float[] normals;
	private float[] uv;
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

	public float[] getUV() {
		return uv;
	}

	public void setUV(float[] uv) {
		this.uv = uv;
	}

	public int[] getIndices() {
		return indices;
	}

	public void setIndices(int[] indices) {
		this.indices = indices;
	}
}
