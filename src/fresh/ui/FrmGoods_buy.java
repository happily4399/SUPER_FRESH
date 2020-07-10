package fresh.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fresh.control.Good_buyManager;
import fresh.model.BeanAdmin;

public class FrmGoods_buy extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JLabel labelcount = new JLabel("采购数量：");
	private JTextField edtcount = new JTextField(20);
	
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private int Goods_num;
	FrmGoods_buy(Frame f,String s,boolean b,int Goods_num){
		super(f,s,b);
		this.Goods_num=Goods_num;
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		
		workPane.add(labelcount);
		workPane.add(edtcount);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 300;
		int height = 100;
		this.setBounds((d.width - width)/2, (d.height-height)/2, width, height);
		this.setSize(300,100);
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
		}
		
		else if(e.getSource()==this.btnOk) {
			int Goods_count = Integer.parseInt(edtcount.getText());
			try {
				Good_buyManager gbm = new Good_buyManager();
				gbm.add(Goods_num, BeanAdmin.currentloginAamin.getAdmin_num(), Goods_count);
				this.setVisible(false);
			}catch (Exception e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}