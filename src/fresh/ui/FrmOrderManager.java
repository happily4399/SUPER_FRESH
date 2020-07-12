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
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import fresh.control.Goods_orderManager;
import fresh.control.UserManager;
import fresh.model.BeanUser;

public class FrmOrderManager extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private Button btnmore = new Button("详细订单信息");
	private JTextField edtKeyword = new JTextField(10);
	private JLabel Labelname = new JLabel("根据用户名查询：");
	private Button btnSearch = new Button("查询");
	private Object tblTitle[]={"用户编号","姓名","手机号","总单数","总优惠","总金额"};
	private Object tblData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable Table=new JTable(tablmod);
	private void reloadUserTable(){
		try {
			UserManager um = new UserManager();
			List<BeanUser> users= um.loadall();
			tblData =new Object[users.size()][6];
			Goods_orderManager gom = new Goods_orderManager();
			for(int i=0;i<users.size();i++){
				int User_num = users.get(i).getUser_num();
				tblData[i][0]=users.get(i).getUser_num();
				tblData[i][1]=users.get(i).getUser_name();
				tblData[i][2]=users.get(i).getUser_Pnum();
				tblData[i][3]=gom.Loadorder_num(User_num);
				tblData[i][4]=gom.LoadPrebyUser(User_num);
				tblData[i][5]=gom.LoadMoneybyUser(User_num);
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.Table.validate();
			this.Table.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void reloadnameTable(String name){
		try {
			UserManager um = new UserManager();
			List<BeanUser> users= um.loadbyUser_name(name);
			tblData =new Object[users.size()][6];
			Goods_orderManager gom = new Goods_orderManager();
			for(int i=0;i<users.size();i++){
				int User_num = users.get(i).getUser_num();
				tblData[i][0]=users.get(i).getUser_num();
				tblData[i][1]=users.get(i).getUser_name();
				tblData[i][2]=users.get(i).getUser_Pnum();
				tblData[i][3]=gom.Loadorder_num(User_num);
				tblData[i][4]=gom.LoadPrebyUser(User_num);
				tblData[i][5]=gom.LoadMoneybyUser(User_num);
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.Table.validate();
			this.Table.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FrmOrderManager(Frame f,String s,boolean b) {
		super(f,s,b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		this.getContentPane().add(new JScrollPane(this.Table), BorderLayout.CENTER);
		this.reloadUserTable();
		toolBar.add(Labelname);
		toolBar.add(this.edtKeyword);
		toolBar.add(btnSearch);
		toolBar.add(this.btnmore);
		this.btnSearch.addActionListener(this);
		this.setSize(1500, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		
		this.btnmore.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnmore) {
			int i = this.Table.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择用户","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"确定查看此用户信息吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				String un=this.tblData[i][0].toString();
				int User_num = Integer.parseInt(un);
				try {
					new FrmMy_Order(this,"用户订单详情",true,User_num);
					this.reloadUserTable();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		else if(e.getSource()==this.btnSearch) {
			this.reloadnameTable(this.edtKeyword.getText());
		}
	}
}
