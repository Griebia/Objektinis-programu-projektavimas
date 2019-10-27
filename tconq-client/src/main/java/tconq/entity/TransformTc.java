package tconq.entity;

import org.joml.Matrix2f;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class TransformTc {
	public Vector3f pos;
	public Vector3f scale;
	
	public TransformTc() {
		pos = new Vector3f();
		scale = new Vector3f(1, 1, 1);
	}
	
	public Matrix4f getProjection(Matrix4f target) {
		target.translate(pos);
		target.scale(scale);
		return target;
	}
	public Vector2f getVector2f() {
		return new Vector2f(pos.x,pos.y);
	}
}