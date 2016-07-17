package F1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Road extends JPanel implements ActionListener, Runnable {

	Timer mainTimer = new Timer(20, this); // создали таймер и задали выполнение
											// Action каждые 20 мс
	Image img = new ImageIcon(getClass().getClassLoader().getResource("res/bg_road.jpg")).getImage(); // добавили
																										// картинку
																										// дороги

	Player p = new Player();

	Thread enemiesFactory = new Thread(this); // поток дл€ изготовлени€ врагов

	Thread audioThread = new Thread(new AudioThread()); // создали аудио поток

	List<Enemy> enemies = new ArrayList<Enemy>(); // коллекци€ - список -
													// эррейлист

	public Road() { // запуск/старт программ
		mainTimer.start();
		enemiesFactory.start();
		audioThread.start();
		addKeyListener(new MyKeyAdapter());
		setFocusable(true);

	}

	private class MyKeyAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			p.keyPressed(e);
		}

		public void keyReleased(KeyEvent e) {
			p.keyReleased(e);
		}

	}

	public void paint(Graphics g) {
		g = (Graphics2D) g;
		g.drawImage(img, p.layer1, 0, null); // метод пеинт рисует первый слой
		g.drawImage(img, p.layer2, 0, null); // метод пеинт рисует второй слой
		g.drawImage(p.img, p.x, p.y, null); // метод пеинт рисует машину игрока

		double v = (200 / Player.MAX_V) * p.v;
		g.setColor(Color.WHITE);
		Font font = new Font("Arrial", Font.ITALIC, 40); // вид текста
															// спидометра
		g.setFont(font); // устанавливаем фонт
		g.drawString("Speed" + v + " Km\\h", 100, 30); // ¬ывод скорости на
														// экран

		Iterator<Enemy> i = enemies.iterator();
		while (i.hasNext()) {
			Enemy e = i.next();
			if (e.x >= 2400 || e.x <= -2400) {
				i.remove();
			} else {
				e.move();
				g.drawImage(e.img, e.x, e.y, null); // метод пеинт рисует врага

			}
		}
	}

	public void actionPerformed(ActionEvent e) { // это выполн€етс€ каждые 20м/с
													// согласно таймеру
		p.move(); // едит наш игрок
		repaint(); // перерисовываем
		testCollisionWithEnemies(); // провер€ем столкнулись мы с кем-то или нет
		testWin();

	}

	private void testWin() {
		if (p.s > 50000) { // сколько нужно проехать дл€ победы
			JOptionPane.showMessageDialog(null, "You WIN! CONGRATILETION!!!");
			System.exit(0);
		}
	}

	private void testCollisionWithEnemies() {
		Iterator<Enemy> i = enemies.iterator();
		while (i.hasNext()) {
			Enemy e = i.next();
			if (p.getRect().intersects(e.getRect())) {
				JOptionPane.showMessageDialog(null, "Game Over!!!"); // табличка
																		// over
				System.exit(1);
				// i.remove(); //когда мы сталкиваемс€ враги исчезают
			}
		}

	}

	@Override
	public void run() {

		while (true) {
			Random rand = new Random();
			try {
				Thread.sleep(rand.nextInt(2000));
				enemies.add(new Enemy(1200, rand.nextInt(460), rand.nextInt(60), this));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
