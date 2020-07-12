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

import fresh.control.GoodsManager;
import fresh.control.Goods_orderManager;
import fresh.control.Goods_recipeManager;
import fresh.control.RecipeManager;
import fresh.control.UserManager;
import fresh.model.BeanGoods_order;
import fresh.model.BeanGoods_recipe;
import fresh.model.BeanRecipe;
import fresh.model.BeanUser;

public class FrmRecipe_Manager extends JDialog implements ActionListener{
	
	private JPanel toolBar = new JPanel();
	private Button btnChange = new Button("�޸Ĳ�����Ϣ");
	private Button btnAdd = new Button("���Ӳ���");
	private Button btnDelete = new Button("ɾ������");
	private Button btnSearch = new Button("��ѯ");
	private Button btnGoodAdd = new Button("�����Ƽ���Ϣ");
	private Button btnGoodChange = new Button("�޸��Ƽ���Ϣ");
	
	private JTextField edtKeyword = new JTextField(10);
	private JLabel Labelname = new JLabel("�����ַ���ѯ��");
	private JLabel kongbai = new JLabel("                                                               ");
	
	private Object tblTitle[]={"���ױ��","��������","��������","����","ͼƬ"};
	private Object tblData[][];
	private Object tblGoodTitle[]={"�Ƽ���������","�Ƽ���Ʒ����","����"};
	private Object tblGoodData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable RecipeTable=new JTable(tablmod);
	DefaultTableModel Goodtablmod=new DefaultTableModel();
	private JTable GoodTable=new JTable(Goodtablmod);
	
	private void reloadGoodTable() throws Exception {
		GoodsManager gm = new GoodsManager();
		RecipeManager rm = new RecipeManager();
		Goods_recipeManager grm = new Goods_recipeManager();
		List<BeanGoods_recipe> Good = grm.loadAll();
		tblGoodData = new Object[Good.size()][3];
		for(int i=0;i<Good.size();i++) {
			tblGoodData[i][0]=rm.LoadbyNum(Good.get(i).getRecipe_num()).getRecipe_name();
			tblGoodData[i][1]=gm.loadbyGoodsnum(Good.get(i).getGoods_num()).getGoods_name();
			tblGoodData[i][2]=Good.get(i).getRecipe_des();
		}
		Goodtablmod.setDataVector(tblGoodData,tblGoodTitle);
		this.GoodTable.validate();
		this.GoodTable.repaint();
	}
	
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
	
	public FrmRecipe_Manager(Frame f, String s, boolean b) throws Exception {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		if(BeanUser.currentloginUser==null) {
			toolBar.add(btnAdd);
			toolBar.add(btnChange);
			toolBar.add(btnDelete);
		}
		toolBar.add(Labelname);
		toolBar.add(edtKeyword);
		toolBar.add(btnSearch);
		if(BeanUser.currentloginUser==null) {
			toolBar.add(kongbai);
			toolBar.add(this.btnGoodAdd);
			toolBar.add(this.btnGoodChange);
		}
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		//��ȡ��������
		this.reloadRecipeTable();
		this.getContentPane().add(new JScrollPane(this.RecipeTable), BorderLayout.CENTER);
		this.reloadGoodTable();
		this.getContentPane().add(new JScrollPane(this.GoodTable), BorderLayout.EAST);
		// ��Ļ������ʾ
		this.setSize(1500, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnGoodAdd.addActionListener(this);
		this.btnGoodChange.addActionListener(this);
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
				JOptionPane.showMessageDialog(null,  "��ѡ�����","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"ȷ��ɾ��������","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
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
				JOptionPane.showMessageDialog(null,  "��ѡ�����","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"ȷ�����Ĵ˲�����Ϣ��","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				String rn=this.tblData[i][0].toString();
				int Recipe_num = Integer.parseInt(rn);
				try {
					FrmRecipe_Change fcp = new FrmRecipe_Change(this,"�޸Ĳ�����Ϣ",true, Recipe_num);
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
		
		else if(e.getSource()==this.btnGoodAdd) {
			new FrmGood_recipe_add(this,"����Ƽ���Ϣ",true);
			try {
				this.reloadGoodTable();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		else if(e.getSource()==this.btnGoodChange) {
			int i = this.GoodTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ���Ƽ���Ϣ","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"ȷ�����Ĵ��Ƽ���Ϣ��","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				String rn=this.tblGoodData[i][0].toString();
				String gn=this.tblGoodData[i][1].toString();
				try {
					RecipeManager rm = new RecipeManager();
					int Recipe_num =rm.Loadbyoncename(rn).getRecipe_num();
					GoodsManager gm = new GoodsManager();
					int Goods_num = gm.loadbyGoodsname(gn).getGoods_num();
					new FrmGood_recipe_Change(this,"�޸��Ƽ���Ϣ",true,Goods_num,Recipe_num);
					this.reloadGoodTable();
					this.reloadRecipeTable();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
				
			}
			this.reloadRecipeTable();
		}
	}
}
