package fresh.ui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import fresh.control.AdminManager;
import fresh.control.Fresh_categoryManager;
import fresh.control.GoodsManager;
import fresh.model.BeanAdmin;
import fresh.model.BeanFresh_category;
import fresh.model.BeanGoods;
import fresh.model.BeanUser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
public class FrmMain extends JFrame implements ActionListener{
	
	private JMenuBar menubar=new JMenuBar();
    private JMenu menu_User=new JMenu("�˻�����");
    
    private JMenuItem  menuItem_UserPasswd=new JMenuItem("�޸�����");
    private JMenuItem  menuItem_User_person=new JMenuItem("�û�������Ϣ����");
    
    private JMenu menu_Admin=new JMenu("admin�˺Ź���");
    
    private JMenuItem Admin_ChangPasswd=new JMenuItem("�޸�����");
    private JMenuItem Admin_Cancel=new JMenuItem("ע�����˺�");
    private JMenuItem Admin_Changname = new JMenuItem("�޸�����");
    
    private JMenu menu_Admin_System=new JMenu("ϵͳ����");
    
    private JMenuItem System_User=new JMenuItem("�û�����");
    private JMenuItem System_Coupon = new JMenuItem("�Ż�ȯ����");
    
	private FrmLogin dlglogin = null;
	private JPanel statusBar = new JPanel();
	private JPanel toolBar = new JPanel();
	
	private JMenu menu_Fresh=new JMenu("������");
	private JMenuItem JMFresh_Change = new JMenuItem("�޸������Ϣ");
	private JMenuItem JMFresh_Add = new JMenuItem("�������");
	private JMenuItem JMFresh_Delete = new JMenuItem("ɾ�����");
	
	private JMenu menu_Goods=new JMenu("��Ʒ����");
	private JMenuItem JMGoods_Add = new JMenuItem("������Ʒ");
	private JMenuItem JMGoods_Change = new JMenuItem("�޸���Ʒ��Ϣ");
	private JMenuItem JMGoods_Delete = new JMenuItem("ɾ����Ʒ");
	
	private Button btnFreshSearch = new Button("����ѯ");
	private Button btnGoods_Search = new Button("��Ʒ��ѯ");
	private Button btnClear = new Button("ȡ��ѡ�����");
	private JTextField edtFreshKeyword = new JTextField(10);
	private JTextField edtGoodsKeyword = new JTextField(10);
	private JLabel LabelFreshname = new JLabel("�����������ѯ��");
	private JLabel LabelGoodsname = new JLabel("������Ʒ����ѯ��");
	private Object tblTitle[]={"�����","�������","�������","������Ʒ����"};
	private Object tblData[][];
	private Object tblGoodsTitle[]={"���","����","�۸�","vip�۸�","���","���","����","����"};
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
	
