package fresh.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fresh.control.Fresh_categoryManager;
import fresh.control.GoodsManager;

public class FrmGoods_Change extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelname = new JLabel("商品名称：");
	private JLabel labelcount = new JLabel("商品数量：");
	private JLabel labelspe = new JLabel("商品规格：");
	private JLabel labeldes = new JLabel("商品描述：");
	private JLabel labelprice = new JLabel("商品价格：");
	private JLabel labelvipprice = new JLabel("v i p价 格：");
	
	private JTextField edtname = new JTextField(20);
	private JTextField edtcount = new JTextField(20);
	private JTextField edtspe = new JTextField(20);
	private JTextField edtdes = new JTextField(20);
	private JTextField edtprice = new JTextField(20);
	private JTextField edtvipprice = new JTextField(20);
	
	private int Goods_num;
	
	public FrmGoods_Change(Frame f, String s, boolean b,int Goods_num) throws Exception {
		super(f,s,b);
		this.Goods_num=Goods_num;
		GoodsManager gm = new GoodsManager();
		edtname = new JTextField(gm.loadbyGoodsnum(Goods_num).getGoods_name(),20);
		edtcount = new JTextField(String.valueOf(gm.loadbyGoodsnum(Goods_num).getGoods_count()),20);
		edtspe = new JTextField(gm.loadbyGoodsnum(Goods_num).getGoods_spe(),20);
		edtdes = new JTextField(gm.loadbyGoodsnum(Goods_num).getGoods_det(),20);
		edtprice = new JTextField(String.valueOf(gm.loadbyGoodsnum(Goods_num).getGoods_price()),20);
		edtvipprice = new JTextField(String.valueOf(gm.loadbyGoodsnum(Goods_num).getVip_price()),20);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelname);
		workPane.add(edtname);
		workPane.add(labelcount);
		workPane.add(edtcount);
		workPane.add(labelspe);
		workPane.add(edtspe);
		workPane.add(labeldes);
		workPane.add(edtdes);
		workPane.add(labelprice);
		workPane.add(edtprice);
		workPane.add(labelvipprice);
		workPane.add(edtvipprice);
		
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
			String spe = edtspe.getText();
			String des = edtdes.getText();
			float price = Float.parseFloat(edtprice.getText());
			float vipprice = Float.parseFloat(edtvipprice.getText());
			try {
				GoodsManager gm = new GoodsManager();
				gm.ChangeGoods_name(Goods_num, name);
				gm.ChangeGoods_count(Goods_num, count);
				gm.ChangeGoods_spe(Goods_num, spe);
				gm.ChangeGoods_des(Goods_num, des);
				gm.ChangeGoods_price(Goods_num, price);
				gm.ChangeVip_price(Goods_num, vipprice);
				this.setVisible(false);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}