package dungeon;

public class Projectile {
	private int x, y, spdx;
	private boolean visible;
	
	public Projectile(int startx, int starty) {
		x = startx;
		y = starty;
		spdx = 7;
		visible = true;
	}
	
	public void update() {
		x += spdx;
		if (x > 800) {
			visible = false;
		}
	}
	
	public int getx() {
		return x;
	}
	
	public int gety() {
		return y;
	}
	
	public int getspdx() {
		return spdx;
	}

	public boolean isVisible() {
		return visible;
	}
	
	public void setx(int x) {
		this.x = x;
	}
	
	public void sety(int y) {
		this.y = y;
	}
	
	public void setspdx(int spdx) {
		this.spdx = spdx;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
