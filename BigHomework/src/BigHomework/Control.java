package BigHomework;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Control {
	boolean myturn = false;
	int mymusic = 0;
	public void localput(final int row, final int col) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (row * col < 0 || row >= Model.Width || col >= Model.Width)
					return;
				if ((Vars.m.data[row][col] == 0) && myturn) {
					Vars.m.putchess(row, col, Vars.m.localcolor);
					myturn = false;
					sendmychess(row,col);
					Vars.cp.resettime();
					Vars.cp.changelabelbgcolor();
					if (Vars.m.win(row + 1, col + 1)) {
						iwined();
					}
				}
			}
		});
	}

	public void enemyput(final int row, final int col) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (row * col < 0 || row >= Model.Width || col >= Model.Width)
					return;
				if ((Vars.m.data[row][col] == 0) && !myturn) {
					Vars.m.putchess(row, col, Vars.m.enemycolor);
					Vars.cp.resettime();
					myturn = true;
					Vars.cp.changelabelbgcolor();
					if (Vars.m.win(row + 1, col + 1)) {
						enemywined();
					}
				}
			}
		});
	}

	private void iwined() {
		new Thread() {
			public void run() {
				JOptionPane.showMessageDialog(Vars.cv, "You win!");
			}
		}.start();
		Vars.cp.addstr("You win!", false);
		Vars.n.sendmsg("You lose!");
		myturn = false;
		Vars.cp.fupan.setEnabled(true);
	}

	private void enemywined() {
		JOptionPane.showMessageDialog(Vars.cv, "You lose!");
		Vars.cp.addstr("Enemy win!", false);
		Vars.n.sendmsg("Enemy lose!");
		myturn = false;
		Vars.cp.fupan.setEnabled(true);
	}

	public void connectpushed() throws Exception {
		Vars.n.connected();
	}

	public void listenpushed() {
		Vars.n.listened();
	}

	public void huiqipushed() {
		Vars.n.sendorder(2);
		Vars.cp.addstr("发起悔棋", false);
	}

	public void enemyhq() {// 点击确定为0
		Vars.cp.addstr("发起悔棋", true);
		int check = JOptionPane.showConfirmDialog(Vars.cv, "是否同意", "对方发起悔棋",
				JOptionPane.YES_NO_OPTION);
		if (check == 0) {
			Vars.cp.addstr("同意悔棋", false);
			Vars.n.sendmsg("同意悔棋");
			Vars.m.huiqi();
			Vars.c.myturn=(myturn==true?false:true);
			Vars.cp.changelabelbgcolor();
			Vars.n.sendorder(3);// 3为同意悔棋
		} else {
			Vars.cp.addstr("拒绝悔棋", false);
			Vars.n.sendmsg("拒绝悔棋");
		}
	}

	public void enemysrdr() {
		Vars.cp.addstr("对方投降", false);
		JOptionPane.showMessageDialog(Vars.cv, "Enemy surrendered");
		iwined();
	}

	public void surrenderpushed() {
		Vars.n.sendorder(1);
		Vars.cp.addstr("你发起投降", false);
		JOptionPane.showMessageDialog(Vars.cv, "You surrendered");
		enemywined();
	}

	public void Sendpushed() {
		Vars.n.sendmsg();
	}

	public void stoppushed() throws Exception {
		Vars.n.stopped();
	}

	public void sendmychess(int row, int col) {
		Vars.n.sendmychess(row, col);
	}

	public void receivechess(int x, int y) {
		Vars.n.receivechess(x, y);
	}

	public void reveiveorder(int x) {// 发送的消息为一位数字代码。1=投降,2=对方发起悔棋，3=对方同意悔棋
		switch (x) {
		case 1:
			enemysrdr();
			break;
		case 2:
			enemyhq();
			break;
		case 3:
			Vars.m.huiqi();
			myturn=myturn==true?false:true;
			Vars.cp.changelabelbgcolor();
			break;
		}
	}

	public void fupanpushed() {
		Vars.m.fupan();
	}

	public void playmusic() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (mymusic == 0) {
					Vars.mus.loopgo("kunte.wav");
					mymusic=1;
					Vars.cp.musicgo.setBackground(Color.red);
				} else{
					Vars.mus.stopmusic();
					Vars.cp.musicgo.setBackground(Color.orange);
					mymusic=0;
				}
			}
		});
	}

}
