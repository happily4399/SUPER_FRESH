package fresh.ui;

import javax.swing.*;

import fresh.model.BeanAdmin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class textui extends JDialog implements ActionListener{
	Box vBox = Box.createVerticalBox();
	private JPanel panelname = new JPanel();
	private JPanel panelpwd = new JPanel();
	private JPanel panellogin = new JPanel();
	private JLabel Labelname = new JLabel("ÓÃ»§Ãû");
	private JLabel Labelpwd = new JLabel("ÃÜ     Âë");
	private JButton Butlogin = new JButton("µÇÂ¼");
	private JButton Butreg = new JButton("×¢²á");
	private JButton Butcancel = new JButton("ÍË³ö");
	private JPasswordField passwd = new JPasswordField(15);
	private JTextField name = new JTextField(15);
	
	public static void main(String []args) {
		JFrame jf=new JFrame(); 
		new textui(jf,"µÇÂ¼",true);
	}
	
	public textui(Frame f,String s,boolean b) {
		super(f,s,b);
		panelname.add(Labelname);
		panelname.add(name);
		vBox.add(panelname);
		
		panelpwd.add(Labelpwd);
		panelpwd.add(passwd);
		vBox.add(panelpwd);
		
		panellogin.add(Butlogin);
		panellogin.add(Butreg);
		panellogin.add(Butcancel);
		vBox.add(panellogin);

		this.setSize(320,140);
		this.setContentPane(vBox);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.validate();
		
		Butlogin.addActionListener(this);
		Butcancel.addActionListener(this);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.Butlogin) {
			String userid = this.name.getText();
			String pwd = new String(this.passwd.getPassword());
			try {
				
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "´íÎó",JOptionPane.ERROR_MESSAGE);
				return;
			} 
		}
	}
}
