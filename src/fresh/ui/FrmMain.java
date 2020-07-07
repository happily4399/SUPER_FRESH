package fresh.ui;
import javax.swing.*;

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
    
	private FrmLogin dlglogin = null;
	private FrmUser_hello uh = null;
	private FrmAdmin_Hello fh = null;
	
	private JPanel statusBar = new JPanel();
	public FrmMain() {
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("��������ϵͳ");
		dlglogin = new FrmLogin(this,"����������¼ϵͳ",true);
		if(!"".equals(String.valueOf(BeanUser.currentloginUser.getUser_num()))) {
			uh = new FrmUser_hello(this,"��ӭ~",true);
			menu_User.add(menuItem_UserPasswd);
			menuItem_UserPasswd.addActionListener(this);
			menu_User.add(menuItem_User_person);
			menuItem_User_person.addActionListener(this);
			
			menubar.add(menu_User);
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
		}else if(e.getSource()==this.menuItem_User_person){
			FrmChang_person fcp=new FrmChang_person(this,"��Ϣ�޸�",true);
			fcp.setVisible(false);
		}
	}
}
