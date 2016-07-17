package pacObj;

import pac.SnakeGame;

public class Apple {
	SnakeGame main;
	
	public int posX;
	public int posY;
	
	public Apple(int startX,int startY){
		posX=startX;
		posY=startY;
	}
	
	public void setRandomPosition(){
		posX=(int)(Math.random()*main.WIDTH); // (int) - означает округление до интеджера
		posY=(int)(Math.random()*main.HEIGHT);
	}

}
