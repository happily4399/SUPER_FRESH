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

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import fresh.control.Good_buyManager;
import fresh.control.RecipeManager;
import fresh.model.BeanGood_buy;
import fresh.model.BeanRecipe;

public class FrmGoodbuy_SystemManager extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private Button btnDelete = new Button("删除记录");
	private Button btnSearch = new Button("查询");
	private Button btnChange = new Button("调整状态");
	private JComboBox cmbbuytype=null;
	String[] listData ={"入库","在途","下单"};
	
	private JTextField edtKeyword = new JTextField(10);
	
	private Object tblTitle[]={"采购表编号","商品编号","采购员编号","采购数量","采购状态"};
	private Object tblData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable GoodbuyTable=new JTable(tablmod);
	
	private void reloadTable(){
		try {
			Good_buyManager gbm = new Good_buyManager();
			List<BeanGood_buy> bgb = gbm.LoadAll();
			tblData =new Object[bgb.size()][5];
			for(int i=0;i<bgb.size();i++){
				tblData[i][0]=bgb.get(i).getBuy_num();
				tblData[i][1]=bgb.get(i).getGoods_num();
				tblData[i][2]=bgb.get(i).getAdmin_num();
				tblData[i][3]=bgb.get(i).getBuy_count();
			    tblData[i][4]=bgb.get(i).getBuy_state();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.GoodbuyTable.validate();
			this.GoodbuyTable.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void reloadSearchTable(){
		try {
			String state = this.cmbbuytype.getSelectedItem().toString();
			Good_buyManager gbm = new Good_buyManager();
			List<BeanGood_buy> bgb = gbm.LoadbyState(state);
			tblData =new Object[bgb.size()][5];
			for(int i=0;i<bgb.size();i++){
				tblData[i][0]=bgb.get(i).getBuy_num();
				tblData[i][1]=bgb.get(i).getGoods_num();
				tblData[i][2]=bgb.get(i).getAdmin_num();
				tblData[i][3]=bgb.get(i).getBuy_count();
			    tblData[i][4]=bgb.get(i).getBuy_state();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.GoodbuyTable.validate();
			this.GoodbuyTable.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	FrmGoodbuy_SystemManager(Frame f,String s,boolean b){
		super(f,s,b);
		this.cmbbuytype = new JComboBox(listData);
		toolBar.add(this.btnDelete);
		toolBar.add(cmbbuytype);
		toolBar.add(btnChange);
		toolBar.add(btnSearch);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.GoodbuyTable), BorderLayout.CENTER);
		this.setSize(1500, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		
		this.btnSearch.addActionListener(this);
		this.btnChange.addActionListener(this);
		this.btnDelete.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource()==this.btnChange) {
			int n = this.cmbbuytype.getSelectedIndex();
			int i = this.GoodbuyTable.getSelectedRow();
			String state = this.cmbbuytype.getSelectedItem().toString();
			if(n<0) {
				JOptionPane.showMessageDialog(null,  "请选择状态","提示",JOptionPane.ERROR_MESSAGE);
			}
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择采购表","提示",JOptionPane.ERROR_MESSAGE);
			}
			String gbString = this.tblData[i][0].toString();
			String good = this.tblData[i][1].toString();
			int Good_num = Integer.parseInt(good);
			int buy_num = Integer.parseInt(gbString);
			if("入库".equals(this.tblData[i][4].toString())) {
					JOptionPane.showMessageDialog(null, "商品已入库，无法更改","错误",JOptionPane.ERROR_MESSAGE);
			}else {
				try {
					Good_buyManager gbm = new Good_buyManager();
					gbm.ChangeState(buy_num, state);
					gbm.ChangeGoods_CountByState(buy_num, Good_num, state);
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
			}
			this.reloadTable();
		}
		
		else if(e.getSource()==this.btnDelete) {
			int i = this.GoodbuyTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择采购表","提示",JOptionPane.ERROR_MESSAGE);
			}
			String buy = this.tblData[i][0].toString();
			int buy_num = Integer.parseInt(buy);
			try {
				Good_buyManager gbm = new Good_buyManager();
				gbm.Delete(buy_num);
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
			this.reloadTable();
		}
		
		else if(e.getSource()==this.btnSearch) {
			this.reloadSearchTable();
		}
		
	}
}
