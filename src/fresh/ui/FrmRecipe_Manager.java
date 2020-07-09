package fresh.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import fresh.control.Goods_orderManager;
import fresh.control.RecipeManager;
import fresh.control.UserManager;
import fresh.model.BeanGoods_order;
import fresh.model.BeanRecipe;
import fresh.model.BeanUser;

public class FrmRecipe_Manager extends JDialog implements ActionListener{
	
	private JPanel toolBar = new JPanel();
	private Button btnChange = new Button("�޸Ĳ�����Ϣ");
	private Button btnAdd = new Button("���Ӳ���");
	private Button btnDelete = new Button("ɾ������");
	private Button btnSearch = new Button("��ѯ");
	
	private JTextField edtKeyword = new JTextField(10);
	private JLabel Labelname = new JLabel("�����ַ���ѯ��");
	
	private Object tblTitle[]={"���ױ��","��������","��������","����","ͼƬ"};
	private Object tblData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable RecipeTable=new JTable(tablmod);
	
	private void reloadRecipeTable(){
		try {
			RecipeManager  rm = new RecipeManager();
			List<BeanRecipe> recipes = new ArrayList<BeanRecipe>();
			recipes = rm.LoadAll();
			tblData =new Object[recipes.size()][5];
			for(int i=0;i<recipes.size();i++){
				tblData[i][0]=recipes.get(i).getRecipe_num();
				tblData[i][1]=recipes.get(i).getRecipe_name();
				tblData[i][2]=recipes.get(i).getRecipe_mater();
				tblData[i][3]=recipes.get(i).getRecipe_step();
				if(!"".equals(recipes.get(i).getRecipe_picture())) {
					ImageIcon icon = new ImageIcon("E:\\java photo\\timg.jpg");
					JLabel label = new JLabel(icon);
					tblData[i][4]=label;
				}
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.RecipeTable.validate();
			this.RecipeTable.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void reloadTable(){
		try {
			RecipeManager  rm = new RecipeManager();
			List<BeanRecipe> recipes = new ArrayList<BeanRecipe>();
			recipes = rm.Loadbyname(this.edtKeyword.getText());
			tblData =new Object[recipes.size()][5];
			for(int i=0;i<recipes.size();i++){
				tblData[i][0]=recipes.get(i).getRecipe_num();
				tblData[i][1]=recipes.get(i).getRecipe_name();
				tblData[i][2]=recipes.get(i).getRecipe_mater();
				tblData[i][3]=recipes.get(i).getRecipe_step();
				if(!"".equals(recipes.get(i).getRecipe_picture())) {
					ImageIcon icon = new ImageIcon(recipes.get(i).getRecipe_picture());
					JLabel label = new JLabel(icon);
					tblData[i][4]=label;
				}
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.RecipeTable.validate();
			this.RecipeTable.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FrmRecipe_Manager(Frame f, String s, boolean b) {
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
		this.reloadRecipeTable();
		this.getContentPane().add(new JScrollPane(this.RecipeTable), BorderLayout.CENTER);
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
			try {
				FrmRecipe_Add fra = new FrmRecipe_Add(this,"��Ӳ���",true);
				this.reloadRecipeTable();
				fra.setVisible(false);
			}catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		else if(e.getSource()==this.btnDelete){
			RecipeManager rm = new RecipeManager();
			int i = this.RecipeTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ���˺�","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"ȷ��ɾ���˺���","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				String rc=this.tblData[i][0].toString();
				int Recipe_num = Integer.parseInt(rc);
				try {
					rm.Delete(Recipe_num);
					this.reloadRecipeTable();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		
		else if(e.getSource()==this.btnChange) {
			int i = this.RecipeTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ���˺�","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"ȷ�����Ĵ��˺���Ϣ��","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				String rn=this.tblData[i][0].toString();
				int Recipe_num = Integer.parseInt(rn);
				try {
					FrmRecipe_Change fcp = new FrmRecipe_Change(this,"�޸��û���Ϣ",true, Recipe_num);
					this.reloadRecipeTable();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
				
			}
			this.reloadRecipeTable();
		}
		else if(e.getSource()==this.btnSearch) {
			this.reloadTable();
		}
	}
}
