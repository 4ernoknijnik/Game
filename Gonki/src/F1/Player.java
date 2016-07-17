package F1;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player {

	public static final int MAX_V = 100; // �������� ��������� ����. ��������
	public static final int MAX_TOP = 10; // ������� ����� ������
	public static final int MAX_BOTTOM = 460; // ������� ��� ������

	Image img_centr = new ImageIcon(getClass().getClassLoader().getResource("res/player.jpg")).getImage();
	Image img_left = new ImageIcon(getClass().getClassLoader().getResource("res/player_left.jpg")).getImage();
	Image img_rigth = new ImageIcon(getClass().getClassLoader().getResource("res/player_right.jpg")).getImage();

	Image img = img_centr;

	public Rectangle getRect() {
		return new Rectangle(x, y, 120, 80); // ������ ������ ������������
	}

	int v = 0; // ��������, to start =0
	int dv = 0; // ���������, to start =0
	int s = 0; // ����

	int x = 30; // ��������� ������ ��
	int y = 100; // ������
	int dy = 0; // �������� ������� ����� ����

	int layer1 = 0; // ������ ���� ������
	int layer2 = 1200; // ������ ���� ������

	public void move() {
		s += v; // ���� ������� ����� ��������
		v += dv; // ����������/���������� ��������

		if (v <= 0)
			v = 0;
		if (v >= MAX_V)
			v = MAX_V;
		y -= dy; // �������� �����/��� �� ��� Y
		if (y <= MAX_TOP)
			y = MAX_TOP;
		if (y >= MAX_BOTTOM)
			y = MAX_BOTTOM;

		if (layer2 - v <= 0) { // ������� �������� ����� ������ - ������������
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
			dv = 5; // ��������� �� 5 �������� �� ������ ������� ������
		}
		if (key == KeyEvent.VK_LEFT) {
			dv = -5;
		}

		if (key == KeyEvent.VK_UP) {
			dy = 5; // �������� �� ��� Y �� 5 �������� �� ������ ������� ������
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
