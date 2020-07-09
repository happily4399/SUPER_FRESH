package fresh.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fresh.control.Fresh_categoryManager;
import fresh.control.GoodsManager;
import fresh.model.BeanFresh_category;

public class FrmGoods_add extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("ȷ��");
	private Button btnCancel = new Button("ȡ��");
	
	private JLabel labelname = new JLabel("��Ʒ���ƣ�");
	private JLabel labelprice = new JLabel("��Ʒ�۸�");
	private JLabel labelcount = new JLabel("��Ʒ������");
	private JLabel labelspe = new JLabel("��Ʒ���");
	private JLabel labeldes = new JLabel("��Ʒ������");
	
	private JTextField edtname = new JTextField(20);
	private JTextField edtprice = new JTextField(20);
	private JTextField edtcount = new JTextField(20);
	private JTextField edtspe = new JTextField(20);
	private JTextField edtdes = new JTextField(20);
	private int Fresh_num=0;
	
	public FrmGoods_add(Frame f, String s, boolean b,int i) throws Exception {
		super(f,s,b);
		Fresh_categoryManager fcm = new Fresh_categoryManager();
		List<BeanFresh_category> Fresh= fcm.loadall();
		Fresh_num=Fresh.get(i).getCategory_number();
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelname);
		workPane.add(edtname);
		workPane.add(labelprice);
		workPane.add(edtprice);
		workPane.add(labelcount);
		workPane.add(edtcount);
		workPane.add(labelspe);
		workPane.add(edtspe);
		workPane.add(labeldes);
		workPane.add(edtdes);
		
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
			int count = Integer.parseInt(edtcount.getText());
			float price = Float.parseFloat(edtprice.getText());
			String spe = edtspe.getText();
			String des = edtdes.getText();
			try {
				GoodsManager gm = new GoodsManager();
				gm.Add(Fresh_num, name, price, count, spe, des);
				this.setVisible(false);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}