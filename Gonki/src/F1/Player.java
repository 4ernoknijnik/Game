package F1;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player {

	public static final int MAX_V = 100; // обьявили константу макс. скорость
	public static final int MAX_TOP = 10; // граница вверх экрана
	public static final int MAX_BOTTOM = 460; // граница низ экрана

	Image img_centr = new ImageIcon(getClass().getClassLoader().getResource("res/player.jpg")).getImage();
	Image img_left = new ImageIcon(getClass().getClassLoader().getResource("res/player_left.jpg")).getImage();
	Image img_rigth = new ImageIcon(getClass().getClassLoader().getResource("res/player_right.jpg")).getImage();

	Image img = img_centr;

	public Rectangle getRect() {
		return new Rectangle(x, y, 120, 80); // оздали обьект столкновения
	}

	int v = 0; // скорость, to start =0
	int dv = 0; // ускорение, to start =0
	int s = 0; // путь

	int x = 30; // кординаты машины на
	int y = 100; // дороге
	int dy = 0; // движение машинки вверх вниз

	int layer1 = 0; // первый слой дороги
	int layer2 = 1200; // второй слой дороги

	public void move() {
		s += v; // путь сколько всего проехали
		v += dv; // увеличение/уменьшение скорости

		if (v <= 0)
			v = 0;
		if (v >= MAX_V)
			v = MAX_V;
		y -= dy; // движение вверх/низ по оси Y
		if (y <= MAX_TOP)
			y = MAX_TOP;
		if (y >= MAX_BOTTOM)
			y = MAX_BOTTOM;

		if (layer2 - v <= 0) { // подмена кординат слоев дороги - зацикливание
			layer1 = 0;
			layer2 = 1200;
		} else {
			layer1 -= v;
			layer2 -= v;

		}
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT) {
			dv = 5; // ускорение на 5 пикселей за каждое нажатие кнопки
		}
		if (key == KeyEvent.VK_LEFT) {
			dv = -5;
		}

		if (key == KeyEvent.VK_UP) {
			dy = 5; // движение по оси Y на 5 пикселей за каждое нажатие кнопки
			img = img_left;
		}
		if (key == KeyEvent.VK_DOWN) {
			dy = -5;
			img = img_rigth;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
			dv = 0;
		}
		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
			dy = 0;
			img = img_centr;
		}
	}
}
