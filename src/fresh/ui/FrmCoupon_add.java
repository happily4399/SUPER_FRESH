package fresh.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fresh.control.AdminManager;
import fresh.control.CouponManager;
import fresh.control.UserManager;

public class FrmCoupon_add extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelCoupon_con = new JLabel("优惠券名：");
	private JLabel labelapp_amount = new JLabel("适用金额：");
	private JLabel labelDed_amount = new JLabel("减免金额：");
	private JLabel labelstart_date = new JLabel("适用日期：");
	private JLabel labelend_date = new JLabel("过期日期：");
	
	private JTextField edtCoupon_con = new JTextField(20);
	private JTextField edtapp_amount = new JTextField(20);
	private JTextField edtded_amount = new JTextField(20);
	private JTextField edtstart_date = new JTextField(20);
	private JTextField edtend_date = new JTextField(20);
	
	public FrmCoupon_add(JDialog f, String s, boolean b) {
		super(f,s,b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelCoupon_con);
		workPane.add(edtCoupon_con);
		workPane.add(labelapp_amount);
		workPane.add(edtapp_amount);
		workPane.add(labelDed_amount);
		workPane.add(edtded_amount);
		workPane.add(labelstart_date);
		workPane.add(edtstart_date);
		workPane.add(labelend_date);
		workPane.add(edtend_date);
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
			String Coupon_con = edtCoupon_con.getText();
			float app_amount = Float.parseFloat(edtapp_amount.getText());
			float Ded_amount = Float.parseFloat(edtded_amount.getText());
			String Coupon_start_date = edtstart_date.getText();
			String Coupon_end_date = edtend_date.getText();
			try {
				CouponManager cm = new CouponManager();
				cm.add(Coupon_con, app_amount, Ded_amount, Coupon_start_date, Coupon_end_date);
				this.setVisible(false);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
