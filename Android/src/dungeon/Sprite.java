package dungeon;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sprite {
	private static BufferedImage spritesheet;
	
	public static BufferedImage loadSprite(String file) {
		BufferedImage sprite = null;
		try {
			sprite = ImageIO.read(new File("data/" + file + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sprite;
	}
	
	public static BufferedImage getSprite(int OFFSETX, 
										  int OFFSETY, 
										  int TILE_SIZEX, 
										  int TILE_SIZEY, 
										  int xGrid, 
										  int yGrid, 
										  String filename) {
		spritesheet = loadSprite(filename);
		return spritesheet.getSubimage(OFFSETX + (xGrid * TILE_SIZEX), 
									   OFFSETY + (yGrid * TILE_SIZEY), 
									   TILE_SIZEX, 
									   TILE_SIZEY);
	}
}
