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

import fresh.control.PromotionManager;

public class FrmGoods_pro_add extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelPro_name = new JLabel("促销名称：");
	private JLabel labelPro_price = new JLabel("促销价格：");
	private JLabel labelPro_count = new JLabel("促销数量：");
	private JLabel labelstart_date = new JLabel("起始日期：");
	private JLabel labelend_date = new JLabel("过期日期：");
	
	private JTextField edtPro_name = new JTextField(20);
	private JTextField edtPro_price = new JTextField(20);
	private JTextField edtPro_count = new JTextField(20);
	private JTextField edtstart_date = new JTextField(20);
	private JTextField edtend_date = new JTextField(20);
	private int Good_num=0;

	
	public FrmGoods_pro_add(Frame f, String s, boolean b,int Good_num) throws Exception {
		super(f,s,b);
		this.Good_num=Good_num;
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelPro_name);
		workPane.add(edtPro_name);
		workPane.add(labelPro_price);
		workPane.add(edtPro_price);
		workPane.add(labelPro_count);
		workPane.add(edtPro_count);
		workPane.add(labelstart_date);
		workPane.add(edtstart_date);
		workPane.add(labelend_date);
		workPane.add(edtend_date);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 300;
		int height = 230;
		this.setBounds((d.width - width)/2, (d.height-height)/2, width, height);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 230);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			String Pro_name = this.edtPro_name.getText();
			float Pro_price = Float.parseFloat(this.edtPro_price.getText());
			int Pro_count = Integer.parseInt(this.edtPro_count.getText());
			String Pro_start_date = edtstart_date.getText();
			String Pro_end_date = edtend_date.getText();
			PromotionManager pm = new PromotionManager();
			try {
				pm.Add(Good_num, Pro_name, Pro_price, Pro_count, Pro_start_date, Pro_end_date);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
