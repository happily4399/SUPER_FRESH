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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import fresh.control.CouponManager;
import fresh.control.ShippingManager;
import fresh.model.BeanCoupon;
import fresh.model.BeanShipping;
import fresh.model.BeanUser;

public class FrmUser_shipManager extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("增加地址信息");
	private Button btnChange = new Button("修改地址信息");
	private Button btnDelete = new Button("删除地址");
	
	private Object tblTitle[]={"用户编号","地址编号","省","市","区","地址","联系人","电话"};
	private Object tblData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable ShipTable=new JTable(tablmod);
	
	private void reloadShipTablebyUser(){
		try {
			ShippingManager sm = new ShippingManager();
			List<BeanShipping> ship = sm.loadbyUser_num(BeanUser.currentloginUser.getUser_num()); 
			tblData =new Object[ship.size()][8];
			for(int i=0;i<ship.size();i++) {
				tblData[i][0]=ship.get(i).getUser_num();
				tblData[i][1]=ship.get(i).getShip_num();
				tblData[i][2]=ship.get(i).getShip_pro();
				tblData[i][3]=ship.get(i).getShip_city();
				tblData[i][4]=ship.get(i).getShip_area();
				tblData[i][5]=ship.get(i).getShip_address();
				tblData[i][6]=ship.get(i).getShip_con();
				tblData[i][7]=ship.get(i).getShip_tele();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.ShipTable.validate();
			this.ShipTable.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void reloadShipTable(){
		try {
			ShippingManager sm = new ShippingManager();
			List<BeanShipping> ship = sm.loadAll(); 
			tblData =new Object[ship.size()][8];
			for(int i=0;i<ship.size();i++) {
				tblData[i][0]=ship.get(i).getUser_num();
				tblData[i][1]=ship.get(i).getShip_num();
				tblData[i][2]=ship.get(i).getShip_pro();
				tblData[i][3]=ship.get(i).getShip_city();
				tblData[i][4]=ship.get(i).getShip_area();
				tblData[i][5]=ship.get(i).getShip_address();
				tblData[i][6]=ship.get(i).getShip_con();
				tblData[i][7]=ship.get(i).getShip_tele();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.ShipTable.validate();
			this.ShipTable.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FrmUser_shipManager(Frame f, String s, boolean b) {
		super(f,s,b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.getContentPane().add(new JScrollPane(this.ShipTable), BorderLayout.CENTER);
		toolBar.add(btnChange);
		toolBar.add(btnDelete);
		this.btnChange.addActionListener(this);
		this.btnDelete.addActionListener(this);
		if(BeanUser.currentloginUser==null) {
			this.reloadShipTable();
		}else {
			toolBar.add(btnAdd);
			this.btnAdd.addActionListener(this);
			this.reloadShipTablebyUser();
		}
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		// 屏幕居中显示
		this.setSize(1000, 600);
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
		if(e.getSource()==this.btnAdd) {
			FrmShip_Add fusm = new FrmShip_Add(this,"地址增加",true);
			fusm.setVisible(false);
			this.reloadShipTablebyUser();
		}
		
		else if(e.getSource()==this.btnChange) {
			int i = this.ShipTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择地址","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"确定更改此地址信息吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				String sn=this.tblData[i][1].toString();
				int Ship_num=Integer.parseInt(sn);
				try {
					FrmShip_change fsc = new FrmShip_change(this,"修改地址",true,Ship_num); 
					if(BeanUser.currentloginUser==null) {
						this.reloadShipTable();
					}else {
						this.reloadShipTablebyUser();
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		
		else if(e.getSource()==this.btnDelete) {
			int i = this.ShipTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择地址信息","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"确定删除地址吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					int Ship_num=Integer.parseInt(this.tblData[i][1].toString());
					ShippingManager sm = new ShippingManager();
					sm.Delete(Ship_num);
					if(BeanUser.currentloginUser==null) {
						this.reloadShipTable();
					}else {
						this.reloadShipTablebyUser();
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
	}
}
