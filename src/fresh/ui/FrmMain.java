package fresh.ui;
import javax.swing.*;

import fresh.control.AdminManager;
import fresh.model.BeanAdmin;
import fresh.model.BeanUser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class FrmMain extends JFrame implements ActionListener{
	private JMenuBar menubar=new JMenuBar(); ;
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
	public FrmMain() {
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
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
		}
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
			AdminManager am = new AdminManager();
			am.cancell(BeanAdmin.currentloginAamin.getAdmin_num());
			System.exit(0);
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
	}
}
