package GameState;

import java.awt.Graphics2D;

import TileMap.TileMap;

public class Level1State extends GameState {
	
	private TileMap tileMap;
	
	public Level1State (GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	public void init() {
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/TileSet1.png");
		tileMap.loadMap("/Maps/mapQuick.txt");
		tileMap.setPosition(0, 0);
	}
	public void update() {}
	public void draw(Graphics2D g) {}

	public void keyPressed(int k) {}

	public void keyReleased(int k) {}
}
