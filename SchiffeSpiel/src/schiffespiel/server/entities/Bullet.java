package schiffespiel.server.entities;

import schiffespiel.common.math.vec.Vec3f;
import schiffespiel.common.util.Ref;

public class Bullet {
	
	private Vec3f positions;
	private Vec3f direktions;
	
	public Bullet(Vec3f mPositions, Vec3f mDirektions) {
		positions = mPositions;
		direktions = mDirektions;
	}

	public void move() {
		positions.add(direktions);
		positions.y -= Ref.GRAV;
	}
	
	public Vec3f getPositions() {
		return positions;
	}

	public void setPositions(Vec3f positions) {
		this.positions = positions;
	}

	public Vec3f getDirektions() {
		return direktions;
	}

	public void setDirektions(Vec3f direktions) {
		this.direktions = direktions;
	} 

}
