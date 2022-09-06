package BigHomework;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;

import javax.swing.*;

public class ChessView extends JPanel implements ImageObserver{
	private static final long serialVersionUID = 1L;
	
	int x=26,y=26,unit=36,gap=20;
	private ImageIcon bg= new ImageIcon("bg.png");
	public ChessView(){
		this.setBackground(Color.cyan);
		addclicklistener();
		addsizelistener();
		
	}
	private void addsizelistener() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				super.componentResized(e);
				int w=getWidth();
				int h=getHeight();
				System.out.println("当前窗口大小"+w+"*"+h);
				int min=w<h?w:h;
				unit=(min-2*gap)/(Model.Width-1);
				x=(w-unit*(Model.Width-1))/2;
				y=(h-unit*(Model.Width-1))/2;
			}
		});
		
	}
	private void addclicklistener() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int row=(e.getY()-y)/unit;
				int col=(e.getX()-x)/unit;
				if((e.getY()-y)%unit>unit/2)
					row++;
				if((e.getX()-x)%unit>unit/2)
					col++;
				Vars.c.localput(row,col);
			}
		});
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawbg(g);
		drawlines(g);
		drawchess(g);
	}
	private void drawbg(Graphics g) {
		// TODO 没有设置成随窗口变化大小
		g.drawImage(bg.getImage(),0,0,1000,660,this);
		
	}
	
	private void drawchess(Graphics g1) {
		Graphics2D g=(Graphics2D)g1;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_DEFAULT);
		for (int i = 0; i < Model.Width; i++) {
			for (int j = 0; j < Model.Width; j++) {
				if(i!=0&&j!=0&&i!=Model.Width-1&&j!=Model.Width-1){
					g.setColor(Color.black);
					g.fillOval( x+unit*j, y+unit*i, 4, 4);
				}
				int color=Vars.m.getchesscolor(i,j);
				if(color==Model.Black){
					g.setColor(Color.orange);
					g.fillOval( x+unit*j-unit/2, y+unit*i-unit/2, unit, unit);
					g.setColor(Color.black);
					g.fillOval( x+unit*j-unit/2+2, y+unit*i-unit/2+2, unit-4, unit-4);
				}
				if(color==Model.White){
					g.setColor(Color.orange);
					g.fillOval( x+unit*j-unit/2, y+unit*i-unit/2, unit, unit);
					g.setColor(Color.white);
					g.fillOval( x+unit*j-unit/2+2, y+unit*i-unit/2+2, unit-4, unit-4);
				}
				this.repaint();
			}
		}
	}
	private void drawlines(Graphics g) {
		g.setColor(Color.black);
		for(int i=0;i<Model.Width;i++){
			if(i==0||i==Model.Width-1){
				g.setColor(Color.red);
				g.drawLine(x, y+unit*i, x+(Model.Width-1)*unit, y+unit*i);
				g.drawLine(x-2, y+unit*i-2, x+(Model.Width-1)*unit+2, y+unit*i-2);
				g.drawLine(x, y+unit*i+2, x+(Model.Width-1)*unit, y+unit*i+2);
				
				g.drawLine(x+unit*i-2, y-2, x+unit*i-2, y+(Model.Width-1)*unit+2);
				g.drawLine(x+unit*i+2, y, x+unit*i+2, y+(Model.Width-1)*unit);
				g.drawLine(x+unit*i, y, x+unit*i, y+(Model.Width-1)*unit);
				g.setColor(Color.black);
			}
			else{
			g.drawLine(x, y+unit*i, x+(Model.Width-1)*unit, y+unit*i);
			g.drawLine(x, y+unit*i+2, x+(Model.Width-1)*unit, y+unit*i+2);
			g.drawLine(x+unit*i+2, y, x+unit*i+2, y+(Model.Width-1)*unit);
			g.drawLine(x+unit*i, y, x+unit*i, y+(Model.Width-1)*unit);
			}
		}
	}
}
