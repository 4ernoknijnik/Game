package F1;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		JFrame f = new JFrame("Java F1"); // создали фрейм

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // по нажатию на
															// крестик окно
															// закрывается
		f.setSize(1100, 615); // указали размер фрейма
		f.add(new Road());
		f.setVisible(true);

	}

}
