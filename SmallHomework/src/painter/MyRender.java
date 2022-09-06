package painter;

import java.awt.*;

import javax.swing.*;

public class MyRender extends JLabel implements ListCellRenderer<Color> {

	public MyRender() {//���캯��
        setOpaque(true); //���ò�͸��
        setHorizontalAlignment(CENTER);//���ñ���ǩˮƽ���ж���
        setVerticalAlignment(CENTER);//���ñ���ǩ��ֱ���ж���
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
