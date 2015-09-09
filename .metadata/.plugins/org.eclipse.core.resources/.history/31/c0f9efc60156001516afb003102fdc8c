package dungeon;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MainCharac {
	final int JUMPSPD = -15;
	final int MOVESPD = 5;
	static int SCREEN_WIDTH = MainClass.getScreenWidth();
	private int centX = 0;
	private int centY = 0;

	private String spriteSheet = "CastlevaniaSoma";
	private int tilex = 45;
	private int tiley = 56;
	private int offx = 0;
	private int offy = 95;
	private BufferedImage[] walkingLeft = {Sprite.getSprite(offx, offy, tilex, tiley, 0, 4, spriteSheet), 
										   Sprite.getSprite(offx, offy, tilex, tiley, 1, 4, spriteSheet),
										   Sprite.getSprite(offx, offy, tilex, tiley, 2, 4, spriteSheet),
										   Sprite.getSprite(offx, offy, tilex, tiley, 3, 4, spriteSheet),
										   Sprite.getSprite(offx, offy, tilex, tiley, 4, 4, spriteSheet)};
	private BufferedImage[] walkingRight = {Sprite.getSprite(offx, offy, tilex, tiley, 2, 3, spriteSheet),
										   Sprite.getSprite(offx, offy, tilex, tiley, 3, 3, spriteSheet),
										   Sprite.getSprite(offx, offy, tilex, tiley, 4, 3, spriteSheet),
										   Sprite.getSprite(offx, offy, tilex, tiley, 5, 3, spriteSheet),
										   Sprite.getSprite(offx, offy, tilex, tiley, 6, 3, spriteSheet),
										   Sprite.getSprite(offx, offy, tilex, tiley, 7, 3, spriteSheet),
										   Sprite.getSprite(offx, offy, tilex, tiley, 8, 3, spriteSheet),
										   Sprite.getSprite(offx, offy, tilex, tiley, 9, 3, spriteSheet),
										   Sprite.getSprite(offx, offy, tilex, tiley, 10, 3, spriteSheet),
										   Sprite.getSprite(offx, offy, tilex, tiley, 11, 3, spriteSheet),
										   Sprite.getSprite(offx, offy, tilex, tiley, 12, 3, spriteSheet),
										   Sprite.getSprite(offx, offy, tilex, tiley, 13, 3, spriteSheet),
										   Sprite.getSprite(offx, offy, tilex, tiley, 14, 3, spriteSheet),
										   Sprite.getSprite(offx, offy, tilex, tiley, 15, 3, spriteSheet),
										   Sprite.getSprite(offx, offy, tilex, tiley, 16, 3, spriteSheet),
										   Sprite.getSprite(offx, offy, tilex, tiley, 17, 3, spriteSheet)};
	private BufferedImage[] still = {Sprite.getSprite(offx, offy, tilex, tiley, 0, 0, spriteSheet), 
									 Sprite.getSprite(offx, offy, tilex, tiley, 1, 0, spriteSheet), 
									 Sprite.getSprite(offx, offy, tilex, tiley, 2, 0, spriteSheet),
									 Sprite.getSprite(offx, offy, tilex, tiley, 3, 0, spriteSheet)};
	private Animation walkLeft = new Animation(walkingLeft, 15);
	private Animation walkRight = new Animation(walkingRight, 10);
	private Animation standing= new Animation(still, 20);
	public Animation animation = standing;
	
	private static Background bg1 = MainClass.getBg1();
	private static Background bg2 = MainClass.getBg2();
	private int spdX = 0;
	private int spdY = 0;
	
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	public static Rectangle rect = new Rectangle(0, 0, 0, 0);
	
	private boolean jump = false;
	private boolean ducked = false;
	private boolean moveRight = false;
	private boolean tapLeft = false;
	
	public MainCharac() {
		animation.start();
	}
	
	public void update() {
		if (animation == standing || animation == walkRight) {
			animation.update();
		}
		
		if (spdX<0) {
			centX += spdX;
		}
		
		if (spdX == 0 || spdX < 0) {
			bg1.setSpdx(0);
			bg2.setSpdx(0);
		}
		if (centX<=SCREEN_WIDTH / 4 && spdX > 0) {
			centX += spdX;
		}
		if (spdX > 0 && centX > SCREEN_WIDTH / 4) {
			bg1.setSpdx(-MOVESPD/5);
			bg2.setSpdx(-MOVESPD/5);
		}
		if (centX>=SCREEN_WIDTH / 4 && spdX < 0) {
			centX -= spdX;
		}
		if (spdX < 0 && centX < SCREEN_WIDTH / 4) {
			bg1.setSpdx(MOVESPD/5);
			bg2.setSpdx(MOVESPD/5);
		}

		if (jump == true) {
			spdY += 1;
		}
		
		if(centX + spdX <= SCREEN_WIDTH / 5) {
			centX = SCREEN_WIDTH / 5 + 1;
		}
	}
	
	public void moveRight() {
		if (ducked == false) {
			setMovingRight(true);
			spdX = MOVESPD;
			animation = walkRight;
			animation.start();
		}
	}
	public void moveLeft() {
		if (ducked == false) {
			setMovingLeft(true);
			spdX = -MOVESPD;
			animation = walkLeft;
			animation.start();
		}
	}

	public void stop() {
		if (isMovingRight() == false && isMovingLeft() == false) {
			spdX = 0;
			animation = standing;
		}
		if (isMovingRight() == true && isMovingLeft() == false) {
			moveRight();
		}
		if (isMovingRight() == false && isMovingLeft() == true) {
			moveLeft();
		}
	}
	
	public void jump() {
		if (jump == false) {
			spdY = JUMPSPD;
			jump = true;
		}
	}

	public void shoot() {
		Projectile p = new Projectile(centX + 50, centY - 25);
		projectiles.add(p);
	}
	public int getCenterX() {
		return centX;
	}

	public int getCenterY() {
		return centY;
	}

	public boolean isJump() {
		return jump;
	}

	public int getSpdX() {
		return spdX;
	}

	public int getSpdY() {
		return spdY;
	}

	public void setCenterX(int centX) {
		this.centX = centX;
	}

	public void setCenterY(int centY) {
		this.centY = centY;
	}

	public void setJump(boolean jump) {
		this.jump = jump;
	}

	public void setSpdX(int spdX) {
		this.spdX = spdX;
	}

	public void setSpdY(int spdY) {
		this.spdY = spdY;
	}
	public int getHeight() {
		return tiley;
	}
	public boolean isDucked() {
		return ducked;
	}
	public void setDucked(boolean ducked) {
		this.ducked = ducked;
	}
	public void setMovingRight(boolean moving) {
		this.moveRight = moving;
	}
	public boolean isMovingRight() {
		return moveRight;
	}
	public void setMovingLeft(boolean moving) {
		this.tapLeft = moving;
	}
	public boolean isMovingLeft() {
		return tapLeft;
	}
	
	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}
}