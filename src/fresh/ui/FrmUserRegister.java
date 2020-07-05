package fresh.ui;

import javax.swing.*;

import fresh.control.AdminManager;
import fresh.control.UserManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmUserRegister extends JDialog implements ActionListener {
	Box vBox = Box.createVerticalBox();
	private JPanel P_Pnum = new JPanel();
	private JPanel P_pwd = new JPanel();
	private JPanel P_name = new JPanel();
	private JPanel P_email = new JPanel();
	private JPanel P_city = new JPanel();
	private JPanel P_sex = new JPanel();
	
	private JPanel workPane = new JPanel();
	
	private Button ButOK = new Button("确认");
	private Button Butcancel = new Button("退出");
	
	private JLabel laberPnum = new JLabel("手  机  号:");
	private JLabel laberpwd = new JLabel("密         码:");
	private JLabel labername = new JLabel("用户姓名:");
	private JLabel labersex = new JLabel("用户性别:");
	private JLabel laberemail = new JLabel("用户邮箱:");
	private JLabel labercity = new JLabel("所在城市:");
	
	private JTextField edtUserPnum = new JTextField(20);
	private JTextField edtUsername = new JTextField(20);
	private JTextField edtUsersex = new JTextField(20);
	private JTextField edtUseremail = new JTextField(20);
	private JTextField edtUsercity = new JTextField(20);
	
	private JPasswordField edtPwd = new JPasswordField(20);
	
	public FrmUserRegister(Dialog f,String s,boolean b) {
		super(f,s,b);
		//手机号
		P_Pnum.add(laberPnum);
		P_Pnum.add(edtUserPnum);
		vBox.add(P_Pnum);
		//用户密码
		P_pwd.add(laberpwd);
		P_pwd.add(edtPwd);
		vBox.add(P_pwd);
		//用户名
		P_name.add(labername);
		P_name.add(edtUsername);
		vBox.add(P_name);
		//用户性别
		P_sex.add(labersex);
		P_sex.add(edtUsersex);
		vBox.add(P_sex);
		//用户邮箱
		P_email.add(laberemail);
		P_email.add(edtUseremail);
		vBox.add(P_email);
		//用户城市
		P_city.add(labercity);
		P_city.add(edtUsercity);
		vBox.add(P_city);
		
		workPane.add(ButOK);
		workPane.add(Butcancel);
		vBox.add(workPane);
		this.setSize(400,300);
		this.setContentPane(vBox);
		this.validate();
		this.Butcancel.addActionListener(this);
		this.ButOK.addActionListener(this);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.Butcancel) {
			this.setVisible(false);
		}else if(e.getSource()==this.ButOK){
			String userPnum = this.edtUserPnum.getText();
			String username = this.edtUsername.getText();
			String usercity = this.edtUsercity.getText();
			String useremail = this.edtUseremail.getText();
			String usersex = this.edtUsersex.getText();
			String passwd = new String(this.edtPwd.getPassword());
			try {
				UserManager user = new UserManager();
				user.reg(username, usersex, passwd, userPnum, useremail, usercity);
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
		}
	}
	
}
