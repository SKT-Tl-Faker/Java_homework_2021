package BigHomework;

import java.util.LinkedList;

public class Model {
	public final static int Black = -1;
	public final static int White = 1;
	public final static int Space = 0;
	public final static int Width = 19;
	public int localcolor = Black;
	public int enemycolor = White;
	private LinkedList<ChessPoint> chesses = new LinkedList<>();
	public int data[][] = new int[Width][Width];

	public int getchesscolor(int i, int j) {
		return data[i][j];
	}

	public void putchess(int x, int y, int color) {
		Vars.m.data[x][y] = color;
		chesses.add(new ChessPoint(color, x, y));
	}

	public void huiqi() {
		ChessPoint temp = chesses.removeLast();
		data[temp.x][temp.y] = 0;
	}

	boolean win(int row, int col) {// 从下棋点往四个方向分别判断，row从1开始
		for (int x = row, y = 1, win = 1; insize(x, y + 1); win++) {// 横向
			if (data[x - 1][y - 1] == data[x - 1][y] && data[x - 1][y - 1] != 0) {
			} else
				win = 1;
			y++;
			if (win == 5)
				return true;
		}
		for (int x = 1, y = col, win = 1; insize(x + 1, y); win++) {// 纵向
			if (data[x - 1][y - 1] == data[x][y - 1] && data[x - 1][y - 1] != 0) {
			} else
				win = 1;
			x++;
			if (win == 5)
				return true;
		}
		int x1 = row, y1 = col;
		while (x1 != Width && y1 != 1) {// x1和y1作为游标，归到左下角
			x1++;
			y1--;
		}
		for (int win = 1; insize(x1 - 1, y1 + 1); win++) {// 副对角线
			if (data[x1 - 1][y1 - 1] == data[x1 - 2][y1]
					&& data[x1 - 1][y1 - 1] != 0) {
			} else
				win = 1;
			x1--;
			y1++;
			if (win == 5)
				return true;
		}
		int x2 = row, y2 = col;
		while (x2 != 1 && y2 != 1) {// 归到左上角
			x2--;
			y2--;
		}
		for (int win = 1; insize(x2 + 1, y2 + 1); win++) {// 主对角线
			if (data[x2 - 1][y2 - 1] == data[x2][y2]
					&& data[x2 - 1][y2 - 1] != 0) {
			} else
				win = 1;
			x2++;
			y2++;
			if (win == 5)
				return true;
		}
		return false;
	}

	boolean insize(int row, int col) {// 判断目标位置是否在棋盘内
		if (row <= Width && row > 0 && col <= Width && col > 0)
			return true;
		return false;
	}

	public void fupan() {
		for (int i = 0; i < Width; i++) {
			for (int j = 0; j < Width; j++) {
				data[i][j] = 0;
			}
		}
		new Thread() {
			public void run() {
				int num=chesses.size();
				for (int i = 0; i < num; i++){
					ChessPoint temp= chesses.get(i);
					data[temp.x][temp.y]=temp.color;
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
