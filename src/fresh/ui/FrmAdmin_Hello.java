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

public class FrmAdmin_Hello extends JDialog implements ActionListener{
	Box vBox = Box.createVerticalBox();
	
	private JPanel panelHello = new JPanel();
	private JPanel panelwork = new JPanel();
	
	private JButton ButOk = new JButton("好的，谢谢");
	
	public FrmAdmin_Hello(Frame f,String s,boolean b){
		JLabel Hello = new JLabel("你好，管理员"+BeanAdmin.currentloginAamin.getAdmin_name()+"今天又是活力满满的一天");
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
