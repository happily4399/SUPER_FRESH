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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import fresh.control.DiscountManager;
import fresh.control.GoodsManager;
import fresh.control.Goods_discountManager;
import fresh.control.PromotionManager;
import fresh.model.BeanDiscount_infor;
import fresh.model.BeanGoods_discount;
import fresh.model.BeanPromotion;
import fresh.model.BeanUser;

public class FrmDiscountManager extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private Button btnChange = new Button("修改满折信息");
	private Button btnAdd = new Button("增加满折");
	private Button btnDelete = new Button("删除满折");
	private Button btnDeletebytime = new Button("删除过期满折");
	private Button btnSearch = new Button("查询(all)");
	private Button btnScr_Search = new Button("查询(可用)");
	private Button btnScreen = new Button("筛选当期满折");
	
	private JTextField edtKeyword = new JTextField(10);
	private JLabel Labelname = new JLabel("根据满折名查询：");
	
	private Object tblTitle[]={"满折编号","满折名称","满折数量","满折折扣","满折时间","过期时间"};
	private Object Good_distblTitle[]={"商品名称","可使用时间","过期时间"};
	private Object tblData[][];
	private Object Good_distblData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	DefaultTableModel Good_distablmod=new DefaultTableModel();
	private JTable Table=new JTable(tablmod);
	private JTable Good_disTable = new JTable(Good_distablmod);
	
	private void reloadPromotionTable(){
		try {
			DiscountManager dm = new DiscountManager();
			List<BeanDiscount_infor> discount = dm.loadAll();
			tblData =new Object[discount.size()][6];
			for(int i=0;i<discount.size();i++){
				tblData[i][0]=discount.get(i).getDis_num();
				tblData[i][1]=discount.get(i).getDis_content();
				tblData[i][2]=discount.get(i).getDis_count();
				tblData[i][3]=discount.get(i).getDicount();
				tblData[i][4]=discount.get(i).getDis_start_date();
				tblData[i][5]=discount.get(i).getDis_end_date();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.Table.validate();
			this.Table.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void reloadScreenTable(){
		try {
			DiscountManager dm = new DiscountManager();
			List<BeanDiscount_infor> discount = dm.loadbytime();
			tblData =new Object[discount.size()][6];
			for(int i=0;i<discount.size();i++){
				tblData[i][0]=discount.get(i).getDis_num();
				tblData[i][1]=discount.get(i).getDis_content();
				tblData[i][2]=discount.get(i).getDis_count();
				tblData[i][3]=discount.get(i).getDicount();
				tblData[i][4]=discount.get(i).getDis_start_date();
				tblData[i][5]=discount.get(i).getDis_end_date();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.Table.validate();
			this.Table.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void reloadTable(){
		try {
			DiscountManager dm = new DiscountManager();
			List<BeanDiscount_infor> discount = dm.loadbyname(this.edtKeyword.getText());
			tblData =new Object[discount.size()][6];
			for(int i=0;i<discount.size();i++){
				tblData[i][0]=discount.get(i).getDis_num();
				tblData[i][1]=discount.get(i).getDis_content();
				tblData[i][2]=discount.get(i).getDis_count();
				tblData[i][3]=discount.get(i).getDicount();
				tblData[i][4]=discount.get(i).getDis_start_date();
				tblData[i][5]=discount.get(i).getDis_end_date();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.Table.validate();
			this.Table.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void reloadTablebytime(){
		try {
			DiscountManager dm = new DiscountManager();
			List<BeanDiscount_infor> discount = dm.loadbynametime(this.edtKeyword.getText());
			tblData =new Object[discount.size()][6];
			for(int i=0;i<discount.size();i++){
				tblData[i][0]=discount.get(i).getDis_num();
				tblData[i][1]=discount.get(i).getDis_content();
				tblData[i][2]=discount.get(i).getDis_count();
				tblData[i][3]=discount.get(i).getDicount();
				tblData[i][4]=discount.get(i).getDis_start_date();
				tblData[i][5]=discount.get(i).getDis_end_date();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.Table.validate();
			this.Table.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
		}
	}
	
    private void reloadGood_Dis(int ig) {
		try {
			String Dn=this.tblData[ig][0].toString();
			int Dis_num = Integer.parseInt(Dn);
			GoodsManager gm = new GoodsManager();
			Goods_discountManager gdm = new Goods_discountManager();
			List<BeanGoods_discount> bd = gdm.LoadByDis_num(Dis_num);
			this.Good_distblData = new Object[bd.size()][3];
			for(int i = 0; i<bd.size();i++) {
				Good_distblData[i][0]=gm.loadbyGoodsnum(bd.get(i).getGoods_num()).getGoods_name();
				Good_distblData[i][1]=bd.get(i).getStart_Date();
			    Good_distblData[i][2]=bd.get(i).getEnd_Date();
			}
			this.Good_distablmod.setDataVector(this.Good_distblData,this.Good_distblTitle);
			this.Good_disTable.validate();
			this.Good_disTable.repaint();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	FrmDiscountManager(Frame f,String s,boolean b){
		super(f,s,b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.getContentPane().add(new JScrollPane(this.Table), BorderLayout.CENTER);
		
		this.Table.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmDiscountManager.this.Table.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmDiscountManager.this.reloadGood_Dis(i);
			}
	    	
	    });
		
		this.getContentPane().add(new JScrollPane(this.Good_disTable), BorderLayout.EAST);
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
		this.setSize(1400, 600);
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
			DiscountManager dm = new DiscountManager();
			int i = this.Table.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择满折","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"确定删除满折吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					int Dis_num=this.tblData[i][0].hashCode();
					dm.Delete(Dis_num);
					this.reloadPromotionTable();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		
		else if(e.getSource()==this.btnAdd) {
			try {
				FrmDis_add fda = new FrmDis_add(this,"添加满折",true);
				fda.setVisible(false);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
			this.reloadPromotionTable();
		}
		
		else if(e.getSource()==this.btnChange) {
			int i = this.Table.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择满折","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"确定更改此满折信息吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				String Dn=this.tblData[i][0].toString();
				int Dis_num=Integer.parseInt(Dn);
				try {
					new FrmDis_Change(this,"修改满折信息",true,Dis_num);
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
				DiscountManager dm = new DiscountManager();
				dm.Deletebytime();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
			this.reloadPromotionTable();
		}
	}
}
