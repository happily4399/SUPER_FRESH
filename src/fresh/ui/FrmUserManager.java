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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import fresh.control.Goods_orderManager;
import fresh.control.UserManager;
import fresh.model.BeanGoods_order;
import fresh.model.BeanUser;

public class FrmUserManager extends JDialog implements ActionListener{
	
	private JPanel toolBar = new JPanel();
	private Button btnChange = new Button("�޸��û���Ϣ");
	private Button btnAdd = new Button("�����û�");
	private Button btnDelete = new Button("ɾ���û�");
	private Button btnSearch = new Button("��ѯ");
	
	private JTextField edtKeyword = new JTextField(10);
	private JLabel Labelname = new JLabel("�����û�����ѯ��");
	
	private Object tblGoods_orderTitle[]= {"�������","��ַ���","�Ż�ȯ���","ԭʼ���","������","Ҫ���ʹ�ʱ��","����״̬"};
	private Object tblTitle[]={"�û����","����","�Ա�","����","�ֻ���","����","����","ע������","�û�״̬","�Ƿ��Ա","��Ա����ʱ��"};
	private Object tblData[][];
	private Object tblGoods_orderData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable userTable=new JTable(tablmod);
	DefaultTableModel tablmod_goods=new DefaultTableModel();
	private JTable Goods_OrdersTable=new JTable(tablmod_goods);
	private void reloadUserTable(){
		try {
			UserManager um = new UserManager();
			List<BeanUser> users= um.loadall();
			tblData =new Object[users.size()][11];
			for(int i=0;i<users.size();i++){
				tblData[i][0]=users.get(i).getUser_num();
				tblData[i][1]=users.get(i).getUser_name();
				tblData[i][2]=users.get(i).getUser_sex();
				tblData[i][3]=users.get(i).getUser_pwd();
				tblData[i][4]=users.get(i).getUser_Pnum();
				tblData[i][5]=users.get(i).getUser_email();
				tblData[i][6]=users.get(i).getUser_city();
				tblData[i][7]=users.get(i).getUser_reg_date();
				tblData[i][8]=users.get(i).getUser_state();
				tblData[i][9]=users.get(i).getUser_vip();
				tblData[i][10]=users.get(i).getVip_ddl();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.userTable.validate();
			this.userTable.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void reloadGoods_OrderTable(int User_cow){
		try {
			UserManager um = new UserManager();
			List<BeanUser> users= um.loadall();
			int User_num=users.get(User_cow).getUser_num();
			Goods_orderManager gom = new Goods_orderManager();
			List<BeanGoods_order> bgo= gom.LoadbyUser_num(User_num);
			tblGoods_orderData =new Object[bgo.size()][8];
			for(int i=0;i<bgo.size();i++){
				tblGoods_orderData[i][0]=bgo.get(i).getOrder_num();
				tblGoods_orderData[i][1]=bgo.get(i).getShip_num();
				tblGoods_orderData[i][2]=bgo.get(i).getCoupon_num();
				tblGoods_orderData[i][3]=bgo.get(i).getOri_price();
				tblGoods_orderData[i][4]=bgo.get(i).getFin_price();
				tblGoods_orderData[i][5]=bgo.get(i).getService_time();
				tblGoods_orderData[i][6]=bgo.get(i).getOrder_state();
			}
			tablmod_goods.setDataVector(tblGoods_orderData,tblGoods_orderTitle);
			this.Goods_OrdersTable.validate();
			this.Goods_OrdersTable.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void reloadTable(){
		try {
			List<BeanUser> users = new ArrayList<BeanUser>();
			users=(new UserManager()).loadbyUser_name(this.edtKeyword.getText());
			tblData =new Object[users.size()][11];
			for(int i=0;i<users.size();i++){
				tblData[i][0]=users.get(i).getUser_num();
				tblData[i][1]=users.get(i).getUser_name();
				tblData[i][2]=users.get(i).getUser_sex();
				tblData[i][3]=users.get(i).getUser_pwd();
				tblData[i][4]=users.get(i).getUser_Pnum();
				tblData[i][5]=users.get(i).getUser_email();
				tblData[i][6]=users.get(i).getUser_city();
				tblData[i][7]=users.get(i).getUser_reg_date();
				tblData[i][8]=users.get(i).getUser_state();
				tblData[i][9]=users.get(i).getUser_vip();
				tblData[i][10]=users.get(i).getVip_ddl();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.userTable.validate();
			this.userTable.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FrmUserManager(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnChange);
		toolBar.add(btnDelete);
		toolBar.add(Labelname);
		toolBar.add(edtKeyword);
		toolBar.add(btnSearch);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		//��ȡ��������
		this.reloadUserTable();
		this.getContentPane().add(new JScrollPane(this.userTable), BorderLayout.CENTER);
		// ��Ļ������ʾ
		this.setSize(1500, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		
		this.btnAdd.addActionListener(this);
		this.btnDelete.addActionListener(this);
		this.btnChange.addActionListener(this);
		this.btnSearch.addActionListener(this);
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
			FrmUserRegister fug = new FrmUserRegister(this,"����û�",true);
			this.reloadUserTable();
			fug.setVisible(false);
		}
		
		else if(e.getSource()==this.btnDelete){
			UserManager um = new UserManager();
			int i = this.userTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ���˺�","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"ȷ��ɾ���˺���","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				String userPnum=this.tblData[i][4].toString();
				try {
					um.Delete(userPnum);
					this.reloadUserTable();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		
		else if(e.getSource()==this.btnChange) {
			int i = this.userTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ���˺�","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"ȷ�����Ĵ��˺���Ϣ��","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				String userPnum=this.tblData[i][4].toString();
				try {
					FrmUser_Change fcp = new FrmUser_Change(this,"�޸��û���Ϣ",true, userPnum);
					this.reloadUserTable();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
				
			}
			this.reloadUserTable();
		}
		else if(e.getSource()==this.btnSearch) {
			this.reloadTable();
		}
	}
}
