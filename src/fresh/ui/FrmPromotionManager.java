package fresh.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import fresh.control.CouponManager;
import fresh.control.GoodsManager;
import fresh.control.PromotionManager;
import fresh.model.BeanCoupon;
import fresh.model.BeanPromotion;
import fresh.model.BeanUser;

public class FrmPromotionManager extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private Button btnChange = new Button("修改促销信息");
	private Button btnAdd = new Button("增加促销");
	private Button btnDelete = new Button("删除促销");
	private Button btnDeletebytime = new Button("删除过期促销");
	private Button btnSearch = new Button("查询(all)");
	private Button btnScr_Search = new Button("查询(可用)");
	private Button btnScreen = new Button("筛选当期促销");
	
	private JTextField edtKeyword = new JTextField(10);
	private JLabel Labelname = new JLabel("根据促销名查询：");
	
	private Object tblTitle[]={"促销编号","促销名称","商品名称","促销价格","促销数量","可使用时间","过期时间"};
	private Object tblData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable Table=new JTable(tablmod);
	
	private void reloadPromotionTable(){
		try {
			PromotionManager pm = new PromotionManager();
			List<BeanPromotion> promotion = pm.loadAll();
			tblData =new Object[promotion.size()][7];
			for(int i=0;i<promotion.size();i++){
				tblData[i][0]=promotion.get(i).getPro_num();
				tblData[i][1]=promotion.get(i).getPro_name();
				tblData[i][2]=(new GoodsManager()).loadbyGoodsnum(promotion.get(i).getGoods_num()).getGoods_name();
				tblData[i][3]=promotion.get(i).getPro_price();
				tblData[i][4]=promotion.get(i).getPro_count();
				tblData[i][5]=promotion.get(i).getPro_start_date();
				tblData[i][6]=promotion.get(i).getPro_end_date();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.Table.validate();
			this.Table.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void reloadScreenTable(){
		try {
			PromotionManager pm = new PromotionManager();
			List<BeanPromotion> promotion = pm.loadbytime();
			tblData =new Object[promotion.size()][7];
			for(int i=0;i<promotion.size();i++){
				tblData[i][0]=promotion.get(i).getPro_num();
				tblData[i][1]=promotion.get(i).getPro_name();
				tblData[i][2]=(new GoodsManager()).loadbyGoodsnum(promotion.get(i).getGoods_num()).getGoods_name();
				tblData[i][3]=promotion.get(i).getPro_price();
				tblData[i][4]=promotion.get(i).getPro_count();
				tblData[i][5]=promotion.get(i).getPro_start_date();
				tblData[i][6]=promotion.get(i).getPro_end_date();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.Table.validate();
			this.Table.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void reloadTable(){
		try {
			PromotionManager pm = new PromotionManager();
			List<BeanPromotion> promotion = pm.loadbyname(this.edtKeyword.getText());
			tblData =new Object[promotion.size()][7];
			for(int i=0;i<promotion.size();i++){
				tblData[i][0]=promotion.get(i).getPro_num();
				tblData[i][1]=promotion.get(i).getPro_name();
				tblData[i][2]=(new GoodsManager()).loadbyGoodsnum(promotion.get(i).getGoods_num()).getGoods_name();
				tblData[i][3]=promotion.get(i).getPro_price();
				tblData[i][4]=promotion.get(i).getPro_count();
				tblData[i][5]=promotion.get(i).getPro_start_date();
				tblData[i][6]=promotion.get(i).getPro_end_date();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.Table.validate();
			this.Table.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void reloadTablebytime(){
		try {
			PromotionManager pm = new PromotionManager();
			List<BeanPromotion> promotion = pm.loadbynametime(this.edtKeyword.getText());
			tblData =new Object[promotion.size()][7];
			for(int i=0;i<promotion.size();i++){
				tblData[i][0]=promotion.get(i).getPro_num();
				tblData[i][1]=promotion.get(i).getPro_name();
				tblData[i][2]=(new GoodsManager()).loadbyGoodsnum(promotion.get(i).getGoods_num()).getGoods_name();
				tblData[i][3]=promotion.get(i).getPro_price();
				tblData[i][4]=promotion.get(i).getPro_count();
				tblData[i][5]=promotion.get(i).getPro_start_date();
				tblData[i][6]=promotion.get(i).getPro_end_date();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.Table.validate();
			this.Table.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	FrmPromotionManager(Frame f,String s,boolean b){
		super(f,s,b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.getContentPane().add(new JScrollPane(this.Table), BorderLayout.CENTER);
		if(BeanUser.currentloginUser==null) {
			toolBar.add(btnAdd);
			toolBar.add(btnChange);
			toolBar.add(btnDelete);
			toolBar.add(btnDeletebytime);
			toolBar.add(btnScreen);
			this.btnAdd.addActionListener(this);
			this.btnDelete.addActionListener(this);
			this.btnChange.addActionListener(this);
			this.btnDeletebytime.addActionListener(this);
			this.btnScreen.addActionListener(this);
			
			this.reloadPromotionTable();
		}else {
			this.reloadScreenTable();
		}
		toolBar.add(Labelname);
		toolBar.add(edtKeyword);
		toolBar.add(btnScr_Search);
		if(BeanUser.currentloginUser==null) {
			toolBar.add(btnSearch);
			this.btnSearch.addActionListener(this);
		}
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		// 屏幕居中显示
		this.setSize(1000, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnScr_Search.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource()==this.btnSearch) {
			this.reloadTable();
		}
		
		else if(e.getSource()==this.btnScr_Search) {
			this.reloadTablebytime();
		}
		
		else if(e.getSource()==this.btnDelete){
			PromotionManager pm = new PromotionManager();
			int i = this.Table.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择促销","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"确定删除促销吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					int Pro_num=this.tblData[i][0].hashCode();
					pm.DELETE(Pro_num);
					this.reloadPromotionTable();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		
		else if(e.getSource()==this.btnAdd) {
			FrmCoupon_add fca = new FrmCoupon_add(this,"促销添加界面",true);
			fca.setVisible(false);
		}
		
		else if(e.getSource()==this.btnChange) {
			int i = this.Table.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择促销","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"确定更改此促销信息吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				String pn=this.tblData[i][0].toString();
				int Pro_num=Integer.parseInt(pn);
				try {
					new FrmPro_Change(this,"修改促销信息",true,Pro_num);
					this.reloadPromotionTable();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
				
			}
			this.reloadPromotionTable();
		}
		
		else if(e.getSource()==this.btnScreen) {
			this.reloadScreenTable();
		}
		
		else if(e.getSource()==this.btnDeletebytime) {
			try {
				PromotionManager pm = new PromotionManager();
				pm.Deletebytime();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
			this.reloadPromotionTable();
		}
	}
}

