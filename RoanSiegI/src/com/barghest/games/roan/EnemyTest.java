package com.barghest.games.roan;

import java.awt.image.BufferedImage;

public class EnemyTest extends Enemy {

	
	private String spriteSheet = "CastlevaniaGenya";
	private int tilex = 16;
	private int tiley = 42;
	private int offx = 0;
	private int offy = 0;
	private BufferedImage[] still = {Sprite.getSprite(offx, offy, tilex, tiley, 0, 0, spriteSheet)};
	private Animation standing= new Animation(still, 10);
	public Animation animation = standing;
	
	public EnemyTest(int centx, int centy) {
		setCenterX(centx);
		setCenterY(centy);
	}
}
