package local.engine.entities;

import org.lwjgl.util.vector.Vector3f;

import local.engine.models.TexturedModel;

public class Entity {
	
	private TexturedModel model;
	private Vector3f position;
	private Vector3f rotation;
	private float scale;
	
	public Entity(TexturedModel model, Vector3f position, Vector3f rotation, float scale) {
		this.model = model;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}
	
	public void increasePosition( float pX, float pY, float pZ ){
		this.position.x += pX;
		this.position.y += pY;
		this.position.z += pZ;
	}
	
	public void increaseRotation( float rotX, float rotY, float rotZ ){
		this.rotation.x += rotX;
		this.rotation.y += rotY;
		this.rotation.z += rotZ;
	}
	
	public TexturedModel getModel() {
		return model;
	}
	public void setModel(TexturedModel model) {
		this.model = model;
	}
	public Vector3f getPosition() {
		return position;
	}
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	public float getRotX() {
		return this.rotation.x;
	}
	public void setRotX(float rotX) {
		this.rotation.x = rotX;
	}
	public float getRotY() {
		return this.rotation.y;
	}
	public void setRotY(float rotY) {
		this.rotation.y = rotY;
	}
	public float getRotZ() {
		return this.rotation.z;
	}
	public void setRotZ(float rotZ) {
		this.rotation.z = rotZ;
	}
	public float getScale() {
		return scale;
	}
	public void setScale(float scale) {
		this.scale = scale;
	}
	
}
