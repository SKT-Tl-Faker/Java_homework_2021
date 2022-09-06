package cdStore;

import java.io.*;
import java.util.*;

public class Mainwindow {
	public static int vnum = 1;
	public static int cnum = 1;
	LinkedList<Users> user = new LinkedList<Users>();
	LinkedList<Disks> cd = new LinkedList<Disks>();
	BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
	Scanner cinint = new Scanner(System.in);

	public static void main(String[] args) {
		Mainwindow test = new Mainwindow();
		test.run();
	}

	void run() {// ִ��
		while (true) {
			printmain();
				int j=Input();
				switch (j) {
				case -1:
					System.out.println(cd.toString());
					break;
				case 0:
					System.out.println(user.toString());
					break;
				case 1:
					vipMan();
					break;
				case 2:
					cdMan();
					break;
				case 3:
					return;
				default:
					System.out.println("wrong");
				}
			
		}

	}

	void printmain() {//���˵�
		System.out.println("-1:showcd");
		System.out.println("0:showvip");
		System.out.println("1:Vips");
		System.out.println("2:Cds");
		System.out.println("3:Quit");
	}

	void vipMan() {//��Ա����̨
		while (true) {
			System.out.println("0:��vip");
			System.out.println("1:��ֵ");
			System.out.println("2:��Ǯ");
			System.out.println("3:����");
			
				int j = Input();
				switch (j) {
				case 0:
					setvip();
					break;
				case 1:
					addmoney();
					break;
				case 2:
					delvip();
					break;
				case 3:
					return;
				default:
					System.out.println("wrong");
				}
			} 
		}

	private void addmoney() {
		System.out.println("id,money");
		int id = cinint.nextInt();
		int m = cinint.nextInt();
		user.get(id).money += m;
	}

	private void setvip() {
		System.out.println("money,name");
		try {
			int id = Mainwindow.vnum++;
			int money = cinint.nextInt();
			String name = cin.readLine();
			user.add(new Users(id, name, money));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void delvip() {
		try {
			String n = cin.readLine();
			int m = Integer.parseInt(n);
			user.remove(m);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void cdMan() {//disk����̨
		while (true) {
			System.out.println("0:��cd");
			System.out.println("1:��cd");
			System.out.println("2:����");
			System.out.println("3:����");
			int j = Input();
			switch (j) {
			case 0:
				borrow();
				break;
			case 1:
				sellcd();
				break;
			case 2:
				setcd();
				break;
			case 3:
				return;
			default:
				System.out.println("wrong");
			}
		}
	}

	private void borrow() {// �ݶ�����۸�Ϊ�ۼ�һ��
		System.out.println("user id");
		int m = cinint.nextInt();
		System.out.println("cd id");
		int j = cinint.nextInt();
		user.get(m).money -= cd.get(j).price / 2;
	}

	private void setcd() {
		System.out.println("price,name");
		try {
			int id = Mainwindow.cnum++;
			int price = cinint.nextInt();
			String name = cin.readLine();
			cd.add(new Disks(id, price, name));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sellcd() {
		System.out.println("user id");
		int m = cinint.nextInt();
		System.out.println("cd id");
		int j = cinint.nextInt();
		user.get(m).money -= cd.get(j).price;
		cd.remove(j);
	}
	private int Input() {//���뺯��
		try {
			String line;
			line = cin.readLine();
			return Integer.parseInt(line);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
