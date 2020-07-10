package fresh.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fresh.control.GoodsManager;
import fresh.control.PromotionManager;

import fresh.model.BeanPromotion;

public class FrmPro_Change extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelPro_name = new JLabel("促销名称：");
	private JLabel labelGood_num= new JLabel("促销商品：");
	private JLabel labelPro_price = new JLabel("促销价格：");
	private JLabel labelPro_count = new JLabel("促销数量：");
	private JLabel labelstart_date = new JLabel("起始日期：");
	private JLabel labelend_date = new JLabel("过期日期：");
	
	private JTextField edtPro_name = new JTextField(20);
	private JTextField edtGood_num = new JTextField(20);
	private JTextField edtPro_price = new JTextField(20);
	private JTextField edtPro_count = new JTextField(20);
	private JTextField edtstart_date = new JTextField(20);
	private JTextField edtend_date = new JTextField(20);
	private int Pro_num=0;

	
	public FrmPro_Change(JDialog f, String s, boolean b,int pn) throws Exception {
		super(f,s,b);
		this.Pro_num=pn;
		PromotionManager pm = new PromotionManager();
		BeanPromotion bp = pm.LoadByPro_Num(pn);
		
		edtPro_name = new JTextField(bp.getPro_name(),20);
		edtGood_num = new JTextField(String.valueOf(bp.getGoods_num()),20);
		edtPro_price = new JTextField(String.valueOf(bp.getPro_price()),20);
		edtPro_count = new JTextField(String.valueOf(bp.getPro_count()),20);
		edtstart_date = new JTextField(String.valueOf(bp.getPro_start_date()),20);
		edtend_date = new JTextField(String.valueOf(bp.getPro_end_date()),20);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelPro_name);
		workPane.add(edtPro_name);
		workPane.add(labelGood_num);
		workPane.add(edtGood_num);
		workPane.add(labelPro_price);
		workPane.add(edtPro_price);
		workPane.add(labelPro_count);
		workPane.add(edtPro_count);
		workPane.add(labelstart_date);
		workPane.add(edtstart_date);
		workPane.add(labelend_date);
		workPane.add(edtend_date);
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 230);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			String Pro_name = this.edtPro_name.getText();
			int Goods_num = Integer.parseInt(this.edtGood_num.getText());
			float Pro_price = Float.parseFloat(this.edtPro_price.getText());
			int Pro_count = Integer.parseInt(this.edtPro_count.getText());
			String Pro_start_date = edtstart_date.getText();
			String Pro_end_date = edtend_date.getText();
			try {
				SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date start_date = sdf2.parse(Pro_start_date);
				java.util.Date end_date = sdf2.parse(Pro_end_date);
				if(end_date.getTime() < start_date.getTime()) throw new Exception("结束日期不可早于开始日期");
				GoodsManager gm = new GoodsManager();
				if(Pro_price > gm.loadbyGoodsnum(Goods_num).getGoods_price()) throw new Exception("促销价格不可高于商品价格");
				if(Pro_count > gm.LoadGoods_count(Goods_num)) throw new Exception("促销数量不可高于库存数量");
				PromotionManager pm = new PromotionManager();
				pm.ChangePro_name(Pro_num, Pro_name);
				pm.ChangePro_count(Pro_num, Pro_count);
				pm.ChangeGoods_num(Pro_num, Goods_num);
				pm.ChangePro_price(Pro_num, Pro_price);
				pm.ChangePro_start(Pro_num, Pro_start_date);
				pm.ChangePro_end(Pro_num, Pro_end_date);
				this.setVisible(false);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
