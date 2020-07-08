package fresh.ui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fresh.model.BeanAdmin;
import fresh.model.BeanUser;

public class FrmUser_hello extends JDialog implements ActionListener{
    Box vBox = Box.createVerticalBox();
	
	private JPanel panelHello = new JPanel();
	private JPanel panelwork = new JPanel();
	
	private JButton ButOk = new JButton("�õģ�лл");
	
	public  FrmUser_hello(Frame f,String s,boolean b){
		super(f,s,b);
		JLabel Hello = new JLabel("��ã�"+BeanUser.currentloginUser.getUser_name()+"������Ҳ�ǳ���������һ��");
		panelHello.add(Hello);
		panelwork.add(ButOk);
		vBox.add(panelHello);
		vBox.add(panelwork);
		
		this.setSize(280,140);
		this.setContentPane(vBox);
		this.setLocationRelativeTo(null);
		this.validate();
		
		this.ButOk.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.ButOk) {
			this.setVisible(false);
		}
	}
}