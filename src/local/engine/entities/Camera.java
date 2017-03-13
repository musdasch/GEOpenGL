package local.engine.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	private static final float SPEED = 0.2f;

	private Vector3f position;
	private Vector3f rotation;

	public Camera(Vector3f position, Vector3f rotation) {
		this.position = position;
		this.rotation = rotation;
	}

	public Vector3f getPosition() {
		return this.position;
	}

	public void move() {
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			position.z -= SPEED;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			position.z += SPEED;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			position.x -= SPEED;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			position.x += SPEED;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			position.y += SPEED;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
			position.y -= SPEED;
		}

	}

	public Vector3f getRotation() {
		return rotation;
	}

	public float getPitch() {
		return rotation.x;
	}

	public float getYaw() {
		return rotation.y;
	}

	public float getRoll() {
		return rotation.y;
	}

}
