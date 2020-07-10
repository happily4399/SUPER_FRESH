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

import fresh.control.DiscountManager;
import fresh.model.BeanDiscount_infor;

public class FrmDis_Change extends JDialog implements ActionListener {
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
	private int Dis_num = 0;
	
	public FrmDis_Change(JDialog f, String s, boolean b, int Dis_num) throws Exception {
		super(f,s,b);
		this.Dis_num = Dis_num;
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		
		DiscountManager dm = new DiscountManager();
		BeanDiscount_infor bdi = dm.loadbyNum(Dis_num);
		
		this.edtname = new JTextField(String.valueOf(bdi.getDis_content()),20);
		this.edtcount = new JTextField(String.valueOf(bdi.getDis_count()),20);
		this.edtdiscount = new JTextField(String.valueOf(bdi.getDicount()),20);
		this.edtstart_date = new JTextField(String.valueOf(bdi.getDis_start_date()),20);
		this.edtend_date = new JTextField(String.valueOf(bdi.getDis_end_date()),20);
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
				dm.ChangeDis_con(Dis_num, name);
				dm.ChangeDis_count(Dis_num, count);
				dm.ChangeDiscount(Dis_num, discount);
				dm.ChangeDis_start(Dis_num, start_date);
				dm.ChangeDis_end(Dis_num, end_date);
				this.setVisible(false);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
