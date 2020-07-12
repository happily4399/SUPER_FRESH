package fresh.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fresh.control.DiscountManager;
import fresh.control.Fresh_categoryManager;
import fresh.control.GoodsManager;
import fresh.model.BeanFresh_category;

public class FrmDis_add extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelname = new JLabel("满折名称：");
	private JLabel labelcount = new JLabel("满折数量：");
	private JLabel labeldiscount = new JLabel("满折折扣：");
	private JLabel labelstart_date = new JLabel("满折时间：");
	private JLabel labelend_date = new JLabel("过期时间：");
	
	private JTextField edtname = new JTextField(20);
	private JTextField edtcount = new JTextField(20);
	private JTextField edtdiscount = new JTextField(20);
	private JTextField edtstart_date = new JTextField(20);
	private JTextField edtend_date = new JTextField(20);
	
	public FrmDis_add(JDialog f, String s, boolean b) throws Exception {
		super(f,s,b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		edtcount = new JTextField(String.valueOf(0),20);
		edtdiscount = new JTextField(String.valueOf(0),20);
		workPane.add(labelname);
		workPane.add(edtname);
		workPane.add(labelcount);
		workPane.add(edtcount);
		workPane.add(labeldiscount);
		workPane.add(edtdiscount);
		workPane.add(labelstart_date);
		workPane.add(edtstart_date);
		workPane.add(labelend_date);
		workPane.add(edtend_date);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 300;
		int height = 250;
		this.setBounds((d.width - width)/2, (d.height-height)/2, width, height);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 250);
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
			int count = Integer.parseInt(this.edtcount.getText());
			float discount = Float.parseFloat(this.edtdiscount.getText());
			String start_date = this.edtstart_date.getText();
			String end_date = this.edtend_date.getText();
			try {
				DiscountManager dm = new DiscountManager();
				dm.Add(name, count, discount, start_date, end_date);
				this.setVisible(false);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
