package fresh.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
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
	private Button btnChange = new Button("调整状态为在途");
	private Button btnAdd = new Button("调整状态为入库");
	private Button btnDelete = new Button("删除记录");
	
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
			e.printStackTrace();
		}
	}
	
	FrmGoodbuy_SystemManager(Frame f,String s,boolean b){
		super(f,s,b);
		toolBar.add(btnAdd);
		toolBar.add(btnChange);
		toolBar.add(this.btnDelete);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.GoodbuyTable), BorderLayout.CENTER);
		this.setSize(1500, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		
		this.btnAdd.addActionListener(this);
		this.btnDelete.addActionListener(this);
		this.btnChange.addActionListener(this);
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
		
	}
}
