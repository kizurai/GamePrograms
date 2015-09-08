package dungeon;

public class Enemy {
	private int maxHealth, currentHealth, power, spdx, centx, centy;
	private Background bg = MainClass.getBg1();
	
	// Behavioral Methods
	public void update() {
		centx += spdx;
		spdx = bg.getSpdx()*5;
	}
	
	public void die() {
		//TODO
	}
	
	public void attack() {
		//TODO
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public int getCurrentHealth() {
		return currentHealth;
	}
	public int getPower() {
		return power;
	}
	
	public int getSpeedX() {
		return spdx;
	}
	
	public int getCenterX() {
		return centx;
	}
	
	public int getCenterY() {
		return centy;
	}
	
	public Background getBg() {
		return bg;
	}
	
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	public void setCurrentHealth (int currentHealth) {
		this.currentHealth = currentHealth;
	}
	
	public void setPower (int power) {
		this.power = power;
	}
	
	public void setSpeedX(int spdx) {
		this.spdx = spdx;
	}
	
	public void setCenterX(int centx) {
		this.centx = centx;
	}
	
	public void setCenterY(int centy) {
		this.centy = centy;
	}
	
	public void setBg(Background bg) {
		this.bg = bg;
	}
}
