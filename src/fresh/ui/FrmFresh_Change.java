package fresh.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fresh.control.Fresh_categoryManager;

public class FrmFresh_Change extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelname = new JLabel("类别名称：");
	private JLabel labeldes = new JLabel("类别描述：");
	
	private JTextField edtname = new JTextField(20);
	private JTextField edtdes = new JTextField(20);
	private int Fresh_num;
	
	public FrmFresh_Change(JDialog f, String s, boolean b,int Fresh_num) {
		super(f,s,b);
		this.Fresh_num=Fresh_num;
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelname);
		workPane.add(edtname);
		workPane.add(labeldes);
		workPane.add(edtdes);
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 150);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			String name = edtname.getText();
			String des = edtdes.getText();
			try {
				Fresh_categoryManager fcm = new Fresh_categoryManager();
				fcm.changename(Fresh_num, name);
				fcm.changedes(Fresh_num, des);
				this.setVisible(false);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}