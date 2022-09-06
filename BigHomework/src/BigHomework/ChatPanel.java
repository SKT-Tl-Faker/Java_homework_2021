package BigHomework;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class ChatPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	northpanel north;// 大面板
	JTextArea chatrecord;
	southpanel south;
	JTextField portfield = new JTextField("8888");// 北部
	JTextField ipfield = new JTextField("localhost");
	JButton connect = new JButton("Connect");
	JButton listenbt = new JButton("Listen");
	JButton stopbt = new JButton("Stop");
	JButton huiqi = new JButton("悔棋");
	JButton surrender = new JButton("投降");
	JButton fupan = new JButton("复盘");
	JButton musicgo = new JButton("music");
	JLabel portlabel = new JLabel("Port:");
	JLabel iplabel = new JLabel("IP :");
	JLabel timelabel = new JLabel("00");
	JButton sendbt = new JButton("Send");
	JTextArea messagearea = new JTextArea("Please enter sth...");// 南部
	ImageIcon bg = new ImageIcon("bg2.png");
	int nowtime = 60;

	public static ActionListener listenner = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand() + " button pushed");
			switch (e.getActionCommand()) {
			case "Connect":
				try {
					Vars.c.connectpushed();
				} catch (Exception e1) {
					Vars.cp.addstr("Connect error", false);
					e1.printStackTrace();
				}
				break;
			case "Listen":
				Vars.c.listenpushed();
				break;
			case "悔棋":
				Vars.c.huiqipushed();
				break;
			case "投降":
				Vars.c.surrenderpushed();
				break;
			case "music":
				Vars.c.playmusic();
				break;
			case "Send":
				Vars.c.Sendpushed();
				break;
			case "复盘":
				Vars.c.fupanpushed();
				break;
			case "Stop":
				try {
					Vars.c.stoppushed();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
			}
		}
	};

	void changelabelbgcolor(){
		if(timelabel.getBackground()==Color.black)
			timelabel.setBackground(Color.white);
		else timelabel.setBackground(Color.black);
	}
	public ChatPanel() {
		chatrecord = new JTextArea();
		north = new northpanel();
		south = new southpanel();
		this.setPreferredSize(new Dimension(300, 700));
		this.setLayout(new BorderLayout());
		this.add(north, BorderLayout.NORTH);
		
		// 构造滚动组件并使之透明
		JScrollPane js = new JScrollPane(chatrecord);
		chatrecord.setOpaque(false);
		chatrecord.setFont(new Font(null, 1, 14));
		TitledBorder t=new TitledBorder("聊天记录");
		t.setTitleFont(new Font(null, 3, 18));
		chatrecord.setBorder(t);
		js.setOpaque(false);
		js.getViewport().setOpaque(false);
		this.add(js, BorderLayout.CENTER);
		this.add(south, BorderLayout.SOUTH);
	}

	@Override
	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
		arg0.drawImage(bg.getImage(),0,0,300,660,this);
	}
	void addstr(final String str, final boolean flag) {// flag 为1表示对方，0表示自己
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				chatrecord.append(new SimpleDateFormat("y-M-d H:mm:s")
				.format(new Date()));
				chatrecord.append("\n");
				if (flag)
					chatrecord.append(" Enemy: ");
				else
					chatrecord.append(" You: ");
				chatrecord.append(str + "\n");
			}
		});
	}

	class northpanel extends JPanel {
		private static final long serialVersionUID = 1L;

		public northpanel() {
			this.setPreferredSize(new Dimension(300, 130));
			this.setLayout(null);// 绝对布局
			setlocation();
			setcolors();
			stopbt.setEnabled(false);
			fupan.setEnabled(false);
			chatrecord.setEditable(false);
			timelabel.setFont(new Font(null, 2, 70));
			this.add(iplabel);
			this.add(portlabel);
			this.add(ipfield);
			this.add(portfield);
			this.add(connect);
			this.add(listenbt);
			this.add(huiqi);
			this.add(surrender);
			this.add(stopbt);
			this.add(fupan);
			this.add(timelabel);
			this.add(musicgo);
			musicgo.addActionListener(listenner);
			stopbt.addActionListener(listenner);
			connect.addActionListener(listenner);
			listenbt.addActionListener(listenner);
			huiqi.addActionListener(listenner);
			surrender.addActionListener(listenner);
			fupan.addActionListener(listenner);
		}

		private void setcolors() {
			this.setBackground(Color.lightGray);
			timelabel.setBackground(Color.GRAY);
			timelabel.setOpaque(true);
			timelabel.setForeground(Color.red);
			chatrecord.setBackground(Color.LIGHT_GRAY);
			surrender.setBackground(Color.orange);
			connect.setBackground(Color.orange);
			musicgo.setBackground(Color.orange);
//			ipfield.setOpaque(false);
			ipfield.setBackground(Color.yellow);
			portfield.setBackground(Color.yellow);
			messagearea.setBackground(Color.LIGHT_GRAY);
			sendbt.setBackground(Color.darkGray);
			sendbt.setForeground(Color.white);
			listenbt.setBackground(Color.orange);
			huiqi.setBackground(Color.orange);
			stopbt.setBackground(Color.orange);
			fupan.setBackground(Color.orange);
		}

		private void setlocation() {
			ipfield.setBounds(100, 3, 190, 30);
			iplabel.setBounds(70, 3, 30, 30);
			portlabel.setBounds(70, 35, 30, 30);
			portfield.setBounds(100, 35, 93, 30);
			connect.setBounds(100, 66, 93, 30);
			listenbt.setBounds(197, 66, 93, 30);
			surrender.setBounds(3, 3, 60, 30);
			huiqi.setBounds(3, 35, 60, 30);
			stopbt.setBounds(197, 98, 93, 30);
			fupan.setBounds(100, 98, 93, 30);
			timelabel.setBounds(3, 67, 90, 60);
			musicgo.setBounds(197, 35, 93, 30);
		}
	}

	class southpanel extends JPanel {
		private static final long serialVersionUID = 1L;

		public southpanel() {
			this.setBackground(Color.DARK_GRAY);
			this.setPreferredSize(new Dimension(300, 100));
			this.setLayout(new BorderLayout());
			this.add(new JScrollPane(messagearea));
			this.add(sendbt, BorderLayout.EAST);

			messagearea.setFont(new Font(null, 2, 20));
			sendbt.setFont(new Font(null, 3, 15));
			sendbt.addActionListener(listenner);
		}
	}

	public void resettime() {
		nowtime = 60;
	}

	public void timecount() {
		new Thread() {
			public void run() {
				while (true) {
					if (nowtime > 0)
						nowtime--;
					timelabel.setText(Integer.toString(nowtime));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}
