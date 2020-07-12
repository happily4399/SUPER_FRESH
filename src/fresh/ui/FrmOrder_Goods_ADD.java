package fresh.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
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

public class FrmOrder_Goods_ADD extends JDialog implements ActionListener {
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelOrder = new JLabel("订单编号：");
	private JLabel labelDis = new JLabel("满折编号：");
	private JLabel labelcount = new JLabel("商品数量：");
	
	private JTextField edtOrder = new JTextField(20);
	private JTextField edtDis = new JTextField(20);
	private JTextField edtcount = new JTextField(20);
	
	private int Goods_num = 0;
	
	FrmOrder_Goods_ADD(Frame f,String s,boolean b,int Goods_num){
		super(f,s,b);
		this.Goods_num=Goods_num;
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		edtDis=new JTextField(String.valueOf(0),20);
		edtcount=new JTextField(String.valueOf(0),20);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelOrder);
		workPane.add(edtOrder);
		workPane.add(labelDis);
		workPane.add(edtDis);
		workPane.add(labelcount);
		workPane.add(edtcount);
		
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
			Order_detailManager odm = new Order_detailManager();
			int Order = Integer.parseInt(this.edtOrder.getText());
			int dis = Integer.parseInt(this.edtDis.getText());
			int count = Integer.parseInt(this.edtcount.getText());
			try {
				Order_detailManager od = new Order_detailManager();
				odm.Add(Order, dis, Goods_num, count);
				BeanOrder_detail bod = od.loadonce(Order, Goods_num);
				Goods_orderManager gom = new Goods_orderManager();
				if(!"购物车中".equals(gom.LoadbyOrder_num(Order).getOrder_state())) throw new Exception("订单不在购物车中");
				gom.reload_price(Order, gom.LoadbyOrder_num(Order).getCoupon_num());
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
		}
	}
	
}
