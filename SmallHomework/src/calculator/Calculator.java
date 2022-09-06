package calculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

public class Calculator {
	
	public static void main(String[] args) {
		Frame fr=new Frame("超简易的计算器");
		fr.setVisible(true);
	}	
}

class Frame extends JFrame {
	
	JButton cbts[]=new JButton[12];//数字
	JButton nbts[]=new JButton[4];//运算
	JButton ebts[]=new JButton[4];//符号
	Label topl=new Label("0");//显示区域

	Frame(String x){//初始化构造函数
		this.setTitle(x);
		this.setSize(700, 700);
		this.setLocationRelativeTo(null);//居中
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		topl.setFont(new Font(null,1,90));//显示区的设置
		topl.setAlignment(2);
		topl.setPreferredSize(new Dimension(WIDTH,200));
		topl.setBackground(Color.red);
		topl.setForeground(Color.black);
		this.add(topl,BorderLayout.NORTH);
		
		JPanel mpls=new JPanel();//按键板的设置
		mpls.setBackground(Color.cyan);
		mpls.setLayout(new BorderLayout());
		this.add(mpls,BorderLayout.CENTER);
		
		JPanel cpls=new JPanel();//数字板
		JPanel npls=new JPanel();//符号板
		JPanel epls=new JPanel();//运算板
		cpls.setPreferredSize(new Dimension(WIDTH,400));
		npls.setPreferredSize(new Dimension(WIDTH,100));
		epls.setPreferredSize(new Dimension(this.getWidth()/4,HEIGHT));
		cpls.setLayout(new GridLayout(4,3));
		npls.setLayout(new GridLayout(1,5));
		epls.setLayout(new GridLayout(4,1));
		cpls.setBackground(Color.BLUE);
		npls.setBackground(Color.pink);
		epls.setBackground(Color.white);
		mpls.add(npls,BorderLayout.NORTH);
		mpls.add(cpls,BorderLayout.CENTER);
		mpls.add(epls,BorderLayout.EAST);
		
		for (int i = 1; i < 13; i++){//数字键,1-9为数字，10为+/-，11为0,12为.;
			cbts[i-1]=new JButton(Integer.toString(i));
			cpls.add(cbts[i-1]);
			cbts[i-1].setBackground(Color.blue);
			cbts[i-1].setForeground(Color.white);
			cbts[i-1].setFont(new Font(null,1,30));
			cbts[i-1].addActionListener(numbers);
		}
		cbts[11].setText(".");
		cbts[9].setText("+/-");
		cbts[9].setBackground(Color.gray);
		cbts[11].setBackground(Color.gray);
		cbts[10].setText("0");
		
		for (int i = 1; i < 5; i++){//运算键
			nbts[i-1]=new JButton(Integer.toString(i));
			npls.add(nbts[i-1]);
			nbts[i-1].setBackground(Color.yellow);
			nbts[i-1].setFont(new Font(null,1,30));
			nbts[i-1].addActionListener(commands);
		}
		nbts[0].setText("红配绿yyds!");
		nbts[0].setFont(new Font(null,1,20));
		nbts[1].setText("c");
		nbts[2].setText("←");
		nbts[3].setText("=");
		
		for (int i = 1; i < 5; i++){//符号键
			ebts[i-1]=new JButton(Integer.toString(i));
			epls.add(ebts[i-1]);
			ebts[i-1].setBackground(Color.green);
			ebts[i-1].setFont(new Font(null,1,30));
			ebts[i-1].addActionListener(operations);
		}
		ebts[0].setText("+");
		ebts[1].setText("－");//为了避免和负号的冲突，减号用的中文
		ebts[2].setText("*");
		ebts[3].setText("/");		
	}
	
	ActionListener numbers=new ActionListener() {//按钮们的监听器
			@Override
			public void actionPerformed(ActionEvent e) {
				solvenum(e.getActionCommand());
			}
		};
	ActionListener commands =new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			solvecom(e.getActionCommand());
		}
	};
	ActionListener operations =new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			solveope(e.getActionCommand());
		}
	};
	
	private void solvenum(String actionCommand) {//对数字键的处理
		if(actionCommand.compareTo("0")>=0&&actionCommand.compareTo("9")<=0){
			if(isempty())
				topl.setText(actionCommand);
			else
				topl.setText(topl.getText()+actionCommand);
		}
		if(actionCommand.equals("."))
			topl.setText(topl.getText()+actionCommand);
	}
	
	private void solvecom(String actionCommand) {//对运算键的处理
		if(actionCommand.equals("c")){
			topl.setText("0");
		}
		if(actionCommand.equals("=")){
			topl.setText(calcu());
		}
		if(actionCommand.equals("←")){
			if(topl.getText().length()!=1)
			topl.setText(topl.getText().substring(0, topl.getText().length()-1));
			else topl.setText("0");
		}
	}
	
	private void solveope(String actionCommand) {//符号的处理
		topl.setText(calcu());
		if(!isempty())
		topl.setText(topl.getText()+actionCommand);
	}
	
	private String calcu() {
		String s=topl.getText();//对加减乘除进行计算
		double x;
		double y;
		double z=0;
		if(s.contains("+")){
			x=Double.parseDouble(s.split("\\+")[0]);
			y=Double.parseDouble(s.split("\\+")[1]);
			z=x+y;
		}
		else if(s.contains("－")){
			x=Double.parseDouble(s.split("\\－")[0]);
			y=Double.parseDouble(s.split("\\－")[1]);
			z=x-y;
		}
		else if(s.contains("*")){
			x=Double.parseDouble(s.split("\\*")[0]);
			y=Double.parseDouble(s.split("\\*")[1]);
			z=x*y;
		}
		else if(s.contains("/")){
			x=Double.parseDouble(s.split("\\/")[0]);
			y=Double.parseDouble(s.split("\\/")[1]);
			z=x/y;
		}
		else z=Double.parseDouble(s);
		if(z==(int)z)
			return Integer.toString((int)z);//如果是整数就不输出小数点后面的0
		return String.format("%.4f", z).toString();//输出保留四位小数
	}
	
	boolean isempty(){//判断文本是否空
		if(topl.getText().equals("0"))
			return true;
		else return false;
	}
}