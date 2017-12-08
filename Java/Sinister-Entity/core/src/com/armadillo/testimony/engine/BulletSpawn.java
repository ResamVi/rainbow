package com.armadillo.testimony.engine;

import java.util.ArrayList;

import com.armadillo.testimony.gameobjects.Bullet;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

// Responsible for creation and deletion of Bullets
public class BulletSpawn {

	// Holds all currently flying bullets
	private ArrayList<Bullet> bulletList;

	private World bodyStore;
	
	// Initialize
	public BulletSpawn(World bodyStore) {
		bulletList = new ArrayList<Bullet>();
		this.bodyStore = bodyStore;
	}
	
	// Main loop of this object
	public void update()
	{
		// Update ALL Bullet objects currently flying
		for(Bullet bullet : bulletList) {
			bullet.update();
		}
	}
	
	// Spawn Bulled (Interface used by Player)
	public void spawnBullet(float playerX, float playerY, Vector2 direction) {
		Bullet shot = new Bullet(bulletList.size(), this, playerX, playerY, bodyStore);
		shot.setInMotion(direction);
		bulletList.add(shot);
	}
	
	// Delete Bullet (Interface used by Bullet)
	/* EXPLANATION TIME:
	 * Trying to remove the bullet by simply deleting a reference in the list will
	 * result in a java.util.ConcurrentModificationException due to Line21's loop.
	 * Thus the solution is to delete of the clone and copy it back
	 * to the original.
	 */
	@SuppressWarnings("unchecked")
	public void deleteBullet(int index)
	{		
		ArrayList<Bullet> clone = (ArrayList<Bullet>) bulletList.clone();
		bodyStore.destroyBody(clone.get(index).getBody());
		clone.remove(0);
		bulletList = (ArrayList<Bullet>) clone.clone();
	}
	
	public ArrayList<Bullet> getBulletList() {
		return bulletList;
	}
}
