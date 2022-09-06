package BigHomework;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class MainClass {
	public static void main(String[] args) {
		JFrame f = new JFrame("这不是五子棋");

		f.setSize(1000, 700);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);

		f.add(Vars.cv, BorderLayout.CENTER);
		f.add(Vars.cp, BorderLayout.EAST);

		f.setVisible(true);
	}
}
