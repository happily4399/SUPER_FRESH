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
	
	private Button ButOK = new Button("ȷ��");
	private Button Butcancel = new Button("�˳�");
	
	private JLabel laberPnum = new JLabel("��  ��  ��:");
	private JLabel laberpwd = new JLabel("��         ��:");
	private JLabel labername = new JLabel("�û�����:");
	private JLabel labersex = new JLabel("�û��Ա�:");
	private JLabel laberemail = new JLabel("�û�����:");
	private JLabel labercity = new JLabel("���ڳ���:");
	
	private JTextField edtUserPnum = new JTextField(20);
	private JTextField edtUsername = new JTextField(20);
	private JTextField edtUsersex = new JTextField(20);
	private JTextField edtUseremail = new JTextField(20);
	private JTextField edtUsercity = new JTextField(20);
	
	private JPasswordField edtPwd = new JPasswordField(20);
	
	public FrmUserRegister(Dialog f,String s,boolean b) {
		super(f,s,b);
		//�ֻ���
		P_Pnum.add(laberPnum);
		P_Pnum.add(edtUserPnum);
		vBox.add(P_Pnum);
		//�û�����
		P_pwd.add(laberpwd);
		P_pwd.add(edtPwd);
		vBox.add(P_pwd);
		//�û���
		P_name.add(labername);
		P_name.add(edtUsername);
		vBox.add(P_name);
		//�û��Ա�
		P_sex.add(labersex);
		P_sex.add(edtUsersex);
		vBox.add(P_sex);
		//�û�����
		P_email.add(laberemail);
		P_email.add(edtUseremail);
		vBox.add(P_email);
		//�û�����
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
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
		}
	}
	
}
