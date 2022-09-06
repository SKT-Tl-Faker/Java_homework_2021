package painter;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


//字体会被整体修改，如果要修正，得在myshape类储存文本信息，或者新建个类存文本
//自由pencil还未实现
public class panels extends JPanel {
	String[] strsize = new String[26];// 调字体
	Color[] strcolor = { Color.red, Color.orange, Color.yellow, Color.green,
			Color.cyan, Color.blue, Color.BLACK };// 调颜色
	myshape shapes[] = new myshape[100];//图形数组
	int num = 0;//图形个数
	JPanel top = new JPanel();
	JPanel west = new JPanel();
	JTextField text = new JTextField();
	Color nowcolor = Color.black;//当前画笔的默认值
	String nowshape="Line";
	String nowsize="15";
	String nowtext="hello";
	
	panels() {
		this.setBackground(Color.gray);
		this.setPreferredSize(new Dimension(0, 800));
		this.setLayout(new BorderLayout());
		setmytop();
		setmywest();
		addMouseListener(new plistener());//设置面板内容
	}

	@Override
	protected void paintComponent(Graphics gp) {//画图形
		super.paintComponent(gp);
		int x0,y0,x1,y1;
		for (int i = 0; i < num; i++) {
			gp.setColor(shapes[i].co);
			x0=shapes[i].x0;
			y0=shapes[i].y0;
			y1=shapes[i].y1;
			x1=shapes[i].x1;
			switch (shapes[i].name) {
			case "Line":
				gp.drawLine(x0, y0, x1, y1);
				break;
			case "Rect":
				gp.drawRect(Math.min(x0, x1), Math.min(y0, y1),
						Math.abs(x0 - x1), Math.abs(y0 - y1));
				break;
			case "Oval":
				gp.drawOval(Math.min(x0, x1), Math.min(y0, y1),
						Math.abs(x0 - x1), Math.abs(y0 - y1));
				break;
			case "pencil":
				System.out.println("No");//还没实现
				break;
			case "Text":
				gp.setFont(new Font(null, 0, Integer.parseInt(nowsize)));
				gp.drawString(shapes[i].comp, x0, y0);
				break;
			}
		}
		this.repaint();
	}

	private void setmywest() {//侧面面板
		west.setPreferredSize(new Dimension(100, 0));
		west.setBackground(Color.green);
		west.setLayout(new FlowLayout());
		setmybuttons();
		this.add(west, BorderLayout.WEST);
	}

	private void setmybuttons() {//侧边按钮
		JButton buttons[] = new JButton[6];
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
			buttons[i].setPreferredSize(new Dimension(80, 100));
			west.add(buttons[i]);
			buttons[i].addActionListener(new btlistener());
		}
		buttons[0].setText("RePaint");
		buttons[1].setText("Line");
		buttons[2].setText("pencil");
		buttons[3].setText("Oval");
		buttons[4].setText("Rect");
		buttons[5].setText("Text");
	}

	private void setmytop() {//顶部面板
		top.setBackground(Color.yellow);
		this.add(top, BorderLayout.NORTH);
		top.setLayout(new GridLayout(2, 4, 5, 5));
		top.setPreferredSize(new Dimension(0, 100));
		setmysizeboxes();
		setmycolorboxes();
		setmytext();

		mylab sizelab = new mylab("Size");// labels
		mylab splabel = new mylab("ShapeColor");
		mylab bglabel = new mylab("BackGroundColor");
		mylab textlab = new mylab("Mytext");
		top.add(sizelab);
		top.add(bglabel);
		top.add(splabel);
		top.add(textlab);
	}

	private void setmytext() {//文本框
		top.add(text);
	}

	private void setmycolorboxes() {//颜色框
		// 背景颜色
		JComboBox<Color> bgcolorbox = new JComboBox<>(strcolor);
		bgcolorbox.setSize(new Dimension(100, 50));
		bgcolorbox.setMaximumRowCount(3);

		MyRender myrd = new MyRender();
		bgcolorbox.setRenderer(myrd);

		bgcolorboxlistener bgbl = new bgcolorboxlistener();
		bgcolorbox.addItemListener(bgbl);
		top.add(bgcolorbox);

		// 线条颜色
		JComboBox<Color> spcolorbox = new JComboBox<>(strcolor);
		spcolorbox.setMaximumRowCount(3);

		spcolorbox.setRenderer(myrd);

		spcolorboxlistener wordbl = new spcolorboxlistener();
		spcolorbox.addItemListener(wordbl);
		top.add(spcolorbox);
	}

	void setmysizeboxes() {//字体大小框
		for (int i = 5; i < 31; i++) {
			strsize[i - 5] = Integer.toString(i);
		}
		JComboBox<String> sizebox = new JComboBox<>(strsize);
		sizebox.setForeground(Color.RED);

		sizeboxlistenner bl = new sizeboxlistenner();
		sizebox.addItemListener(bl);
		sizebox.setSelectedIndex(10);

		top.add(sizebox);
	}


	class mylab extends JLabel {
		mylab() {
			this.setText("not initialized");
		}

		mylab(String x) {
			this.setOpaque(true);
			this.setText(x);
			this.setFont(new Font(null, 3, 18));
			this.setHorizontalAlignment(0);
			this.setBackground(Color.pink);
		}
	}

	class plistener implements MouseListener{//面板监听器
		int x0, y0, x1, y1;
		@Override
		public void mousePressed(MouseEvent arg0) {
			x0 = arg0.getX();
			y0 = arg0.getY();
			System.out.println("pressed");
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			x1 = arg0.getX();
			y1 = arg0.getY();
			shapes[num++] = new myshape(x0, y0, x1, y1, nowshape, nowcolor);
			if(nowshape=="Text"){
				shapes[num-1].comp=nowtext;
			}
			System.out.println("released");
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class btlistener implements ActionListener{//按钮监听器
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());
			nowshape = e.getActionCommand();
			if(e.getActionCommand()=="RePaint"){
				System.out.println("repaint successful");
				setbgcolor(Color.white);
				shapes=null;
				shapes= new myshape[100];
				num=0;
			}
			if(e.getActionCommand()=="Text"){
				nowtext=text.getText();
				System.out.println(text.getText());
			}
		}

		}


	class spcolorboxlistener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == 1){// 文颜色
				JComboBox x = (JComboBox) e.getSource();
				nowcolor=(Color)x.getSelectedItem();
				System.out.println("color changed");
			}

		}

	}

	class bgcolorboxlistener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == 1) {// 背景颜色
				JComboBox x = (JComboBox) e.getSource();
				System.out.println(x.getSelectedItem().toString());
				setbgcolor((Color) x.getSelectedItem());
			}
		}

	}

	void setbgcolor(Color c) {
		this.setBackground(c);
	}

	class sizeboxlistenner implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == 1) {
				nowsize =  (String) e.getItem();// 字体大小
				
			}
		}
	}

}
