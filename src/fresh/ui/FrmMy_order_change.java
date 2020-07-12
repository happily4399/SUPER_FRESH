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
import fresh.control.Goods_orderManager;
import fresh.control.ShippingManager;
import fresh.model.BeanGoods_order;
import fresh.model.BeanUser;

public class FrmMy_order_change extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelship = new JLabel("地址编号：");
	private JLabel labelCoupon = new JLabel("优惠券号：");
	private JLabel labelend_date = new JLabel("送达日期：");
	
	private JTextField edtship = new JTextField(20);
	private JTextField edtCoupon = new JTextField(20);
	private JTextField edtend_date = new JTextField(20);
	
	private int Order_num=0;
	
	FrmMy_order_change(JDialog f,String s,boolean b,int Order_num) throws Exception{
		super(f,s,b);
		this.Order_num=Order_num;
		
		Goods_orderManager gom = new Goods_orderManager();
		BeanGoods_order bgo = gom.LoadbyOrder_num(Order_num);
		
		edtship = new JTextField(String.valueOf(bgo.getShip_num()),20);
		edtCoupon = new JTextField(String.valueOf(bgo.getCoupon_num()),20);
		edtend_date = new JTextField(String.valueOf(bgo.getService_time()),20);
		
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelship);
		workPane.add(edtship);
		workPane.add(labelCoupon);
		workPane.add(edtCoupon);
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
		Goods_orderManager gom = new Goods_orderManager();
		// TODO 自动生成的方法存根
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
		}
		
		else if(e.getSource()==this.btnOk) {
			java.util.Date today = new Date();
			int ship = Integer.parseInt(this.edtship.getText());
			int Coupon = Integer.parseInt(this.edtCoupon.getText());
			String end_date = this.edtend_date.getText();
			if(!"".equals(end_date)) {
				try {
					SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					java.util.Date end = sdf2.parse(end_date);
					if(end.getTime() < today.getTime()) throw new Exception("订单无法送到过去，请输入正常的时间");
					gom.ChangeTime(Order_num, end_date);
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			if(ship!=0) {
				try {
					ShippingManager sm = new ShippingManager();
					if(sm.loadbyShip_num(ship)==null) throw new Exception("不存在此订单编号");
					if(sm.loadbyShip_num(ship).getUser_num()!=BeanUser.currentloginUser.getUser_num()) {
						throw new Exception ("你没有这个地址编号");
					}
					gom.ChangeShip(Order_num, ship);
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			
			if(Coupon!=0) {
				try {
					CouponManager cm = new CouponManager();
					cm.loadByCoupon_num(Coupon);
					gom.ChangeCoupon(Order_num, Coupon);
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			this.setVisible(false);
		}
	}
	
}
