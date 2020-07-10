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

import fresh.control.Goods_discountManager;
import fresh.control.PromotionManager;

public class FrmGoods_dis_add extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelDis_num = new JLabel("满折编号：");
	private JLabel labelstart_date = new JLabel("起始日期：");
	private JLabel labelend_date = new JLabel("过期日期：");
	
	private JTextField edtDis_num = new JTextField(20);
	private JTextField edtstart_date = new JTextField(20);
	private JTextField edtend_date = new JTextField(20);
	private int Goods_num=0;
	
	public FrmGoods_dis_add(Frame f, String s, boolean b,int Goods_num) throws Exception {
		super(f,s,b);
		this.Goods_num=Goods_num;
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelDis_num);
		workPane.add(edtDis_num);
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
			int Dis_num = Integer.parseInt(this.edtDis_num.getText());
			String Dis_start_date = edtstart_date.getText();
			String Dis_end_date = edtend_date.getText();
			Goods_discountManager gdm = new Goods_discountManager();
			try {
				gdm.add(Goods_num, Dis_num, Dis_start_date, Dis_end_date);
				this.setVisible(false);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
