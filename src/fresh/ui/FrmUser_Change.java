package fresh.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fresh.control.UserManager;
import fresh.model.BeanUser;

public class FrmUser_Change extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelname = new JLabel("姓名：");
	private JLabel labelsex = new JLabel("性别：");
	private JLabel labelemail = new JLabel("邮箱：");
	private JLabel labelcity = new JLabel("城市：");
	
	private JTextField name = new JTextField(20);
	private JTextField sex = new JTextField(20);
	private JTextField email = new JTextField(20);
	private JTextField city = new JTextField(20);
	private String User_Pnum="";

	
	public FrmUser_Change(JDialog f, String s, boolean b,String Pnum) throws Exception {
		super(f,s,b);
		this.User_Pnum=Pnum;
		UserManager um = new UserManager();
		name = new JTextField(um.loadbyPnum(Pnum).getUser_name(),20);
		sex = new JTextField(um.loadbyPnum(Pnum).getUser_sex(),20);
		email = new JTextField(um.loadbyPnum(Pnum).getUser_email(),20);
		city = new JTextField(um.loadbyPnum(Pnum).getUser_city(),20);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelname);
		workPane.add(name);
		workPane.add(labelsex);
		workPane.add(sex);
		workPane.add(labelemail);
		workPane.add(email);
		workPane.add(labelcity);
		workPane.add(city);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 200);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			try {
				UserManager um = new UserManager();
				um.ChangName(this.User_Pnum, new String(name.getText()));
				um.Changsex(this.User_Pnum, new String(sex.getText()));
				um.Changemail(this.User_Pnum, new String(email.getText()));
				um.ChangCity(this.User_Pnum, new String(city.getText()));
				this.setVisible(false);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
