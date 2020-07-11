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

import fresh.control.Goods_orderManager;
import fresh.control.Order_detailManager;
import fresh.model.BeanOrder_detail;

public class FrmMy_Change extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelDis = new JLabel("满折编号：");
	private JLabel labelGood = new JLabel("商品编号：");
	private JLabel labelCount = new JLabel("商品数量：");
	
	private JTextField edtDis = new JTextField(20);
	private JTextField edtGood = new JTextField(20);
	private JTextField edtCount = new JTextField(20);
	
	private int Order_num=0;
	private int Goods_num=0;
	
	FrmMy_Change(JDialog f,String s,boolean b,int Order_num,int Goods_num) throws Exception{
		super(f,s,b);
		this.Order_num=Order_num;
		this.Goods_num=Goods_num;
		Order_detailManager odm = new Order_detailManager();
		BeanOrder_detail bod = new BeanOrder_detail();
		bod = odm.loadonce(Order_num, Goods_num);
		
		edtDis = new JTextField(String.valueOf(bod.getDis_num()),20);
		edtGood = new JTextField(String.valueOf(bod.getGoods_num()),20);
		edtCount = new JTextField(String.valueOf(bod.getOrder_count()),20);
		
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelDis);
		workPane.add(edtDis);
		workPane.add(labelGood);
		workPane.add(edtGood);
		workPane.add(labelCount);
		workPane.add(edtCount);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 300;
		int height = 230;
		this.setBounds((d.width - width)/2, (d.height-height)/2, width, height);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 230);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
		}
		
		else if(e.getSource()==this.btnOk) {
			try {
				Order_detailManager odm = new Order_detailManager();
				BeanOrder_detail bod = new BeanOrder_detail();
				int dis = Integer.parseInt(this.edtDis.getText());
				int Good = Integer.parseInt(this.edtGood.getText());
				int count = Integer.parseInt(this.edtCount.getText());
				
				odm.ChangeGoods(Order_num, Goods_num, Good);
				Good = Integer.parseInt(this.edtGood.getText());
				odm.ChangeDis(Order_num, Good, dis);
				odm.ChangeCount(Order_num, Good, count);
				
				bod = odm.loadonce(Order_num, Good);
				odm.reloadPrice_count(bod);
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
