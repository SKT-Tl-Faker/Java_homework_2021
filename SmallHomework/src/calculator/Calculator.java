package calculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

public class Calculator {
	
	public static void main(String[] args) {
		Frame fr=new Frame("�����׵ļ�����");
		fr.setVisible(true);
	}	
}

class Frame extends JFrame {
	
	JButton cbts[]=new JButton[12];//����
	JButton nbts[]=new JButton[4];//����
	JButton ebts[]=new JButton[4];//����
	Label topl=new Label("0");//��ʾ����

	Frame(String x){//��ʼ�����캯��
		this.setTitle(x);
		this.setSize(700, 700);
		this.setLocationRelativeTo(null);//����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		topl.setFont(new Font(null,1,90));//��ʾ��������
		topl.setAlignment(2);
		topl.setPreferredSize(new Dimension(WIDTH,200));
		topl.setBackground(Color.red);
		topl.setForeground(Color.black);
		this.add(topl,BorderLayout.NORTH);
		
		JPanel mpls=new JPanel();//�����������
		mpls.setBackground(Color.cyan);
		mpls.setLayout(new BorderLayout());
		this.add(mpls,BorderLayout.CENTER);
		
		JPanel cpls=new JPanel();//���ְ�
		JPanel npls=new JPanel();//���Ű�
		JPanel epls=new JPanel();//�����
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
		
		for (int i = 1; i < 13; i++){//���ּ�,1-9Ϊ���֣�10Ϊ+/-��11Ϊ0,12Ϊ.;
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
		
		for (int i = 1; i < 5; i++){//�����
			nbts[i-1]=new JButton(Integer.toString(i));
			npls.add(nbts[i-1]);
			nbts[i-1].setBackground(Color.yellow);
			nbts[i-1].setFont(new Font(null,1,30));
			nbts[i-1].addActionListener(commands);
		}
		nbts[0].setText("������yyds!");
		nbts[0].setFont(new Font(null,1,20));
		nbts[1].setText("c");
		nbts[2].setText("��");
		nbts[3].setText("=");
		
		for (int i = 1; i < 5; i++){//���ż�
			ebts[i-1]=new JButton(Integer.toString(i));
			epls.add(ebts[i-1]);
			ebts[i-1].setBackground(Color.green);
			ebts[i-1].setFont(new Font(null,1,30));
			ebts[i-1].addActionListener(operations);
		}
		ebts[0].setText("+");
		ebts[1].setText("��");//Ϊ�˱���͸��ŵĳ�ͻ�������õ�����
		ebts[2].setText("*");
		ebts[3].setText("/");		
	}
	
	ActionListener numbers=new ActionListener() {//��ť�ǵļ�����
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
	
	private void solvenum(String actionCommand) {//�����ּ��Ĵ���
		if(actionCommand.compareTo("0")>=0&&actionCommand.compareTo("9")<=0){
			if(isempty())
				topl.setText(actionCommand);
			else
				topl.setText(topl.getText()+actionCommand);
		}
		if(actionCommand.equals("."))
			topl.setText(topl.getText()+actionCommand);
	}
	
	private void solvecom(String actionCommand) {//��������Ĵ���
		if(actionCommand.equals("c")){
			topl.setText("0");
		}
		if(actionCommand.equals("=")){
			topl.setText(calcu());
		}
		if(actionCommand.equals("��")){
			if(topl.getText().length()!=1)
			topl.setText(topl.getText().substring(0, topl.getText().length()-1));
			else topl.setText("0");
		}
	}
	
	private void solveope(String actionCommand) {//���ŵĴ���
		topl.setText(calcu());
		if(!isempty())
		topl.setText(topl.getText()+actionCommand);
	}
	
	private String calcu() {
		String s=topl.getText();//�ԼӼ��˳����м���
		double x;
		double y;
		double z=0;
		if(s.contains("+")){
			x=Double.parseDouble(s.split("\\+")[0]);
			y=Double.parseDouble(s.split("\\+")[1]);
			z=x+y;
		}
		else if(s.contains("��")){
			x=Double.parseDouble(s.split("\\��")[0]);
			y=Double.parseDouble(s.split("\\��")[1]);
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
			return Integer.toString((int)z);//����������Ͳ����С��������0
		return String.format("%.4f", z).toString();//���������λС��
	}
	
	boolean isempty(){//�ж��ı��Ƿ��
		if(topl.getText().equals("0"))
			return true;
		else return false;
	}
}