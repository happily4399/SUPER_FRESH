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

import fresh.control.CouponManager;
import fresh.control.UserManager;
import fresh.model.BeanCoupon;
import fresh.model.BeanUser;

public class FrmCoupon_Manager extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private Button btnChange = new Button("�޸��Ż�ȯ��Ϣ");
	private Button btnAdd = new Button("�����Ż�ȯ");
	private Button btnDelete = new Button("ɾ���Ż�ȯ");
	private Button btnSearch = new Button("��ѯ");
	private JTextField edtKeyword = new JTextField(10);
	private JLabel Labelname = new JLabel("�����Ż�ȯ����ѯ��");
	private Object tblTitle[]={"�Ż�ȯ���","�Ż�ȯ����","���ý��","������","��ʹ��ʱ��","����ʱ��"};
	private Object tblData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable CouponTable=new JTable(tablmod);
	private void reloadCouponTable(){
		try {
			CouponManager cm = new CouponManager();
			List<BeanCoupon> coupon= cm.loadAll();
			tblData =new Object[coupon.size()][6];
			for(int i=0;i<coupon.size();i++){
				tblData[i][0]=coupon.get(i).getCoupon_num();
				tblData[i][1]=coupon.get(i).getCoupon_con();
				tblData[i][2]=coupon.get(i).getApp_amount();
				tblData[i][3]=coupon.get(i).getDed_amount();
				tblData[i][4]=coupon.get(i).getCoupon_start_date();
				tblData[i][5]=coupon.get(i).getCoupon_end_date();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.CouponTable.validate();
			this.CouponTable.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void reloadTable(){
		try {
			CouponManager cm = new CouponManager();
			List<BeanCoupon> coupon= cm.loadByCoupon_name(this.edtKeyword.getText());
			tblData =new Object[coupon.size()][6];
			for(int i=0;i<coupon.size();i++){
				tblData[i][0]=coupon.get(i).getCoupon_num();
				tblData[i][1]=coupon.get(i).getCoupon_con();
				tblData[i][2]=coupon.get(i).getApp_amount();
				tblData[i][3]=coupon.get(i).getDed_amount();
				tblData[i][4]=coupon.get(i).getCoupon_start_date();
				tblData[i][5]=coupon.get(i).getCoupon_end_date();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.CouponTable.validate();
			this.CouponTable.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FrmCoupon_Manager(Frame f, String s, boolean b) {
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
		this.reloadCouponTable();
		this.getContentPane().add(new JScrollPane(this.CouponTable), BorderLayout.CENTER);
		
		// ��Ļ������ʾ
		this.setSize(1000, 600);
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
		if(e.getSource()==this.btnSearch) {
			this.reloadTable();
		}
		
		else if(e.getSource()==this.btnDelete){
			CouponManager cm = new CouponManager();
			int i = this.CouponTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ���Ż�ȯ","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"ȷ��ɾ���Ż�ȯ��","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					int Coupon_num=this.tblData[i][0].hashCode();
					cm.Delete(Coupon_num);
					this.reloadCouponTable();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
	}
	
}
