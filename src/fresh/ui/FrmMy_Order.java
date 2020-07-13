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

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import fresh.control.DiscountManager;
import fresh.control.Good_buyManager;
import fresh.control.GoodsManager;
import fresh.control.Goods_discountManager;
import fresh.control.Goods_orderManager;
import fresh.control.Order_detailManager;
import fresh.control.PromotionManager;
import fresh.model.BeanDiscount_infor;
import fresh.model.BeanGoods;
import fresh.model.BeanGoods_discount;
import fresh.model.BeanGoods_order;
import fresh.model.BeanOrder_detail;
import fresh.model.BeanUser;

public class FrmMy_Order extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	
	private JComboBox cmbbuytype=null;
	String[] listData ={"���ﳵ��","�µ�","���ʹ�","�˻���"};
	private Button btnChange = new Button("���Ķ���״̬");
	private Button btnCh = new Button("�޸Ķ�����Ϣ");
	private Button btnGoodCh = new Button("�޸���Ʒ��Ϣ");
	private Button btnDelete = new Button("ɾ������");
	private Button btnDeletebyGood = new Button("ɾ����Ʒ");
	private Button btneva = new Button("������Ʒ");
	private Button btntuihuo = new Button("�˻�");
	
	private Object Good_ordertblTitle[]={"�������","��ַ���","�Ż�ȯ���","ԭʼ���","������","Ҫ���ʹ�ʱ��","����״̬"};
	private Object order_tblTitle[]= {"���۱��","��Ʒ����","����","�ۿ�","���ռ۸�"};
	private Object Good_ordertblData[][];
	private Object order_tblData[][];
	private int User_num=0;
	
	DefaultTableModel order_tablmod=new DefaultTableModel();
	DefaultTableModel Good_ordertablmod=new DefaultTableModel();
	private JTable order_Table=new JTable(order_tablmod);
	private JTable Good_orderTable = new JTable(Good_ordertablmod);
	
	private void reloadGood_orderTable(){
		try {
			Goods_orderManager gom = new Goods_orderManager();
			List<BeanGoods_order> good_orders = gom.LoadbyUser_num(BeanUser.currentloginUser.getUser_num());
			Good_ordertblData =new Object[good_orders.size()][7];
			for(int i=0;i<good_orders.size();i++){
				Good_ordertblData[i][0]=good_orders.get(i).getOrder_num();
				Good_ordertblData[i][1]=good_orders.get(i).getShip_num();
				Good_ordertblData[i][2]=good_orders.get(i).getCoupon_num();
				Good_ordertblData[i][3]=good_orders.get(i).getOri_price();
				Good_ordertblData[i][4]=good_orders.get(i).getFin_price();
				Good_ordertblData[i][5]=good_orders.get(i).getService_time();
				Good_ordertblData[i][6]=good_orders.get(i).getOrder_state();
			}
			Good_ordertablmod.setDataVector(Good_ordertblData,Good_ordertblTitle);
			this.Good_orderTable.validate();
			this.Good_orderTable.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void reloadTable(int User_num){
		try {
			Goods_orderManager gom = new Goods_orderManager();
			List<BeanGoods_order> good_orders = gom.LoadbyUser_num(User_num);
			Good_ordertblData =new Object[good_orders.size()][7];
			for(int i=0;i<good_orders.size();i++){
				Good_ordertblData[i][0]=good_orders.get(i).getOrder_num();
				Good_ordertblData[i][1]=good_orders.get(i).getShip_num();
				Good_ordertblData[i][2]=good_orders.get(i).getCoupon_num();
				Good_ordertblData[i][3]=good_orders.get(i).getOri_price();
				Good_ordertblData[i][4]=good_orders.get(i).getFin_price();
				Good_ordertblData[i][5]=good_orders.get(i).getService_time();
				Good_ordertblData[i][6]=good_orders.get(i).getOrder_state();
			}
			Good_ordertablmod.setDataVector(Good_ordertblData,Good_ordertblTitle);
			this.Good_orderTable.validate();
			this.Good_orderTable.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	private void reloadOrderTable(int ig){
		try {
			String On=this.Good_ordertblData[ig][0].toString();
			int Order_num = Integer.parseInt(On);
			GoodsManager gm = new GoodsManager();
			Order_detailManager odm = new Order_detailManager();
			List<BeanOrder_detail> Orders = odm.loadbyOrder_num(Order_num);
			this.order_tblData = new Object[Orders.size()][5];
			for(int i = 0; i<Orders.size();i++) {
				order_tblData[i][0]=Orders.get(i).getDis_num();
				order_tblData[i][1]=gm.loadbyGoodsnum(Orders.get(i).getGoods_num()).getGoods_name();
			    order_tblData[i][2]=Orders.get(i).getOrder_count();
			    order_tblData[i][3]=Orders.get(i).getOrder_dis();
				order_tblData[i][4]=Orders.get(i).getOrder_price();
			}
			this.order_tablmod.setDataVector(this.order_tblData,this.order_tblTitle);
			this.order_Table.validate();
			this.order_Table.repaint();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	FrmMy_Order(Frame f,String s,boolean b){
		super(f,s,b);
		this.cmbbuytype = new JComboBox(listData);
		toolBar.add(cmbbuytype);
		toolBar.add(btnChange);
		toolBar.add(btnCh);
		toolBar.add(btnDelete);
		toolBar.add(btnDeletebyGood);
		toolBar.add(btneva);
		
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
        this.getContentPane().add(new JScrollPane(this.Good_orderTable), BorderLayout.CENTER);
		this.reloadGood_orderTable();
		this.Good_orderTable.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmMy_Order.this.Good_orderTable.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmMy_Order.this.reloadOrderTable(i);
			}
	    	
	    });
		this.getContentPane().add(new JScrollPane(this.order_Table), BorderLayout.EAST);
		this.setSize(1400, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnChange.addActionListener(this);
		this.btnCh.addActionListener(this);
		this.btnDelete.addActionListener(this);
		this.btnDeletebyGood.addActionListener(this);
		this.btneva.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
		this.setVisible(true);
	}
	
	FrmMy_Order(JDialog f,String s,boolean b,int User_num){
		super(f,s,b);
		this.User_num=User_num;
		toolBar.add(this.btntuihuo);
		this.btntuihuo.addActionListener(this);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
        this.getContentPane().add(new JScrollPane(this.Good_orderTable), BorderLayout.CENTER);
		this.reloadTable(User_num);
		this.Good_orderTable.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmMy_Order.this.Good_orderTable.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmMy_Order.this.reloadOrderTable(i);
			}
	    	
	    });
		this.getContentPane().add(new JScrollPane(this.order_Table), BorderLayout.EAST);
		this.setSize(1400, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		if(e.getSource()==this.btnChange) {
			if(e.getSource()==this.btnChange) {
				int n = this.cmbbuytype.getSelectedIndex();
				int i = this.Good_orderTable.getSelectedRow();
				String state = this.cmbbuytype.getSelectedItem().toString();
				if(n<0) {
					JOptionPane.showMessageDialog(null,  "��ѡ��״̬","��ʾ",JOptionPane.ERROR_MESSAGE);
				}
				if(i<0) {
					JOptionPane.showMessageDialog(null,  "��ѡ�񶩵���","��ʾ",JOptionPane.ERROR_MESSAGE);
				}
				String on = this.Good_ordertblData[i][0].toString();
				int order_num = Integer.parseInt(on);
				GoodsManager gm = new GoodsManager();
				List<BeanGoods> bg = null;
				Order_detailManager odm = new Order_detailManager();
				Goods_orderManager gom = new Goods_orderManager();
				
				if("�˻�".equals(this.Good_ordertblData[i][6].toString())) {
						JOptionPane.showMessageDialog(null, "��Ʒ���˻����޷�����","����",JOptionPane.ERROR_MESSAGE);
				}else {
					try {
						List<BeanOrder_detail> bod = odm.loadbyOrder_num(order_num);
						if(this.cmbbuytype.getSelectedItem().toString()=="�µ�") {
							if("0".equals(Good_ordertblData[i][1].toString())) throw new Exception("�������ַ���");
							if("".equals(Good_ordertblData[i][5].toString())) throw new Exception("������Ҫ���ʹ�ʱ��");
							for(int j=0;j<bod.size();j++) {
								gm.SubGoods_count(bod.get(j).getGoods_num(), bod.get(j).getOrder_count());
							}
							gom.reload_price(order_num, gom.LoadbyOrder_num(order_num).getCoupon_num());
							gom.ChangeXiadan(order_num);
							this.reloadGood_orderTable();
						}
						
						else if(this.cmbbuytype.getSelectedItem().toString()=="���ʹ�") {
							if(!"�µ�".equals(this.Good_ordertblData[i][6].toString())) throw new Exception("δ�µ����������ʹ�");
							gom.ChangeSongda(order_num);
							this.reloadGood_orderTable();
						}
						
						else if(this.cmbbuytype.getSelectedItem().toString()=="�˻���") {
							if(!"���ʹ�".equals(this.Good_ordertblData[i][6].toString())) throw new Exception("δ�ʹ�����˻�");
							gom.Tuihuoing(order_num);
							this.reloadGood_orderTable();
						}
						else if(this.cmbbuytype.getSelectedItem().toString()=="���ﳵ��") {
							throw new Exception("�޷����Ļع��ﳵ");
						}
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
					}
				}
				this.reloadGood_orderTable();
		}
       }
		else if(e.getSource()==this.btnCh) {
			int i = this.Good_orderTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ�񶩵�","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"ȷ�����Ĵ˶�����Ϣ��","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				String pn=this.Good_ordertblData[i][0].toString();
				int order_num=Integer.parseInt(pn);
				try {
					if(!"���ﳵ��".equals(this.Good_ordertblData[i][6].toString())) throw new Exception("�޷��޸Ĵ˶�����Ϣ");
					new FrmMy_order_change(this,"�޸Ķ�����Ϣ",true,order_num);
					Goods_orderManager gom = new Goods_orderManager();
					gom.reload_price(order_num, gom.LoadbyOrder_num(order_num).getCoupon_num());
					this.reloadGood_orderTable();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
				
			}
			this.reloadGood_orderTable();
		}
		
		else if(e.getSource()==this.btnDelete){
			int i = this.Good_orderTable.getSelectedRow();
			Goods_orderManager gom = new Goods_orderManager();
			Order_detailManager odm = new Order_detailManager();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ�񶩵�","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"ȷ��ɾ��������","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					int order_num=Integer.parseInt(this.Good_ordertblData[i][0].toString());
					if(odm.loadbyOrder_num(order_num).size()!=0) {
						odm.Delete(order_num);
					}
					System.out.println(order_num);
					gom.Delete(order_num);
					this.reloadGood_orderTable();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
				
			}
			this.reloadGood_orderTable();
		}
		
		else if(e.getSource()==this.btnDeletebyGood) {
			int i = this.Good_orderTable.getSelectedRow();
			int j = this.order_Table.getSelectedRow();
			Goods_orderManager gom = new Goods_orderManager();
			Order_detailManager odm = new Order_detailManager();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ�񶩵�","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(j<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ����Ʒ","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"ȷ��ɾ����Ʒ��","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					GoodsManager gm = new GoodsManager();
					int order_num=Integer.parseInt(this.Good_ordertblData[i][0].toString());
					String Good_name = this.order_tblData[j][1].toString();
					int Good_num = gm.loadbyGoodsname(Good_name).getGoods_num();
					odm.Delete(order_num, Good_num);
					gom.reload_price(order_num, gom.LoadbyOrder_num(order_num).getCoupon_num());
					this.reloadGood_orderTable();
					this.reloadOrderTable(i);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		
		else if(e.getSource()==this.btnGoodCh) {
			int i = this.Good_orderTable.getSelectedRow();
			int j = this.order_Table.getSelectedRow();
			Goods_orderManager gom = new Goods_orderManager();
			Order_detailManager odm = new Order_detailManager();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ�񶩵�","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(j<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ����Ʒ","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"ȷ�����Ĵ���Ʒ��Ϣ��","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					GoodsManager gm = new GoodsManager();
					int order_num=Integer.parseInt(this.Good_ordertblData[i][0].toString());
					String Good_name = this.order_tblData[j][1].toString();
					int Good_num = gm.loadbyGoodsname(Good_name).getGoods_num();
					if(!"���ﳵ��".equals(this.Good_ordertblData[i][6].toString())) throw new Exception("�޷��޸Ĵ���Ʒ��Ϣ");
					new FrmMy_Change(this,"�޸���Ʒ��Ϣ",true,order_num,Good_num);
					gom.reload_price(order_num, gom.LoadbyOrder_num(order_num).getCoupon_num());
					this.reloadGood_orderTable();
					this.reloadOrderTable(i);
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
				
			}
			this.reloadGood_orderTable();
		}
		
		else if(e.getSource()==this.btneva) {
			int i = this.Good_orderTable.getSelectedRow();
			int j = this.order_Table.getSelectedRow();
			Goods_orderManager gom = new Goods_orderManager();
			Order_detailManager odm = new Order_detailManager();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ�񶩵�","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(j<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ����Ʒ","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"ȷ�����۸���Ʒ��","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					GoodsManager gm = new GoodsManager();
					int order_num=Integer.parseInt(this.Good_ordertblData[i][0].toString());
					String Good_name = this.order_tblData[j][1].toString();
					int Good_num = gm.loadbyGoodsname(Good_name).getGoods_num();
					if(!"���ʹ�".equals(this.Good_ordertblData[i][6].toString())) throw new Exception("δ�ʹ�޷�����");
					new FrmMy_eva(this,"������Ʒ",true,order_num,Good_num);
					this.reloadGood_orderTable();
					this.reloadOrderTable(i);
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
				
			}
			this.reloadGood_orderTable();
		}
		
		else if(e.getSource()==this.btntuihuo) {
			int i = this.Good_orderTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ�񶩵���","��ʾ",JOptionPane.ERROR_MESSAGE);
			}
			try {
				String on = this.Good_ordertblData[i][0].toString();
				int order_num = Integer.parseInt(on);
				GoodsManager gm = new GoodsManager();
				List<BeanGoods> bg = null;
				Order_detailManager odm = new Order_detailManager();
				Goods_orderManager gom = new Goods_orderManager();
				
				if(!"�˻���".equals(this.Good_ordertblData[i][6].toString())) {
						JOptionPane.showMessageDialog(null, "��Ʒ�����˻����޷�����","����",JOptionPane.ERROR_MESSAGE);
				}else {
					gom.ChangeTuihuo(order_num);
				}
			}catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
			}
			this.reloadTable(User_num);
		}
  }
}
