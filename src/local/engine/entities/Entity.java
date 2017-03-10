package local.engine.entities;

import org.lwjgl.util.vector.Vector3f;

import local.engine.models.TexturedModel;

public class Entity {
	
	private TexturedModel model;
	private Vector3f position;
	private Vector3f rotation;
	private Vector3f scale;
	
	public Entity(TexturedModel model, Vector3f position, Vector3f rotation, Vector3f scale) {
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
	
	public void setPosition( Vector3f position ) {
		this.position = position;
	}
	
	public void setRotation( Vector3f rotation ){
		this.rotation = rotation;
	}
	
	public Vector3f getRotation(){
		return this.rotation;
	}
	
	public float getRotX() {
		return this.rotation.x;
	}
	
	public void setRotX( float rotX ) {
		this.rotation.x = rotX;
	}
	
	public float getRotY() {
		return this.rotation.y;
	}
	
	public void setRotY( float rotY ) {
		this.rotation.y = rotY;
	}
	
	public float getRotZ() {
		return this.rotation.z;
	}
	public void setRotZ( float rotZ ) {
		this.rotation.z = rotZ;
	}
	
	public Vector3f getScale() {
		return scale;
	}
	
	public void setScale( Vector3f scale ) {
		this.scale = scale;
	}
	
	public void setScaleX( float scaleX ) {
		this.scale.x = scaleX;
	}
	
	public void setScaleY( float scaleY ) {
		this.scale.y = scaleY;
	}
	
	public void setScaleZ( float scaleZ ) {
		this.scale.z = scaleZ;
	}
	
}