	private void reloadGoodsTablebyname(){
		try {
			GoodsManager gm = new GoodsManager();
			String name = edtGoodsKeyword.getText();
			List<BeanGoods> goods= gm.loadbyGoods_name(name);
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
	
	private void reloadSearchGoodsTable(int ig){
		try {
			Fresh_categoryManager fcm = new Fresh_categoryManager();
			List<BeanFresh_category> Fresh= fcm.loadall();
			int Fresh_num=Fresh.get(ig).getCategory_number();
			GoodsManager gm = new GoodsManager();
			String Goods_name = edtGoodsKeyword.getText();
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
	
	public FrmMain() {
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		JPanel jp = new JPanel();
		this.setTitle("��������ϵͳ");
		dlglogin = new FrmLogin(this,"����������¼ϵͳ",true);
		if(!(BeanUser.currentloginUser==null)) {
			FrmUser_hello uh = new FrmUser_hello(this,"��ӭ~",true);
			menu_User.add(menuItem_UserPasswd);
			menuItem_UserPasswd.addActionListener(this);
			menu_User.add(menuItem_User_person);
			menuItem_User_person.addActionListener(this);
			
			menubar.add(menu_User);
		}
		else {
			FrmAdmin_Hello fh = new FrmAdmin_Hello(this,"��ӭ~admin",true);
			menubar.add(menu_Fresh);
			menu_Fresh.add(JMFresh_Change);
			menu_Fresh.add(JMFresh_Add);
			menu_Fresh.add(JMFresh_Delete);
			
			menubar.add(menu_Goods);
			menu_Goods.add(JMGoods_Change);
			menu_Goods.add(JMGoods_Add);
			menu_Goods.add(JMGoods_Delete);
			menu_Admin.add(Admin_ChangPasswd);
			Admin_ChangPasswd.addActionListener(this);
			menu_Admin.add(Admin_Cancel);
			Admin_Cancel.addActionListener(this);
			menu_Admin.add(Admin_Changname);
			Admin_Changname.addActionListener(this);
			
			menu_Admin_System.add(System_User);
			System_User.addActionListener(this);
			menu_Admin_System.add(System_Coupon);
			System_Coupon.addActionListener(this);
			
			menubar.add(menu_Admin_System);
			menubar.add(menu_Admin);
			this.JMFresh_Add.addActionListener(this);
			this.JMFresh_Delete.addActionListener(this);
			this.JMFresh_Change.addActionListener(this);
			this.JMGoods_Add.addActionListener(this);
			this.JMGoods_Delete.addActionListener(this);
			this.JMGoods_Change.addActionListener(this);
			
		}
		
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(LabelFreshname);
		toolBar.add(edtFreshKeyword);
		toolBar.add(btnFreshSearch);
		toolBar.add(LabelGoodsname);
		toolBar.add(edtGoodsKeyword);
		toolBar.add(btnGoods_Search);
		toolBar.add(btnClear);
		this.btnFreshSearch.addActionListener(this);
		this.btnGoods_Search.addActionListener(this);
		this.btnClear.addActionListener(this);
		
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		//��ȡ��������
		this.reloadSearchFreshTable();
		this.getContentPane().add(new JScrollPane(this.FreshTable), BorderLayout.WEST);
		this.FreshTable.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmMain.this.FreshTable.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmMain.this.reloadGoodsTable(i);
			}
	    	
	    });
		
		this.getContentPane().add(new JScrollPane(this.GoodsTable), BorderLayout.CENTER);
		// ��Ļ������ʾ
		this.setSize(1000, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		FreshTable.getSelectionModel().clearSelection();
		GoodsTable.getSelectionModel().clearSelection();
		statusBar = (JPanel) this.getContentPane();
		statusBar.setOpaque(false);
		this.setJMenuBar(menubar);
		this.addWindowListener(new WindowAdapter(){
	    	public void windowClosing(WindowEvent e){ 
	    		System.exit(0);
             }
        });
	    this.setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.menuItem_UserPasswd){
			FrmChangpwd dlg=new FrmChangpwd(this,"�����޸�",true);
			dlg.setVisible(false);
		}
		
		else if(e.getSource()==this.menuItem_User_person){
			FrmChang_person fcp=new FrmChang_person(this,"��Ϣ�޸�",true);
			fcp.setVisible(false);
		}
		
		else if(e.getSource()==this.Admin_ChangPasswd) {
			FrmAdmin_ChangePasswd facp = new FrmAdmin_ChangePasswd(this,"�����޸�",true);
			facp.setVisible(false);
		}
		
		else if(e.getSource()==this.Admin_Cancel) {
			if(JOptionPane.showConfirmDialog(this,"ȷ��ע�����˺���","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				AdminManager am = new AdminManager();
				am.cancell(BeanAdmin.currentloginAamin.getAdmin_num());
				System.exit(0);
			}
		}
		
		else if(e.getSource()==this.Admin_Changname) {
			FrmAdmin_ChangeName facn = new FrmAdmin_ChangeName(this,"�����޸�",true);
			facn.setVisible(false);
		}
		
		else if(e.getSource()==this.System_User) {
			FrmUserManager fum = new FrmUserManager(this,"�û��������",true);
			fum.setVisible(false);
		}
		
		else if(e.getSource()==this.System_Coupon) {
			FrmCoupon_Manager fcm = new FrmCoupon_Manager(this,"�Ż�ȯ�������",true);
			fcm.setVisible(false);
		}
		else if(e.getSource()==this.btnFreshSearch) {
			this.reloadSearchFreshTable();
		}
		
		else if(e.getSource()==this.JMFresh_Delete){
			Fresh_categoryManager fcm = new Fresh_categoryManager();
			int i = this.FreshTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ�����","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"ȷ��ɾ�������","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					int Fresh_num=this.tblData[i][0].hashCode();
					fcm.deletebynum(Fresh_num);
					this.reloadSearchFreshTable();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		
		else if(e.getSource()==this.JMFresh_Add) {
			FrmFresh_add fa = new FrmFresh_add(this,"�����ӽ���",true);
			fa.setVisible(false);
			this.reloadSearchFreshTable();
		}
		
		else if(e.getSource()==this.JMFresh_Change) {
			int i = this.FreshTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ�����","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"ȷ�����Ĵ������Ϣ��","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				String Cn=this.tblData[i][0].toString();
				int Fresh_num=Integer.parseInt(Cn);
				try {
					FrmFresh_Change fcc = new FrmFresh_Change(this,"�޸������Ϣ",true,Fresh_num);
					this.reloadSearchFreshTable();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
				
			}
			this.reloadSearchFreshTable();
		}
		
		else if(e.getSource()==this.btnFreshSearch) {
			this.reloadSearchFreshTable();
		}
		
		else if(e.getSource()==this.btnGoods_Search) {
			int i = this.FreshTable.getSelectedRow();
			if(i<0) {
				this.reloadGoodsTablebyname();
			}else {
				this.reloadSearchGoodsTable(i);
			}
		}
		
		else if(e.getSource()==this.JMGoods_Delete){
			GoodsManager gm = new GoodsManager();
			int i = this.FreshTable.getSelectedRow();
			int j = this.GoodsTable.getSelectedRow();
			if(j<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ����Ʒ","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"ȷ��ɾ����Ʒ��","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					int Goods_num=this.tblGoodsData[j][0].hashCode();
					gm.DELETE(Goods_num);
					this.reloadSearchFreshTable();
					if(i>=0) {
						this.reloadGoodsTable(i);
					}else {
						this.reloadGoodsTablebyname();
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		
		else if(e.getSource()==this.JMGoods_Add) {
			int i = this.FreshTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ�����","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				FrmGoods_add fgc = new FrmGoods_add(this,"��Ʒ��ӽ���",true,i);
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
				JOptionPane.showMessageDialog(null,  "��ѡ����Ʒ","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"ȷ�����Ĵ���Ʒ��Ϣ��","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				String Cn=this.tblGoodsData[j][0].toString();
				int Goods_num=Integer.parseInt(Cn);
				try {
					FrmGoods_Change fgc = new FrmGoods_Change(this,"�޸���Ʒ��Ϣ",true,Goods_num);
					if(i>=0) {
						this.reloadGoodsTable(i);
					}else {
						this.reloadGoodsTablebyname();
					}
					this.reloadFreshTable();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
				
			}
			this.reloadGoodsTable(i);
		}
		else if(e.getSource()==this.btnClear) {
			FreshTable.clearSelection();
			GoodsTable.clearSelection();
		}
	}	
}
