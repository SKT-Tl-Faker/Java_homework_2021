package painter;

import java.awt.*;

import javax.swing.*;

public class Painter {
	public static void main(String[] args) {
		myframe x = new myframe();
		x.setVisible(true);
	}
}

class myframe extends JFrame {
	myframe() {
		this.setTitle("Painter");// ≥ı ºªØ
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 800);
		this.setLocationRelativeTo(null);
		panels x = new panels();
		this.add(x, BorderLayout.NORTH);
	}
}



