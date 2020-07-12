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

import fresh.control.ShippingManager;
import fresh.model.BeanShipping;
import fresh.model.BeanUser;

public class FrmShip_change extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelPro = new JLabel("省的全名：");
	private JLabel labelcity = new JLabel("市的全名：");
	private JLabel labelarea = new JLabel("区的全名：");
	private JLabel labeladdress = new JLabel("详细地址：");
	private JLabel labelcon = new JLabel("联系的人：");
	private JLabel labeltele = new JLabel("联系电话：");
	
	private JTextField edtPro = new JTextField(20);
	private JTextField edtcity = new JTextField(20);
	private JTextField edtarea = new JTextField(20);
	private JTextField edtaddress = new JTextField(20);
	private JTextField edtcon = new JTextField(20);
	private JTextField edttele = new JTextField(20);
	private int Ship_num=0;
	
	public FrmShip_change(JDialog f,String s,boolean b,int Ship_num) throws Exception {
		super(f,s,b);
		ShippingManager sm = new ShippingManager();
		BeanShipping bs = sm.loadbyShip_num(Ship_num);
		this.Ship_num=Ship_num;
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		
		edtPro = new JTextField(bs.getShip_pro(),20);
		edtcity = new JTextField(bs.getShip_city(),20);
		edtarea = new JTextField(bs.getShip_area(),20);
		edtaddress = new JTextField(bs.getShip_address(),20);
		edtcon = new JTextField(bs.getShip_con(),20);
		edttele = new JTextField(bs.getShip_tele(),20);
		
		workPane.add(labelPro);
		workPane.add(edtPro);
		workPane.add(labelcity);
		workPane.add(edtcity);
		workPane.add(labelarea);
		workPane.add(edtarea);
		workPane.add(labeladdress);
		workPane.add(edtaddress);
		workPane.add(labelcon);
		workPane.add(edtcon);
		workPane.add(labeltele);
		workPane.add(edttele);
		
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 300;
		int height = 250;
		this.setBounds((d.width - width)/2, (d.height-height)/2, width, height);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 250);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			String Pro = edtPro.getText();
			String city = edtcity.getText();
			String area = edtarea.getText();
			String address = edtaddress.getText();
			String con = edtcon.getText();
			String tele = edttele.getText();
			try {
				ShippingManager sm = new ShippingManager();
				sm.ChangeShip(Ship_num, Pro, city, area, address);
				sm.ChangeCon(Ship_num, con);
				sm.ChangeTel(Ship_num, tele);
				this.setVisible(false);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
