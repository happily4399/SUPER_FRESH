package fresh.ui;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import fresh.control.UserManager;
import fresh.model.BeanUser;

public class FrmOrderManager extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private Button btnChange = new Button("�޸��û���Ϣ");
	private Button btnAdd = new Button("�����û�");
	
	private Object tblTitle[]={"�û����","����","�ֻ���","�ܵ���","���Ż�","�ܽ��"};
	private Object tblData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable Table=new JTable(tablmod);
//	private void reloadUserTable(){
//		try {
//			UserManager um = new UserManager();
//			List<BeanUser> users= um.loadall();
//			tblData =new Object[users.size()][11];
//			for(int i=0;i<users.size();i++){
//				tblData[i][0]=users.get(i).getUser_num();
//				tblData[i][1]=users.get(i).getUser_name();
//				tblData[i][2]=users.get(i).getUser_Pnum();
//				tblData[i][3]=
//				tblData[i][4]=
//				tblData[i][5]=
//			}
//			tablmod.setDataVector(tblData,tblTitle);
//			this.Table.validate();
//			this.Table.repaint();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		
	}
	
	
}
