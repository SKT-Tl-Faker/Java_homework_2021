package painter;

import java.awt.*;

import javax.swing.*;

public class MyRender extends JLabel implements ListCellRenderer<Color> {

	public MyRender() {//构造函数
        setOpaque(true); //设置不透明
        setHorizontalAlignment(CENTER);//设置本标签水平居中对齐
        setVerticalAlignment(CENTER);//设置本标签垂直居中对齐
        this.setPreferredSize(new Dimension(0,50));
        this.setEnabled(true);
	}
	@Override
	public Component getListCellRendererComponent(JList<? extends Color> list,
			Color value, int index, boolean selected, boolean focused) {
		list.setSelectionBackground(value);
		if(selected){
			this.setText(list.getSelectionBackground().toString());
			this.setBackground(list.getSelectionBackground().darker());
		}
		else{
			setBackground(value);
			this.setText(value.toString());
		}
		setForeground(Color.white);
		return this;
	}

}
