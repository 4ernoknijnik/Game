package pacObj;

import javax.swing.JFrame;
import javax.swing.JPanel;

import pac.SnakeGame;

public class GUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void run() {

		SnakeGame Sn = new SnakeGame();

		JFrame frame = new JFrame(); // создаем окно
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // по завешении
																// закрытие окна
		frame.setResizable(false); // изменять размер окна
		frame.setSize(Sn.WIDTH * Sn.SCALE + 7, Sn.HEIGHT * Sn.SCALE + 30); // задаем
																			// размер
		// поля +7 для
		// ровного поля
		frame.setLocationRelativeTo(null); // окошко будет распологаться точно
											// по центру
		frame.add(new SnakeGame()); // добавляем к JFrame JPanel
		frame.setVisible(true); // сделать окно видным
	}
}
