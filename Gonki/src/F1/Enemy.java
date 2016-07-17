package F1;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy {

	int x;
	int y;
	int v;
	Image img = new ImageIcon(getClass().getClassLoader().getResource("res/enemy.jpg")).getImage();
	// нарисовали врага
	Road road;

	public Rectangle getRect() {
		return new Rectangle(x, y, 120, 80); // оздали обьект столкновения
	}

	public Enemy(int x, int y, int v, Road road) {
		this.x = x;
		this.y = y;
		this.v = v;
		this.road = road;
	}

	public void move() {
		x = x - road.p.v + v; // описали движение врага, road.p.v - это скорость
								// самой дороги
	}

}
