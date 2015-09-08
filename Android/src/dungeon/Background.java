package dungeon;

public class Background {
	private int bgx, bgy, spdx;
	
	public Background(int x, int y) {
		bgx = x;
		bgy = y;
		spdx = 0;
	}
	
	public void update() {
		bgx += spdx;
		if (bgx <= -2160) {
			bgx += 4320;
		}
	}

	public int getBgx() {
		return bgx;
	}

	public int getBgy() {
		return bgy;
	}

	public int getSpdx() {
		return spdx;
	}

	public void setBgx(int bgx) {
		this.bgx = bgx;
	}

	public void setBgy(int bgy) {
		this.bgy = bgy;
	}

	public void setSpdx(int spdx) {
		this.spdx = spdx;
	}
}
