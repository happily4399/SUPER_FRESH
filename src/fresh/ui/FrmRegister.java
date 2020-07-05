package fresh.ui;

import javax.swing.*;

import fresh.control.AdminManager;
import fresh.control.UserManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmRegister extends JDialog implements ActionListener {
	Box vBox = Box.createVerticalBox();
	private JPanel num = new JPanel();
	private JPanel Oncepwd = new JPanel();
	private JPanel Twicepwd = new JPanel();
	private JPanel adname = new JPanel();
	private JPanel work = new JPanel();
	
	private Button butOK = new Button("确认");
	private Button butCanel = new Button("取消");
	
	private JLabel name = new JLabel("员工编号");
	private JLabel adminname = new JLabel("员工姓名");
	private JLabel pwd1 = new JLabel("首次密码");
	private JLabel pwd2 = new JLabel("重复密码");
	
	private JTextField edtUserId = new JTextField(20);
	private JTextField edtUsername = new JTextField(20);
	private JPasswordField edtPasswd = new JPasswordField(20);
	private JPasswordField edtPasswd2 = new JPasswordField(20);
	
	public FrmRegister(Dialog f,String s,boolean b) {
		super(f,s,b);
		num.add(name);
		num.add(edtUserId);
		vBox.add(num);
		
		adname.add(adminname);
		adname.add(edtUsername);
		vBox.add(adname);
		
		Oncepwd.add(pwd1);
		Oncepwd.add(edtPasswd);
		vBox.add(Oncepwd);
		
		Twicepwd.add(pwd2);
		Twicepwd.add(edtPasswd2);
		vBox.add(Twicepwd);
		
		work.add(butOK);
		work.add(butCanel);
		vBox.add(work);
		
		this.setSize(400,200);
		this.setContentPane(vBox);
		this.validate();
		this.butCanel.addActionListener(this);
		this.butOK.addActionListener(this);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.butCanel) {
			this.setVisible(false);
		}else if(e.getSource()==this.butOK){
			String usernum = this.edtUserId.getText();
			String username = this.edtUsername.getText();
			String passwd = new String(this.edtPasswd.getPassword());
			String passwd2 = new String(this.edtPasswd2.getPassword());
			try {
				AdminManager admin = new AdminManager();
				admin.reg(usernum, username, passwd, passwd2);
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
		}
	}
}
