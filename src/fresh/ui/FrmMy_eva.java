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

import fresh.control.GoodsManager;
import fresh.control.Goods_evaluationManager;
import fresh.model.BeanUser;

public class FrmMy_eva extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelcon = new JLabel("评价内容：");
	private JLabel labelstar = new JLabel("评价星级：");
	private JLabel labelphoto = new JLabel("相关图片：");
	
	private JTextField edtcon = new JTextField(20);
	private JTextField edtstar = new JTextField(20);
	private JTextField edtphoto = new JTextField(20);
	
	private int Goods_num=0;
	private int Order_num=0;
	
	public FrmMy_eva(JDialog f,String s,boolean b,int Order_num,int Goods_num) {
		super(f,s,b);
		this.Order_num=Order_num;
		this.Goods_num=Goods_num;
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelcon);
		workPane.add(edtcon);
		workPane.add(labelstar);
		workPane.add(edtstar);
		workPane.add(labelphoto);
		workPane.add(edtphoto);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 300;
		int height = 200;
		this.setBounds((d.width - width)/2, (d.height-height)/2, width, height);
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
			String con = edtcon.getText();
			int star = Integer.parseInt(edtstar.getText());
			String photo = edtphoto.getText();
			try {
				Goods_evaluationManager gem = new Goods_evaluationManager();
				gem.Add(Order_num, Goods_num, BeanUser.currentloginUser.getUser_num(), con, star , photo);
				this.setVisible(false);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
