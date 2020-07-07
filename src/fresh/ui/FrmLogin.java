package fresh.ui;

import javax.swing.*;

import fresh.control.AdminManager;
import fresh.control.UserManager;
import fresh.model.BeanAdmin;
import fresh.model.BeanUser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmLogin extends JDialog implements ActionListener{
	Box vBox = Box.createVerticalBox();
	private ButtonGroup btnGroup = new ButtonGroup();
	private JPanel panelname = new JPanel();
	private JPanel panelpwd = new JPanel();
	private JPanel panellogin = new JPanel();
	private JPanel panelchoice = new JPanel();
	private JLabel Labelname = new JLabel("用户名");
	private JLabel Labelpwd = new JLabel("密     码");
	private JButton Butlogin = new JButton("登录");
	private JButton Butreg = new JButton("用户注册");
	private JButton Butadminreg = new JButton("管理员注册");
	private JButton Butcancel = new JButton("退出");
	private JPasswordField passwd = new JPasswordField(15);
	private JTextField name = new JTextField(15);
	private JRadioButton choice1 = new JRadioButton("用户");
	private JRadioButton choice2 = new JRadioButton("管理员");
	
	public FrmLogin(Frame f,String s,boolean b) {
		super(f,s,b);
		panelname.add(Labelname);
		panelname.add(name);
		vBox.add(panelname);
		
		panelpwd.add(Labelpwd);
		panelpwd.add(passwd);
		vBox.add(panelpwd);
		
		btnGroup.add(choice1);
		btnGroup.add(choice2);
		panelchoice.add(choice1);
		panelchoice.add(choice2);
		vBox.add(panelchoice);
		
		panellogin.add(Butreg);
		panellogin.add(Butadminreg);
		panellogin.add(Butlogin);
		panellogin.add(Butcancel);
		vBox.add(panellogin);

		this.setSize(400,180);
		this.setContentPane(vBox);
		this.setLocationRelativeTo(null);
		this.validate();
		
		this.Butreg.addActionListener(this);
		this.Butadminreg.addActionListener(this);
		this.Butlogin.addActionListener(this);
		this.Butcancel.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.Butlogin) {
			String name = this.name.getText();
			String passwd = new String(this.passwd.getPassword());
			try {
				if(choice1.isSelected()) {
					UserManager user = new UserManager();
					BeanUser.currentloginUser = user.login(name, passwd);
				}else {
					AdminManager admin = new AdminManager();
					BeanAdmin.currentloginAamin = admin.login(name, passwd);
				}
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
		}else if(e.getSource()==this.Butcancel) {
			System.exit(0);
		}else if(e.getSource()==this.Butreg) {
			try {
				FrmUserRegister fur = new FrmUserRegister(this,"用户注册",true);
				this.setVisible(true);
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}else if(e.getSource()==this.Butadminreg) {
			try {
				FrmRegister fr = new FrmRegister(this,"管理员注册",true);
				this.setVisible(true);
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
