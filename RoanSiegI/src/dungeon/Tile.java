package dungeon;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class Tile {
	private int tilex, tiley, spdx, type;
	private int width = 32;
	private int height = 32;
	private int offx = 0;
	private int offy = 0;
	public Image tileimg;
	private String spriteSheet = "TileSet1";
	
	//private MainCharac player = MainClass.getPlayer();
	private Background bg = MainClass.getBg1();
	private BufferedImage[] tiling = {Sprite.getSprite(offx, offy, width, height, 0, 0, spriteSheet),
									  Sprite.getSprite(offx, offy, width, height, 1, 0, spriteSheet), 
									  Sprite.getSprite(offx, offy, width, height, 2, 0, spriteSheet), 
									  Sprite.getSprite(offx, offy, width, height, 0, 1, spriteSheet),
									  Sprite.getSprite(offx, offy, width, height, 1, 1, spriteSheet),
									  Sprite.getSprite(offx, offy, width, height, 2, 1, spriteSheet),
									  Sprite.getSprite(offx, offy, width, height, 0, 2, spriteSheet),
									  Sprite.getSprite(offx, offy, width, height, 1, 2, spriteSheet),
									  Sprite.getSprite(offx, offy, width, height, 2, 2, spriteSheet)};
	
	public Tile(int x, int y, char typechar) {
		tilex = x * width;
		tiley = y * height;
		type = Character.getNumericValue(typechar);
		
		if (typechar == 'S') {
			MainClass.getPlayer().setCenterX(tilex);
			MainClass.getPlayer().setCenterY(tiley - (MainClass.getPlayer().getHeight() - height));
		}

		if (type == 7) {
			tileimg = tiling[0];
		} else if (type == 8) {
			tileimg = tiling[1];
		} else if (type == 9) {
			tileimg = tiling[2];
		} else if (type == 4) {
			tileimg = tiling[3];
		} else if (type == 5) {
			tileimg = tiling[4];
		} else if (type == 6) {
			tileimg = tiling[5];
		} else if (type == 1) {
			tileimg = tiling[6];
		} else if (type == 2) {
			tileimg = tiling[7];
		} else if (type == 3) {
			tileimg = tiling[8];
		}
	}
	
	public void update() {
		spdx = bg.getSpdx() * 5;
		tilex += spdx;
	}
	
	public int getTileX() {
		return tilex;
	}
	
	public int getTileY() {
		return tiley;
	}
	
	public void setTileX(int tilex) {
		this.tilex = tilex;
	}
	
	public void setTileY(int tiley) {
		this.tiley = tiley;
	}
	
	public Image getTileImage() {
		return tileimg;
	}
	
	public void setTileImg(Image tileimg) {
		this.tileimg = tileimg;
	}
}
