package fresh.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fresh.control.GoodsManager;
import fresh.control.Goods_recipeManager;
import fresh.control.RecipeManager;

public class FrmGood_recipe_add extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("ȷ��");
	private Button btnCancel = new Button("ȡ��");
	
	private JLabel labelrecipe = new JLabel("���ױ�ţ�");
	private JLabel labelgood = new JLabel("��Ʒ��ţ�");
	private JLabel labeldes = new JLabel("�Ƽ�������");
	
	private JTextField edtrecipe = new JTextField(20);
	private JTextField edtgood = new JTextField(20);
	private JTextField edtdes = new JTextField(20);
	
	public FrmGood_recipe_add(JDialog f,String s,boolean b) {
		super(f,s,b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelrecipe);
		workPane.add(edtrecipe);
		workPane.add(labelgood);
		workPane.add(edtgood);
		workPane.add(labeldes);
		workPane.add(edtdes);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 300;
		int height = 200;
		this.setBounds((d.width - width)/2, (d.height-height)/2, width, height);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 200);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk) {
			int recipe_num = Integer.parseInt(edtrecipe.getText());
			int Good_num = Integer.parseInt(edtgood.getText());
			String des = edtdes.getText();
			try {
				Goods_recipeManager grm = new Goods_recipeManager();
				grm.add(Good_num, recipe_num, des);
			}catch (Exception e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
		}
	}
}
