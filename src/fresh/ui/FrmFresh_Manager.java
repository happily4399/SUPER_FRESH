package fresh.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import fresh.control.CouponManager;
import fresh.control.Fresh_categoryManager;
import fresh.control.GoodsManager;
import fresh.model.BeanCoupon;
import fresh.model.BeanFresh_category;
import fresh.model.BeanGoods;

public class FrmFresh_Manager extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JMenuBar menubar=new JMenuBar();
	
	private JMenu menu_Fresh=new JMenu("类别管理");
	private JMenuItem JMFresh_Change = new JMenuItem("修改类别信息");
	private JMenuItem JMFresh_Add = new JMenuItem("增加类别");
	private JMenuItem JMFresh_Delete = new JMenuItem("删除类别");
	
	private JMenu menu_Goods=new JMenu("商品管理");
	private JMenuItem JMGoods_Add = new JMenuItem("增加商品");
	private JMenuItem JMGoods_Change = new JMenuItem("修改商品信息");
	private JMenuItem JMGoods_Delete = new JMenuItem("删除商品");
	
	private Button btnFreshSearch = new Button("查询");
	private Button btnFresh_GoodsSearch = new Button("类别中的商品查询");
	private Button btnGoods_Search = new Button("单品商品查询");
	private JTextField edtFreshKeyword = new JTextField(10);
	private JTextField edtGoodsKeyword = new JTextField(10);
	private JLabel LabelFreshname = new JLabel("根据类别名查询：");
	private JLabel LabelGoodsname = new JLabel("根据商品名查询：");
	private Object tblTitle[]={"类别编号","类别名称","类别描述","此类商品数量"};
	private Object tblData[][];
	private Object tblGoodsTitle[]={"编号","名称","价格","vip价格","库存","规格","描述","销量"};
	private Object tblGoodsData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable FreshTable=new JTable(tablmod);
	DefaultTableModel Goods_tablmod=new DefaultTableModel();
	private JTable GoodsTable=new JTable(Goods_tablmod);
	private void reloadFreshTable(){
		try {
			Fresh_categoryManager fcm = new Fresh_categoryManager();
			List<BeanFresh_category> Fresh= fcm.loadall();
			GoodsManager gm = new GoodsManager();
			tblData =new Object[Fresh.size()][4];
			for(int i=0;i<Fresh.size();i++){
				tblData[i][0]=Fresh.get(i).getCategory_number();
				tblData[i][1]=Fresh.get(i).getCategory_name();
				tblData[i][2]=Fresh.get(i).getCategory_des();
				tblData[i][3]=gm.LoadBycate(Fresh.get(i).getCategory_number()).size();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.FreshTable.validate();
			this.FreshTable.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void reloadSearchFreshTable(){
		try {
			Fresh_categoryManager fcm = new Fresh_categoryManager();
			List<BeanFresh_category> Fresh= fcm.loadbyname(this.edtFreshKeyword.getText());
			GoodsManager gm = new GoodsManager();
			tblData =new Object[Fresh.size()][4];
			for(int i=0;i<Fresh.size();i++){
				tblData[i][0]=Fresh.get(i).getCategory_number();
				tblData[i][1]=Fresh.get(i).getCategory_name();
				tblData[i][2]=Fresh.get(i).getCategory_des();
				tblData[i][3]=gm.LoadBycate(Fresh.get(i).getCategory_number()).size();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.FreshTable.validate();
			this.FreshTable.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	private void reloadGoodsTable(int ig){
		try {
			Fresh_categoryManager fcm = new Fresh_categoryManager();
			List<BeanFresh_category> Fresh= fcm.loadall();
			int Fresh_num=Fresh.get(ig).getCategory_number();
			GoodsManager gm = new GoodsManager();
			List<BeanGoods> goods= gm.LoadBycate(Fresh_num);
			tblGoodsData =new Object[goods.size()][8];
			for(int i=0;i<goods.size();i++){
				tblGoodsData[i][0]=goods.get(i).getGoods_num();
				tblGoodsData[i][1]=goods.get(i).getGoods_name();
				tblGoodsData[i][2]=goods.get(i).getGoods_price();
				tblGoodsData[i][3]=goods.get(i).getVip_price();
				tblGoodsData[i][4]=goods.get(i).getGoods_count();
				tblGoodsData[i][5]=goods.get(i).getGoods_spe();
				tblGoodsData[i][6]=goods.get(i).getGoods_det();
				tblGoodsData[i][7]=goods.get(i).getSales_count();
			}
			Goods_tablmod.setDataVector(tblGoodsData,tblGoodsTitle);
			this.GoodsTable.validate();
			this.GoodsTable.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void reloadSearchGoodsTable(int ig,String Goods_name){
		try {
			Fresh_categoryManager fcm = new Fresh_categoryManager();
			List<BeanFresh_category> Fresh= fcm.loadall();
			int Fresh_num=Fresh.get(ig).getCategory_number();
			GoodsManager gm = new GoodsManager();
			List<BeanGoods> goods= gm.loadbyGoods_nameFresh(Fresh_num, Goods_name);
			tblGoodsData =new Object[goods.size()][8];
			for(int i=0;i<goods.size();i++){
				tblGoodsData[i][0]=goods.get(i).getGoods_num();
				tblGoodsData[i][1]=goods.get(i).getGoods_name();
				tblGoodsData[i][2]=goods.get(i).getGoods_price();
				tblGoodsData[i][3]=goods.get(i).getVip_price();
				tblGoodsData[i][4]=goods.get(i).getGoods_count();
				tblGoodsData[i][5]=goods.get(i).getGoods_spe();
				tblGoodsData[i][6]=goods.get(i).getGoods_det();
				tblGoodsData[i][7]=goods.get(i).getSales_count();
			}
			Goods_tablmod.setDataVector(tblGoodsData,tblGoodsTitle);
			this.GoodsTable.validate();
			this.GoodsTable.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FrmFresh_Manager(Frame f, String s, boolean b) {
		super(f, s, b);
		
		menubar.add(menu_Fresh);
		menu_Fresh.add(JMFresh_Change);
		menu_Fresh.add(JMFresh_Add);
		menu_Fresh.add(JMFresh_Delete);
		
		menubar.add(menu_Goods);
		menu_Goods.add(JMGoods_Change);
		menu_Goods.add(JMGoods_Add);
		menu_Goods.add(JMGoods_Delete);
		
		this.getContentPane().add(menubar, BorderLayout.NORTH);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(LabelFreshname);
		toolBar.add(edtFreshKeyword);
		toolBar.add(btnFreshSearch);
		toolBar.add(LabelGoodsname);
		toolBar.add(edtGoodsKeyword);
		toolBar.add(btnFresh_GoodsSearch);
		toolBar.add(btnGoods_Search);
		
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		//提取现有数据
		this.reloadSearchFreshTable();
		this.getContentPane().add(new JScrollPane(this.FreshTable), BorderLayout.WEST);
		this.FreshTable.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmFresh_Manager.this.FreshTable.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmFresh_Manager.this.reloadGoodsTable(i);
			}
	    	
	    });
		
		this.getContentPane().add(new JScrollPane(this.GoodsTable), BorderLayout.CENTER);
		// 屏幕居中显示
		this.setSize(1000, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		
		this.JMFresh_Add.addActionListener(this);
		this.JMFresh_Delete.addActionListener(this);
		this.JMFresh_Change.addActionListener(this);
		this.btnFreshSearch.addActionListener(this);
		this.btnFresh_GoodsSearch.addActionListener(this);
		this.btnGoods_Search.addActionListener(this);
		this.JMGoods_Add.addActionListener(this);
		this.JMGoods_Delete.addActionListener(this);
		this.JMGoods_Change.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnFreshSearch) {
			this.reloadSearchFreshTable();
		}
		
		else if(e.getSource()==this.JMFresh_Delete){
			Fresh_categoryManager fcm = new Fresh_categoryManager();
			int i = this.FreshTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择类别","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"确定删除类别吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					int Fresh_num=this.tblData[i][0].hashCode();
					fcm.deletebynum(Fresh_num);
					this.reloadSearchFreshTable();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		
		else if(e.getSource()==this.JMFresh_Add) {
			FrmFresh_add fa = new FrmFresh_add(this,"类别添加界面",true);
			fa.setVisible(false);
			this.reloadSearchFreshTable();
		}
		
		else if(e.getSource()==this.JMFresh_Change) {
			int i = this.FreshTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择类别","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"确定更改此类别信息吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				String Cn=this.tblData[i][0].toString();
				int Fresh_num=Integer.parseInt(Cn);
				try {
					FrmFresh_Change fcc = new FrmFresh_Change(this,"修改类别信息",true,Fresh_num);
					this.reloadSearchFreshTable();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
				
			}
			this.reloadSearchFreshTable();
		}
		
		else if(e.getSource()==this.btnFreshSearch) {
			this.reloadSearchFreshTable();
		}
		
		else if(e.getSource()==this.JMGoods_Delete){
			GoodsManager gm = new GoodsManager();
			int i = this.FreshTable.getSelectedRow();
			int j = this.GoodsTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择商品","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"确定删除商品吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					int Goods_num=this.tblGoodsData[j][0].hashCode();
					gm.DELETE(Goods_num);
					this.reloadSearchFreshTable();
					this.reloadGoodsTable(i);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		
		else if(e.getSource()==this.JMGoods_Add) {
			int i = this.FreshTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择类别","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				FrmGoods_add fgc = new FrmGoods_add(this,"商品添加界面",true,i);
				fgc.setVisible(false);
				this.reloadGoodsTable(i);
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			this.reloadSearchFreshTable();
		}
		
		else if(e.getSource()==this.JMGoods_Change) {
			int i = this.FreshTable.getSelectedRow();
			int j = this.GoodsTable.getSelectedRow();
			if(j<0) {
				JOptionPane.showMessageDialog(null,  "请选择商品","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"确定更改此商品信息吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				String Cn=this.tblGoodsData[j][0].toString();
				int Goods_num=Integer.parseInt(Cn);
				try {
					FrmGoods_Change fgc = new FrmGoods_Change(this,"修改商品信息",true,Goods_num);
					this.reloadGoodsTable(i);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
				
			}
			this.reloadGoodsTable(i);
		}
	}
}
