package fresh.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fresh.control.CouponManager;
import fresh.control.UserManager;
import fresh.model.BeanCoupon;

public class FrmCoupon_Change extends JDialog implements ActionListener {
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
		private int Coupon_num=0;

		
		public FrmCoupon_Change(JDialog f, String s, boolean b,int cn) throws Exception {
			super(f,s,b);
			this.Coupon_num=cn;
			CouponManager cm = new CouponManager();
			BeanCoupon bc = cm.loadByCoupon_num(cn);
			edtCoupon_con = new JTextField(bc.getCoupon_con(),20);
			edtapp_amount = new JTextField(String.valueOf(bc.getApp_amount()),20);
			edtded_amount = new JTextField(String.valueOf(bc.getDed_amount()),20);
			edtstart_date = new JTextField(String.valueOf(bc.getCoupon_start_date()),20);
			edtend_date = new JTextField(String.valueOf(bc.getCoupon_end_date()),20);
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
				SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd");
				String Coupon_con = edtCoupon_con.getText();
				float app_amount = Float.parseFloat(edtapp_amount.getText());
				float Ded_amount = Float.parseFloat(edtded_amount.getText());
				String Coupon_start_date = edtstart_date.getText();
				String Coupon_end_date = edtend_date.getText();
				try {
					java.util.Date start_date = sdf2.parse(Coupon_start_date);
					java.util.Date end_date = sdf2.parse(Coupon_end_date);
					if(end_date.getTime() < start_date.getTime()) throw new Exception("结束日期不可早于开始日期");
					CouponManager cm = new CouponManager();
					cm.ChangeCoupon_con(Coupon_num, Coupon_con);
					cm.ChangeCoupon_app_amount(Coupon_num, app_amount);
					cm.ChangeCoupon_Ded_amount(Coupon_num, Ded_amount);
					cm.ChangeCoupon_start_Date(Coupon_num, Coupon_start_date);
					cm.ChangeCoupon_end_Date(Coupon_num, Coupon_end_date);
					this.setVisible(false);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		}
}
