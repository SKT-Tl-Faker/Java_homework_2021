package BigHomework;

import java.awt.Color;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.SwingUtilities;

public class Net {
	int port = 8888;
	String ip = "localhost";
	ServerSocket ss;
	Socket s;
	BufferedReader br;
	PrintWriter pw;
	boolean shutdown = false;

	void closeall() throws Exception {
		shutdown = true;
		s.close();
		br.close();
		pw.close();
	}

	void analysismsg(String msg) throws Exception {
		if (msg == null) {
			closeall();
			Vars.cp.addstr("对方断开连接", true);
			shutdown = true;
		}
		if (msg.startsWith("Order:")) {
			msg = msg.substring(6);
			int x = Integer.parseInt(msg);
			Vars.c.reveiveorder(x);
		}
		if (msg.startsWith("Play:")) {
			msg = msg.substring(5);
			String loc[] = msg.split(",");
			int x = Integer.parseInt(loc[0]);
			int y = Integer.parseInt(loc[1]);
			Vars.c.receivechess(x, y);
		}
		if (msg.startsWith("Chat:")) {
			msg = msg.substring(5);
			Vars.cp.addstr(msg, true);
		}
	}

	void clientgo() throws Exception {// 建立网络连接
		s = new Socket(ip, port);
		Vars.cp.addstr("连接成功！", false);

		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		pw = new PrintWriter(s.getOutputStream(), true);

		new Thread() {
			public void run() {// 获取网络信息的线程
				while (true) {
					if (shutdown == true) {
						shutdown = false;
						break;
					}
					try {
						String msg = br.readLine();
						analysismsg(msg);
					} catch (Exception e) {
						Vars.cp.addstr("断开连接...", true);
						shutdown = true;
						e.printStackTrace();
						break;
					}
				}
			}
		}.start();
		Vars.cp.timelabel.setBackground(Color.black);
		Vars.cp.addstr("Let's start talking!\n你的颜色是 白色（后手）",
				true);
		Vars.m.localcolor = Model.White;
		Vars.m.enemycolor = Model.Black;
		Vars.c.myturn = false;
		Vars.cp.timecount();
	}

	public void connected() throws Exception {
		port = Integer.parseInt(Vars.cp.portfield.getText());
		ip = Vars.cp.ipfield.getText();

		clientgo();
		Vars.cp.listenbt.setEnabled(false);
		Vars.cp.connect.setEnabled(false);
		Vars.cp.stopbt.setEnabled(true);
		Vars.cp.stopbt.setBackground(Color.red);
	}

	public void listened() {
		port = Integer.parseInt(Vars.cp.portfield.getText());
		try {
			new Thread() {
				public void run() {
					try {
						servergo();
					} catch (Exception e) {
						e.printStackTrace();
					}
				};
			}.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void servergo() throws Exception {
		ss = new ServerSocket(port);
		Vars.cp.addstr("Waiting...", false);
		s = ss.accept();
		Vars.cp.addstr("连接成功！", false);

		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		pw = new PrintWriter(s.getOutputStream(), true);

		new Thread() {
			public void run() {// 获取网络信息的线程
				while (true) {
					if (shutdown == true) {
						shutdown = false;
						break;
					}
					try {
						String msg = br.readLine();
						analysismsg(msg);
					} catch (Exception e) {
						Vars.cp.addstr("断开连接...", true);
						e.printStackTrace();
						shutdown = true;
						break;
					}
				}
			}
		}.start();
		Vars.m.localcolor = Model.Black;
		Vars.m.enemycolor = Model.White;
		Vars.c.myturn = true;
		Vars.cp.addstr("Let's start talking!\n你的颜色是 黑色（先手）",
				true);
		Vars.cp.timelabel.setBackground(Color.black);
		Vars.cp.listenbt.setEnabled(false);
		Vars.cp.connect.setEnabled(false);
		Vars.cp.stopbt.setEnabled(true);
		Vars.cp.stopbt.setBackground(Color.red);
		Vars.cp.timecount();
	}

	public void sendmsg() {
		String msg = Vars.cp.messagearea.getText();
		Vars.cp.addstr(msg, false);
		if (pw != null)
			pw.println("Chat:" + msg);
	}

	public void sendmsg(String m) {
		if (pw != null)
			pw.println("Chat:" + m);
	}

	public void stopped() throws Exception {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Vars.cp.listenbt.setEnabled(true);
				Vars.cp.connect.setEnabled(true);
				Vars.cp.stopbt.setEnabled(false);
				Vars.cp.stopbt.setBackground(Color.orange);
				Vars.cp.addstr("You stopped", false);
			}
		});
		sendmsg("Enemy stopped");
		closeall();
	}

	public void sendmychess(int row, int col) {
		if (pw != null)
			pw.println("Play:" + Integer.toString(row) + ","
					+ Integer.toString(col));
	}

	public void receivechess(int x, int y) {
		Vars.c.enemyput(x, y);
	}

	public void sendorder(int i) {
		if (pw != null)
			pw.println("Order:" + i);
	}
}
