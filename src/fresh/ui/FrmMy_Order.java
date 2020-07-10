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
import fresh.control.GoodsManager;
import fresh.control.Goods_discountManager;
import fresh.control.Goods_orderManager;
import fresh.control.Order_detailManager;
import fresh.model.BeanDiscount_infor;
import fresh.model.BeanGoods_discount;
import fresh.model.BeanGoods_order;
import fresh.model.BeanOrder_detail;
import fresh.model.BeanUser;

public class FrmMy_Order extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	
	private JComboBox cmbbuytype=null;
	String[] listData ={"购物车中","下单","在途","已送达"};
	private Button btnChange = new Button("修改订单状态");
	
	private Object Good_ordertblTitle[]={"订单编号","地址编号","优惠券编号","原始金额","结算金额","要求送达时间","订单状态"};
	private Object order_tblTitle[]= {"满折编号","商品名称","数量","折扣","最终价格"};
	private Object Good_ordertblData[][];
	private Object order_tblData[][];
	
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
			JOptionPane.showMessageDialog(null, e.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
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
			    order_tblData[i][3]=Orders.get(i).getOrder_price();
				order_tblData[i][4]=Orders.get(i).getOrder_dis();
			}
			this.order_tablmod.setDataVector(this.order_tblData,this.order_tblTitle);
			this.order_Table.validate();
			this.order_Table.repaint();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	FrmMy_Order(Frame f,String s,boolean b){
		super(f,s,b);
		this.cmbbuytype = new JComboBox(listData);
		toolBar.add(cmbbuytype);
		toolBar.add(btnChange);
		
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
		
	}
	
}
